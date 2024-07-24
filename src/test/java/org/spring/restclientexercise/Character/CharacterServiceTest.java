package org.spring.restclientexercise.Character;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CharacterServiceTest {

    @Autowired
    private MockMvc mockMvc;
    private static MockWebServer mockWebServer;


    //SETUP
    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void setPropertys(DynamicPropertyRegistry registry) {
        registry.add("basic.url", () -> mockWebServer.url("/").toString());
    }

    //TESTS
    @Test
    public void testsApiCall() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(
                 """
                     {
                        "info": {
                            "count": 2,
                            "pages": 1,
                            "next": null,
                            "prev": null
                        },
                        "results":[
                            {
                                "id": 1,
                                "name": "Rick Sanchez",
                                "status": "Alive",
                                "species": "Human",
                                "type": "",
                                "gender": "Male",
                                "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                "url": "https://rickandmortyapi.com/api/character/1",
                                "created": "2017-11-04T18:48:46.250Z"
                            },
                            {
                                "id": 2,
                                "name": "Morty Smith",
                                "status": "Alive",
                                "species": "Human",
                                "type": "",
                                "gender": "Male",
                                "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                "url": "https://rickandmortyapi.com/api/character/2",
                                "created": "2017-11-04T18:50:21.651Z"
                            }
                        ]
                     }
                 """)
                .addHeader("Content-Type", "application/json"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                """
                    [
                          {
                            "id": 1,
                            "name": "Rick Sanchez",
                            "species": "Human",
                            "status": "Alive"
                          },
                          {
                            "id": 2,
                            "name": "Morty Smith",
                            "species": "Human",
                            "status": "Alive"
                          }
                    ]
                """
                ));
    }
}