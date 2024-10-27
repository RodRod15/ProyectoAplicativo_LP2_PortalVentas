package pe.edu.cibertec.portal.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.portal.modelo.Establecimiento;

@Repository
public interface EstablecimientoRepositorio extends JpaRepository<Establecimiento, Long>{

}
