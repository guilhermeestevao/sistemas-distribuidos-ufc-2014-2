package br.ufc.si.sd.activities;

import java.util.ArrayList;
import java.util.List;

import com.facebook.model.GraphUser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaVendedoresActivity;
import br.ufc.si.sd.rest.UsuarioREST;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MapaAmigos extends Activity{

	private GoogleMap googleMap;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	public static List<GraphUser> listaAmigosFacebook = new ArrayList<GraphUser>();
	private Usuario usuarioPrincipal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_mapa_amigos);

		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			usuarioPrincipal = (Usuario) getIntent().getExtras().get("usuario_principal");

			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true); 
			googleMap.setMyLocationEnabled(true);

			new DownloadJsonAsyncTask().execute();
		
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}


	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Usuario>>{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(MapaAmigos.this, "Aguarde", "Buscando vendedores...");

		}

		@Override
		protected List<Usuario> doInBackground(String... params) {
			// TODO Auto-generated method stub
			usuarios = new UsuarioREST().listarUsuarios();
			if(usuarios != null){
				return usuarios;
			}else{
				return new ArrayList<Usuario>();
			}
		}

		@Override
		protected void onPostExecute(List<Usuario> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			if(result.size() > 0){
				addPontos(result);
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(MapaAmigos.this).setTitle("Atenção") .setMessage("Não foi possivel acessar essas informções...") .setPositiveButton("OK", null); 
				builder.create().show();
			}
		}

		private void addPontos(List<Usuario> usuarios) {
			
			//usuarios.remove(usuarioPrincipal);
			
			for (Usuario usuario  : usuarios) {
				MarkerOptions marker = new MarkerOptions();
				marker.position(new LatLng(usuario.getLat(), usuario.getLng()));
				marker.title(usuario.getNome());
				marker.snippet("Clique aqui para ver a lista de compras");			 
				googleMap.addMarker(marker);
			}
		}
	}
}
