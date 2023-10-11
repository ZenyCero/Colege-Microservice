package com.colege.client;

import com.colege.dto.SchoolDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "school-service", url = "${application.config.school-url}")
public interface SchoolClient {

    @GetMapping("/{id}")
    Optional<SchoolDTO> findById(@PathVariable("id") Integer id);
}
