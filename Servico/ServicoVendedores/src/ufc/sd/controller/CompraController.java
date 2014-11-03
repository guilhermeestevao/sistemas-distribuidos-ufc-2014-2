package ufc.sd.controller;


import java.util.List;

import ufc.sd.dao.CompraDAO;
import ufc.sd.dao.ProdutoDAO;
import ufc.sd.dao.impl.CompraJPADAO;
import ufc.sd.dao.impl.ProdutoJPADAO;
import ufc.sd.modelo.Compra;
import ufc.sd.modelo.Produto;

public class CompraController {
	public String cadastrarCompra(Compra compra){
		
		CompraDAO cdao = new CompraJPADAO();
		ProdutoDAO pdao =  new ProdutoJPADAO();
		Produto produto =pdao.find(compra.getIdProduto());
		int quantidadeAtual =produto.getQuantidade();
		int quantidadeAtualizada = quantidadeAtual - compra.getQuantidadeProduto();
		produto.setQuantidade(quantidadeAtualizada);
		try {
			cdao.beginTransaction();
			pdao.save(produto);
			cdao.save(compra);
			
			cdao.commit();
			
			return "Compra Cadastrada com sucesso  no valor de R$: " + compra.getValorVenda();
		} catch (Exception e) {
			return  e.getMessage();
		}finally{
			
		}
			
	}
	
	public List<Compra> listarComprasDoUsuario(long idUsuario){
		CompraDAO cdao = new CompraJPADAO();
		return cdao.listarComprasDoUsuario(idUsuario);
	}
	
	public List<Compra> listarVendasDoUsuario(long idUsuario){
		CompraDAO cdao = new CompraJPADAO();
		return cdao.listarVendasDoUsuario(idUsuario);
	}
	
}