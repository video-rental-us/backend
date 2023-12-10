package com.igorszalas.rentalappus.film;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igorszalas.rentalappus.utils.Utils;

@CrossOrigin
@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmServiceImpl filmService;
    private FilmRepository filmRepository;

    public FilmController(FilmServiceImpl filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public List<Film> getAllFilms(@RequestParam(required = false) String sort) {
        if (!sort.isEmpty() && !sort.isEmpty()) {
            Sort sortValue = Utils.getSort(sort);
            return filmService.displayAllFilms(sortValue);
        }
        return filmService.displayAllFilms();
    }

    @GetMapping(value = "/findByTitle")
    public ResponseEntity<List<Film>> getSearchedFilms(@RequestParam(required = false) String filmTitle,
            @RequestParam(required = false) String sort) {

        try {
            Sort sortParam = Utils.getSort(sort);
            List<Film> films = new ArrayList<Film>();

            if (filmTitle == null) {
                films = filmRepository.findAll(sortParam);
            } else {
                films = filmRepository.findFilmByFilmTitle(filmTitle, sortParam);
            }
            return new ResponseEntity<>(films, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add-film")
    public ResponseEntity<Film> createFilm(@RequestBody(required = true) Film film) {
        try {
            film = filmRepository.save(film);
            return new ResponseEntity<>(film, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete-film")
    public ResponseEntity<HttpStatus> deleteFilms(@RequestParam(required = true) String id) {
        try {
            filmRepository.deleteById(id);
            System.out.println(id + "is deleted!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/edit-film")
    public ResponseEntity<Film> updateFilm(@RequestParam(required = true) String id,
            @RequestBody(required = true) Film film) {
        try {
            Optional<Film> filmData = filmRepository.findById(id);
            if (filmData.isPresent()) {
                Film editFilm = filmData.get();
                editFilm.setFilmTitle(film.getFilmTitle());
                editFilm.setFilmGenre(film.getFilmGenre());
                editFilm.setDirector(film.getDirector());
                editFilm.setPlannedReturnDate(film.getPlannedReturnDate());
                editFilm.setRating(film.getRating());
                editFilm.setFinalReturnDate(film.getFinalReturnDate());
                editFilm.setActors(film.getActors());
                editFilm.setVideoAdditionDate(film.getVideoAdditionDate());
                return new ResponseEntity<>(filmRepository.save(editFilm), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
