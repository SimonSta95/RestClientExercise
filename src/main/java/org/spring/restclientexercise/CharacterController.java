package org.spring.restclientexercise;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    public List<Character> getAllCharacters() {
        return characterService.loadAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return  characterService.loadCharacterById(id);
    }
}
