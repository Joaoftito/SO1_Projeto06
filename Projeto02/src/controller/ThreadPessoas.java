package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoas extends Thread {

	private int[] corredores = { 1, 2, 3, 4 };
	private int corredor;
	private int velocidade;
	private Semaphore semaforo;

	public ThreadPessoas(int corredor, Semaphore semaforo) {
		this.corredor = corredor;
		this.semaforo = semaforo;

	}

	public void run() {
		Corredor();
		try {
			semaforo.acquire();
			Porta();
		} catch (Exception e) {

		} finally {
			semaforo.release();
		}
	}

	public void Corredor() {
		int percurso = 200;
		for (int i = 0; i < percurso; i = i + velocidade) {
			velocidade = (int) ((Math.random() * 3) + 4);
			try {
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("A pessoa que caminha pelo corredor " + corredores[corredor] + " percorreu: " + i + "m");
		}
		System.out.println("A pessoa que caminha pelo corredor " + corredores[corredor] + " terminou o percurso!");
	}

	public void Porta() {
		velocidade = (int) ((Math.random() * 1001) + 1000);
		System.out.println("A pessoa que caminha pelo corredor " + corredores[corredor] + " chegou na porta!");
		try {
			sleep(velocidade);
		} catch (Exception e) {

		}
		double velocidadeFinal = velocidade;
		velocidadeFinal /= 1000;
		System.out.println("A pessoa que caminha pelo corredor " + corredores[corredor] + " abriu e cruzou a porta em: "
				+ velocidadeFinal + "s");
	}

}
