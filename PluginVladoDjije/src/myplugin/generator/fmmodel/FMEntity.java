package myplugin.generator.fmmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FMEntity extends FMType {

	private FMEntity ancestor;

	private String visibility;

	private String tableName;

	

	private List<FMPersistentProperty> persistentProperties = new ArrayList<FMPersistentProperty>();
	private List<FMLinkedProperty> linkedProperties = new ArrayList<FMLinkedProperty>();

	private Set<FMType> importedPackages = new HashSet<FMType>();
	private List<FMProperty> properties = new ArrayList<FMProperty>();
	
	public List<FMProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<FMProperty> properties) {
		this.properties = properties;
	}

	public FMEntity(String name, String typePackage) {
		super(name, typePackage);
	}

	public Set<FMType> getImportedPackages() {
		return importedPackages;
	}

	public Iterator<FMType> getImportedIterator() {
		return importedPackages.iterator();
	}

	public void addImportedPackage(FMType importedPackage) {
		importedPackages.add(importedPackage);
	}

	public int getImportedCount() {
		return importedPackages.size();
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public FMEntity getAncestor() {
		return ancestor;
	}

	public void setAncestor(FMEntity ancestor) {
		this.ancestor = ancestor;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}



	public List<FMPersistentProperty> getPersistentProperties() {
		return persistentProperties;
	}

	public void setPersistentProperties(List<FMPersistentProperty> persistentProperties) {
		this.persistentProperties = persistentProperties;
	}

	public List<FMLinkedProperty> getLinkedProperties() {
		return linkedProperties;
	}

	public void setLinkedProperties(List<FMLinkedProperty> linkedProperties) {
		this.linkedProperties = linkedProperties;
	}

	public void setImportedPackages(Set<FMType> importedPackages) {
		this.importedPackages = importedPackages;
	}

	public void addLinkedProperties(FMLinkedProperty linkedProperty) {
		linkedProperties.add(linkedProperty);
	}

	public void addPersistentProperties(FMPersistentProperty persistentProperty) {
		persistentProperties.add(persistentProperty);
	}


}
