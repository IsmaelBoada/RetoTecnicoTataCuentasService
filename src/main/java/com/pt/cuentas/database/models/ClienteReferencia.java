package com.pt.cuentas.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteReferencia {

    @Id
    @Column(name = "token_id", nullable = false, updatable = false)
    private UUID tokenId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String identificacion;

}
