package com.capitole;

import com.capitole.rate.dto.FindRateResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateIntegrationTests {

    private static final String PATH = "/api/rate?";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRateAt10hOn14June2020() {

        // Arrange
        String url = PATH + "applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1";

        //Act
        ResponseEntity<FindRateResponseDTO> response = restTemplate.getForEntity(url, FindRateResponseDTO.class);
        FindRateResponseDTO dto = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(dto);
        assertEquals(35455L, dto.productId());
        assertEquals(1, dto.brandId());
        assertEquals(1, dto.priceList());
        assertEquals("35.50 EUR", dto.price ());
    }

    @Test
    public void testRateAt16hOn14June2020() {

        //Arrange
        String url = PATH + "applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1";

        //Act
        ResponseEntity<FindRateResponseDTO> response = restTemplate.getForEntity(url, FindRateResponseDTO.class);
        FindRateResponseDTO dto = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(dto);
        assertEquals(2, dto.priceList());
        assertEquals("25.45 EUR", dto.price());
    }

    @Test
    public void testRateAt21hOn14June2020() {

        //Arrange
        String url = PATH + "applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1";

        //Act
        ResponseEntity<FindRateResponseDTO> response = restTemplate.getForEntity(url, FindRateResponseDTO.class);
        FindRateResponseDTO dto = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(dto);
        assertEquals(1, dto.priceList());
        assertEquals("35.50 EUR", dto.price());
    }

    @Test
    public void testRateAt10hOn15June2020() {

        //Arrange
        String url = PATH + "applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1";

        //Act
        ResponseEntity<FindRateResponseDTO> response = restTemplate.getForEntity(url, FindRateResponseDTO.class);
        FindRateResponseDTO dto = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(dto);
        assertEquals(3, dto.priceList());
        assertEquals("30.50 EUR", dto.price());
    }

    @Test
    public void testRateAt21hOn16June2020() {

        //Arrange
        String url = PATH + "applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1";

        //Act
        ResponseEntity<FindRateResponseDTO> response = restTemplate.getForEntity(url, FindRateResponseDTO.class);
        FindRateResponseDTO dto = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(dto);
        assertEquals(4, dto.priceList());
        assertEquals("38.95 EUR", dto.price());
    }
}
