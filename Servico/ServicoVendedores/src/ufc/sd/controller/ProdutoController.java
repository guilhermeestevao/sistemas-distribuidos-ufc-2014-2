package ufc.sd.controller;

import java.util.List;
import ufc.sd.dao.ProdutoDAO;
import ufc.sd.dao.impl.ProdutoJPADAO;
import ufc.sd.modelo.Produto;

public class ProdutoController {

	public String cadastrarProduto(Produto produto){
		ProdutoDAO dao = new ProdutoJPADAO();
		
		try{
			dao.beginTransaction();
			dao.save(produto);
			dao.commit();
			return "Produto Cadastrado Com Sucesso";
		}catch(Exception e){
			return "-----"+e.getMessage();
		}finally{

		}
	}

	public List<Produto> listarProdutosPorUsuario(long idUsuario){
		ProdutoDAO dao = new ProdutoJPADAO();
		return dao.listarProdutosPorUsuario(idUsuario);
	}

	public String deletarProduto(long id){
		ProdutoDAO dao = new ProdutoJPADAO();
		Produto produto = dao.find(id);
		try{
			dao.beginTransaction();
			dao.delete(produto);
			dao.commit();
			return "Produto apagado";
		}catch(Exception e){
			return e.getMessage();
		}finally{

		}
	}

	public String atualizarProduto(Produto produto){
		ProdutoDAO dao = new ProdutoJPADAO();
		try{
			dao.beginTransaction();
			dao.update(produto);
			dao.commit();
			return "Produto atualizado";
		}catch(Exception e){
			return e.getMessage();
		}finally{

		}
	}
	
}
