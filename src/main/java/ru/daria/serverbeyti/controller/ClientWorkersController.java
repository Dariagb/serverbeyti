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
import ru.daria.serverbeyti.model.Client;
import ru.daria.serverbeyti.model.Workers;
import ru.daria.serverbeyti.service.ClientService;
import ru.daria.serverbeyti.service.WorkersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientWorkersController {

    private final ClientService clientService;
    private final WorkersService workerService;

    @Operation(summary = " Получить список работников, связанных с конкретным клиентом, по идентификатору")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Работник не найден")
    })
    @GetMapping("/{clientId}")
    public ResponseEntity<List<Workers>> getWorkersByClientId(@PathVariable Long clientId) {
        List<Workers> workers = clientService.getWorkers(clientId);
        if (workers == null || workers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workers);
    }

    @Operation(summary = " Получить список клиентов, связанных с конкретным работником")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/workers/{workerId}/clients")
    public ResponseEntity<List<Client>> getClientsByWorkerId(@PathVariable Long workerId) {
        Optional<Workers> worker = workerService.findByIdWorker(workerId);
        if (worker.isPresent()) {
            List<Client> clients = worker.get().getClients();
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Поиск клиентов в базе данных по имени")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Client>> findClientsByName(@PathVariable String name) {
        List<Client> clients = clientService.findByNameClient(name);
        return ResponseEntity.ok(clients);
    }
}


