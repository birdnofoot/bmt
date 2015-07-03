/*
 * Copyright (C) 2015 Uhlig e Korovsky Tecnologia Ltda - ME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.uktech.bmt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Component
public class BMTAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(BMTAuthenticationProvider.class);
    
    @Autowired
    @Qualifier("SystemUserDetailsService")
    private UserDetailsService userDetails;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = a.getCredentials().toString();
        
        UserDetails user = this.userDetails.loadUserByUsername(username);
        
        if (user == null) {
            logger.debug("{exception.account.notfound}");
            throw new BadCredentialsException("{exception.account.notfound}");
        }
        
        if (!user.getUsername().equals(username))
        {
            logger.debug("{exception.account.wrongpassword}");
            throw new BadCredentialsException("{exception.account.wrongpassword}");
        }
            
        if (!passwordEncoder.matches(password, user.getPassword()))
        {
            logger.debug("{exception.account.wrongpassword}");
            logger.debug("Password: " + passwordEncoder.encode(password));
            throw new BadCredentialsException("{exception.account.wrongpassword}");
        }
        
        if (user.isEnabled() == false) {
            logger.debug("{exception.account.disabled}");
            throw new DisabledException("{exception.account.disabled}");
        }
        
        if (user.isAccountNonLocked() == false) {
            logger.debug("{exception.account.locked}");
            throw new LockedException("{exception.account.locked}");
        }
        
        if (user.isAccountNonExpired() == false) {
            logger.debug("{exception.account.expired}");
            throw new AccountExpiredException("{exception.account.expired}");
        }
        
        if (user.isCredentialsNonExpired() == false) {
            logger.debug("{exception.account.credentials.expired}");
            throw new CredentialsExpiredException("{exception.account.credentials.expired}");
        }

        logger.info("login in " + user.getUsername());
        
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
