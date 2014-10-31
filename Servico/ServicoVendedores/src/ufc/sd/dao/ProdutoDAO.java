package ufc.sd.dao;

import java.util.List;

import ufc.sd.modelo.Produto;

public interface ProdutoDAO extends GenericDAO<Produto> {

	public List<Produto> listarProdutosPorUsuario(long idUsuario);
	
}
