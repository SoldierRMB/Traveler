package com.soldiersoft.traveler.model.vo;

import com.soldiersoft.traveler.entity.Menu;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsVO implements UserDetails {
    private UserRoleDTO userRoleDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>(userRoleDTO
                .getMenuList()
                .stream()
                .map(Menu::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .toList());
        authorities.add(new SimpleGrantedAuthority(userRoleDTO.getRole().getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        String password = userRoleDTO.getUser().getPassword();
        userRoleDTO.getUser().setPassword(null);
        return password;
    }

    @Override
    public String getUsername() {
        return userRoleDTO.getUser().getUsername();
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
