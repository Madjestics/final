package com.example.olimp.service.impl;

import com.example.olimp.exceptions.EntityAlreadyExistsException;
import com.example.olimp.exceptions.EntityNotFoundException;
import com.example.olimp.exceptions.InternalServerException;
import com.example.olimp.exceptions.ValidationException;
import com.example.olimp.model.Director;
import com.example.olimp.repository.DirectorRepository;
import com.example.olimp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultDirectorService implements DirectorService{

    private final DirectorRepository directorRepository;

    @Autowired
    public DefaultDirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(Long id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        Director director = directorRepository.findById(id).orElse(null);
        if (director==null) {
            throw new EntityNotFoundException(String.format("Директор с id %d не найден", id));
        }
        return director;
    }

    @Override
    public Director add(Director entity) {
        validate(entity);
        Director existedDirector = directorRepository.findById(entity.getId()).orElse(null);
        if (existedDirector != null) {
            throw new EntityAlreadyExistsException(String.format("Директор с id %d уже существует", entity.getId()));
        }
        return directorRepository.save(entity);
    }

    @Override
    public Director update(Director entity, Long id) {
        validate(entity);
        if (!Objects.equals(entity.getId(), id)) {
            throw new ValidationException("Id в теле запроса и id указанный в строке запроса не соответсвуют друг другу");
        }
        Director existedDirector = directorRepository.findById(entity.getId()).orElse(null);
        if (existedDirector == null) {
            throw new EntityNotFoundException(String.format("Директор с id %d не найден", entity.getId()));
        }
        return directorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new ValidationException("Id не может быть null");
        }
        Director existedDirector = directorRepository.findById(id).orElse(null);
        if (existedDirector == null) {
            throw new EntityNotFoundException(String.format("Директор с id %d не найден", id));
        }
        if (directorRepository.findMoviesByDirectorId(id).size()>0) {
            throw new InternalServerException("Невозможно удалить директора, так как он привзяан к существующим фильмам");
        }
        try {
            directorRepository.deleteById(id);
        }
        catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }

    private void validate(Director director) {
        if (director == null) {
            throw new ValidationException("Директор не может быть null");
        }
        if (director.getId()==null) {
            throw new ValidationException("Id директора не может быть null");
        }
        if (director.getFio()==null || !StringUtils.hasText(director.getFio())) {
            throw new ValidationException("ФИО директора не может быть пустым");
        }
    }
}
