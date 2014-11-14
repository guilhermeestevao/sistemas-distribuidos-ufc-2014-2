package br.ufc.si.sd.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.lists.ListaComprasUsuario;
import br.ufc.si.sd.lists.ListaProdutosPorVendedor;
import br.ufc.si.sd.lists.ListaVendasUsuario;
import br.ufc.si.sd.rest.UsuarioREST;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends Activity {
	
	private UiLifecycleHelper uiHelper;
	private Usuario usuario = new Usuario();
	List<GraphUser> friendsFacebooklist = new ArrayList<GraphUser>();
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChanged(session, state, exception);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.action_bar_opcoes, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.listar_vendedores:
			Intent it = new Intent(MainActivity.this, MapaAmigos.class);
			it.putExtra("usuario_principal", usuario);
			MapaAmigos.listaAmigosFacebook = this.friendsFacebooklist;
			startActivity(it);
			break;

		case R.id.cadastra_produto:
			Intent it1 = new Intent(MainActivity.this, CadastraProdutoActivity.class);
			it1.putExtra("usuario", usuario);
			startActivity(it1);
			break;

		case R.id.lista_produto:
			Intent it2 = new Intent(MainActivity.this, ListaProdutosPorVendedor.class);
			it2.putExtra("usuario", usuario);
			startActivity(it2);
			break;
			
		case R.id.lista_compras:
			Intent it3 = new Intent(MainActivity.this, ListaComprasUsuario.class);
			it3.putExtra("usuario", usuario);
			startActivity(it3);
			break;
			
		case R.id.lista_vendas:
			Intent it4 = new Intent(MainActivity.this, ListaVendasUsuario.class);
			it4.putExtra("usuario", usuario);
			startActivity(it4);
			break;
			
		default:
			break;
			
		}


		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);


		LoginButton lb = (LoginButton) findViewById(R.id.fbLogin);
		lb.setPublishPermissions(Arrays.asList("email", "public_profile", "user_friends"));

	}

	@Override
	protected void onResume() {
		super.onResume();

		Session session = Session.getActiveSession();
		if(session != null && (session.isClosed() || session.isOpened())){
			onSessionStateChanged(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		uiHelper.onSaveInstanceState(bundle);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	public void onSessionStateChanged(final Session session, SessionState state, Exception exception){
		if(session != null && session.isOpened()){

			Request.newMeRequest(session, new Request.GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {
					if(user != null){
						
						TextView tv = (TextView) findViewById(R.id.name);
						tv.setText("Seja bem vindo " + user.getFirstName()+" "+user.getLastName());

						tv = (TextView) findViewById(R.id.email);
						tv.setText(user.getProperty("email").toString());

						tv = (TextView) findViewById(R.id.id);
						tv.setText(user.getId());

						ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.fbImg);
						ppv.setProfileId(user.getId());
						
						getFriends(session);
						
						usuario.setId(Long.parseLong(user.getId()));
						usuario.setNome(user.getFirstName()+" "+user.getLastName());
						usuario.setEmail(user.getProperty("email").toString());

						new VerificaUsuarioAsyncTask().execute(usuario);
						
					}
				}
			}).executeAsync();
		}
		else{
			Log.i("Script", "Usu�rio n�o conectado");
		}
	}

	public void getFriends(Session session){
	 
		
		Request.newMyFriendsRequest(session, new Request.GraphUserListCallback() {
			@Override
			public void onCompleted(List<GraphUser> users, Response response) {
				if(users != null){
					Log.i("Script", "Friends: "+users.size());
					friendsFacebooklist = users;
					for (GraphUser graphUser : users) {
						Log.i("Script", "Script "+graphUser.getName());
					}
				}
				Log.i("Script", "response: "+response);
			}
		}).executeAsync();
		
	}
	
	class VerificaUsuarioAsyncTask extends AsyncTask<Usuario, Void, String>{

		//private GoogleMap googleMap;
		private Location myLocation;
		
		@Override
		protected String doInBackground(Usuario... params) {
			Usuario usuario = params[0];
			UsuarioREST rest =new UsuarioREST();
			boolean status = rest.verificarUsuario(usuario.getId());
			if(status == false){
				try {
					
					String locationProvider = LocationManager.NETWORK_PROVIDER;
					LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
					myLocation = locationManager.getLastKnownLocation(locationProvider);
					double latitude = myLocation.getLatitude();
					double longitude = myLocation.getLongitude();
					usuario.setLat(latitude);
					usuario.setLng(longitude);
					String resposta = rest.cadastrarUsario(usuario);
					return resposta;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "";
				
		}
		
	}
}
