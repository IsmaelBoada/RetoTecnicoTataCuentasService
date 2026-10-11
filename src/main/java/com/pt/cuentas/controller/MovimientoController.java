package com.pt.cuentas.controller;

import com.pt.cuentas.rest.models.FilterModelDto;
import com.pt.cuentas.rest.models.ModelResponseDto;
import com.pt.cuentas.rest.models.MovimientoDto;
import com.pt.cuentas.services.operators.MovimientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("movimientos")
public class MovimientoController {


    private final MovimientoService movimientoService;

    @PostMapping("save")
    public ModelResponseDto saveData(@Valid @RequestBody MovimientoDto model) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(movimientoService.registroMovimiento(model))
                .build();
    }

    @GetMapping("findByTokenId")
    public ModelResponseDto findByTokenId(@RequestParam UUID tokenId) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(movimientoService.findByIdToken(tokenId))
                .build();
    }


    @GetMapping("reporte")
    public ModelResponseDto reportePaginado(FilterModelDto filter,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String field,
                                            @RequestParam(defaultValue = "1") int orderBy) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(movimientoService.obtenerReporte(filter, PageRequest.of(page, size,
                        orderBy == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, field)))
                .build();
    }
}
