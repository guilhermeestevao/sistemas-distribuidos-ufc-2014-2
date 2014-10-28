import ufc.sd.dao.ProdutoDAO;
import ufc.sd.dao.UsuarioDAO;
import ufc.sd.dao.impl.ProdutoJPADAO;
import ufc.sd.dao.impl.UsuarioJPADAO;
import ufc.sd.modelo.Produto;
import ufc.sd.modelo.Usuario;


public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioDAO dao = new UsuarioJPADAO();
		ProdutoDAO itemDao = new ProdutoJPADAO();
		dao.beginTransaction();
		
		Usuario usuarioItem = new Usuario(4L, "guilherme", "guilherme@email.com");
		dao.save(usuarioItem);
		dao.commit();
		
		itemDao.beginTransaction();
		itemDao.save(new Produto("celular","celularDePobe",10,1000,usuarioItem));
		itemDao.commit();
		itemDao.close();
	}

}
