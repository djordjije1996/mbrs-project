package myplugin.analyzer;

import java.util.Iterator;
import java.util.List;

import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEntity;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMPersistentProperty;
import myplugin.generator.fmmodel.FMLinkedProperty;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMType;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;


/** Model Analyzer takes necessary metadata from the MagicDraw model and puts it in 
 * the intermediate data structure (@see myplugin.generator.fmmodel.FMModel) optimized
 * for code generation using freemarker. Model Analyzer now takes metadata only for ejb code 
 * generation

 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and  
 * Model Analyzer methods in order to support GUI generation. */ 


public class ModelAnalyzer {	
	//root model package
	private Package root;
	
	//java root package for generated code
	private String filePackage;
	
	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}

	public Package getRoot() {
		return root;
	}
	
	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getEntities().clear();
		FMModel.getInstance().getEnumerations().clear();
		processPackage(root, filePackage);
	}
	
	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		//Recursive procedure that extracts data from package elements and stores it in the 
		// intermediate data structure
		
		if (pack.getName() == null) throw  
			new AnalyzeException("Packages must have names!");
		
		String packageName = packageOwner;
		if (pack != root) {
			packageName += "." + pack.getName();
		}
		
		if (pack.hasOwnedElement()) {
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Class) {
					Class cl = (Class)ownedElement;
					FMEntity fmEntity = getEntityData(cl, packageName);
					FMModel.getInstance().getEntities().add(fmEntity);
				}
				
				if (ownedElement instanceof Enumeration) {
					Enumeration en = (Enumeration)ownedElement;
					FMEnumeration fmEnumeration = getEnumerationData(en, packageName);
					FMModel.getInstance().getEnumerations().add(fmEnumeration);
				}								
			}
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Package) {					
					Package ownedPackage = (Package)ownedElement;
					if (StereotypesHelper.getAppliedStereotypeByString(ownedPackage, "BackendApp") != null)
						//only packages with stereotype BusinessApp are candidates for metadata extraction and code generation:
						processPackage(ownedPackage, packageName);
				}
			}
			
			/** @ToDo:
			  * Process other package elements, as needed */ 
		}
	}
	
	private FMEntity getEntityData(Class cl, String packageName) throws AnalyzeException {
		if (cl.getName() == null) 
			throw new AnalyzeException("Classes must have names!");
		
		FMEntity fmEntity = new FMEntity(cl.getName(), packageName);
		Stereotype stereotype = StereotypesHelper.getAppliedStereotypeByString(cl, "Entity");
		if(stereotype != null) {
			List<Property> tags = stereotype.getOwnedAttribute();
			for (Property tag : tags) {
				createProperty(tag, fmEntity, cl, stereotype);
			}
		}
		Iterator<Property> it = ModelHelper.attributes(cl);
		while (it.hasNext()) {
			Property p = it.next();
			//FMProperty prop = getPropertyData(p, cl);
			//FMProperty fmProperty = PropertyAnalyzer.createPropertyData(p);
			Boolean isPersistent = StereotypesHelper.hasStereotypeOrDerived(p, "PersistentProperty");
			
			if(isPersistent) {
				FMPersistentProperty fmpp =  PropertyAnalyzer.createPersistentPropertyData(p);
				fmEntity.getPersistentProperties().add(fmpp);
				fmEntity.getProperties().add(fmpp);
				
			}
			else {
				FMLinkedProperty fmpl = PropertyAnalyzer.createLinkedPropertyData(p);
				fmEntity.getLinkedProperties().add(fmpl);
				fmEntity.getProperties().add(fmpl);
				
			}
			
			
		}	
		
		/** @ToDo:
		 * Add import declarations etc. */		
		return fmEntity;
	}
	private static void createProperty(Property tag, FMEntity fmEntity, Class magicClass, Stereotype stereotype) {
		String tagName = tag.getName();

		
		List<?> values = StereotypesHelper.getStereotypePropertyValue(magicClass, stereotype, tagName);

		// switch za slucaj da bude potrebno jos tagova za Entity
		// malo elegantniji pristup za razliku od svega ostalog
		if(values.size() > 0) {
			switch (tagName) {
			case "tableName":
				if(values.get(0) instanceof String) {	
					String tableName = (String) values.get(0);
					fmEntity.setTableName(tableName);
				}
				break;
			}
		}
	}	

	private FMEnumeration getEnumerationData(Enumeration enumeration, String packageName) throws AnalyzeException {
		FMEnumeration fmEnum = new FMEnumeration(enumeration.getName(), packageName);
		List<EnumerationLiteral> list = enumeration.getOwnedLiteral();
		for (EnumerationLiteral literal : enumeration.getOwnedLiteral()) {
			//EnumerationLiteral literal = list.get(i);
			if (literal.getName() == null)  
				throw new AnalyzeException("Items of the enumeration " + enumeration.getName() +
				" must have names!");
			fmEnum.addValue(literal.getName());
		}
		return fmEnum;
	}	
	
	
}
