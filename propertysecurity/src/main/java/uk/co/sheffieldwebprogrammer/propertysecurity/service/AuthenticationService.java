package uk.co.sheffieldwebprogrammer.propertysecurity.service;

import uk.co.sheffieldwebprogrammer.propertysecurity.dto.JwtAuthenticationResponse;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignInRequest;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signupLandlord(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse signupAdmin(SignUpRequest request);
}