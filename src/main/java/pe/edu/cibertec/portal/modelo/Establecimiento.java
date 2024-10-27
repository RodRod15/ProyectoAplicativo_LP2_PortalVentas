package pe.edu.cibertec.portal.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "establecimientos")
public class Establecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombreEstablecimiento;
	
	@Column(name = "direccion", nullable = false, length = 50)
	private String direccionEstablecimiento;
	
	@Column(name = "estado", nullable = false, length = 50)
	private String estadoEstablecimiento;
	
	@Column(name = "aforo", nullable = false, length = 50)
	private String aforoEstablecimiento;
	
	public Establecimiento() {
		
		
	}

	public Establecimiento(Long id, String nombreEstablecimiento, String direccionEstablecimiento,
			String estadoEstablecimiento, String aforoEstablecimiento) {
		super();
		this.id = id;
		this.nombreEstablecimiento = nombreEstablecimiento;
		this.direccionEstablecimiento = direccionEstablecimiento;
		this.estadoEstablecimiento = estadoEstablecimiento;
		this.aforoEstablecimiento = aforoEstablecimiento;
	}

	public Establecimiento(String nombreEstablecimiento, String direccionEstablecimiento, String estadoEstablecimiento,
			String aforoEstablecimiento) {
		super();
		this.nombreEstablecimiento = nombreEstablecimiento;
		this.direccionEstablecimiento = direccionEstablecimiento;
		this.estadoEstablecimiento = estadoEstablecimiento;
		this.aforoEstablecimiento = aforoEstablecimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}

	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}

	public String getDireccionEstablecimiento() {
		return direccionEstablecimiento;
	}

	public void setDireccionEstablecimiento(String direccionEstablecimiento) {
		this.direccionEstablecimiento = direccionEstablecimiento;
	}

	public String getEstadoEstablecimiento() {
		return estadoEstablecimiento;
	}

	public void setEstadoEstablecimiento(String estadoEstablecimiento) {
		this.estadoEstablecimiento = estadoEstablecimiento;
	}

	public String getAforoEstablecimiento() {
		return aforoEstablecimiento;
	}

	public void setAforoEstablecimiento(String aforoEstablecimiento) {
		this.aforoEstablecimiento = aforoEstablecimiento;
	}

	@Override
	public String toString() {
		return "Establecimiento [id=" + id + ", nombreEstablecimiento=" + nombreEstablecimiento
				+ ", direccionEstablecimiento=" + direccionEstablecimiento + ", estadoEstablecimiento="
				+ estadoEstablecimiento + ", aforoEstablecimiento=" + aforoEstablecimiento + "]";
	}
	
	

}
