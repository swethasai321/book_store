package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.LoginDTO;
import com.bookstoreapplication.dto.UserDTO;
import com.bookstoreapplication.module.UserRegistrationModule;

public interface IUserService { Object addPerson(UserRegistrationModule newUserRegistartionModule);
    Object update(Integer id, UserDTO userDTO);

    String userLogin(LoginDTO loginDTO);

    Object searchAll();

    UserRegistrationModule getUserById(String token);

//    Optional<UserRegistration> userLogin(LoginDTO loginDTO);
}