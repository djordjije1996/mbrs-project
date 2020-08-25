package model
<#list importedPackages as import>
import ${import.typePackage};
</#list>

@Controller
public class ${name}Controller {  


}
