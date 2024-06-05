package com.rdelarosa.jwt.api.controller;

import com.rdelarosa.jwt.api.exception.CreatingUserWithExistentEmailException;
import com.rdelarosa.jwt.api.exception.UserErrorResponse;
import com.rdelarosa.jwt.api.service.UserService;
import com.rdelarosa.jwt.api.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        this.userService = theUserService;
    }

    @GetMapping("/users")
    public List<UserVO> findAll() {
        return userService.findAll();
    }


    @GetMapping("/users/{userId}")
    public UserVO getUserById(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public UserVO save(@RequestBody UserVO theUser) {
        UserVO userVOResponse = null;

        userVOResponse = userService.save(theUser);

        return userVOResponse;
    }

    @PutMapping("/users")
    public UserVO update(@RequestBody UserVO theUser) {

        UserVO returnedUserVO = userService.update(theUser);
        return returnedUserVO;
    }

    @PutMapping("/users/{userId}")
    public void deactivate(@PathVariable("userId") Integer userId) {

        userService.deactivateById(userId);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(CreatingUserWithExistentEmailException exc) {
        UserErrorResponse error = new UserErrorResponse(exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
