package com.mbrs.model;

import javax.persistence.*;


@Entity
@Table(name="${tableName}")
public class ${name} {  

<#-- PERSISTENT PROPERTIES  -->
<#list persistentProperties as pp>
	<#if (pp.id)??>
	@Id
	<#if (pp.generatedValue)??>@GeneratedValue(strategy = GenerationType.${pp.generatedValue})</#if>
	<#else>
	@Column
	</#if>
	${pp.visibility} ${pp.type.typePackage} ${pp.type.name};
</#list>

<#-- LINKED PROPERTIES  -->
<#list linkedProperties as lp>
	<#if lp.upper == -1 && lp.oppositeEnd.upper == -1>@ManyToMany<#elseif lp.upper == -1 && lp.oppositeEnd.upper == 1>@OneToMany<#elseif lp.upper == 1 && lp.oppositeEnd.upper == -1>@ManyToOne<#else>@OneToOne</#if><#rt>
	<#lt><#if (lp.fetch)?? || (lp.cascade)?? || (lp.mappedBy)?? || (lp.optional)?? || (lp.orphanRemoval)??>(<#rt>
		<#if (lp.cascade)??>
			<#lt>cascade = CascadeType.${lp.cascade}<#rt>
		</#if>
		<#if (lp.fetch)??>
			<#lt><#if (lp.cascade)??>, </#if>fetch = FetchType.${lp.fetch}<#rt>
		</#if>
		<#if (lp.mappedBy)??>
			<#lt><#if (lp.cascade)?? || (lp.fetch)??>, </#if>mappedBy = "${lp.mappedBy}"<#rt>
		</#if>
		<#if (lp.optional)??>
			<#lt><#if (lp.cascade)?? || (lp.fetch)?? || (lp.mappedBy)??>, </#if>optional = ${lp.optional?c}<#rt>
		</#if>
		<#if (lp.orphanRemoval)??>
			<#lt><#if (lp.cascade)?? || (lp.fetch)?? || (lp.mappedBy)?? || (lp.optional)??>, </#if>orphanRemoval = ${lp.orphanRemoval?c}<#rt>
		</#if>
		<#lt>)</#if>
	<#if lp.upper == 1 >   
	${lp.visibility} ${lp.type.typePackage} ${lp.type.name};
	<#else> 
	${lp.visibility} Set<${lp.type.typePackage}> ${lp.type.name} = new HashSet<${lp.type.typePackage}>();	
	</#if>
</#list>
	
<#--CONSTRUCTORS#-->
	public ${name}() {
		super();
	}
	
<#--PERSISTENT PROPERTY GETTERS-->
	<#list persistentProperties as pp>
	<#if pp.type.typePackage == "boolean">
	public ${pp.type.typePackage} is${pp.type.name?cap_first}() {
	<#else>
	public ${pp.type.typePackage} get${pp.type.name?cap_first}() {
	</#if>      
		return ${pp.type.name};
	}
<#--PERSISTENT PROPERTY SETTERS-->
	public void set${pp.type.name?cap_first}(${pp.type.typePackage} ${pp.type.name}) {
		this.${pp.type.name} = ${pp.type.name};
	}
	</#list>
	
<#--LINKED PROPERTY GETTERS-->
	<#list linkedProperties as lp>
	public <#rt>
	<#if lp.upper == -1>
		<#lt>Set<<#rt> 
	</#if>
	<#lt>${lp.type.name?cap_first}<#rt>
	<#if lp.upper == -1>
		<#lt>><#rt> 
	</#if>
	<#lt> get${lp.type.name?cap_first}() {
		return ${lp.type.name};
	}
<#--LINKED PROPERTY GETTERS AND SETTERS-->
	public void set${lp.type.name?cap_first}(<#rt>
	<#if lp.upper == -1>
		<#lt>Set<<#rt> 
	</#if>
	<#lt>${lp.type.name}<#rt>
	<#if lp.upper == -1>
		<#lt>><#rt> 
	</#if>
	<#lt> ${lp.type.name}) {
		this.${lp.type.name} = ${lp.type.name};
	}${'\n'}
	</#list>
}


