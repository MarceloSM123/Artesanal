package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class testServirCervezaJunit {
	private Maquina rubia;

	@BeforeEach
	public void setUp() {
		rubia = new Maquina("Pilsener", "cerveza", 0.02, 8000);
		rubia.llenarMaquina(); // cantidadActual = 7800
	}

	@Test
	public void testServirCervezaExitosa() {
		double valor = rubia.servirCerveza(1000);

		assertEquals(20.0, valor, 0.0001);
		assertEquals(6800, rubia.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaCantidadExacta() {
		double valor = rubia.servirCerveza(7800);

		assertEquals(156.0, valor, 0.0001);
		assertEquals(0, rubia.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaCantidadInsuficiente() {
		double valor = rubia.servirCerveza(8000);

		assertEquals(0, valor, 0.0001);
		assertEquals(7800, rubia.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaCantidadCero() {
		double valor = rubia.servirCerveza(0);

		assertEquals(0, valor, 0.0001);
		assertEquals(7800, rubia.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaMultiplesVeces() {
		rubia.servirCerveza(500);
		rubia.servirCerveza(300);
		double valor = rubia.servirCerveza(200);

		assertEquals(4.0, valor, 0.0001);
		assertEquals(6800, rubia.getCantidadActual(), 0.0001);
	}

	@Test
	public void testServirCervezaConMaquinaVacia() {
		Maquina vacia = new Maquina("Club", "cerveza fria", 0.03, 8000);
		// No se llena la máquina, cantidadActual = 0

		double valor = vacia.servirCerveza(500);

		assertEquals(0, valor, 0.0001);
		assertEquals(0, vacia.getCantidadActual(), 0.0001);
	}
}
