package enumeration;

public enum ${enumeration.name?cap_first} {

	<#list enumeration.values as value>
	${value}<#if !value?is_last>,</#if>
	</#list>

}

