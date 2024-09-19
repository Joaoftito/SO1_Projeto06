package view;

import controller.ThreadBanco;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore semaforo2 = new Semaphore(permissao);

		for (int codigoConta = 1001; codigoConta <= 1020; codigoConta++) {
			int saldo = (int) ((Math.random() * 10001) + 0);
			int valor = (int) ((Math.random() * 10001) + 0);
			Thread thread = new ThreadBanco(codigoConta, saldo, valor, semaforo, semaforo2);
			thread.start();
		}
	}

}
