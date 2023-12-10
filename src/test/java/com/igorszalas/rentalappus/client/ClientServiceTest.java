package com.igorszalas.rentalappus.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {
  @Mock
  ClientRepository clientRepository;

  @Test
  void displayAllClients() {
    Client client = new Client("6574ec43d8afe517f4d5da5c", "Igor", "Shelter", "śląska 5",
        "123456789", "2015-05-01T05:00:00Z");
    List<Client> data = new ArrayList<Client>() {
      {
        add(client);
      }
    };
    Mockito.when(clientRepository.findAll()).thenReturn(data);
    ClientService service = new ClientServiceImpl(clientRepository);

    List<Client> clients = service.displayAllClients();

    assertEquals(clients, data);
  }
}
