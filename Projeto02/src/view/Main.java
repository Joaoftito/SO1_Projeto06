package view;

import controller.ThreadPessoas;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {

		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);

		for (int corredor = 0; corredor < 4; corredor++) {
			Thread thread = new ThreadPessoas(corredor, semaforo);
			thread.start();
		}

	}

}
