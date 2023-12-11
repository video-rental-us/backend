package com.igorszalas.rentalappus.rental;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
    private final RentalServiceImpl rentalService;

    public RentalController(RentalServiceImpl rentalService){
        this.rentalService = rentalService;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/rentals")
    public List<Rental> getAllRentals(){
        return rentalService.displayAllRentals();
    }
   
}
