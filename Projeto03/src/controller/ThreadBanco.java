package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {

	private int codigoConta;
	private int saldo;
	private int valor;
	private int tipo;
	private int saldoFinal;
	private Semaphore semaforo;
	private Semaphore semaforo2;

	public ThreadBanco(int codigoConta, int saldo, int valor, Semaphore semaforo, Semaphore semaforo2) {
		this.codigoConta = codigoConta;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforo = semaforo;
		this.semaforo2 = semaforo2;

	}

	public void run() {
		TipoTransacao();
		if (tipo == 0) {
			try {
				semaforo.acquire();
				FinalizacaoTransacao();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
		if (tipo == 1) {
			try {
				semaforo2.acquire();
				FinalizacaoTransacao();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
			}
		}
	}

	public void TipoTransacao() {
		tipo = (int) ((Math.random() * 2) + 0);
		if (tipo == 0 && valor > saldo) {
			valor = (int) ((Math.random() * saldo + 1) + 0);
		}
	}

	public void FinalizacaoTransacao() {
		if (tipo == 0) {
			saldoFinal = saldo - valor;
			System.out.println("Conta nº: " + codigoConta + " /// Saldo: R$ " + saldo + " /// Sacou: R$ " + valor
					+ " /// Saldo atualizado: R$ " + saldoFinal);
		} else {
			saldoFinal = saldo + valor;
			System.out.println("Conta nº: " + codigoConta + " /// Saldo: R$ " + saldo + " /// Depositou: R$ " + valor
					+ " /// Saldo atualizado: R$ " + saldoFinal);
		}
	}

}
