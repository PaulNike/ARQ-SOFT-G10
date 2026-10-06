package pe.mercadotech.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.mercadotech.catalogo.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
