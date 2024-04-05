package com.hedspi.nihongobe.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.Objects;

public class CustomJwt extends JwtAuthenticationToken {
    private String firstname;
    private String lastname;
    public CustomJwt(Jwt jwt, Collection<? extends GrantedAuthority> authorities){
        super(jwt, authorities);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomJwt customJwt = (CustomJwt) o;
        return Objects.equals(firstname, customJwt.firstname) && Objects.equals(lastname, customJwt.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstname, lastname);
    }
}
