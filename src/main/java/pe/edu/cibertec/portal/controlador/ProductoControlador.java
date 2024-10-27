package pe.edu.cibertec.portal.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.edu.cibertec.portal.db.MySQLDataSource;
import pe.edu.cibertec.portal.modelo.Producto;
import pe.edu.cibertec.portal.servicio.ProductoServicio;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ProductoControlador {

	@Autowired
	private ProductoServicio servicio;

	@GetMapping("/listadoP")
	public String listarProductos(Model modelo) {

		modelo.addAttribute("productos", servicio.listarProductos());
		return "listadoP";
	}
	
	@GetMapping("/listadoP/nuevo")
	public String crearProductoFormulario(Model modelo) {
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);
		return "crear_producto";	
	}
	
	@PostMapping("/listadoP")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		servicio.guardarProducto(producto);
		return "redirect:/listadoP";
	}
	
	@GetMapping("/listadoP/editar/{id}")
	public String editarProductoFormulario(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("producto", servicio.obtenerProductoPorId(id));
		return "editar_producto";
	}
	
	@PostMapping("/listadoP/{id}")
	public String guardarProductoEditado(@PathVariable Long id, @ModelAttribute("producto") Producto producto, Model modelo) {
		
		Producto productoExistente = servicio.obtenerProductoPorId(id);
		productoExistente.setId(id);
		productoExistente.setNombreProducto(producto.getNombreProducto());
		productoExistente.setTipoProducto(producto.getTipoProducto());
		productoExistente.setStockProducto(producto.getStockProducto());
		productoExistente.setPrecioProducto(producto.getPrecioProducto());
		
		servicio.actualizarProducto(productoExistente);
		
		return "redirect:/listadoP";
		
	}
	
	@GetMapping("/listadoP/{id}")
	public String eliminarProducto(@PathVariable Long id) {
		servicio.eliminarProducto(id);
		return "redirect:/listadoP";
	}
	
	@RequestMapping(value="/reporteSinStock", method = RequestMethod.GET)
	public void Reporte(HttpServletResponse response) throws JRException, IOException{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ReporteSinStock.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(is);
		Connection con = MySQLDataSource.getMySQLConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=reportesinstock.pdf");
		OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
	
	
	}
	
	
	

}
