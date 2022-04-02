package net.ancronik.samples.cucumber.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Global application configuration class.
 *
 * @author Nikola Presecki
 */
@Configuration
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
