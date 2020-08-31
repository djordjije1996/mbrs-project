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
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static void setProjectOptions(ProjectOptions projectOptions) {
		ProjectOptions.projectOptions = projectOptions;
	}

	private List<TypeMapping> typeMappings = new ArrayList<TypeMapping>();
	
	//Hash map for linking generators with its options
	private Map<String, GeneratorOptions> generatorOptions = new HashMap<String, GeneratorOptions>();
	
	private static ProjectOptions projectOptions = null; 
	
	private List<StaticResource> staticResources = new ArrayList<StaticResource>();
	
	public List<StaticResource> getStaticResources() {
		return staticResources;
	}

	public void setStaticResources(List<StaticResource> staticResources) {
		this.staticResources = staticResources;
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
	
	private ProjectOptions() {		
		
	}
	
	public static ProjectOptions getProjectOptions() {
		if (projectOptions ==null) { 
			projectOptions = new ProjectOptions();	
		}	
		return projectOptions;
	}

}
