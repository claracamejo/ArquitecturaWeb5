package arquiweb.spring.demo.entities;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import arquiweb.spring.demo.dtos.BillReportDTO;

/**
 * 
 *  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
 *  En esta clase se mapea la Bill
 *  
 *  La native query que hicimos como jpa no toma bien el formato 
 *  sql.Date, casteamos las fechas a string 
 *
 *	La entidad de Bill, se crea de la relacion entre cliente y producto, cuando un cliente compra un producto 
 *		lo que genera es una factura donde se guardan los datos de dicha compra, en este caso decidimos separar
 *		los datos de la compra en 2, en esta entidad de guarda el Id de la factura, el cliente, la fecha y el total
 *		de la compra; y el Bill_product, se guardan los datos del producto (id_bill, id_producto, precio al momento
 *		de la compra y precio de lo que compro)
 */
@Entity
@NamedNativeQuery(
	    name = "bill_report_for_day_dto",
	    query = "SELECT b.date AS date, SUM(b.total) AS total FROM Bill b GROUP BY b.date",
	    resultSetMapping = "billReport_dto"
	)
@SqlResultSetMapping(
    name = "billReport_dto",
    classes = @ConstructorResult(
        targetClass = BillReportDTO.class,
        columns = {
            @ColumnResult(name = "date", type = String.class),
            @ColumnResult(name = "total", type = BigInteger.class)
        }
    )
)
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@ManyToOne
	@JoinColumn(name="dni")
	private Client client;
	private Date date;
	private Long total;
	
	/**
	 * Contructor default de Bill
	 */
	public Bill() {
		super();
	}
	
	/**
	 * Contructor de Bill donde inicializa la instancia con los siguientes datos:
	 * @param client: objeto @Client que hace referencia al cliente que adquiere productos
	 * @param date: fecha de la compra
	 * @param total: monto total de la compra
	 */
	public Bill(Client client, Date date, Long total) {
		super();
		this.client = client;
		this.date = date;
		this.total = total;
	}
	/**
	 * Devuelve la instancia del Cliente que realizo la compra
	 * @return Cliente que hizo la compra
	 */
	public Client getClient() {
		return client;
	}
	/**
	 * Modifica el cliente de la compra
	 * @param client: nuevo cliente de la factura
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	/**
	 * Fecha de la compra
	 * @return fecha
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Modifica la fecha de la factura
	 * @param date: nueva fecha
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Devuelve el total que se guardo de la factura
	 * @return total
	 */
	public Long getTotal() {
		return total;
	}
	/**
	 * Modifica el total de la factura, si se modifica el precio de Bill_Product relacionado
	 * a esta factura, o la cantidad de un producto se debe actualizar el valor Total de la factura
	 * 
	 * @param total: precio total de la factura
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * Clave univoca de la factura
	 * @return id de la factura
	 */
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", client=" + client + ", date=" + date + ", total=" + total + "]";
	}
}
