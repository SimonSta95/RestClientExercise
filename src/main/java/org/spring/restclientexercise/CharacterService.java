package org.spring.restclientexercise;

import org.spring.restclientexercise.api.model.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService() {
        restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<Character> loadAllCharacters() {
        ApiResponse response = restClient.get()
                .uri("/character")
                .retrieve()
                .body(ApiResponse.class);

        return response.results();
    }

    public Character loadCharacterById(int id){
         return restClient.get()
                 .uri("/character/" + id)
                .retrieve()
                .body(Character.class);

    }
}
