package br.ufc.si.sd;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import br.ufc.si.sd.rest.UsuarioREST;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;


public class MainActivity extends Activity {
	private UiLifecycleHelper uiHelper;
	Usuario usuario = new Usuario();
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
			Intent it = new Intent(MainActivity.this, ListaVendedoresActivity.class);
			startActivity(it);
			break;

		case R.id.cadastro:
			final UsuarioREST rest = new UsuarioREST();
			new Thread(){
				public void run() {
					try {
						String resposta = rest.cadastrarUsario(usuario);						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();

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
						tv.setText(user.getFirstName()+" "+user.getLastName());

						tv = (TextView) findViewById(R.id.email);
						tv.setText(user.getProperty("email").toString());

						tv = (TextView) findViewById(R.id.id);
						tv.setText(user.getId());

						ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.fbImg);
						ppv.setProfileId(user.getId());

						final UsuarioREST rest = new UsuarioREST();
						new Thread(){
							public void run() {
								try {
									String resposta = rest.cadastrarUsario(usuario);						
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							};
						}.start();
						
						usuario.setId(Long.parseLong(user.getId()));
						usuario.setNome(user.getFirstName()+" "+user.getLastName());
						usuario.setEmail(user.getProperty("email").toString());


					}
				}
			}).executeAsync();
		}
		else{
			Log.i("Script", "Usuário não conectado");
		}
	}

}
