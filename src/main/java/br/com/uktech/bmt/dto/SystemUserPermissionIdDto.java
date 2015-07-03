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
package br.com.uktech.bmt.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class SystemUserPermissionIdDto implements Serializable {

    private SystemUserDto systemUser;

    private SystemModuleDto systemModule;

    /**
     * @return the systemUser
     */
    public SystemUserDto getSystemUser() {
        return systemUser;
    }

    /**
     * @param systemUser the systemUser to set
     */
    public void setSystemUser(SystemUserDto systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * @return the systemModule
     */
    public SystemModuleDto getSystemModule() {
        return systemModule;
    }

    /**
     * @param systemModule the systemModule to set
     */
    public void setSystemModule(SystemModuleDto systemModule) {
        this.systemModule = systemModule;
    }

}
