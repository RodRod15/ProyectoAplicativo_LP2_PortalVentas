package pe.edu.cibertec.portal.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.cibertec.portal.dto.UsuarioRegistroDTO;
import pe.edu.cibertec.portal.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService {
	
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public Usuario obtenerUsuarioPorId(Long id);
	
	public Usuario actualizarUsuario(Usuario usuario);
	
	public void eliminarUsuario(Long id);
	
	public List<Usuario> listarUsuarios();

}
