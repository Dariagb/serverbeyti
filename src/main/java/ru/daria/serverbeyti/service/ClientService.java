package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ClientsRepository;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientsRepository clientsRepository;

    public Client createClient(Client client) {
        return clientsRepository.save(client);
    }

    public List<Client> readAllClient() {
        return clientsRepository.findAll();
    }

    public void deleteClientsById(Long id) {
       clientsRepository.deleteById(id);
    }

}
