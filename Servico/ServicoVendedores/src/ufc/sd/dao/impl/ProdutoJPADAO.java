package ufc.sd.dao.impl;

import java.util.List;
import javax.persistence.Query;
import ufc.sd.dao.ProdutoDAO;
import ufc.sd.modelo.Produto;

public class ProdutoJPADAO extends GenericJPADAO<Produto> implements ProdutoDAO {
	public ProdutoJPADAO(){
		super();
		this.persistentClass = Produto.class; 
	}

	@Override
	public List<Produto> listarProdutosPorUsuario(long idUsuario) {
		try{
			Query query = em.createQuery("select p from Produto p where p.usuario.id=?1");
			query.setParameter(1, idUsuario);
			List<Produto> produtos = query.getResultList();
			return produtos;
		}catch(Exception e){
			return null;
		}
	}
}
