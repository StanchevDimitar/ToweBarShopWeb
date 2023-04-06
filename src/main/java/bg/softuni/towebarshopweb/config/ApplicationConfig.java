package bg.softuni.towebarshopweb.config;

import bg.softuni.towebarshopweb.Interceptors.LoginInterceptor;
import bg.softuni.towebarshopweb.repository.UserRepository;
import bg.softuni.towebarshopweb.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@ComponentScan("bg.softuni.towebarshopweb")
public class ApplicationConfig{

    Condition<?, ?> isStringBlank = new AbstractCondition<Object, Object>() {
        @Override
        public boolean applies(MappingContext<Object, Object> context) {
            if(context.getSource() instanceof String) {
                return null!=context.getSource() && !"".equals(context.getSource());
            } else {
                return context.getSource() != null;
            }
        }
    };



    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setPropertyCondition(isStringBlank)
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }



    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }




}
