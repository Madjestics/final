package com.example.olimp.repository;

import com.example.olimp.model.Director;
import com.example.olimp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    @Query(value = "select m from Movie m join Director d on m.director.id=d.id where d.id=:id")
    public List<Movie> findMoviesByDirectorId(Long id);
}
