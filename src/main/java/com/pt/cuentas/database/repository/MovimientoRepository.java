package com.pt.cuentas.database.repository;

import com.pt.cuentas.database.models.Movimiento;
import com.pt.cuentas.database.projection.MovimientoReporteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Optional<Movimiento> findByTokenId(UUID tokenId);


    @Query(
            value = """
                    SELECT
                        m.fecha AS "fecha",
                        cr.nombre AS "cliente",
                        c.numero_cuenta AS "numeroCuenta",
                        c.tipo_cuenta AS "tipoCuenta",
                        c.saldo_inicial AS "saldoInicial",
                        c.estado AS "estado",
                        m.tipo_movimiento AS "tipoMovimiento",
                        m.valor AS "valorMovimiento",
                        c.saldo_disponible AS "saldoDisponible"
                    FROM movimiento m
                    INNER JOIN cuenta c
                        ON c.id = m.cuenta_id
                    INNER JOIN cliente_referencia cr
                        ON cr.token_id = c.token_id_cliente
                    WHERE m.fecha >= CAST(:fechaInicio AS date)
                      AND m.fecha < CAST(:fechaFin AS date) + INTERVAL '1 day'
                      AND (
                          CAST(:tokenIdCliente AS uuid) IS NULL
                          OR c.token_id_cliente = CAST(:tokenIdCliente AS uuid)
                      )
                    ORDER BY m.fecha DESC
                    """,
            countQuery = """
                    SELECT COUNT(*)
                    FROM movimiento m
                    INNER JOIN cuenta c
                        ON c.id = m.cuenta_id
                    INNER JOIN cliente_referencia cr
                        ON cr.token_id = c.token_id_cliente
                    WHERE m.fecha >= CAST(:fechaInicio AS date)
                      AND m.fecha < CAST(:fechaFin AS date) + INTERVAL '1 day'
                      AND (
                          CAST(:tokenIdCliente AS uuid) IS NULL
                          OR c.token_id_cliente = CAST(:tokenIdCliente AS uuid)
                      )
                    """,
            nativeQuery = true
    )
    Page<MovimientoReporteProjection> buscarReporte(@Param("fechaInicio") LocalDate fechaInicio,
                                                    @Param("fechaFin") LocalDate fechaFin,
                                                    @Param("tokenIdCliente") UUID tokenIdCliente,
                                                    Pageable pageable
    );


}
