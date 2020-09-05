package com.mbrs.model;

import javax.persistence.*;
import com.mbrs.enumeration.Outcome;
import java.util.Date;


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
	<#if pp.type.typePackage == "date">
	${pp.visibility} ${pp.type.typePackage?cap_first} ${pp.type.name};
	<#else>
	${pp.visibility} ${pp.type.typePackage} ${pp.type.name};
	</#if>
</#list>

	<#-- LINKED PROPERTIES  -->
	<#list linkedProperties as property>
	<#if property.upper == -1 && property.oppositeEnd.upper == -1>@ManyToMany<#elseif property.upper == -1 && property.oppositeEnd.upper == 1>@OneToMany<#elseif property.upper == 1 && property.oppositeEnd.upper == -1>@ManyToOne<#else>@OneToOne</#if><#rt>
	<#lt><#if (property.fetch)?? || (property.cascade)?? || (property.mappedBy)?? || (property.optional)??>(<#rt>
		<#if (property.cascade)??>
			<#lt>cascade = CascadeType.${property.cascade}<#rt>
		</#if>
		<#if (property.fetch)??>
			<#lt><#if (property.cascade)??>, </#if>fetch = FetchType.${property.fetch}<#rt>
		</#if>
		<#if (property.mappedBy)??>
			<#lt><#if (property.cascade)?? || (property.fetch)??>, </#if>mappedBy = "${property.mappedBy}"<#rt>
		</#if>
		<#if (property.optional)??>
			<#lt><#if (property.cascade)?? || (property.fetch)?? || (property.mappedBy)??>, </#if>optional = ${property.optional?c}<#rt>
		</#if>
		<#lt>)</#if>	
	<#if property.upper == 1>
	${property.visibility} ${property.type.typePackage} ${property.type.name};
	<#else>
	${property.visibility} Set<${property.type.typePackage}> ${property.type.name} = new HashSet<${property.type.tyePackage}>();	
	</#if>${'\n'}
	</#list>

	
<#--CONSTRUCTORS#-->
	public ${name}() {
		super();
	}
	
<#--PERSISTENT PROPERTY GETTERS-->
	<#list persistentProperties as pp>
	<#if pp.type.typePackage == "boolean">
	public ${pp.type.typePackage} is${pp.type.name?cap_first}() {
	<#elseif pp.type.typePackage == "date">
	public ${pp.type.typePackage?cap_first} get${pp.type.name?cap_first}() {
	<#else>
	public ${pp.type.typePackage} get${pp.type.name?cap_first}() {
	</#if>      
		return ${pp.type.name};
	}
<#--PERSISTENT PROPERTY SETTERS-->
	<#if pp.type.typePackage == "date">
	public void set${pp.type.name?cap_first}(${pp.type.typePackage?cap_first} ${pp.type.name}) {
	<#else>
	public void set${pp.type.name?cap_first}(${pp.type.typePackage} ${pp.type.name}) {
	</#if>
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
<#--LINKED PROPERTY SETTERS-->
	public void set${lp.type.typePackage}(<#rt>
	<#if lp.upper == -1>
		<#lt>Set<<#rt> 
	</#if>
	<#lt>${lp.type.typePackage?cap_first}<#rt>
	<#if lp.upper == -1>
		<#lt>><#rt> 
	</#if>
	<#lt> ${lp.type.name}) {
		this.${lp.type.name} = ${lp.type.name};
	}${'\n'}
	</#list>
}


