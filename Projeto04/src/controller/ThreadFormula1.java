package controller;

import java.util.concurrent.Semaphore;

public class ThreadFormula1 extends Thread {

	private int carro;
	private Semaphore semaforo;
	private Semaphore semaforo2;
	private Semaphore semaforo3;
	private String[] escuderias = { "", "Alpine", "Alpine", "Aston Martin", "Aston Martin", "Ferrari", "Ferrari",
			"McLaren", "McLaren", "Mercedes", "Mercedes", "Red Bull", "Red Bull", "Williams", "Williams" };
	private static int auxiliar = 1;
	private static int auxiliar2;
	private String escuderia;
	private int melhorTempo;
	private static int[] rankingTempo = new int[14];
	private static int[] rankingCarro = new int[14];

	public ThreadFormula1(int carro, Semaphore semaforo, Semaphore semaforo2, Semaphore semaforo3) {
		this.carro = carro;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;
		this.semaforo3 = semaforo3;
	}

	public void run() {
		try {
			semaforo.acquire();
			Escuderia();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		try {
			semaforo2.acquire();
			Pista();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo2.release();
		}
		try {
			semaforo3.acquire();
			Ranking();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo3.release();
		}
	}

	public void Escuderia() {
		escuderia = escuderias[auxiliar];
		auxiliar++;
	}

	public void Pista() {
		for (int volta = 0; volta < 3; volta++) {
			int tempo = (int) ((Math.random() * 301) + 0);
			System.out.println("Piloto " + carro + " equipe " + escuderia + " fez o tempo de: " + tempo + "s");
			if (melhorTempo < tempo) {
				melhorTempo = tempo;
			}
		}
	}

	public void Ranking() {
		
		try {
			sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rankingCarro[auxiliar2] = carro;
		rankingTempo[auxiliar2] = melhorTempo;
		auxiliar2 += 1;

		if (auxiliar2 == 14) {
			for (int i = 0; i < 13; i++) {
				for (int j = i + 1; j < 14; j++) {
					if (rankingTempo[i] < rankingTempo[j]) {
						int inter = rankingTempo[i];
						rankingTempo[i] = rankingTempo[j];
						rankingTempo[j] = inter;
						inter = rankingCarro[i];
						rankingCarro[i] = rankingCarro[j];
						rankingCarro[j] = inter;
					}
				}
			}
			System.out.println("Grid de Largada");
			for (int i = 0; i < 14; i++) {
				System.out.println(
						"Piloto " + rankingCarro + " equipe " + escuderia + " fez o tempo de: " + rankingTempo + "s");
			}
		}
	}

}
