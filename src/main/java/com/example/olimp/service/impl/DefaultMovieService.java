package com.example.olimp.service.impl;

import com.example.olimp.exceptions.EntityAlreadyExistsException;
import com.example.olimp.exceptions.EntityNotFoundException;
import com.example.olimp.exceptions.InternalServerException;
import com.example.olimp.exceptions.ValidationException;
import com.example.olimp.model.Director;
import com.example.olimp.model.Movie;
import com.example.olimp.repository.DirectorRepository;
import com.example.olimp.repository.MovieRepository;
import com.example.olimp.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public DefaultMovieService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie==null) {
            throw new EntityNotFoundException(String.format("Кинофильм с id %d не найден", id));
        }
        return movie;
    }

    @Override
    public Movie add(Movie entity) {
        validate(entity);
        Movie existedDMovie = movieRepository.findById(entity.getId()).orElse(null);
        if (existedDMovie != null) {
            throw new EntityAlreadyExistsException(String.format("Кинофильм с id %d уже существует", entity.getId()));
        }
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity, Long id) {
        validate(entity);
        if (!Objects.equals(entity.getId(), id)) {
            throw new ValidationException("Id в теле запроса и id указанный в строке запроса не соответсвуют друг другу");
        }
        Movie existedMovie= movieRepository.findById(entity.getId()).orElse(null);
        if (existedMovie == null) {
            throw new EntityNotFoundException(String.format("Кинофильм с id %d не найден", entity.getId()));
        }
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        Movie existedMovie = movieRepository.findById(id).orElse(null);
        if (existedMovie == null) {
            throw new EntityNotFoundException(String.format("Кинофильм с id %d не найден", id));
        }
        try {
            movieRepository.deleteById(id);
        }
        catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }

    private void validate(Movie movie) {
        if (movie == null) {
            throw new ValidationException("Кинофильм не может быть null");
        }
        if (movie.getId()==null) {
            throw new ValidationException("Id кинофильма не может быть null");
        }
        if (movie.getTitle()==null || !StringUtils.hasText(movie.getTitle())) {
            throw new ValidationException("Заголовок кинофильма не может быть пустым");
        }
        if (movie.getTitle().length()>100) {
            throw new ValidationException("Заголовок кинофильма не может иметь длину более 100");
        }
        if (movie.getYear()==null) {
            throw new ValidationException("Год выпуска кинофильма не может быть null");
        }
        if (movie.getYear()<1900 || movie.getYear()>2100) {
            throw new ValidationException("Год выпуска кинофильма должен быть в пределах от 1900 до 2100 годов");
        }
        if (movie.getLength()==null) {
            throw new ValidationException("Длина кинофильма не может быть null");
        }
        if (movie.getRating() == null) {
            throw new ValidationException("Рейтинг кинофильма не может быть null");
        }
        if (movie.getRating()>10 || movie.getRating()<0) {
            throw new ValidationException("Рейтинг кинофильма должен быть в пределах от 0 до 10 баллов");
        }
        if (movie.getDirector() == null) {
            throw new ValidationException("Директор кинофильма должен быть указан и не может быть null");
        }
        Long directorId = movie.getDirector().getId();
        if (directorId==null) {
            throw new ValidationException("Id директора кинофильма должно быть указно и не может быть null");
        }
        if (directorRepository.findById(directorId).orElse(null) == null) {
            throw new EntityNotFoundException(String.format("Директора с id %d не существует", directorId));
        }
    }
}
