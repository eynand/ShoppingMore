package com.eynan.shoppingmore.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "login")
@Scope(value = "session")
public class LoginController {

  public boolean isLogin() {
      return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().filter(auth ->
              ((GrantedAuthority) auth).getAuthority().equals("USER")).count() > 0;
  }
}
