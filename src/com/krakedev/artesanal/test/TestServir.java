package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestServir {
	public static void main(String[] args) {
    Maquina rubia = new Maquina("Pilsener", "Cerveza fría", 0.02, 8000);
        
        System.out.println("---ESTADO INICIAL---");
        rubia.imprimir();
        
        System.out.println("\n---LLENANDO MAQUINA---");
        rubia.llenarMaquina();
        rubia.imprimir();
	}
}
