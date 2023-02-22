package me.matvienkoeg.coursework3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.matvienkoeg.coursework3.model.Color;
import me.matvienkoeg.coursework3.model.Size;
import me.matvienkoeg.coursework3.model.SocksBatch;
import me.matvienkoeg.coursework3.services.SocksOnlineStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("socks")
@Tag(name = "API по учету носки", description = "Регистрация поступления, отпуска со склада, количества остатков, списания брака")
public class SocksOnlineStoreController {
    private SocksOnlineStoreService socksOnlineStoreService;

    public SocksOnlineStoreController(SocksOnlineStoreService socksOnlineStoreService) {
        this.socksOnlineStoreService = socksOnlineStoreService;
    }

    @PostMapping
    @Operation(summary = "Регистрация поступления товара на склад")
    @ApiResponse(responseCode = "200", description = "Удалось добавить поступление")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<SocksBatch> entrance(@RequestBody SocksBatch socksBatch) {
        socksOnlineStoreService.entrance(socksBatch);
        return ResponseEntity.ok(socksBatch);
    }

    @GetMapping
    @Operation(summary = "Возвращает количество остатков товара на складе, в соответствии с параметрами запроса")
    @ApiResponse(responseCode = "200", description = " Запрос выполнен")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Integer> getCount(@RequestParam Color color,
                                            @RequestParam Size size,
                                            @RequestParam int cottonMin,
                                            @RequestParam int cottonMax) {
        int socksCount = socksOnlineStoreService.getCount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(socksCount);
    }

    @PutMapping
    @Operation(summary = "Регистрация отпуска товара со склада")
    @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск товара со склада")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<SocksBatch> extradite(@RequestBody SocksBatch socksBatch) {
        socksOnlineStoreService.extradite(socksBatch);
        return ResponseEntity.ok(socksBatch);

    }

    @DeleteMapping
    @Operation(summary = "Регистрация списания испорченного (бракованного) товара со склада")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "200", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<SocksBatch> writeOff(@RequestBody SocksBatch socksBatch) {
        socksOnlineStoreService.writeOff(socksBatch);
        return ResponseEntity.ok(socksBatch);
    }

}
