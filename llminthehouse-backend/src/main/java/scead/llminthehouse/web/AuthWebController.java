package scead.llminthehouse.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import scead.llminthehouse.base.channel.web.AuthApi;
import scead.llminthehouse.base.channel.web.dto.LoginRequestWebDTO;
import scead.llminthehouse.base.channel.web.dto.RegisterRequestWebDTO;
import scead.llminthehouse.base.channel.web.dto.UserWebDTO;
import scead.llminthehouse.domain.businessobject.User;
import scead.llminthehouse.domain.service.UserService;
import scead.llminthehouse.web.mapper.UserMapper;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthWebController implements AuthApi
{
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthWebController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<UserWebDTO> login(@RequestBody LoginRequestWebDTO loginRequestWebDTO) {
        Optional<User> userOpt = userService.findByUsername(loginRequestWebDTO.getUsername());
        if (userOpt.isPresent() && passwordEncoder.matches(loginRequestWebDTO.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.ok(UserMapper.mapUser(userOpt.get()));
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<UserWebDTO> register(@RequestBody RegisterRequestWebDTO registerRequestWebDTO) {
        if (userService.findByUsername(registerRequestWebDTO.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already in use");
        }
        User user = userService.registerUser(registerRequestWebDTO.getUsername(), registerRequestWebDTO.getPassword());
        return ResponseEntity.status(201).body(UserMapper.mapUser(user));
    }
}
