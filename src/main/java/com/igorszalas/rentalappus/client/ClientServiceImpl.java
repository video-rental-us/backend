package com.igorszalas.rentalappus.client;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    
    @Override
    public List<Client> displayAllClients(){
        return clientRepository.findAll();
    }
}
