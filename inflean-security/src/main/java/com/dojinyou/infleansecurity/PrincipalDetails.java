package com.dojinyou.infleansecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetails implements UserDetails {

  private final User user;

  public PrincipalDetails(User user) {
    this.user = user;
  }

  // 해당 유저의 권한 리턴
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> c = new ArrayList<>();
    c.add(new SimpleGrantedAuthority(user.getRoleName()));
    System.out.println(user.getRoleName());
    return c;
  }

  @Override
  public String getPassword() {
    return user.getEncodedPassword();
  }

  @Override
  public String getUsername() {
    return user.getId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
