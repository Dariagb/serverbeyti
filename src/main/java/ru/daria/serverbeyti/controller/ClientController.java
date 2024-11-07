package ru.daria.serverbeyti.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.model.Client;

import java.util.List;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Создать клиента")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Клиент не создан")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        client=clientService.createClient(client);
                return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить список клиентов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientService.readAllClient(),HttpStatus.OK);
    }

    @Operation(summary = "Удалить клиента по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно "),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientsById(id);
        return ResponseEntity.noContent().build();
    }


}
