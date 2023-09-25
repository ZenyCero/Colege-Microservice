package com.colege.service;

import com.colege.dto.TokenDTO;
import com.colege.dto.UserDTO;
import com.colege.entity.Usuario;
import com.colege.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String create(UserDTO userDTO){
        Usuario usuario = new Usuario(userDTO.getUsuario(),passwordEncoder.encode(userDTO.getClave()));
        if(userRepository.existsById(userDTO.getUsuario())){
            throw new UsernameNotFoundException("Usuario already exists");
        }
        userRepository.save(usuario);
        return "user added to the system";
    }
    public TokenDTO login(UserDTO userDTO){
        UsernamePasswordAuthenticationToken userAth
                = new UsernamePasswordAuthenticationToken(userDTO.getUsuario(),userDTO.getClave());
        Authentication authentication = this.authenticationManager.authenticate(userAth);
        TokenDTO token = TokenDTO.builder().token(jwtService.generateToken(userDTO.getUsuario())).build();
        return token;
    }
    public void validate(String token){
        jwtService.validateToken(token);
    }
}
