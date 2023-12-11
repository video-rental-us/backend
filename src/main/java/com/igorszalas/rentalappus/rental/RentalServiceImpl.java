package com.igorszalas.rentalappus.rental;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public List<Rental> displayAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public List<Rental> displayAllRentals(Sort sort) {
        return rentalRepository.findAll(sort);
    }
}
