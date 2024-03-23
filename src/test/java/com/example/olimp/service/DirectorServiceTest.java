package com.example.olimp.service;

import com.example.olimp.model.Director;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DirectorServiceTest {

    @Autowired
    private DirectorService directorService;

    @BeforeEach
    public void addTest() {
        Director director = new Director(1L, "Test test");
        directorService.add(director);
    }

    @Test
    public void findAllTest() {
        List<Director> directors = directorService.findAll();
        Assertions.assertEquals(1, directors.size());
        Assertions.assertEquals("Test test", directors.get(0).getFio());
    }

    @Test
    public void findByIdTest() {
        Director director = directorService.findById(1L);
        Assertions.assertNotNull(director);
        Assertions.assertEquals("Test test", director.getFio());
    }

    @Test
    public void updateTest() {
        Director director = directorService.findById(1L);
        Assertions.assertNotNull(director);
        director.setFio("update test");
        Director updatedDirector = directorService.update(director, 1L);
        Assertions.assertNotNull(updatedDirector);
        Assertions.assertEquals("update test", updatedDirector.getFio());
    }

    @AfterEach
    public void deleteTest() {
        directorService.delete(1L);
        Assertions.assertEquals(0, directorService.findAll().size());
    }
}
