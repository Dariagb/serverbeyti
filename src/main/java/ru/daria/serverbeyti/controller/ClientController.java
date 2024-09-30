package ru.daria.serverbeyti.controller;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.model.Client;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        client=clientService.createClient(client);
                return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.readAllClient(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientsById(id);
        return ResponseEntity.noContent().build();
    }


}
