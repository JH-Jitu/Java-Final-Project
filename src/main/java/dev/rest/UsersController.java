package dev.rest;

import dev.domain.UsersEntity;
import dev.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUsersEntity(@RequestBody UsersEntity usersEntity) {
        try {
            usersService.saveUsersEntity(usersEntity);
            return new ResponseEntity<>("Users entity created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create users entity.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            UsersEntity user = usersService.findByEmail(email);
            if (user != null) {
                // You can customize the response based on the user's role
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to find user by email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
