package ufc.sd.dao.impl;

import ufc.sd.dao.CompraDAO;
import ufc.sd.modelo.Compra;

public class CompraJPADAO extends GenericJPADAO<Compra> implements CompraDAO {
	public CompraJPADAO(){
		this.persistentClass = Compra.class;
	}
}
