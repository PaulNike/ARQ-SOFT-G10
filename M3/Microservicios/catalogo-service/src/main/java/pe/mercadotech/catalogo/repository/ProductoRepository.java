package pe.mercadotech.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.mercadotech.catalogo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
