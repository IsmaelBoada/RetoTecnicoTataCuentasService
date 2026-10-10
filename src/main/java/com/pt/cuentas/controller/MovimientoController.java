package com.pt.cuentas.controller;

import com.pt.cuentas.rest.models.ModelResponseDto;
import com.pt.cuentas.rest.models.MovimientoDto;
import com.pt.cuentas.services.operators.MovimientoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
                .body(movimientoService.save(model))
                .build();
    }


    @PutMapping("update")
    public ModelResponseDto updateData(@RequestParam UUID tokenId,
                                       @Valid @RequestBody MovimientoDto model) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(movimientoService.update(tokenId, model))
                .build();
    }


    @GetMapping("findByTokenId")
    public ModelResponseDto findByTokenId(@RequestParam UUID tokenId) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(movimientoService.findByIdToken(tokenId))
                .build();
    }

    @DeleteMapping("delete")
    public ModelResponseDto deleteData(@RequestParam UUID tokenId) {
        movimientoService.delete(tokenId);
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .build();
    }


}
