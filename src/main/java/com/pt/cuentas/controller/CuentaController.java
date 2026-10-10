package com.pt.cuentas.controller;

import com.pt.cuentas.rest.models.CuentaDto;
import com.pt.cuentas.rest.models.ModelResponseDto;
import com.pt.cuentas.services.operators.CuentaService;
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
@RequestMapping("cuentas")
public class CuentaController {


    private final CuentaService cuentaService;

    @PostMapping("save")
    public ModelResponseDto saveData(@Valid @RequestBody CuentaDto model) {

        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(cuentaService.save(model))
                .build();
    }


    @PutMapping("update")
    public ModelResponseDto updateData(@RequestParam UUID tokenId,
                                       @Valid @RequestBody CuentaDto model) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(cuentaService.update(tokenId, model))
                .build();
    }


    @GetMapping("findByTokenId")
    public ModelResponseDto findByTokenId(@RequestParam UUID tokenId) {
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .body(cuentaService.findByIdToken(tokenId))
                .build();
    }

    @DeleteMapping("delete")
    public ModelResponseDto deleteData(@RequestParam UUID tokenId) {
        cuentaService.delete(tokenId);
        return ModelResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .build();
    }


}
