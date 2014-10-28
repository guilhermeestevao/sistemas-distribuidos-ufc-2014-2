package ufc.sd.controller;

import ufc.sd.dao.ProdutoDAO;
import ufc.sd.dao.impl.ProdutoJPADAO;
import ufc.sd.modelo.Produto;

public class ProdutoController {
	
	public String cadastrarItem(Produto item){
		ProdutoDAO dao = new ProdutoJPADAO();
		try{
		dao.beginTransaction();
		dao.save(item);
		dao.commit();
		return "Item Cadastrado Com Sucesso";
		}catch(Exception e){
			return e.getMessage();
		}finally{
			dao.close();
		}
		
		
	}
}
