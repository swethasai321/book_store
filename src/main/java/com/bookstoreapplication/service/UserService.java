package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.LoginDTO;
import com.bookstoreapplication.dto.UserDTO;
import com.bookstoreapplication.email.EmailService;
import com.bookstoreapplication.exception.BookStoreExceptionHandler;
import com.bookstoreapplication.module.UserRegistrationModule;
import com.bookstoreapplication.repository.UserRegistrationRepository;
import com.bookstoreapplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements IUserService{

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;


    @Override
    public Object addPerson(UserRegistrationModule newUserRegistartionModule) {
        UserRegistrationModule userRegistrationModule = new UserRegistrationModule(newUserRegistartionModule);
        userRegistrationRepository.save(userRegistrationModule);
        String token=tokenUtility.createToken(userRegistrationModule.getId());
        emailService.sendEmail("swethasaichakrala@gmail.com", "Token", "Registeration SuccessFull and generated token is--> "+token);
        return userRegistrationModule;
    }

    @Override
    public Object update(Integer id, UserDTO userDTO) {
        if (userRegistrationRepository.findById(id).isPresent()) {
            UserRegistrationModule newUserRegistrationModule = new UserRegistrationModule(id, userDTO);
            UserRegistrationModule userRegistrationModule = userRegistrationRepository.save(newUserRegistrationModule);
            return "Done " + userRegistrationModule;
        }
        else throw (new BookStoreExceptionHandler("Record not Found"));
    }

    @Override
    public String userLogin(LoginDTO loginDTO) {
        Optional<UserRegistrationModule> newUserRegistration = userRegistrationRepository.findByEmailIdAndPassword(loginDTO.emailId, loginDTO.password);
        if (newUserRegistration.isPresent()) {
            return "LOGIN SUCCESSFUL";
        } else {
            System.out.println("User not Found Exception:");
            throw (new BookStoreExceptionHandler("Record not Found"));
        }
    }

    @Override
    public Object searchAll() {
        return userRegistrationRepository.findAll();
    }

    @Override
    public UserRegistrationModule getUserById(String token) {
        int id=tokenUtility.decodeToken(token);
        return userRegistrationRepository.findById(id).orElseThrow(() -> new BookStoreExceptionHandler("User  with id " + id + " does not exist in database..!"));

    }
}