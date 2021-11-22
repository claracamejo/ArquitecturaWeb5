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

import arquiweb.spring.demo.dtos.BillReportDTO;

/**
 * 
 *  Abstrae el formato de información de la clase sin depender de la base de datos que tenga  asociada
 *  En esta clase se mapea la Bill
 *  
 *  La native query que hicimos como jpa no toma bien el formato 
 *  sql.Date, casteamos las fechas a string 
 *
 *  Decidimos crear una tabla Factura donde esta guardara el dato del Cliente y con su ID desde Factura_Producto
 *  completamos los datos de una venta completa.  
 *
 *
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
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@ManyToOne
	@JoinColumn(name="dni")
	private Client client;
	private Date date;
	private Long total;
	
	
	public Bill() {
		super();
	}

	public Bill(Client client, Date date, Long total) {
		super();
		this.client = client;
		this.date = date;
		this.total = total;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", client=" + client + ", date=" + date + ", total=" + total + "]";
	}
}
