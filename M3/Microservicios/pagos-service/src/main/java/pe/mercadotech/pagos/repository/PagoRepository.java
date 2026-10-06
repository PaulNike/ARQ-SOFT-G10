package pe.mercadotech.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.mercadotech.pagos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
