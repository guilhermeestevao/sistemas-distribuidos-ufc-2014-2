package caseRemoto;

import java.awt.EventQueue;
import java.rmi.RMISecurityManager;

public class Main {

	public static void main(String[] args) {
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		} else
			System.out.println("Já há um gerenciador de Seg");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCriacaoUsuario window = new FormCriacaoUsuario();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	
}
