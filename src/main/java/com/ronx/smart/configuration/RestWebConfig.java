package com.ronx.smart.configuration;

import com.ronx.smart.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.ronx.smart")
@Import({RepositoryRestMvcConfiguration.class, MongoDBConfig.class})
@EnableWebMvc
@EnableAutoConfiguration
public class RestWebConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setResourceMappingForDomainType(User.class)
                .addResourceMappingFor("id")
                .setPath("identifier");

    }

}
