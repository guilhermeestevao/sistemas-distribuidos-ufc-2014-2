package ufc.sd.dao;

import java.util.List;

import ufc.sd.modelo.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
	public List<Compra> listarComprasDoVendedor(long idComprador);
	public List<Compra> listarVendasDoVendedor(long idVendedor);
}
