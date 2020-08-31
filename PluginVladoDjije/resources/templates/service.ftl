package service;

import model.${name};
import service.${name}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Service
public class ${name}Service {
	
	@Autowired
	protected ${name}Repository ${name?lower_case}Repository;
	
	public ${name} getOne(${id?cap_first} id) {
		Optional<${name}> ${name?lower_case} = this.${name?lower_case}Repository.findById(id);
		if(${name?lower_case}.isPresent())
			return ${name?lower_case}.get();
		return null;
	}
	
	public List<${name}> findAll() {
		return this.${name?lower_case}Repository.findAll();
	}
	
	public ${name} save(${name} ${name?lower_case}) {
		return this.${name?lower_case}Repository.save(${name?lower_case});
	}
	
	public boolean delete(${id?cap_first} id) {
		${name} ${name?lower_case} = this.getOne(id);
		if (${name?lower_case} != null) {
			this.${name?lower_case}Repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public ${name} update(${id?cap_first} id, ${name} ${name?lower_case}) {
		${name} old${name} = this.getOne(id);
		
		if(old${name} == null)
			return null;
		
		<#list persistentProperties as property>
   		old${name}.set${property.type.name?cap_first}(${name?lower_case}.get${property.type.name?cap_first}());
		</#list>

		return this.save(old${name});
	}
	


}
