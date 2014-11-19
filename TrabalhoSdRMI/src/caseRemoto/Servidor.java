package caseRemoto;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class Servidor {

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			CaseRemoto refServente = new CaseServente();
			Naming.bind("caseServer", refServente);
			System.out.println("Servidor em execucao");
		} catch (Exception e) {
			System.out.println("Server main " + e.getMessage());
		}

	}

}
