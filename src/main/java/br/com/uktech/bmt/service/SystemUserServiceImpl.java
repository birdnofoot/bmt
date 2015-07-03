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

import br.com.uktech.bmt.dto.SystemUserDto;
import br.com.uktech.bmt.model.SystemUser;
import br.com.uktech.bmt.model.repository.SystemUserRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SystemUserService")
public class SystemUserServiceImpl implements SystemUserService {
    
    @Autowired
    private SystemUserRepository repository;
    
    @Autowired
    private Mapper mapper;
    
    @Transactional(readOnly = true)
    @Override
    public SystemUserDto getUserByEmail(String email) {
        SystemUser user = repository.findOneByEmail(email);
        SystemUserDto userDto = null;
        if (user != null) {
            userDto = new SystemUserDto();
            mapper.map(user, userDto);
        }
        return userDto;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SystemUserDto> searchAllUsers(Pageable p) {
        List <SystemUserDto> users = new ArrayList<>();
        Page<SystemUser> systemUsers = repository.findAll(p);
        SystemUserDto userDto = null;
        Iterator<SystemUser> itr = systemUsers.getContent().iterator();
        while(itr.hasNext()) {
            userDto = new SystemUserDto();
            mapper.map(itr.next(), userDto);
            users.add(userDto);
        }
        Page<SystemUserDto> page = null;
        if (!users.isEmpty()) {
            page = new PageImpl<>(users, p, systemUsers.getTotalElements());
        }        
        return page;
    }
    
}
