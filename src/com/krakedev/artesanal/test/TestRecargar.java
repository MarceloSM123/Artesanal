package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestRecargar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean resultado;
		Maquina rubia = new Maquina("Pilsener", "Cerveza fria", 0.02, 8000);
		System.out.println("---ESTADO INICIAL---");
		rubia.imprimir();

		System.out.println("---RECARGA 1---");
		resultado = rubia.recargarCerveza(3000);
		System.out.println("¿Recarga exitosa? " + resultado);
		rubia.imprimir();

		System.out.println("---RECARGA 2---");
		resultado = rubia.recargarCerveza(2000);
		System.out.println("¿Recarga exitosa? " + resultado);
		rubia.imprimir();
		System.out.println("---RECARGA 3---");
		resultado = rubia.recargarCerveza(2900);
		System.out.println("¿Recarga exitosa? " + resultado);
		rubia.imprimir();
		
		double valor;

		valor = rubia.servirCerveza(1000);

		System.out.println("Valor a pagar " + valor);

		System.out.println("---SERVIZ 2000 ML---");

		valor = rubia.servirCerveza(2000);

		System.out.println("Valor a pagar " + valor);
		
		System.out.println("---SERVIZ 6000 ML---");

		valor = rubia.servirCerveza(6000);

		System.out.println("Valor a pagar " + valor);
	}

}
