package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.Negocio;

public class TestVaciar {

	public static void main(String[] args) {
		double servir;
		// TODO Auto-generated method stub
		 Maquina maquinaA = new Maquina("Pilsener", "Rubia", 0.002, 8000);

		 Negocio barDeMoe = new Negocio("Bar de Moe", maquinaA);
		 maquinaA.llenarMaquina();
		 maquinaA.vaciarMaquina();
		 System.out.println(maquinaA.getCantidadActual());
		 System.out.println(maquinaA.getCantidadDesperdicio());
		 
	}

}
