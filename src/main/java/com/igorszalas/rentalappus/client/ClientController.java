package com.igorszalas.rentalappus.client;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientServiceImpl clientService;
    private ClientRepository clientRepository;

    public ClientController(ClientServiceImpl clientService, ClientRepository clientRepository){
        this.clientService = clientService;
        this.clientRepository =clientRepository;
    }
    
    @GetMapping
    public List<Client> getAllClients(){
        return clientService.displayAllClients();
    }

    @PostMapping(value="/add-client")
    public ResponseEntity<Client> addClient(@RequestBody(required=true) Client client){
        try{
            client = clientRepository.save(client);
            return new ResponseEntity<Client>(client, HttpStatus.CREATED);
        }
        catch(Exception exception){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/delete-client")
    public ResponseEntity<HttpStatus> deleteClient(@RequestParam(required=true) String id){
        try{
            clientRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/edit-client")
    public ResponseEntity<Client> editClient(@RequestParam(required=true) String id, @RequestBody(required=true) Client client){
        Optional<Client> clientData = clientRepository.findById(id);
        try{
            if(clientData.isPresent()){
                Client editClient = clientData.get();
                editClient.setFirstName(client.getFirstName());
                editClient.setLastName(client.getLastName());
                editClient.setHomeAddress(client.getHomeAddress());
                editClient.setPhoneNumber(client.getPhoneNumber());
                editClient.setRegisterDate(client.getRegisterDate());
                return new ResponseEntity<>(clientRepository.save(editClient), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
