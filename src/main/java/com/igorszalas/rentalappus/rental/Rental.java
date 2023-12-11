package com.igorszalas.rentalappus.rental;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection="wypozyczenia")
public class Rental {
    @Id
    private String ID;

    @Field(name = "dane_klienta")
    private String userData;

    @Field(name = "tytul_filmu")
    private String filmTitle;

    @Field(name = "data_wypozyczenia")
    private String rentalDate;

    @Field(name = "data_planowanego_zwrotu")
    private String plannedReturnDate;
    
    @Field(name = "data_faktycznego_zwrotu")
    private String finalReturnDate;

}
