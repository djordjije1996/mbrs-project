package myplugin.generator.options;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ova klasa moze da se sacuva kao xml i ucita.
 * Nije singlton, ima prazan konstruktor i get i set za sva polja.
 */
public class SerializableProjectOptions implements Serializable {
	private static final long serialVersionUID = 1L;
	private String path;
	private String projectName;
	private String projectPackage;
	private String driverClassName;
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private List<TypeMapping> typeMappings = new ArrayList<TypeMapping>();
	private Map<String, GeneratorOptions> generatorOptions = new HashMap<String, GeneratorOptions>();
	private List<StaticResource> staticResources = new ArrayList<StaticResource>();
	
	public SerializableProjectOptions(ProjectOptions projectOptions) {
		path = projectOptions.getPath();
		projectName = projectOptions.getProjectPackage();
		projectPackage = projectOptions.getProjectPackage();
		driverClassName = projectOptions.getDriverClassName();
		dbURL = projectOptions.getDbURL();
		dbPassword = projectOptions.getDbPassword();
		dbUsername = projectOptions.getDbPassword();
		typeMappings = projectOptions.getTypeMappings();
		generatorOptions = projectOptions.getGeneratorOptions();
		staticResources = projectOptions.getStaticResources();
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPackage() {
		return projectPackage;
	}

	public void setProjectPackage(String projectPackage) {
		this.projectPackage = projectPackage;
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

	public SerializableProjectOptions() {
		super();
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<TypeMapping> getTypeMappings() {
		return typeMappings;
	}

	public void setTypeMappings(List<TypeMapping> typeMappings) {
		this.typeMappings = typeMappings;
	}

	public Map<String, GeneratorOptions> getGeneratorOptions() {
		return generatorOptions;
	}

	public void setGeneratorOptions(Map<String, GeneratorOptions> generatorOptions) {
		this.generatorOptions = generatorOptions;
	}

	public List<StaticResource> getStaticResources() {
		return staticResources;
	}

	public void setStaticResources(List<StaticResource> staticResources) {
		this.staticResources = staticResources;
	}
	
}
