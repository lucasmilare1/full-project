package com.project.full.controller;

import com.project.full.domain.dto.UserDTO;
import com.project.full.exceptions.DeleteUserException;
import com.project.full.exceptions.UserException;
import com.project.full.exceptions.UserNotFoundException;
import com.project.full.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class UserController {

    @Autowired
    private UserService service;


    public DeferredResult<ResponseEntity<UserDTO>> createUser(@RequestBody UserDTO user) {
        var dr = new DeferredResult<ResponseEntity<UserDTO>>();
        try {
            dr.setResult(ResponseEntity.ok(service.createNewUser(user)));
        } catch (UserException e) {
            dr.setErrorResult(ResponseEntity.badRequest().body(e.getMessage()));
        }
        return dr;
    }

    public DeferredResult<ResponseEntity<UserDTO>> findByDocument(@RequestParam String document) {
        var dr = new DeferredResult<ResponseEntity<UserDTO>>();
        try {
            dr.setResult(ResponseEntity.ok(service.findUserByDocument(document)));
        } catch (UserNotFoundException e) {
            dr.setErrorResult(ResponseEntity.badRequest().body(e.getMessage()));
        }
        return dr;
    }

    public DeferredResult<ResponseEntity<Void>> deleteUser(@RequestParam String document) {
        var dr = new DeferredResult<ResponseEntity<Void>>();
        try {
            service.deleteUserByDocument(document);
            dr.setResult(ResponseEntity.ok().build());
        } catch (DeleteUserException e) {
            dr.setErrorResult(ResponseEntity.badRequest().body(e.getMessage()));
        }
        return dr;
    }

}
