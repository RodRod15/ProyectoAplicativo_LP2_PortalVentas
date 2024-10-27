package pe.edu.cibertec.portal.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.portal.dto.UsuarioRegistroDTO;
import pe.edu.cibertec.portal.modelo.Rol;
import pe.edu.cibertec.portal.modelo.Usuario;
import pe.edu.cibertec.portal.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private UsuarioRepositorio usuarioRepositorio;

	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {

		super();
		this.usuarioRepositorio = usuarioRepositorio;

	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), registroDTO.getEmail(),
				registroDTO.getRol(), passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol(registroDTO.getRol())));

		return usuarioRepositorio.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Credenciales inválidas");
		}

		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());

	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		return usuarioRepositorio.findById(id).get();
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioRepositorio.deleteById(id);		
	}

}
