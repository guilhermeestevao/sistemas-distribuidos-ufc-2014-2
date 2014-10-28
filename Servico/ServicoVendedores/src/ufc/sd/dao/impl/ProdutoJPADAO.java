package ufc.sd.dao.impl;

import ufc.sd.dao.ProdutoDAO;
import ufc.sd.modelo.Produto;

public class ProdutoJPADAO extends GenericJPADAO<Produto> implements ProdutoDAO {
	public ProdutoJPADAO(){
		super();
		this.persistentClass = Produto.class; 
	}
}
