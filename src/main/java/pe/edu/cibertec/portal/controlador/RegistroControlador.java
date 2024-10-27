package pe.edu.cibertec.portal.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.edu.cibertec.portal.modelo.Usuario;
import pe.edu.cibertec.portal.servicio.UsuarioServicio;


@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;

	@GetMapping("/login")
	public String iniciarSesion() {

		return "login";

	}

	@GetMapping("/index")
	public String verPaginaDeInicio() {
		return "index";

	}

	@GetMapping("/listadoU")
	//@PostMapping("/listadoU")
	public String verListadoUsuarios(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "listadoU";

	}

	@GetMapping("/listadoU/editar/{id}")
	public String verFormularioEdicion(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("usuario", servicio.obtenerUsuarioPorId(id));
		return "editar_usuario";

	}

	@PostMapping("/listadoU/{id}")
	public String actualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") Usuario usuario, Model modelo) {

		Usuario usuarioExistente = servicio.obtenerUsuarioPorId(id);
		usuarioExistente.setId(id);
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setApellido(usuario.getApellido());
		usuarioExistente.setEmail(usuario.getEmail());
		//usuarioExistente.setRol(usuario.getRol());

		servicio.actualizarUsuario(usuarioExistente);
		return "redirect:/listadoU";
	}

	@GetMapping("/listadoU/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		servicio.eliminarUsuario(id);
		return "redirect:/listadoU";
	}

}
