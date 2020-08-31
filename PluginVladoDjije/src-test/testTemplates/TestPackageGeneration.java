package testTemplates;

import java.util.List;



import myplugin.generator.ModelGenerator;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMType;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** TestPackageGeneration: Class for package generation testing
 * @ToDo: Create another test class that loads metadata saved by MagicDraw plugin 
 * ( @see myplugin.GenerateAction#exportToXml() ) and activate code generation. 
 * This is the way to perform code generation testing without
 *  need to restart MagicDraw 
 *  */

public class TestPackageGeneration {
	
	public TestPackageGeneration(){
		
	}
	
	private void initModel() {		
		/*
		List<FMClass> classes = FMModel.getInstance().getClasses();
		
		classes.clear();
		
		FMClass cl = new FMClass ("Preduzece", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty(new FMType("sifraPreduzeca", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("nazivPreduzeca", "String"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Materijal", "ejb.magacin", "public");
		cl.addProperty(new FMProperty(new FMType("sifraMaterijala", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("nazivMaterijala", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("slozen", "Boolean"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Odeljenje", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty(new FMType("sifra", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("naziv", "String"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Osoba", "ejb", "public");
		cl.addProperty(new FMProperty(new FMType("prezime", "String"), "private", 1, 1));		
		cl.addProperty(new FMProperty(new FMType("ime", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("datumRodjenja", "Date"), "private", 0, 1));
		cl.addProperty(new FMProperty(new FMType("clanoviPorodice", "Osoba"), "private", 0, -1));	
		cl.addProperty(new FMProperty(new FMType("vestina", "String"), "private", 1, 3));
		
		classes.add(cl);
		
		cl = new FMClass ("Kartica", "ejb.magacin.kartica", "public");
		cl.addProperty(new FMProperty(new FMType("sifraKartice", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty(new FMType("nazivKartice", "String"), "private", 1, 1));
		
		classes.add(cl);
		*/		
	}
	
	
	public void testGenerator() {
		initModel();		
		GeneratorOptions go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ModelGenerator");	
		ModelGenerator g = new ModelGenerator(go);
		g.generate();
	}
	
	public static void main(String[] args) {
		TestPackageGeneration tg = new TestPackageGeneration();
		/** @Todo: load project options from xml file */
		
		//for test purpose only:
		GeneratorOptions modelOptions = new GeneratorOptions("c:/temp", "model", "./resources/templates/", "{0}.java", true, "model"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ModelGenerator", modelOptions);
				
		tg.testGenerator();
	}
	
	
	
	
}
