package br.ufc.si.sd.services;

import java.util.Calendar;

import br.ufc.si.sd.entidades.Usuario;
import br.ufc.si.sd.util.SDCardUtils;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IniciaAtualizacao extends BroadcastReceiver{

	private final String SERVICE = "ATUALIZAR_USUARIO";
	private final int TEMPO = 1; //inicia apartir de um minuto depois q  foi lançada
	private final int REPETIR = 300000; //5 minutos em milisegundos
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Usuario usuario = SDCardUtils.getUsuario();
		Intent it = new Intent(SERVICE);
		it.putExtra("usuario", usuario);
		PendingIntent p = PendingIntent.getService(context, 0, it, 0);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.MINUTE, TEMPO);
		AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		long duracao = c.getTimeInMillis();
		alarme.setRepeating(AlarmManager.RTC_WAKEUP, duracao, REPETIR, p);
	}

	
}
