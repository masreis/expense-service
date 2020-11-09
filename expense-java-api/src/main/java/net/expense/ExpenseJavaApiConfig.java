package net.expense;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseJavaApiConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
