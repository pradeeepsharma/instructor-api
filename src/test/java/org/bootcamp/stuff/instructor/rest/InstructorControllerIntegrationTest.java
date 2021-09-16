package org.bootcamp.stuff.instructor.rest;

import org.bootcamp.stuff.instructor.InstructorApiApplication;
import org.bootcamp.stuff.instructor.service.Instructor;
import org.json.JSONException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InstructorApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InstructorControllerIntegrationTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void can_get_subordinates() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/subordinates?id=0"),HttpMethod.GET, entity, String.class);
        String expected = "[]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void can_add_subordinates() {

        Instructor subordinate = new Instructor("11", "NoOne", "10Steps");

        HttpEntity<Instructor> entity = new HttpEntity<Instructor>(subordinate, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/subordinates?id=0"),
                HttpMethod.POST, entity, String.class);

        String responseBody = response.getBody();
        assertThat(responseBody.contains("Subordinates added"));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
