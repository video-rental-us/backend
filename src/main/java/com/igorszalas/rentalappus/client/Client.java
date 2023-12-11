package com.igorszalas.rentalappus.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "klienci")
public class Client {
    @Id
    private String id;

    @Field(name = "imie")
    private String firstName;

    @Field(name = "nazwisko")
    private String lastName;

    @Field(name = "adres")
    private String homeAddress;

    @Field(name = "telefon")
    private String phoneNumber;
    
    @Field(name = "data_rejestracji")
    private String registerDate;
}
