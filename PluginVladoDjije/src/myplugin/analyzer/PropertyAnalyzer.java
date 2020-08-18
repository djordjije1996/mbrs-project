package myplugin.analyzer;

import java.util.List;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

import myplugin.generator.fmmodel.CascadeType;
import myplugin.generator.fmmodel.FMLinkedProperty;
import myplugin.generator.fmmodel.FMPersistentProperty;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMType;
import myplugin.generator.fmmodel.FetchType;
import myplugin.generator.fmmodel.GeneratedValue;





public class PropertyAnalyzer {
	public static FMPersistentProperty createPersistentPropertyData(Property property) throws AnalyzeException {
		String propertyName = property.getName();
		if (propertyName.trim() == null) {
			throw new AnalyzeException("Properties must have names!");
		}

		int lower = property.getLower();
		int upper = property.getUpper();

		String typeName = property.getType().getName();
		String typePackage = "";

		/*TypeMapping typeMapping = getDataType(typeName);
		boolean isUml = false;
		String defaultValue = "";
		if (typeMapping != null) {
			typeName = typeMapping.getDestType();
			typePackage = typeMapping.getLibraryName();
			isUml = true;
			defaultValue = typeMapping.getDefaultValue();
		}
		*/
		FMType type = new FMType(propertyName, typeName);

		String visibility = property.getVisibility().toString();
		FMProperty fmProperty = new FMProperty(type, visibility, lower, upper);

	
		setAbstractProperties(property, fmProperty);
	

		// ucitati tagove za perzistente atribute
		FMPersistentProperty fmpp = new FMPersistentProperty(fmProperty);
		Stereotype persistentPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(property,
				"PersistentProperty");
		if (persistentPropertyStereotype != null) {	
			fmpp = setPersistantPropertyData(property, fmProperty, persistentPropertyStereotype);
			return fmpp;
		}

		
		return fmpp;
	}
	public static FMLinkedProperty createLinkedPropertyData(Property property) throws AnalyzeException {
		String propertyName = property.getName();
		if (propertyName.trim() == null) {
			throw new AnalyzeException("Properties must have names!");
		}

		int lower = property.getLower();
		int upper = property.getUpper();

		String typeName = property.getType().getName();
		String typePackage = "";

		/*TypeMapping typeMapping = getDataType(typeName);
		boolean isUml = false;
		String defaultValue = "";
		if (typeMapping != null) {
			typeName = typeMapping.getDestType();
			typePackage = typeMapping.getLibraryName();
			isUml = true;
			defaultValue = typeMapping.getDefaultValue();
		}
		*/
		FMType type = new FMType(propertyName, typeName);

		String visibility = property.getVisibility().toString();
		FMProperty fmProperty = new FMProperty(type, visibility, lower, upper);
		
		
		
		Property referencingProperty = property.getOpposite();
		int upperRef = referencingProperty.getUpper();
		int lowerRef = referencingProperty.getLower();
		String nameRef = referencingProperty.getName();

		String typeNameRef = referencingProperty.getType().getName();
	
		FMType typeRef = new FMType(nameRef, typeNameRef);
		String vis = referencingProperty.getVisibility().toString();
		FMProperty pRef = new FMProperty(typeRef, vis, lowerRef, upperRef );
		
		
		setAbstractProperties(property, fmProperty);
		setAbstractProperties(referencingProperty, pRef);
		
			
		
		

		// ucitati tagove za linked atribute
		FMLinkedProperty fmpl = new FMLinkedProperty(fmProperty);
		FMLinkedProperty fmplRef = new FMLinkedProperty(pRef);
		fmpl.setOppositeEnd(fmplRef);
		Stereotype linkedPropertyStereotype = StereotypesHelper.getAppliedStereotypeByString(property,
				"LinkedProperty");
		if (linkedPropertyStereotype != null) {
			fmpl = setLinkedPropertyData(property, fmProperty, linkedPropertyStereotype, fmpl);
			//fmpl.setMappedBy("Mapiranoooooooooooooooooooooooo");
			return fmpl;
		}
		return fmpl;
	}
	private static FMPersistentProperty setPersistantPropertyData(Property property, FMProperty fmProperty,
			Stereotype stereotype) {
		FMPersistentProperty persistantProperty = new FMPersistentProperty(fmProperty);
		List<Property> tags = stereotype.getOwnedAttribute();
		for (Property tag : tags) {
			createPersistantProperty(tag.getName(), property, fmProperty, stereotype, persistantProperty);
		}

		return persistantProperty;
	}
	private static void createPersistantProperty(String tagName, Property property, FMProperty fmProperty,
			Stereotype stereotype, FMPersistentProperty persistantProperty) {
		

		// preuzimanje vrednosti tagova
		List<?> values = StereotypesHelper.getStereotypePropertyValue(property, stereotype, tagName);

		// ako tag ima vrednosti
		if (values.size() > 0) {
			switch (tagName) {
			case "id":
				if (values.get(0) instanceof Boolean) {
					Boolean id = (Boolean) values.get(0);
					persistantProperty.setId(id);
				}
				break;
			case "unique":
				if(values.get(0) instanceof Boolean) {
					Boolean unique = (Boolean) values.get(0);
					persistantProperty.setUnique(unique);
				}
				break;
			
			case "length":
				if (values.get(0) instanceof Integer) {
					Integer length = (Integer) values.get(0);
					persistantProperty.setLength(length);
				}
				break;
			case "precision":
				if (values.get(0) instanceof Integer) {
					Integer precision = (Integer) values.get(0);
					persistantProperty.setPrecision(precision);
				}
				break;
			case "scale":
				if (values.get(0) instanceof Integer) {
					Integer scale = (Integer) values.get(0);
					persistantProperty.setScale(scale);
				}
				break;
			case "generatedValue":
				if (values.get(0) instanceof EnumerationLiteral) {
					EnumerationLiteral el = (EnumerationLiteral) values.get(0); 
					String temp = el.getName();
					persistantProperty.setGeneratedValue(GeneratedValue.valueOf(temp));
				}
				break;
			}
		}
	}

