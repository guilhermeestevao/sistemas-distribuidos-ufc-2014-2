import ufc.sd.dao.UsuarioDAO;
import ufc.sd.dao.impl.UsuarioJPADAO;
import ufc.sd.modelo.Usuario;


public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioDAO dao = new UsuarioJPADAO();
		dao.beginTransaction();
		dao.save(new Usuario(2L, "guilherme", "guilherme@email.com"));
		dao.commit();
		dao.close();
	}

}
