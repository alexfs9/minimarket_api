package com.minimarket_api.services.implementations;

import com.minimarket_api.controllers.dto.AuthLoginRequest;
import com.minimarket_api.controllers.dto.AuthRegisterRequest;
import com.minimarket_api.controllers.dto.AuthResponse;
import com.minimarket_api.persistence.entities.AccountEntity;
import com.minimarket_api.persistence.entities.RoleEntity;
import com.minimarket_api.services.AccountService;
import com.minimarket_api.services.RoleService;
import com.minimarket_api.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = this.accountService
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("La cuenta " + username + " no existe."));

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();

        account.getRoles()
                .forEach(role -> grantedAuthorities
                        .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

        account.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> grantedAuthorities
                        .add(new SimpleGrantedAuthority(permission.getPermission().name())));

        return new User(
                account.getEmail(),
                account.getPassword(),
                account.isEnabled(),
                account.isAccountNoExpired(),
                account.isCredentialNoExpired(),
                account.isAccountNoLocked(),
                grantedAuthorities);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        /*
        loadUserByUsername method already throws an exception
        if (userDetails == null)
            throw new BadCredentialsException("Usuario no registrado.");
        */
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Contraseña incorrecta.");

        return new UsernamePasswordAuthenticationToken(
                username,
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    public /*AuthResponse*/ ResponseEntity<?> register(AuthRegisterRequest authRegisterRequest) {
        String username = authRegisterRequest.username();
        String firstNames = authRegisterRequest.firstNames();
        String lastNames = authRegisterRequest.lastNames();
        String phone = authRegisterRequest.phone();
        String email = authRegisterRequest.email();
        String password = authRegisterRequest.password();
        List<String> roleList = authRegisterRequest.roleRequest().roleList();

        // If input role is not in RoleEnum.java throws an exception (IllegalArgumentException)
        Set<RoleEntity> roles = new HashSet<>(this.roleService.findRoleEntitiesByRoleIn(roleList));

        /*
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("Los roles especificados no existen.");
        }
        */

        AccountEntity account = new AccountEntity();
        account.setUsername(username);
        account.setFirstNames(firstNames);
        account.setLastNames(lastNames);
        account.setPhone(phone);
        account.setEmail(email);
        account.setPassword(this.passwordEncoder.encode(password));
        account.setRoles(roles);
        account.setEnabled(true);
        account.setAccountNoLocked(true);
        account.setAccountNoExpired(true);
        account.setCredentialNoExpired(true);

        AccountEntity registeredAccount = this.accountService.save(account);

        /*
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        registeredAccount.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));
        registeredAccount.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermission().name())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                registeredAccount.getUsername(),
                registeredAccount.getPassword(),
                authorities);

        String jwt = jwtUtils.createJwt(authentication);

        AuthResponse authResponse = new AuthResponse(
                registeredAccount.getUsername(),
                "¡Cuenta registrada exitosamente!",
                jwt,
                true);

        return authResponse;
        */
        return ResponseEntity.noContent().build();
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.createJwt(authentication);

        return new AuthResponse(username, "¡Inició sesión!", jwt, true);
    }
}
