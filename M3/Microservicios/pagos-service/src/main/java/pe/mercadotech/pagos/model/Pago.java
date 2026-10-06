package pe.mercadotech.pagos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private BigDecimal monto;
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    protected Pago() {
    }

    public Pago(Long pedidoId, BigDecimal monto) {
        this.pedidoId = pedidoId;
        this.monto = monto;
        this.estado = EstadoPago.COBRADO;
    }

    public void reversar() {
        this.estado = EstadoPago.REVERSADO;
    }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public EstadoPago getEstado() {
        return estado;
    }
}
