package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maquina rubia = new Maquina("Pilsener", "cerveza fria", 0.02, 8000);
		rubia.imprimir();
		rubia.llenarMaquina();
		rubia.imprimir();
		Maquina negra = new Maquina("Club", "Cerveza buena", 0.03);
		negra.imprimir();
		negra.llenarMaquina();
		negra.imprimir();
	}

}
