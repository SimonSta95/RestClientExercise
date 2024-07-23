package org.spring.restclientexercise.api.model;

public record ApiResponseInfo(
        int count,
        int pages,
        String next,
        String prev) {

}
