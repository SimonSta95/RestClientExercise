package org.spring.restclientexercise.Species;

import lombok.RequiredArgsConstructor;
import org.spring.restclientexercise.Character.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/species-statistic")
@RequiredArgsConstructor
public class SpeciesController {

    private final CharacterService characterService;

    @GetMapping
    public int getSpeciesStatistics(@RequestParam String species) {
        return characterService.getSpeciesAlive(species);
    }


}
