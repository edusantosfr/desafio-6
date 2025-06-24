package com.teach.security.controller;

import com.teach.security.service.UserService;
import com.teach.security.dto.req.LoginReqDTO;
import com.teach.security.dto.req.UserReqDTO;
import com.teach.security.dto.res.LoginResDTO;
import com.teach.security.dto.res.UserResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResDTO createUser(
            @RequestBody UserReqDTO dto
            ) {
        UserResDTO response = userService.createUser(dto);

        return response;
    }

    @PostMapping("/login")
    public LoginResDTO login(
            @RequestBody LoginReqDTO dto
    ) {
        LoginResDTO response = userService.login(dto);

        return response;
    }
}
