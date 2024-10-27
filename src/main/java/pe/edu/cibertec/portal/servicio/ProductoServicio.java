package pe.edu.cibertec.portal.servicio;

import java.util.List;

import pe.edu.cibertec.portal.modelo.Producto;

public interface ProductoServicio {
	
	public List<Producto> listarProductos();
	
	public Producto guardarProducto(Producto producto);
	
	public Producto obtenerProductoPorId(Long id);
	
	public Producto actualizarProducto(Producto producto);
	
	public void eliminarProducto(Long id);

}
