package com.colege.expection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExpectionResponse {
    private String url;
    private HttpStatus status;
    private String detail;

    public ExpectionResponse(String url, HttpStatus status, String detail) {
        this.url = url;
        this.status = status;
        this.detail = detail;
    }
}
