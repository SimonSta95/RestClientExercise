package org.spring.restclientexercise.Character;

import org.spring.restclientexercise.api.model.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;


@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService() {
        restClient = RestClient.builder()
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<Character> loadAllCharacters(Map<String,String> params) {

        String uri = "/character";

        if (params.containsKey("status")){
            uri = uri + "?status=" + params.get("status");
        }

        ApiResponse response = restClient.get()
                .uri(uri)
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
