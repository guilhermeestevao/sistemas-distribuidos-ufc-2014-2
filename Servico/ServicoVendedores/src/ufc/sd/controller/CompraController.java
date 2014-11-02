package ufc.sd.controller;

import ufc.sd.dao.CompraDAO;
import ufc.sd.dao.impl.CompraJPADAO;
import ufc.sd.modelo.Compra;

public class CompraController {
	public String cadastrarCompra(Compra compra){
		CompraDAO cdao = new CompraJPADAO();
		try {
			cdao.beginTransaction();
			cdao.save(compra);
			cdao.commit();
			return "Compra Cadastrada com sucesso";
		} catch (Exception e) {
			return "---" + e.getMessage();
		}finally{
			
		}
			
	}
	
}
