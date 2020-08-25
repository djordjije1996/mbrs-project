package model
<#list importedPackages as import>
import ${import.typePackage};
</#list>

@Entity("${tableName}")
public class ${name} {  

<#list persistentProperties as persistentProperty>
	<#if persistentProperty.id??>
	@Id
	</#if>
	${persistentProperty.visibility} ${persistentProperty.type.typePackage} ${persistentProperty.type.name};
</#list>
}
