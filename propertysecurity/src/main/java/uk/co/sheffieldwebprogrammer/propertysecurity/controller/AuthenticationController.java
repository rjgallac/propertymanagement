package uk.co.sheffieldwebprogrammer.propertysecurity.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.JwtAuthenticationResponse;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignInRequest;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignUpRequest;
import uk.co.sheffieldwebprogrammer.propertysecurity.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signuplandlord")
    public ResponseEntity<JwtAuthenticationResponse> signuplandlord(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signupLandlord(request));
    }

    @PostMapping("/signupadmin")
    public ResponseEntity<JwtAuthenticationResponse> signupadmin(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signupAdmin(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}