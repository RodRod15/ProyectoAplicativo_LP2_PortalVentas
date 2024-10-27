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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.cibertec.portal.db.MySQLDataSource;
import pe.edu.cibertec.portal.modelo.Establecimiento;
import pe.edu.cibertec.portal.servicio.EstablecimientoServicio;


@Controller
public class EstablecimientoControlador {
	
	@Autowired
	private EstablecimientoServicio servicio;

	@GetMapping("/listadoE")
	public String listarEstablecimientos(Model modelo) {

		modelo.addAttribute("establecimientos", servicio.listarEstablecimientos());
		return "listadoE";
	}
	
	@GetMapping("/listadoE/nuevo")
	public String crearEstablecimientoFormulario(Model modelo) {
		Establecimiento establecimiento = new Establecimiento();
		modelo.addAttribute("establecimiento", establecimiento);
		return "crear_establecimiento";	
	}
	
	@PostMapping("/listadoE")
	public String guardarEstablecimiento(@ModelAttribute("establecimiento") Establecimiento establecimiento) {
		servicio.guardarEstablecimiento(establecimiento);
		return "redirect:/listadoE";
	}
	
	@GetMapping("/listadoE/editar/{id}")
	public String editarEstablecimientoFormulario(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("establecimiento", servicio.obtenerEstablecimientoPorId(id));
		return "editar_establecimiento";
	}
	
	@PostMapping("/listadoE/{id}")
	String guardarEstablecimientoEditado(@PathVariable Long id, @ModelAttribute("establecimiento") Establecimiento establecimiento, Model modelo) {
		
		Establecimiento establecimientoExistente = servicio.obtenerEstablecimientoPorId(id);
		establecimientoExistente.setId(id);
		establecimientoExistente.setNombreEstablecimiento(establecimiento.getNombreEstablecimiento());
		establecimientoExistente.setDireccionEstablecimiento(establecimiento.getDireccionEstablecimiento());
		establecimientoExistente.setEstadoEstablecimiento(establecimiento.getEstadoEstablecimiento());
		establecimientoExistente.setAforoEstablecimiento(establecimiento.getAforoEstablecimiento());
		
		servicio.actualizarEstablecimiento(establecimientoExistente);
		
		return "redirect:/listadoE";
		
	}
	
	@GetMapping("/listadoE/{id}")
	public String eliminarEstablecimiento(@PathVariable Long id) {
		servicio.eliminarEstablecimiento(id);
		return "redirect:/listadoE";
	}
	
	
	@RequestMapping(value="/reporteLocalesInoperativos", method = RequestMethod.GET)
	public void Reporte(HttpServletResponse response) throws JRException, IOException{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ReporteLocalesNoOperativos.jasper");
		Map<String, Object> params = new HashMap<String, Object>();
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(is);
		Connection con = MySQLDataSource.getMySQLConnection();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=reportelocalcerrados.pdf");
		OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
	
	
	}

}
