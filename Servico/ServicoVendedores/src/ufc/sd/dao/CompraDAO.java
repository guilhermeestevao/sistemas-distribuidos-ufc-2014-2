package ufc.sd.dao;

import java.util.List;

import ufc.sd.modelo.Compra;

public interface CompraDAO extends GenericDAO<Compra> {
	
	public List<Compra> listarComprasDoUsuario(long idUsuario);
	public List<Compra> listarVendasDoUsuario(long idUsuario);
	
}
