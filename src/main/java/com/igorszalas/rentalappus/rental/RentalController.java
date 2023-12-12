package com.igorszalas.rentalappus.rental;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igorszalas.rentalappus.utils.Utils;

@CrossOrigin
@RestController
public class RentalController {
    private final RentalServiceImpl rentalService;

    public RentalController(RentalServiceImpl rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping(value = "/rentals")
    public List<Rental> getAllRentals(@RequestParam(required = false) String sort) {
        if (sort != null && !sort.isEmpty() && !sort.isEmpty()) {
            Sort sortValue = Utils.getSort(sort);
            return rentalService.displayAllRentals(sortValue);
        }
        return rentalService.displayAllRentals();
    }

}
