package org.spring.restclientexercise.Character;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    public List<Character> getAllCharacters(@RequestParam(required = false) String status) {

        Map<String, String> params = new HashMap<>();

        if (status != null) {
            params.put("status", status);
        }
        return characterService.loadAllCharacters(params);
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return characterService.loadCharacterById(id);
    }

}
