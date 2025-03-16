package br.com.alysonrodrigo.elevechangecambio.infrastructure.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ActiveProfiles("test")
public class ApplicationConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testRestTemplateBeanExists() {
        Assertions.assertNotNull(restTemplate);
    }
}
