package ru.daria.serverbeyti.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.model.Workers;
import ru.daria.serverbeyti.service.WorkersService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workers")
@Tag(name = "workers api", description = "управление работой мастеров.")
public class WorkersController {

    private final WorkersService workersServise;
    private final WorkersRepository workersRepository;

    @Operation(summary = "Создает работника")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "не найден")
    })
    @PostMapping
    public ResponseEntity<Workers> createWorker(@RequestBody Workers workers) {
        workers=workersServise.createWorkers(workers);
        return new ResponseEntity<>(workers, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить всех работников", description = "Возвращает всех работников")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Работники не найдены")
    })
    @GetMapping
    public ResponseEntity<List<Workers>> readAllWorkers() {
        return new ResponseEntity<>(workersServise.readAllWorkers(), HttpStatus.OK);
    }

    @Operation(summary = "Получить работников по должности", description = "Возвращает фамилию и имя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Должность отсутствует")
    })
    @GetMapping("/post/{post}")
    public ResponseEntity<List<Workers>> getWorkersByPost(@PathVariable String post) {
        List<Workers> workers = workersServise.getWorkersByPost(post);

        if (workers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workers);
        }
    }

    @Operation(summary = "Получить работников по фамилии", description = "Возвращает имя и должность")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Такой работник отсутствует")
    })
    @GetMapping("/surname/{surname}")
    public ResponseEntity<Workers> getWorkersBySurname(@PathVariable String surname) {
        Optional<Workers> worker = workersServise.getWorkersBySurname(surname);

        return worker.map(p -> status(HttpStatus.OK).body(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удалить работника", description = "Удаляем работника по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно удален"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        workersServise.deleteWorkersById(id);
        return ResponseEntity.noContent().build();
    }
}
