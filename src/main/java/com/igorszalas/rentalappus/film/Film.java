package com.igorszalas.rentalappus.film;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "filmy")
public class Film {

    @Id
    private String id;

    @Field(name = "tytul_filmu")
    private String filmTitle;

    @Field(name = "gatunek")
    private String filmGenre;

    @Field(name = "rezyser")
    private String director;

    @Field(name = "czas_trwania")
    private String plannedReturnDate;

    @Field(name = "ocena")
    private String rating;

    @Field(name = "opis")
    private String finalReturnDate;

    @Field(name = "aktorzy")
    private String actors;

    @Field(name = "data_dodania")
    private String videoAdditionDate;

    public String getID(){
        return id;
    }

    public void setID(String ID){
        this.id = ID;
    }

    public String getFilmTitle(){
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle){
        this.filmTitle = filmTitle;
    }

    public String getFilmGenre(){
        return filmGenre;
    }
    
    public void getFilmGenre(String filmGenre){
        this.filmGenre = filmGenre;
    }

    public String getDirector(){
        return director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public String getPlannedReturnDate(){
        return plannedReturnDate;
    }

    public void setPlannedReturnDate(String plannedReturnDate){
        this.plannedReturnDate = plannedReturnDate;
    }

    public Film() {
    }
}
