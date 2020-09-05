package com.mbrs.controller;

import com.mbrs.model.${name};
import com.mbrs.service.${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${name?lower_case}")
@CrossOrigin
public class ${name}Controller {

@Autowired
	protected ${name?cap_first}Service ${name?lower_case}Service;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.${name?lower_case}Service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		${name?cap_first} ${name?lower_case} = this.${name?lower_case}Service.getOne(id);
		return (${name?lower_case} == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(${name?lower_case});
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody ${name?cap_first} ${name?lower_case}) {
		${name?cap_first} ${name?lower_case}New = this.${name?lower_case}Service.save(${name?lower_case});
		
		return (${name?lower_case}New == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(${name?lower_case}New);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return (this.${name?lower_case}Service.delete(id)) ? ResponseEntity.status(200).build() : ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ${name?cap_first} ${name?lower_case}) {
		${name?cap_first} updated${name?cap_first} = this.${name?lower_case}Service.update(id, ${name?lower_case});
		
		return (updated${name?cap_first} == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(updated${name?cap_first});
	}

    
}
