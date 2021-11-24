package arquiweb.spring.demo.dtos;

import java.math.BigInteger;

/**
* Este DTO nace de la necesidad de mostrar determinados datos 
* que son el resultado de la nativeQuery bill_report_for_day_dto
*/

public class BillReportDTO {

	private String date;
	private BigInteger total;
	
	/**
	 * @param date
	 * @param total
	 * 
	 * Constructor de la clase BillReportDTO
	 * 
	 */
	public BillReportDTO(String date, BigInteger total) {
		this.date = date;
		this.total = total;
	}
	
	/**
	 * @return date
	 * Retorna la fecha la clase
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @param date
	 * Modifca el parametro date de la clase
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return total
	 * Retorna el total de la clase que hace referencia al total del saldo
	 */
	public BigInteger getTotal() {
		return total;
	}
	
	/**
	 * @param total
	 * Modifica el parametro total de la clase que hace refencia al total 
	 * del saldo
	 */
	public void setTotal(BigInteger total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "BillReportDTO [date=" + date + ", total=" + total + "]";
	}
	
}
