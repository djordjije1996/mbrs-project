package myplugin.generator;


import myplugin.generator.options.GeneratorOptions;


public class GeneratorFactory {
	
	public static StaticFilesGenerator getStaticFilesGenerator() {
		return new StaticFilesGenerator();
	}

	public static BasicGenerator getGenerator(String name, GeneratorOptions options) {
		if(name.equals(ModelGenerator.class.getSimpleName())) {
			return new ModelGenerator(options);
		}
		else if(name.equals(ControllerGenerator.class.getSimpleName())) {
			return new ControllerGenerator(options);
		}
		else if(name.equals(RepositoryGenerator.class.getSimpleName())) {
			return new RepositoryGenerator(options);
		}
		else if(name.equals(ServiceGenerator.class.getSimpleName())) {
			return new ServiceGenerator(options);
		}
		else if(name.equals(EnumerationGenerator.class.getSimpleName())) {
			return new EnumerationGenerator(options);
		}
		else if(name.equals(PomXmlGenerator.class.getSimpleName())) {
			return new PomXmlGenerator(options);
		}
		else if(name.equals(ApplicationYmlGenerator.class.getSimpleName())) {
			return new ApplicationYmlGenerator(options);
		}
		else {
			return null;
		}
	}
}
