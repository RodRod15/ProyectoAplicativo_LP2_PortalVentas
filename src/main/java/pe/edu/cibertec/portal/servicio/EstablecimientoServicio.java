package pe.edu.cibertec.portal.servicio;

import java.util.List;

import pe.edu.cibertec.portal.modelo.Establecimiento;

public interface EstablecimientoServicio {
	
	public List<Establecimiento> listarEstablecimientos();
	
	public Establecimiento guardarEstablecimiento(Establecimiento establecimiento);
	
	public Establecimiento obtenerEstablecimientoPorId(Long id);
	
	public Establecimiento actualizarEstablecimiento(Establecimiento establecimiento);
	
	public void eliminarEstablecimiento(Long id);

}
