package com.krakedev.artesanal.testJunit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import com.krakedev.artesanal.Maquina;

public class testRecargarJUnit {
	@Test
	public void testRecargaExitosa() {

	    Maquina rubia = new Maquina("Pilsener", "cerveza", 0.02, 8000);

	    boolean resultado = rubia.recargarCerveza(3000);

	    assertTrue(resultado);
	    assertEquals(3000, rubia.getCantidadActual(), 0.0001);

	}
	
	@Test
	public void testRecargaFallidaPorDesborde() {

	    Maquina negra = new Maquina("Club", "cerveza fria", 0.03, 8000);

	    negra.recargarCerveza(7000);

	    boolean resultado = negra.recargarCerveza(8);

	    assertTrue(resultado);
	    assertEquals(7008, negra.getCantidadActual(), 0.0001);
	}
}
