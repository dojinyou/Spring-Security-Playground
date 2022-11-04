package com.dojinyou.infleansecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public PrincipalDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("loadUserByUsername: " + username);
    User user = userRepository
        .findById(username)
        .orElse(null);
    return user == null ? null : new PrincipalDetails(user);
  }
}
