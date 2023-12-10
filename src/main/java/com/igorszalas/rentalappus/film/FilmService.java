package com.igorszalas.rentalappus.film;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface FilmService {
    public List<Film> displayAllFilms();

    public List<Film> displayAllFilms(Sort sort);

    public List<Film> displayFilmBySearchPhrase(String searchPhrase);

    public ResponseEntity<List<Film>> getFilmByFilmTitle(String filmTitle);

}
