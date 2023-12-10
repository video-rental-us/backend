package com.igorszalas.rentalappus.film;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> displayAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> displayAllFilms(Sort sort) {
        return filmRepository.findAll(sort);
    }

    @Override
    public List<Film> displayFilmBySearchPhrase(String searchPhrase) {
        return filmRepository.findBy(null, null);
    }

    @Override
    public ResponseEntity<List<Film>> getFilmByFilmTitle(@RequestParam(required = false) String filmTitle) {
        try {
            List<Film> films = new ArrayList<Film>();

            if (filmTitle == null) {
                filmRepository.findAll().forEach(films::add);
            } else {
                filmRepository.findFilmByFilmTitle(filmTitle).forEach(films::add);
            }
            return new ResponseEntity<>(films, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
