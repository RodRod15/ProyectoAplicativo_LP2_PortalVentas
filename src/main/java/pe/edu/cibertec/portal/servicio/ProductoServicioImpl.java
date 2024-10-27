package pe.edu.cibertec.portal.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.portal.modelo.Producto;
import pe.edu.cibertec.portal.repositorio.ProductoRepositorio;

@Service
public class ProductoServicioImpl implements ProductoServicio {

	@Autowired
	private ProductoRepositorio repositorio;

	@Override
	public List<Producto> listarProductos() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		return repositorio.save(producto);
	}

	@Override
	public Producto obtenerProductoPorId(Long id) {
		return repositorio.findById(id).get();

	}

	@Override
	public Producto actualizarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return repositorio.save(producto);
	}

	@Override
	public void eliminarProducto(Long id) {
		repositorio.deleteById(id);

	}

}
