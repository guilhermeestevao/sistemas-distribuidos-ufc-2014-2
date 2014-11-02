package ufc.sd.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="compras")
@XmlRootElement
public class Compra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idVendedor;
	private long idCOmprador;
	private long idProduto;
	private double valorVenda;
	private double quantidadeProduto;
	
	public long getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}
	public long getIdCOmprador() {
		return idCOmprador;
	}
	public void setIdCOmprador(long idCOmprador) {
		this.idCOmprador = idCOmprador;
	}
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public double getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(double quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCOmprador ^ (idCOmprador >>> 32));
		result = prime * result + (int) (idProduto ^ (idProduto >>> 32));
		result = prime * result + (int) (idVendedor ^ (idVendedor >>> 32));
		long temp;
		temp = Double.doubleToLongBits(quantidadeProduto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorVenda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (idCOmprador != other.idCOmprador)
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (idVendedor != other.idVendedor)
			return false;
		if (Double.doubleToLongBits(quantidadeProduto) != Double
				.doubleToLongBits(other.quantidadeProduto))
			return false;
		if (Double.doubleToLongBits(valorVenda) != Double
				.doubleToLongBits(other.valorVenda))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Compra [idVendedor=" + idVendedor + ", idCOmprador="
				+ idCOmprador + ", idProduto=" + idProduto + ", valorVenda="
				+ valorVenda + ", quantidadeProduto=" + quantidadeProduto + "]";
	}
	
	
	
}
