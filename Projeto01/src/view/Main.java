package view;

import controller.ThreadCruzamento;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {

		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore semaforo2 = new Semaphore(permissao);

		for (int i = 0; i < 4; i++) {
			Thread thread = new ThreadCruzamento(semaforo, semaforo2);
			thread.start();
		}
	}

}
