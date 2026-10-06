package pe.mercadotech.pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productoId;
    private int cantidad;
    private boolean stockVerificado;
    private Long pagoId;
    private Long envioId;
    private String estado;
    private LocalDateTime fecha;

    protected Pedido() {
        // Constructor reservado para que JPA reconstruya la entidad.
    }

    public Pedido(Long productoId, int cantidad, boolean stockVerificado) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.stockVerificado = stockVerificado;
        this.estado = stockVerificado ? "CONFIRMADO" : "PENDIENTE";
        this.fecha = LocalDateTime.now();
    }

    public Pedido(Long productoId, int cantidad, Long pagoId, Long envioId, String estado) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.stockVerificado = true;
        this.pagoId = pagoId;
        this.envioId = envioId;
        this.estado = estado;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isStockVerificado() {
        return stockVerificado;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public Long getEnvioId() {
        return envioId;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
