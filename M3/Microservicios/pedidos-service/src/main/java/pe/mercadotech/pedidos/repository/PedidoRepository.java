package pe.mercadotech.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.mercadotech.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
