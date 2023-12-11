package com.igorszalas.rentalappus.rental;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalRepository extends MongoRepository<Rental, String>{
    
}
