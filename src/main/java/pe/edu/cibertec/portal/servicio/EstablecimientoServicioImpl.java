package pe.edu.cibertec.portal.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.portal.modelo.Establecimiento;
import pe.edu.cibertec.portal.repositorio.EstablecimientoRepositorio;


@Service
public class EstablecimientoServicioImpl implements EstablecimientoServicio {
	
	@Autowired
	private EstablecimientoRepositorio repositorio;

	@Override
	public List<Establecimiento> listarEstablecimientos() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Establecimiento guardarEstablecimiento(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		return repositorio.save(establecimiento);
	}

	@Override
	public Establecimiento obtenerEstablecimientoPorId(Long id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).get();
	}

	@Override
	public Establecimiento actualizarEstablecimiento(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		return repositorio.save(establecimiento);
	}

	@Override
	public void eliminarEstablecimiento(Long id) {
		// TODO Auto-generated method stub
		repositorio.deleteById(id);
	}

}
