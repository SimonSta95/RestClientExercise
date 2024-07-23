package org.spring.restclientexercise.api.model;

import org.spring.restclientexercise.Character;

import java.util.List;

public record ApiResponse(
        ApiResponseInfo info,
        List<Character> results
) {
}
