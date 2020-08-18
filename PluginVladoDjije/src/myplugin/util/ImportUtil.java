package myplugin.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMType;

/*
 * Za prosleđenu listu obeležja generiše jedinstvene tipove koje treba importovati.
 */
public class ImportUtil {
	public static Collection<FMType> uniqueTypesUsed(List<FMProperty> properties){
		HashSet<FMType> uniqueTypes = new HashSet<FMType>();
		for(FMProperty property: properties) {
			FMType type = property.getType();
			
			if(!uniqueTypes.contains(type))
				uniqueTypes.add(type);
		}
		return uniqueTypes;
		
	}
}
