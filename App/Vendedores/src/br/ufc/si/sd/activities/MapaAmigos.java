package br.ufc.si.sd.activities;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import br.ufc.si.sd.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MapaAmigos extends Activity{

	 private GoogleMap googleMap;
	
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
 
            // check if map is created successfully or not
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
	
}
