package com.project.full.service;

import com.project.full.domain.dto.UserDTO;
import com.project.full.domain.entity.User;
import com.project.full.domain.repository.UserRepository;
import com.project.full.exceptions.DeleteUserException;
import com.project.full.exceptions.UserException;
import com.project.full.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private ModelMapper converter;
    @Autowired
    private UserRepository repository;

    public UserDTO createNewUser(UserDTO newUser) throws UserException {
        try {
            return createUser(newUser);
        } catch (Exception e) {
            throw new UserException("fail to save new User");
        }
    }

    private UserDTO createUser(UserDTO newUser) {
        User user = new User();
        user.setDocument(newUser.getDocument());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());
        repository.save(user);
        return converter.map(user, UserDTO.class);
    }

    public UserDTO findUserByDocument(String document) throws UserNotFoundException {
        try {
            return converter.map(repository.findUserByDocument(document), UserDTO.class);
        } catch (Exception e) {
            throw new UserNotFoundException("Fail to find user");
        }
    }

    public void deleteUserByDocument(String document) throws DeleteUserException {
        try {
            repository.delete(repository.findUserByDocument(document));
        } catch (Exception e) {
            throw new DeleteUserException("fail to delete User");
        }
    }

}
