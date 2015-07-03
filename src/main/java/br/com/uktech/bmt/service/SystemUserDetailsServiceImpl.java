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
package br.com.uktech.bmt.service;

import br.com.uktech.bmt.dto.SystemModuleDto;
import br.com.uktech.bmt.dto.SystemUserDto;
import br.com.uktech.bmt.dto.SystemUserPermissionDto;
import br.com.uktech.bmt.security.BmtUserDetails;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Service("SystemUserDetailsService")
public class SystemUserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(SystemUserDetailsServiceImpl.class);
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private SystemUserService systemUserService;
    
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = null;
        SystemUserDto userDto = systemUserService.getUserByEmail(username);
        if (userDto == null) {
            String message = messageSource.getMessage("DigestAuthenticationFilter.usernameNotFound", new Object[] { username }, LocaleContextHolder.getLocale());
            UsernameNotFoundException ex = new UsernameNotFoundException(message);
            logger.warn(ex.getLocalizedMessage());
            throw ex;
        } else {
            user = new BmtUserDetails(userDto);
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) user.getAuthorities();
            Collection<SystemUserPermissionDto> permissions = userDto.getSystemUserPermission();
            permissions.stream().forEach((permission) -> {
                SystemModuleDto module = permission.getSystemUserPermissionId().getSystemModule();
                if (permission.getRead()) {
                    authorities.add(createRole(module, "READ"));
                }
                if (permission.getAdd()) {
                     authorities.add(createRole(module, "ADD"));
                }
                if (permission.getEdit()) {
                    authorities.add(createRole(module, "EDIT"));
                }
                if (permission.getDelete()) {
                    authorities.add(createRole(module, "DELETE"));
                }
            });
        }
        return user;
    }
    
    private SimpleGrantedAuthority createRole(SystemModuleDto module, String action) {
        StringBuilder sb = new StringBuilder("ROLE".concat("_"));
        sb.append(module.getCategory().concat("_"));
        sb.append(module.getName().concat("_"));
        sb.append(action);
        return new SimpleGrantedAuthority(sb.toString().trim().toUpperCase());
    }
    
}
