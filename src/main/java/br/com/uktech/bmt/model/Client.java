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
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Entity
@Table(schema = "public",
        name = "bacula_client",
        indexes = {
            @Index(name = "idx_id_bacula_client", columnList = "id_bacula_client")
        }
)
@SequenceGenerator(name = "BaculaClientIdGenerator", sequenceName = "seq_bacula_client", initialValue = 1, allocationSize = 1)
public class Client implements Serializable {
    @Id
    @Column(name = "id_bacula_client", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BaculaClientIdGenerator")
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
    
    @Column(name = "hostname", nullable = false, unique = false, length = 100)
    private String hostname;

    @Column(name = "port", nullable = false, unique = false, length = 100)
    private Integer port = 9101;

    @Column(name = "password", nullable = false, unique = false, length = 256)
    private String password;
    
    @Column(name = "enabled", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private Boolean enabled;
    
    @Column(name = "autoprune", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    private Boolean autoprune = true;
    
    @Column(name = "priority", nullable = false, unique = false, length = 4)
    private Integer priority = 10;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="id_bacula_director", nullable=false)
    private Director director;

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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAutoprune() {
        return autoprune;
    }

    public void setAutoprune(Boolean autoprune) {
        this.autoprune = autoprune;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.hostname);
        hash = 73 * hash + Objects.hashCode(this.port);
        hash = 73 * hash + Objects.hashCode(this.password);
        hash = 73 * hash + Objects.hashCode(this.enabled);
        hash = 73 * hash + Objects.hashCode(this.autoprune);
        hash = 73 * hash + Objects.hashCode(this.priority);
        hash = 73 * hash + Objects.hashCode(this.director);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.hostname, other.hostname)) {
            return false;
        }
        if (!Objects.equals(this.port, other.port)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        if (!Objects.equals(this.autoprune, other.autoprune)) {
            return false;
        }
        if (!Objects.equals(this.priority, other.priority)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", hostname=" + hostname + ", port=" + port + ", password=" + password + ", enabled=" + enabled + ", autoprune=" + autoprune + ", priority=" + priority + ", director=" + director + '}';
    }
    
}
