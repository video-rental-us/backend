package com.igorszalas.rentalappus.film;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mongodb.MongoSocketOpenException;

@SpringBootTest
public class FilmServiceTest {
  @Mock
  FilmRepository filmRepository;
  Film[] films = new Film[] {
      Film.builder()
          .id("6575720801b96473c7285b7c")
          .filmTitle("Czaas krwawego ksiezyca")
          .director("Martin Scorsese")
          .actors("Leonardo DiCaprio")
          .filmGenre("Kryminał")
          .build(),
      Film.builder()
          .id("6575732d0ef13c1c7a8bc2ab")
          .filmTitle("Służące")
          .director("Tate Taylor")
          .actors("Emma Stone")
          .filmGenre("Dramat")
          .build(),
      Film.builder()
          .id("6575733bb5dd0d1dd47aeaa1")
          .filmTitle("Na skraju jutra")
          .director("Doug Liman")
          .actors("Tom Cruise")
          .filmGenre("Sci-Fi")
          .build()
  };

  @Test
  void displayAllFilms() {
    List<Film> filmList = Arrays.stream(films).toList();
    Mockito.when(filmRepository.findAll()).thenReturn(filmList);
    FilmService service = new FilmServiceImpl(filmRepository);

    List<Film> videos = service.displayAllFilms();

    assertEquals(videos, filmList);
  }

  @Test
  void getFilmByFilmTitleReturnAllFilmsWhenNoTitleWithStatusOK() {
    List<Film> filmList = Arrays.stream(films).toList();
    Mockito.when(filmRepository.findAll()).thenReturn(filmList);
    FilmService service = new FilmServiceImpl(filmRepository);

    ResponseEntity<List<Film>> videosResponse = service.getFilmByFilmTitle(null);
    List<Film> body = videosResponse.getBody();

    assertEquals(videosResponse.getStatusCode(), HttpStatus.OK);
    assertNotNull(body);
    assertEquals(filmList, body);
  }

  @Test
  void getFilmByFilmTitleReturnFilmWhenTitleWithStatusOK() {
    List<Film> filmList = Arrays.stream(new Film[] { films[0] }).toList();
    Mockito.when(filmRepository.findFilmByFilmTitle(anyString())).thenReturn(filmList);
    FilmService service = new FilmServiceImpl(filmRepository);

    ResponseEntity<List<Film>> videosResponse = service.getFilmByFilmTitle("Czas krwawego ksiezyca");
    List<Film> body = videosResponse.getBody();

    assertEquals(videosResponse.getStatusCode(), HttpStatus.OK);
    assertNotNull(body);
    assertEquals(filmList, body);
  }

  @Test
  void getFilmByFilmTitleReturnInternalServerErrorOnRepositoryException() {
    Mockito.when(filmRepository.findAll()).thenThrow(new MongoSocketOpenException(null, null));
    FilmService service = new FilmServiceImpl(filmRepository);

    ResponseEntity<List<Film>> videosResponse = service.getFilmByFilmTitle(null);

    assertEquals(videosResponse.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
