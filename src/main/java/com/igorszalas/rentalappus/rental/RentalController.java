package com.igorszalas.rentalappus.rental;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igorszalas.rentalappus.utils.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class RentalController {
    private final RentalServiceImpl rentalService;
    private final RentalRepository rentalRepository;

    public RentalController(RentalServiceImpl rentalService, RentalRepository rentalRepository) {
        this.rentalService = rentalService;
        this.rentalRepository = rentalRepository;
    }

    @GetMapping(value = "/rentals")
    public List<Rental> getAllRentals(@RequestParam(required = false) String sort) {
        if (sort != null && !sort.isEmpty() && !sort.isEmpty()) {
            Sort sortValue = Utils.getSort(sort);
            return rentalService.displayAllRentals(sortValue);
        }
        return rentalService.displayAllRentals();
    }

    @PostMapping("/rentals")
    public ResponseEntity<List<Rental>> createRent(@RequestBody(required = true) Rental rental) {
        List<Rental> rentals = this.rentalService.displayAllRentals();

        boolean isBorrowed = false;
        for (int i = 0; i < rentals.size(); i++) {
            Rental rent = rentals.get(i);
            if (rent.getFilmTitle().equals(rental.getFilmTitle())) {
                isBorrowed = true;
            }
        }

        if (isBorrowed) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } else {
            this.rentalRepository.save(rental);
            List<Rental> rents = new ArrayList<Rental>();
            rents.add(rental);
            return new ResponseEntity<>(rents, HttpStatus.OK);
        }
    }

}
