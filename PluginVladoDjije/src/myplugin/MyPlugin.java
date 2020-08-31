package myplugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.OptionsLoader;
import myplugin.generator.options.ProjectOptions;
import myplugin.generator.options.SerializableProjectOptions;
import myplugin.generator.options.StaticResource;

import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null; 
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */
		
		
		OptionsLoader optionsLoader = new OptionsLoader();
		/*
		try {			
			SerializableProjectOptions projectOptions = optionsLoader.loadProjectOptionsFromXML(pluginDir, "ProjectOptions.xml");
			ProjectOptions.getProjectOptions().setPath(projectOptions.getPath());
			ProjectOptions.getProjectOptions().setGeneratorOptions(projectOptions.getGeneratorOptions());
			ProjectOptions.getProjectOptions().setTypeMappings(projectOptions.getTypeMappings());
	//		ProjectOptions.getProjectOptions().setStaticResources(projectOptions.getStaticResources());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog( null, "Loading plugin options failed.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog( null, e.getStackTrace());
		}
		*/
		//for test purpose only:
		GeneratorOptions ejbOptions = new GeneratorOptions("C:/Users/User/Desktop/generated_app/model", "ejbclass", "templates", "{0}.java", true, "generated"); 				
		GeneratorOptions controllerOptions = new GeneratorOptions("C:/Users/User/Desktop/generated_app/controller", "controller", "templates", "{0}Controller.java", true, "generated"); 				
		StaticResource staticResource = new StaticResource("static", "Application.java", "C:/Users/User/Desktop/generated_app/run", "Application.java", false);
		
		
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ControllerGenerator", controllerOptions);
		ProjectOptions.getProjectOptions().getStaticResources().add(staticResource);
		
		controllerOptions.setTemplateDir(pluginDir + File.separator + controllerOptions.getTemplateDir());
		ejbOptions.setTemplateDir(pluginDir + File.separator + ejbOptions.getTemplateDir()); 
		//staticResource.setDestinationFolder(pluginDir + File.separator + staticResource.getDestinationFolder()); //apsolutna putanja
		staticResource.setSourceFolder(pluginDir + File.separator + staticResource.getSourceFolder());
	
		try {
			optionsLoader.saveProjectOprionsToXML(pluginDir + "/ProjectOptions.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


