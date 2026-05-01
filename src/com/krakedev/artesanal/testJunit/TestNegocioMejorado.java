package com.krakedev.artesanal.testJunit;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class TestNegocioMejorado {
	 @Test
	    @DisplayName("Debería generar un código con el prefijo 'M-'")
	    void testGenerarCodigoTienePrefijoM() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();
	        
	        assertTrue(codigo.startsWith("M-"), 
	            "El código debe comenzar con 'M-'");
	    }
	    
	    @Test
	    @DisplayName("Debería generar un número entre 1 y 100")
	    void testGenerarCodigoNumeroEnRango() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        
	        for (int i = 0; i < 1000; i++) {
	            String codigo = negocio.generarCodigo();
	            String numeroStr = codigo.substring(2); // Extraer después de "M-"
	            int numero = Integer.parseInt(numeroStr);
	            
	            assertTrue(numero >= 1 && numero <= 100, 
	                "El número debe estar entre 1 y 100, pero fue: " + numero);
	        }
	    }
	    
	    @Test
	    @DisplayName("Debería generar un código con formato válido")
	    void testGenerarCodigoFormatoValido() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();
	        
	        // Verificar formato: M- seguido de 1-3 dígitos
	        assertTrue(codigo.matches("M-\\d{1,3}"), 
	            "El formato del código es inválido: " + codigo);
	    }
	    
	    @Test
	    @DisplayName("Debería generar un número entero después del prefijo")
	    void testGenerarCodigoParteNumericaEsEntero() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();
	        
	        String parteNumerica = codigo.substring(2);
	        
	        assertDoesNotThrow(() -> Integer.parseInt(parteNumerica),
	            "La parte después de 'M-' debe ser un número entero");
	    }
	    
	    @RepeatedTest(100)
	    @DisplayName("Prueba repetida: debería generar códigos válidos consistentemente")
	    void testGenerarCodigoRepetidoValidacionCompleta() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        String codigo = negocio.generarCodigo();
	        
	        // Verificar formato
	        assertTrue(codigo.startsWith("M-"));
	        
	        // Verificar que el número está en rango
	        String numeroStr = codigo.substring(2);
	        int numero = Integer.parseInt(numeroStr);
	        assertTrue(numero >= 1 && numero <= 100);
	        
	        // Verificar que no hay caracteres adicionales
	        assertEquals(2 + String.valueOf(numero).length(), codigo.length());
	    }
	    
	    @Test
	    @DisplayName("Debería generar diferentes códigos en múltiples llamadas")
	    void testGenerarCodigoDiferentesLlamadas() {
	        NegocioMejorado negocio = new NegocioMejorado();
	        
	        String codigo1 = negocio.generarCodigo();
	        String codigo2 = negocio.generarCodigo();

}}
