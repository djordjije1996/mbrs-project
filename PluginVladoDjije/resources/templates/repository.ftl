package repository;

import controller.${name}Controller;
import model.${name};
import service.${name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Repository
public interface ${name}Repository extends JpaRepository<${name}, Long> {
    
}
