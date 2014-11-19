package br.ufc.si.sd.services;

import org.json.JSONException;

import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.rest.UsuarioREST;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class AtualizarLocalizacao extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Usuario usuario = (Usuario) intent.getExtras().get("usuario");
		new AtualizaLocalizacaoAsyncTask().execute(usuario);
		return super.onStartCommand(intent, flags, startId);
	}

	
	class AtualizaLocalizacaoAsyncTask extends AsyncTask<Usuario, Void, String>{
		
		private Location myLocation;
		
		@Override
		protected String doInBackground(Usuario... params) {
			Usuario usuario = params[0];
			String locationProvider = LocationManager.NETWORK_PROVIDER;
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			myLocation = locationManager.getLastKnownLocation(locationProvider);
			double latitude = myLocation.getLatitude();
			double longitude = myLocation.getLongitude();
			usuario.setLat(latitude);
			usuario.setLng(longitude);
			
			try {
				String resposta = new UsuarioREST().atualizarUsuario(usuario);
				return resposta;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		
		
	}
}
