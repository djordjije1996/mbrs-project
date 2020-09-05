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

public class PomXmlGenerator extends BasicGenerator{

	public PomXmlGenerator(GeneratorOptions generatorOptions) {
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
			if (out != null) {
				context.clear();
				//JOptionPane.showMessageDialog(null, ProjectOptions.getProjectOptions().getProjectName());
				//JOptionPane.showMessageDialog(null, ProjectOptions.getProjectOptions().getProjectPackage());
				context.put("projectName", ProjectOptions.getProjectOptions().getProjectName());
				context.put("projectPackage", ProjectOptions.getProjectOptions().getProjectPackage());
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
