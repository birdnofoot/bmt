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
package br.com.uktech.bmt.configuration;

import br.com.uktech.bmt.dozer.SystemModuleMapper;
import br.com.uktech.bmt.dozer.SystemUserMapper;
import br.com.uktech.bmt.dozer.SystemUserPermissionIdMapper;
import br.com.uktech.bmt.dozer.SystemUserPermissionMapper;
import br.com.uktech.bmt.dozer.converter.LocaleConverter;
import br.com.uktech.bmt.dozer.converter.TimeZoneConverter;
import br.com.uktech.bmt.dozer.converter.URLConverter;
import br.com.uktech.bmt.dozer.converter.UUIDConverter;
import java.util.ArrayList;
import java.util.List;
import org.dozer.CustomConverter;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Configuration
public class MapperConfig {
    
    @Bean
    public List<BeanMappingBuilder> getMapperBuilders() {
        List<BeanMappingBuilder> mappers = new ArrayList();
        mappers.add(new SystemModuleMapper());
        mappers.add(new SystemUserMapper());
        mappers.add(new SystemUserPermissionMapper());
        mappers.add(new SystemUserPermissionIdMapper());
        return mappers;
    }
    
    @Bean
    public List<CustomConverter> getCustomConverters() {
        List<CustomConverter> converters = new ArrayList();
        converters.add(new LocaleConverter());
        converters.add(new TimeZoneConverter());
        converters.add(new URLConverter());
        converters.add(new UUIDConverter());
        return converters;
    }
    
    @Bean
    public DozerBeanMapperFactoryBean mapper() {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        mapper.setCustomConverters(getCustomConverters());
        //mapper.setCustomFieldMapper(new HibernateInitializedFieldMapper());
        mapper.setMappingBuilders(getMapperBuilders());
        return mapper;
    }    
}
