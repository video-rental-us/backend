package com.igorszalas.rentalappus.rental;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igorszalas.rentalappus.utils.Utils;

@RestController
public class RentalController {
    private final RentalServiceImpl rentalService;

    public RentalController(RentalServiceImpl rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping(value = "/rentals")
    public List<Rental> getAllRentals(@RequestParam(required = false) String sort) {
        Sort sortParam = Utils.getSort(sort);
        return rentalService.displayAllRentals(sortParam);
    }

}
