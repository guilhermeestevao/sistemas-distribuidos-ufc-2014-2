package ufc.sd.controller;


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
			pdao.commit();
			return "Compra Cadastrada com sucesso  no valor de R$: " + compra.getValorVenda();
		} catch (Exception e) {
			return "---" + e.getMessage();
		}finally{
			
		}
			
	}
	
}
