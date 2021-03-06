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
package br.com.uktech.bmt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Entity
@Table(schema = "public",
        name = "system_module",
        indexes = {
            @Index(name = "idx_id_system_module", columnList = "id_system_module")
        }
)
@SequenceGenerator(name = "SystemModuleIdGenerator", sequenceName = "seq_system_module", initialValue = 1, allocationSize = 1)
public class SystemModule implements Serializable {

    @Id
    @Column(name = "id_system_module", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SystemModuleIdGenerator")
    private Long id;

    @Column(name = "system_module_name", nullable = false)
    private String name;

    @Column(name = "system_module_category", nullable = false)
    private String category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "systemUserPermissionId.systemModule", fetch = FetchType.LAZY)
    private List<SystemUserPermission> systemUserPermission = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SystemUserPermission> getSystemUserPermission() {
        return systemUserPermission;
    }

    public void setSystemUserPermission(List<SystemUserPermission> systemUserPermission) {
        this.systemUserPermission = systemUserPermission;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.category);
        hash = 31 * hash + Objects.hashCode(this.systemUserPermission);
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
        final SystemModule other = (SystemModule) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.systemUserPermission, other.systemUserPermission)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemModule{" + "id=" + id + ", name=" + name + ", category=" + category + ", systemUserPermission=" + systemUserPermission + '}';
    }

}
