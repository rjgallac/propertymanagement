package uk.co.sheffieldwebprogrammer.propertysecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.JwtAuthenticationResponse;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignInRequest;
import uk.co.sheffieldwebprogrammer.propertysecurity.dto.SignUpRequest;
import uk.co.sheffieldwebprogrammer.propertysecurity.model.Role;
import uk.co.sheffieldwebprogrammer.propertysecurity.model.User;
import uk.co.sheffieldwebprogrammer.propertysecurity.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        User save = userRepository.save(user);
        var jwt = jwtService.generateToken(save);
        jwtService.addClaim("Authorities", Role.USER.name()) ;

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signupLandlord(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.LANDLORD).build();
        User save = userRepository.save(user);
        var jwt = jwtService.generateToken(save);
        jwtService.addClaim("Authorities", Role.LANDLORD.toString()) ;

        return JwtAuthenticationResponse.builder().role(Role.LANDLORD.toString()).token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        jwtService.addClaim("Authorities", user.getAuthorities().stream().findFirst().get().getAuthority());
        var jwt = jwtService.generateToken(user);

        Optional<? extends GrantedAuthority> first = user.getAuthorities().stream().findFirst();
        return JwtAuthenticationResponse.builder().token(jwt).role(first.get().getAuthority()).build();
    }

    @Override
    public JwtAuthenticationResponse signupAdmin(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN).build();
        User save = userRepository.save(user);
        var jwt = jwtService.generateToken(save);
        jwtService.addClaim("Authorities", Role.ADMIN.toString()) ;

        return JwtAuthenticationResponse.builder().role(Role.ADMIN.toString()).token(jwt).build();
    }
}
