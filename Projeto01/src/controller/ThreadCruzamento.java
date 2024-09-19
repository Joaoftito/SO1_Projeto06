package controller;

import java.util.concurrent.Semaphore;

public class ThreadCruzamento extends Thread {

	private int tid;
	private String[] sentidos = { "norte", "sul", "leste", "oeste" };
	private static int auxiliar = 3;
	private int sentido;
	private Semaphore semaforo;
	private Semaphore semaforo2;

	public ThreadCruzamento(Semaphore semaforo, Semaphore semaforo2) {
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
	}

	public void run() {
		ThreadId();
		try {
			semaforo.acquire();
			Sentido();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		if (sentido == 0 || sentido == 1) {
			try {
				sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			semaforo2.acquire();
			Cruzamento();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo2.release();
		}
	}

	public void ThreadId() {
		tid = (int) threadId();
	}

	public void Sentido() {
		sentido = auxiliar;
		auxiliar--;
	}

	public void Cruzamento() {
		System.out.println("#" + tid + " Carro acabou de passar pelo cruzamento sentido " + sentidos[sentido]);
	}

}