	private static FMLinkedProperty setLinkedPropertyData(Property property, FMProperty fmProperty, Stereotype stereotype, FMLinkedProperty linkedProperty) {
		
		List<Property> tags = stereotype.getOwnedAttribute();
		for (Property tag : tags) {
			createLinkedProperty(tag.getName(), property, fmProperty, stereotype, linkedProperty);
		}
		return linkedProperty;
	}
	
	private static void setAbstractProperties(Property property, FMProperty fmProperty) {
		
		
		List<?> values = StereotypesHelper.getStereotypePropertyValue(property, "AbstractProperty", "colName");
		if (values.size() > 0) {
			if (values.get(0) instanceof String) {
				String columnName = (String) values.get(0);
				fmProperty.setColName(columnName);
			}
		}
	}
	
	private static void createLinkedProperty(String tagName, Property property, FMProperty fmProperty,
			Stereotype stereotype, FMLinkedProperty linkedProperty) {

		
		
		
		// preuzimanje vrednosti taga
		List<?> values = StereotypesHelper.getStereotypePropertyValue(property, stereotype, tagName);
		List<?> values2 = StereotypesHelper.getStereotypePropertyValue(property.getOpposite(), stereotype, tagName);

		// ako tag ima vrednosti
		if (values.size() > 0) {
			switch (tagName) {			
			case "mappedBy":
				if (values.get(0) instanceof String) {
					String mappedBy = (String) values.get(0);
					linkedProperty.setMappedBy(mappedBy);
				}
				break;
			case "optional":
				if (values.get(0) instanceof Boolean) {
					Boolean optional = (Boolean) values.get(0);
					linkedProperty.setOptional(optional);
				}
				break;
			case "orphanRemoval":
				if (values.get(0) instanceof Boolean) {
					Boolean orphanRemoval = (Boolean) values.get(0);
					linkedProperty.setOrphanRemoval(orphanRemoval);
				}
				break;
			case "fetch":
				if (values.get(0) instanceof EnumerationLiteral) {
					EnumerationLiteral el = (EnumerationLiteral) values.get(0);
					String value = el.getName();
					
					linkedProperty.setFetch(FetchType.valueOf(value));
				}
				break;
			case "cascade":
				if (values.get(0) instanceof EnumerationLiteral) {
					EnumerationLiteral el = (EnumerationLiteral) values.get(0);
					String value = el.getName();
					linkedProperty.setCascade(CascadeType.valueOf(value));
				}
				break;
			}
		}
		
		//za oppositeEnd
		//jako neelegantan nacin da se procita oppositeEnd LinkedProperty - a
		if (values2.size() > 0) {
			switch (tagName) {			
			case "mappedBy":
				if (values2.get(0) instanceof String) {
					String mappedBy = (String) values2.get(0);
					linkedProperty.getOppositeEnd().setMappedBy(mappedBy);
				}
				break;
			case "optional":
				if (values2.get(0) instanceof Boolean) {
					Boolean optional = (Boolean) values2.get(0);
					linkedProperty.getOppositeEnd().setOptional(optional);
				}
				break;
			case "orphanRemoval":
				if (values2.get(0) instanceof Boolean) {
					Boolean orphanRemoval = (Boolean) values2.get(0);
					linkedProperty.getOppositeEnd().setOrphanRemoval(orphanRemoval);
				}
				break;
			case "fetch":
				if (values2.get(0) instanceof EnumerationLiteral) {
					EnumerationLiteral el = (EnumerationLiteral) values2.get(0);
					String value = el.getName();
					
					linkedProperty.getOppositeEnd().setFetch(FetchType.valueOf(value));
				}
				break;
			case "cascade":
				if (values2.get(0) instanceof EnumerationLiteral) {
					EnumerationLiteral el = (EnumerationLiteral) values2.get(0);
					String value = el.getName();
					linkedProperty.getOppositeEnd().setCascade(CascadeType.valueOf(value));
				}
				break;
			}
		}
		
		
		
	}
	

	

	

}
