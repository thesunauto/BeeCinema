package vn.edu.poly.beecinema.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
    @PostMapping("/list-film-showing")
    public ResponseEntity listFilmShowing(){
        return ResponseEntity.ok().body("hello");
    }
}
