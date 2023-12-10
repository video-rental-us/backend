package com.igorszalas.rentalappus.film;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilmRepository extends MongoRepository<Film, String> {
   List<Film> findFilmByFilmTitle(String filmTitle);

   List<Film> findFilmByFilmTitle(String filmTitle, Sort sortParam);

}
