package uk.co.sheffieldwebprogrammer.propertysecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}