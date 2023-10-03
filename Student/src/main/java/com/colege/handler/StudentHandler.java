package com.colege.handler;

import com.colege.entity.Student;
import com.colege.service.StudentService;
import com.colege.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentHandler {
    private final StudentService service;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> getAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.getALl(), Student.class);
    }
    public Mono<ServerResponse> save(ServerRequest request){
        Mono<Student> student = request.bodyToMono(Student.class).doOnNext(objectValidator::validate);
        return student.flatMap(
                dto->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.save(dto), Student.class)
        );
    }
    public Mono<ServerResponse> getAllByIdSchool(ServerRequest request){
        Integer idSchool = Integer.parseInt(request.pathVariable("id_school"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.getAllByIdSchool(idSchool), Student.class);
    }
}
