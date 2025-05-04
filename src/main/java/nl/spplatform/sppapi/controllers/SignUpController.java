package nl.spplatform.sppapi.controllers;

import nl.spplatform.sppapi.dtos.SignUpRequestDTO;
import nl.spplatform.sppapi.dtos.SignUpResponseDTO;
import nl.spplatform.sppapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<SignUpResponseDTO> createUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO result = userService.createUser(signUpRequestDTO);
        if (result.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}