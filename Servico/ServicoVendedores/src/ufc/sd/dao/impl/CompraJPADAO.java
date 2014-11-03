package ufc.sd.dao.impl;

import java.util.List;

import javax.persistence.Query;

import ufc.sd.dao.CompraDAO;
import ufc.sd.modelo.Compra;


public class CompraJPADAO extends GenericJPADAO<Compra> implements CompraDAO {
	public CompraJPADAO(){
		super();
		this.persistentClass = Compra.class;
	}
	
	@Override
	public List<Compra> listarComprasDoUsuario(long idUsuario){
		try {
			Query query = em.createQuery("select c from Compra c where c.idComprador=?1");
			query.setParameter(1, idUsuario);
			List<Compra> compras = query.getResultList();
			return compras;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Compra> listarVendasDoUsuario(long idUsuario){
		try {
			Query query = em.createQuery("select c from Compra c where c.idVendedor=?1");
			query.setParameter(1, idUsuario);
			List<Compra> vendasVendedor = query.getResultList();
			return vendasVendedor;
		} catch (Exception e) {
			return null;
		}
	}
}