package net.ancronik.samples.cucumber.application.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import net.ancronik.samples.cucumber.application.Util;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() { //FIXME NOT WORKING
        return builder -> {
            builder.simpleDateFormat("yyyy-MM-dd hh:mm:ss");
            builder.serializers(new LocalDateSerializer(Util.APP_DATE_TIME_FORMATTER));
            builder.serializers(new LocalDateTimeSerializer(Util.APP_DATE_TIME_FORMATTER));
        };
    }

}
