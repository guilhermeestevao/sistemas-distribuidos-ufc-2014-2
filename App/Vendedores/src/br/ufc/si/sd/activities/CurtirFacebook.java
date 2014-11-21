package br.ufc.si.sd.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import br.ufc.si.sd.R;
import br.ufc.si.sd.entidades.Produto;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import com.facebook.widget.FacebookDialog;

public class CurtirFacebook extends FragmentActivity{

	private UiLifecycleHelper uiHelper;
	private TextView estado;
	private Produto produto;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        	System.out.println("Entrou no call");
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
		Log.i("Estado", "OnCreate...");
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		
		setContentView(R.layout.testeface);
		
		Bundle extras = getIntent().getExtras();
		produto = (Produto) extras.getSerializable("produto");
		estado = (TextView) findViewById(R.id.txt_status_face);
        
	}
	
    
	private boolean hasPublishPermission() {
	   Session session = Session.getActiveSession();
	   return session != null && session.getPermissions().contains("publish_actions");
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		System.out.println("Entrou no onSession");
	    if (state.isOpened()) {
	        Log.i("Estado", "Logged in...");
	        if(hasPublishPermission()){
	        	
	        	OpenGraphObject objeto = OpenGraphObject.Factory.createForPost("object");
	        	objeto.setProperty("title", produto.getNome());
	        
	        	objeto.setProperty("description", produto.getDescricao());
	        	objeto.setProperty("image", "http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-11/128/sale-icon.png");
	        	
	        	objeto.setProperty("url", "https://play.google.com/store/apps/details?id=com.app.applucianokikao");
	        	
				OpenGraphAction action = GraphObject.Factory.create(OpenGraphAction.class);
				action.setType("og.likes");
				action.setProperty("object", objeto);
				
				FacebookDialog shareDialog = new FacebookDialog.OpenGraphActionDialogBuilder(this, action, "object").build();																						
				
				uiHelper.trackPendingDialogCall(shareDialog.present());
				
				finish();
	        }else{
	        	System.out.println("não tem permissão");
	        	session.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, "publish_actions"));
	        }	
	    } else if (state.isClosed()) {
	        Log.i("Estado", "Logged out...");
	    }
	    
	}

	@Override
    public void onResume() {
	    super.onResume();
	    Session s = Session.getActiveSession();
	    if(s!=null){
	    	System.out.println("Sessão nao nula");
	    	if(!s.isClosed()){
	    		System.out.println("Sessão nao fechada");
	    		estado.setText("Aguarde alguns instantes...");
	    		if(hasPublishPermission()){
		        	
		        	OpenGraphObject objeto = OpenGraphObject.Factory.createForPost("object");
		        	objeto.setProperty("title", produto.getNome());
		        
		        	objeto.setProperty("description", produto.getDescricao());
		        	objeto.setProperty("image", "http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-11/128/sale-icon.png");
		        		
		        	objeto.setProperty("url", "https://play.google.com/store/apps/details?id=com.app.applucianokikao");
		        	
					OpenGraphAction action = GraphObject.Factory.create(OpenGraphAction.class);
					action.setType("og.likes");
					action.setProperty("object", objeto);
					
					FacebookDialog shareDialog = new FacebookDialog.OpenGraphActionDialogBuilder(this, action, "object").build();																						
					
					uiHelper.trackPendingDialogCall(shareDialog.present());
					
					finish();
		        }else{
		        	System.out.println("Não tem permissão");
		        	if(s.isOpened()){
		        		s.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, "publish_actions"));
		        	}else{
		        		estado.setText("Clique para entrar no Facebook.");
		        	}
		        	
		        }	
	    	}else{
	    		System.out.println("Sessão fechada");
	    		estado.setVisibility(View.GONE);
	    	}
	    }else{
	    	estado.setVisibility(View.GONE);
	    	System.out.println("Sessão nula");
	    }
	    		
	    uiHelper.onResume();
	   
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    System.out.println("onResult");
	    
	    uiHelper.onActivityResult(requestCode, resultCode, data, dialogCallback);
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
	
	private int verificaFace(String date){
		SharedPreferences settings = getSharedPreferences("Kikao", 0);
	    int result = settings.getInt(date+"_curtir", -1);
		return result;
	}
	
	private void inserirFace(String date){
		SharedPreferences settings = getSharedPreferences("Kikao", 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putInt(date+"_curtir", 1);
        editor.commit();
	}

	
}
