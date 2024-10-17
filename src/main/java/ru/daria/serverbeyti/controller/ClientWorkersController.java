package ru.daria.serverbeyti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daria.serverbeyti.dao.ClientsRepository;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.exeption.NotFoundException;
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientWorkersController {

    private final ClientsRepository clientsRepository;
    private final WorkersRepository workersRepository;

    @Operation(summary = " Получить список работников, связанных с конкретным клиентом, по идентификатору")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Работник не найден")
    })
    @GetMapping("/{clientId}/workers")
    public ResponseEntity<List<Workers>> getWorkersByClientId(@PathVariable Long clientId) {
        List<Workers> workers = clientsRepository.getWorkersById(clientId);
        return ResponseEntity.ok(workers);
    }

    @Operation(summary = " Получить список клиентов, связанных с конкретным работником")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/workers/{workerId}/clients")
    public ResponseEntity<List<Client>> getClientsByWorkerId(@PathVariable Long workerId) {

        Workers worker = workersRepository.findById(workerId).orElseThrow(() -> new NotFoundException("Worker not found"));
        List<Client> clients = worker.getClients();

        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Поиск клиентов в базе данных по имени")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Client>> findClientsByName(@PathVariable String name) {
        List<Client> clients = clientsRepository.findByName(name);
        return ResponseEntity.ok(clients);
    }
}


