package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEntity;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMPersistentProperty;
import myplugin.generator.options.GeneratorOptions;
import myplugin.util.ImportUtil;

/**
 * EJB generator that now generates incomplete ejb classes based on MagicDraw
 * class model
 * 
 * @ToDo: enhance resources/templates/ejbclass.ftl template and intermediate
 *        data structure (@see myplugin.generator.fmmodel) in order to generate
 *        complete ejb classes
 */

public class ServiceGenerator extends BasicGenerator {

	public ServiceGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}

	public void generate() {

		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		List<FMEntity> entities = FMModel.getInstance().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			FMEntity cl = entities.get(i);
			Writer out;
			Map<String, Object> context = new HashMap<String, Object>();
			try {
				out = getWriter(cl.getName(), cl.getTypePackage());
				if (out != null) {
					context.clear();
					context.put("tableName", cl.getTableName());
					context.put("name", cl.getName());
					context.put("visibility", cl.getVisibility());
					context.put("properties", cl.getProperties());
					context.put("persistentProperties", cl.getPersistentProperties());
					context.put("linkedProperties", cl.getLinkedProperties());
					context.put("importedPackages", ImportUtil.uniqueTypesUsed(cl.getProperties()));
					
					for (FMPersistentProperty property : cl.getPersistentProperties()) {
						if (property.getId()) {
							context.put("id", property.getType().getTypePackage());
							break;
						}
					}

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
}
