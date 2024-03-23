package com.example.olimp.controller;

import com.example.olimp.model.Director;
import com.example.olimp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Director> findAll() {
        return directorService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Director findById(@PathVariable(name = "id") Long id) {
        return directorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Director addDirector(@RequestBody Director director) {
        return directorService.add(director);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Director updateDirector(@RequestBody Director director, @PathVariable(name = "id") Long id) {
        return directorService.update(director, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDirector(@PathVariable(name = "id") Long id) {
        directorService.delete(id);
    }
}
