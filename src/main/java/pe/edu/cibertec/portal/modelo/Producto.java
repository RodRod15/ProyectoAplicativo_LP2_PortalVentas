package pe.edu.cibertec.portal.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombreProducto;
	
	@Column(name = "tipo", nullable = false, length = 50)
	private String tipoProducto;
	
	@Column(name = "stock", nullable = false, length = 50)
	private String stockProducto;
	
	@Column(name = "precio", nullable = false, length = 50)
	private String precioProducto;
	
	public Producto() {
		
		
	}

	public Producto(Long id, String nombreProducto, String tipoProducto, String stockProducto, String precioProducto) {
		super();
		this.id = id;
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.stockProducto = stockProducto;
		this.precioProducto = precioProducto;
	}
	
	public Producto(String nombreProducto, String tipoProducto, String stockProducto, String precioProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.tipoProducto = tipoProducto;
		this.stockProducto = stockProducto;
		this.precioProducto = precioProducto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(String stockProducto) {
		this.stockProducto = stockProducto;
	}

	public String getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombreProducto=" + nombreProducto + ", tipoProducto=" + tipoProducto
				+ ", stockProducto=" + stockProducto + ", precioProducto=" + precioProducto + "]";
	}
	
	
	
}
