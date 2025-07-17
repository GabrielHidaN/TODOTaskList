package com.hidan.todo_list.user;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;


    @RequestMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){

        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){

            //status code
            //mensage

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("existing user");
        }

        var passwordHashed = BCrypt.withDefaults().hashToString(12 , userModel.getPassword()
                .toCharArray());
        userModel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModel);
        return  ResponseEntity.status(HttpStatus.OK).body(userCreated);

    }
}
