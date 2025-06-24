package com.teach.security.service;

import com.teach.security.domain.Role;
import com.teach.security.dto.req.LoginReqDTO;
import com.teach.security.dto.req.UserReqDTO;
import com.teach.security.dto.res.LoginResDTO;
import com.teach.security.dto.res.UserResDTO;
import com.teach.security.model.User;
import com.teach.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResDTO createUser(UserReqDTO dto) {

        if (dto.username() == null)
            throw new RuntimeException("Username cannot be null");

        if (dto.password() == null)
            throw new RuntimeException("Password cannot be null");

        if (dto.username().isEmpty())
            throw new RuntimeException("Username cannot be empty");

        if (dto.password().isEmpty())
            throw new RuntimeException("Password cannot be empty");

        if (userRepository.existsByUsername(dto.username()))
            throw new RuntimeException("Username already in use");

        User user = new User();

        user.setUsername(dto.username());

        String encodedPassword = passwordEncoder.encode(dto.password());

        user.setPassword(encodedPassword);

        List<Role> roles = List.of(dto.role());

        user.setRoles(roles);

        userRepository.save(user);

        return new UserResDTO(user.getId(), user.getUsername());
    }

    public LoginResDTO login(LoginReqDTO dto) {

        if (dto.username() == null)
            throw new RuntimeException("Username cannot be null");

        if (dto.password() == null)
            throw new RuntimeException("Password cannot be null");

        if (dto.username().isEmpty())
            throw new RuntimeException("Username cannot be empty");

        if (dto.password().isEmpty())
            throw new RuntimeException("Password cannot be empty");

        Optional<User> optionalUser = userRepository.findByUsername(dto.username());

        if (optionalUser.isEmpty())
            throw new RuntimeException("User not found");

        User user = optionalUser.get();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        authenticationManager.authenticate(token);

        return tokenService.generateToken(user);
    }
}
