package view;

import controller.ThreadFormula1;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		
		int permissao = 1;
		int maximoCarros = 5;
		Semaphore semaforo = new Semaphore(permissao);
		Semaphore semaforo2 = new Semaphore(maximoCarros);
		Semaphore semaforo3 = new Semaphore(permissao);

		for (int carro = 0; carro < 14; carro++) {
			Thread thread = new ThreadFormula1(carro, semaforo, semaforo2, semaforo3);
			thread.start();
		}
	}

}
