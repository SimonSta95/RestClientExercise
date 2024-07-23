package org.spring.restclientexercise.Species;

import org.spring.restclientexercise.api.model.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SpeciesService {

    private final RestClient restClient;

    public SpeciesService() {
        restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public int getSpeciesAlive(String speciesName) {

        ApiResponse response = restClient.get()
                .uri("/character?status=alive&species=" + speciesName)
                .retrieve()
                .body(ApiResponse.class);

        return response.info().count();
    }
}
