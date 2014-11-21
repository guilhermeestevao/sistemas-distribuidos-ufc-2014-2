package br.ufc.si.sd.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import br.ufc.si.sd.R;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class ActivityConvidarAmigos  extends Activity{

		private Context contexto;
		private UiLifecycleHelper uiHelper;
		private TextView txt_status_login;
		
		private Session.StatusCallback callback = new Session.StatusCallback() {
	        @Override
	        public void call(Session session, SessionState state, Exception exception) {
	            onSessionStateChange(session, state, exception);
	            
	        }
	    };
	    
	    private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
	        @Override
	        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
	        }

	        @Override
	        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	        	System.out.println("Entrou no dialogCallBack");
	            Log.d("HelloFacebook", "Success!");
	        }
	    };
	    
	    @Override
	   	protected void onCreate(Bundle savedInstanceState) {
	   		super.onCreate(savedInstanceState);
	   		
	   		contexto = this;
	   		uiHelper = new UiLifecycleHelper(this, callback);
			uiHelper.onCreate(savedInstanceState);
			
			setContentView(R.layout.activity_compartilhar_face);
			
			txt_status_login = (TextView) findViewById(R.id.txt_status_login);
			
	    }
	   	
	    private void sendRequestDialog() {
	        Bundle params = new Bundle();
	        params.putString("message", "Instale o aplicativo do Sale e conheça todos os vendedores e produtos.");

	        WebDialog requestsDialog = (new WebDialog.RequestsDialogBuilder(contexto, Session.getActiveSession(),
	                params))
	                .setOnCompleteListener(new OnCompleteListener() {

						@Override
						public void onComplete(Bundle values,
								FacebookException error) {
							if (error != null) {
	                            if (error instanceof FacebookOperationCanceledException) {
	                            } else {
	                                Toast.makeText(contexto, 
	                                    "Requisição Cancelada.", 
	                                    Toast.LENGTH_SHORT).show();
	                            }
	                        } else {
	                            final String requestId = values.getString("request");
	                            if (requestId != null) {
	                                Toast.makeText(contexto, 
	                                    "Convite enviado.",  
	                                    Toast.LENGTH_SHORT).show();
	                            } else {
	                                Toast.makeText(contexto.getApplicationContext(), 
	                                    "Ocorreu algum problema, tente novamente.", 
	                                    Toast.LENGTH_SHORT).show();
	                            }
	                        }
							finish();
						}
	                })
	                .build();
	        requestsDialog.show();
	    }
	    
	    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    	System.out.println("Ta no Session");
	        if (state.isOpened()) {
	        	System.out.println("Ta aberta onSession");
	        	txt_status_login.setText("Aguarde alguns instantes...");
	        	sendRequestDialog();
	        } else if (state.isClosed()) {
	        	txt_status_login.setText("Você está: Desconectado");
	        }
	    }
	    
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		    System.out.println("onResult");
		    uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
		}
	    
	    @Override
		public void onResume() {
		    super.onResume();
		    Session s = Session.getActiveSession();
		    if(s!=null){
		    	System.out.println("Sessão nao nula");
		    	if(!s.isClosed()){
		    		System.out.println("Sessão nao fechada");
		    		txt_status_login.setText("Aguarde alguns instantes...");
		    		if(s.isOpened()){
		    			System.out.println("Ta aberta onResume");
		    			sendRequestDialog();
		    		}else{
		    			txt_status_login.setVisibility(View.GONE);
		    		}
		    		
		    	}else{
		    		System.out.println("Sessão fechada");
		    		txt_status_login.setVisibility(View.GONE);
		    	}
		    }else{
		    	txt_status_login.setVisibility(View.GONE);
		    	System.out.println("Sessão nula");
		    }
		    System.out.println("onResume");
		    uiHelper.onResume();
		}
	    
	    @Override
		public void onPause() {
		    super.onPause();
		    uiHelper.onPause();
		}
	    
	    @Override
		public void onDestroy() {
		    super.onDestroy();
		    uiHelper.onDestroy();
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
		    super.onSaveInstanceState(outState);
		    uiHelper.onSaveInstanceState(outState);
		}
}