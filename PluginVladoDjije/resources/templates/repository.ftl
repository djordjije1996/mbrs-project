package com.mbrs.repository;


import com.mbrs.model.${name};
import com.mbrs.service.${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${name}Repository extends JpaRepository<${name}, Long> {
    
}
