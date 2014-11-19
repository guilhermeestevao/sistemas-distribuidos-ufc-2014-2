package br.ufc.si.sd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.util.Log;
import br.ufc.si.sd.entidades.Usuario;

public class SDCardUtils {

	private final static String diretorio = "Vendedores";
	private final static String fileName = "usuario";
	
	private static File getFile(){
		File sdCardFile = android.os.Environment.getExternalStorageDirectory();
		File dir = new File(sdCardFile, diretorio);
		if(!dir.exists()){
			dir.mkdir();
		}
		File file = new File(dir, fileName);
		return file;
	}
	
	public static void salvarUsuario(Usuario usuario){
		File file = getFile();
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(usuario);
			out.close();
			Log.i("script", usuario.getNome()+" salvo com sucesso em arquivo");
		} catch (FileNotFoundException e){
			e.printStackTrace();
			Log.i("script", e.getMessage());
		} catch (IOException e){
			e.printStackTrace();
			Log.i("script", e.getMessage());
		}
	}
	
	public static Usuario getUsuario(){
		File file = getFile();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			Usuario usuario = (Usuario) in.readObject();
			in.close();
			return usuario;
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("script", e.getMessage());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("script", e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("script", e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("script", e.getMessage());
		}
		return null;	
	}
	
}
