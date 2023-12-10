package com.igorszalas.rentalappus.rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RentalServiceTest {
  @Mock
  RentalRepository rentalRepository;

  @Test
  void displayAllRentals() {
    Rental rental = new Rental("6574ef21f22bd98848ba25a4", "Kacper Malachowski", "Chłopi", "2023-01-01T12:43:00Z",
        "2023-01-04T12:43:00Z", "2023-01-05T12:43:00Z");
    List<Rental> data = new ArrayList<Rental>() {
      {
        add(rental);
      }
    };
    Mockito.when(rentalRepository.findAll()).thenReturn(data);
    RentalService service = new RentalServiceImpl(rentalRepository);

    List<Rental> rentals = service.displayAllRentals();

    assertEquals(rentals, data);

  }
}
