package com.colege.router;

import com.colege.handler.StudentHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class StudentRouter {
    private static final String PATH = "api/v1/students";
    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }
    @Bean
    RouterFunction<ServerResponse> routerFunction(StudentHandler handle){
        return RouterFunctions.route()
                .GET(PATH, handle::getAll)
                .POST(PATH, handle::save)
                .GET(PATH + "/school/{id_school}", handle::getAllByIdSchool)
                .build();
    }
}
