package myplugin.generator.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/** ProjectOptions: singleton class that guides code generation process
 * @ToDo: enable save to xml file and load from xml file for this class */

public class ProjectOptions {
	//List of UML 2.0 to java (or any other destination language) mappings	
	private String path;
	
	private String projectPackage;
	private String projectName;
	private String driverClassName;
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	
	private List<TypeMapping> typeMappings = new ArrayList<TypeMapping>();
	private List<StaticResource> staticResources = new ArrayList<StaticResource>();
	
	//Hash map for linking generators with its options
	private Map<String, GeneratorOptions> generatorOptions = new HashMap<String, GeneratorOptions>();
	
	public String getPath() {
		return path;
	}
	

	public String getProjectPackage() {
		return projectPackage;
	}


	public void setProjectPackage(String projectPackage) {
		this.projectPackage = projectPackage;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public void setPath(String path) {
		this.path = path;
	}
	

	public String getDriverClassName() {
		return driverClassName;
	}


	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}


	public String getDbURL() {
		return dbURL;
	}


	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}


	public String getDbUsername() {
		return dbUsername;
	}


	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}


	public String getDbPassword() {
		return dbPassword;
	}


	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}


	private static ProjectOptions projectOptions = null; 
	

	public List<TypeMapping> getTypeMappings() {
		return typeMappings;
	}

	public void setTypeMappings(List<TypeMapping> typeMappings) {
		this.typeMappings = typeMappings;
	}

	public List<StaticResource> getStaticResources() {
		return staticResources;
	}

	public void setStaticResources(List<StaticResource> staticResources) {
		this.staticResources = staticResources;
	}

	public Map<String, GeneratorOptions> getGeneratorOptions() {
		return generatorOptions;
	}

	public void setGeneratorOptions(Map<String, GeneratorOptions> generatorOptions) {
		this.generatorOptions = generatorOptions;
	}

	private ProjectOptions() {		
		
	}
	
	public static ProjectOptions getProjectOptions() {
		if (projectOptions ==null) { 
			projectOptions = new ProjectOptions();	
		}	
		return projectOptions;
	}

}
