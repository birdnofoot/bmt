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
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class SystemUserDto implements Serializable {
    private Long id;
    
    private String firstName;
    
    private String middleName;
    
    private String lastName;
       
    private String email;
    
    private String password;
    
    private Date accountExpiration;
    
    private Boolean accountCanExpire;
    
    private Boolean locked;

    private Date credentialExpiration;

    private Boolean credentialCanExpire;
    
    private Boolean enabled;
        
    private List<SystemUserPermissionDto> systemUserPermission;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the accountExpiration
     */
    public Date getAccountExpiration() {
        return accountExpiration;
    }

    /**
     * @param accountExpiration the accountExpiration to set
     */
    public void setAccountExpiration(Date accountExpiration) {
        this.accountExpiration = accountExpiration;
    }

    /**
     * @return the accountCanExpire
     */
    public Boolean getAccountCanExpire() {
        return accountCanExpire;
    }

    /**
     * @param accountCanExpire the accountCanExpire to set
     */
    public void setAccountCanExpire(Boolean accountCanExpire) {
        this.accountCanExpire = accountCanExpire;
    }

    /**
     * @return the locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked the locked to set
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return the credentialExpiration
     */
    public Date getCredentialExpiration() {
        return credentialExpiration;
    }

    /**
     * @param credentialExpiration the credentialExpiration to set
     */
    public void setCredentialExpiration(Date credentialExpiration) {
        this.credentialExpiration = credentialExpiration;
    }

    /**
     * @return the credentialCanExpire
     */
    public Boolean getCredentialCanExpire() {
        return credentialCanExpire;
    }

    /**
     * @param credentialCanExpire the credentialCanExpire to set
     */
    public void setCredentialCanExpire(Boolean credentialCanExpire) {
        this.credentialCanExpire = credentialCanExpire;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the systemUserPermission
     */
    public List<SystemUserPermissionDto> getSystemUserPermission() {
        return systemUserPermission;
    }

    /**
     * @param systemUserPermission the systemUserPermission to set
     */
    public void setSystemUserPermission(List<SystemUserPermissionDto> systemUserPermission) {
        this.systemUserPermission = systemUserPermission;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.middleName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.accountExpiration);
        hash = 29 * hash + Objects.hashCode(this.accountCanExpire);
        hash = 29 * hash + Objects.hashCode(this.locked);
        hash = 29 * hash + Objects.hashCode(this.credentialExpiration);
        hash = 29 * hash + Objects.hashCode(this.credentialCanExpire);
        hash = 29 * hash + Objects.hashCode(this.enabled);
        hash = 29 * hash + Objects.hashCode(this.systemUserPermission);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserDto other = (SystemUserDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.accountExpiration, other.accountExpiration)) {
            return false;
        }
        if (!Objects.equals(this.accountCanExpire, other.accountCanExpire)) {
            return false;
        }
        if (!Objects.equals(this.locked, other.locked)) {
            return false;
        }
        if (!Objects.equals(this.credentialExpiration, other.credentialExpiration)) {
            return false;
        }
        if (!Objects.equals(this.credentialCanExpire, other.credentialCanExpire)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        if (!Objects.equals(this.systemUserPermission, other.systemUserPermission)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUserDto{" + "id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", accountExpiration=" + accountExpiration + ", accountCanExpire=" + accountCanExpire + ", locked=" + locked + ", credentialExpiration=" + credentialExpiration + ", credentialCanExpire=" + credentialCanExpire + ", enabled=" + enabled + ", systemUserPermission=" + systemUserPermission + '}';
    }
    
    
    
}
