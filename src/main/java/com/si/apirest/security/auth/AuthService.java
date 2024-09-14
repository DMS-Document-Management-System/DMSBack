package com.si.apirest.security.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.si.apirest.entity.Person;
import com.si.apirest.entity.RoleEntity;
import com.si.apirest.exceptions.PersonExistException;
import com.si.apirest.repository.PersonRepository;
import com.si.apirest.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonRepository personRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Person user = personRepository.findByUsuario(request.getUsername()).orElseThrow( () -> new UsernameNotFoundException("Login: Usuario no encontrado"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<Person> userExist = personRepository.findByUsuario(request.getUsername());
        if(userExist.isPresent()){
            throw new PersonExistException("This user already exist mr presidente.");
        }
        Person user = Person.builder()
        .usuario(request.getUsername())
        .contraseña(passwordEncoder.encode(request.getPassword()))
        .role(RoleEntity.builder().id(2).build())
        .enabled(true)
        .build();

        personRepository.save(user);

        return AuthResponse.builder()
        .token(jwtService.getToken(user))
        .build();
    }

}
