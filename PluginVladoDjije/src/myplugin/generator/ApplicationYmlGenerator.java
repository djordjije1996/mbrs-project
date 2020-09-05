package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;
import myplugin.util.ImportUtil;

public class ApplicationYmlGenerator extends BasicGenerator{

	public ApplicationYmlGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}
	public void generate() throws IOException {
		
		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		Writer out ;
		
		Map<String, Object> context = new HashMap<String, Object>();
		
		try {
			out = getWriter("", "");
			if(out == null) {
				JOptionPane.showMessageDialog(null, ProjectOptions.getProjectOptions().getDbURL());
				
			}
			if (out != null) {
				context.clear();
				//JOptionPane.showMessageDialog(null, ProjectOptions.getProjectOptions().getProjectName());
				//JOptionPane.showMessageDialog(null, ProjectOptions.getProjectOptions().getProjectPackage());
				context.put("driverClassName", ProjectOptions.getProjectOptions().getDriverClassName());
				context.put("dbURL", ProjectOptions.getProjectOptions().getDbURL());
				context.put("dbUsername", ProjectOptions.getProjectOptions().getDbUsername());
				context.put("dbPassword", ProjectOptions.getProjectOptions().getDbPassword());
				getTemplate().process(context, out);
				out.flush();
			}
		} catch (TemplateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
}
