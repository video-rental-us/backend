package com.igorszalas.rentalappus.rental;

import java.util.List;

import org.springframework.data.domain.Sort;

public interface RentalService {
    public List<Rental> displayAllRentals();

    public List<Rental> displayAllRentals(Sort sort);

}
