package com.krakedev.artesanal.testJunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

public class TestRecuperarMaquina {
	private NegocioMejorado negocio;
    private Maquina maquina1;
    private Maquina maquina2;
    private Maquina maquina3;
    
    @BeforeEach
    void setUp() {
        negocio = new NegocioMejorado();
        
        // Crear máquinas de prueba
        maquina1 = new Maquina("Cerveza IPA", "Cerveza artesanal IPA", 0.05);
        maquina1.setCodigo("M-001");
        
        maquina2 = new Maquina("Cerveza Stout", "Cerveza artesanal Stout", 0.06);
        maquina2.setCodigo("M-002");
        
        maquina3 = new Maquina("Cerveza Lager", "Cerveza artesanal Lager", 0.04);
        maquina3.setCodigo("M-003");
        
        // Agregar máquinas al negocio
        negocio.getMaquinas().add(maquina1);
        negocio.getMaquinas().add(maquina2);
        negocio.getMaquinas().add(maquina3);
    }
    
    @Test
    @DisplayName("Debería recuperar una máquina existente por su código")
    void testRecuperarMaquinaExistente() {
        Maquina encontrada = negocio.recuperarMaquina("M-002");
        
        assertNotNull(encontrada, "La máquina debería ser encontrada");
        assertEquals("M-002", encontrada.getCodigo());
        assertEquals("Cerveza Stout", encontrada.getNombreCerveza());
        assertEquals("Cerveza artesanal Stout", encontrada.getDescripcion());
        assertEquals(0.06, encontrada.getPrecioPorMl());
    }
    
    @Test
    @DisplayName("Debería retornar null cuando el código no existe")
    void testRecuperarMaquinaInexistente() {
        Maquina encontrada = negocio.recuperarMaquina("M-999");
        
        assertNull(encontrada, "Debería retornar null para código inexistente");
    }
    
    @Test
    @DisplayName("Debería recuperar la primera máquina de la lista")
    void testRecuperarPrimeraMaquina() {
        Maquina encontrada = negocio.recuperarMaquina("M-001");
        
        assertNotNull(encontrada);
        assertEquals("M-001", encontrada.getCodigo());
        assertEquals("Cerveza IPA", encontrada.getNombreCerveza());
    }
    
    @Test
    @DisplayName("Debería recuperar la última máquina de la lista")
    void testRecuperarUltimaMaquina() {
        Maquina encontrada = negocio.recuperarMaquina("M-003");
        
        assertNotNull(encontrada);
        assertEquals("M-003", encontrada.getCodigo());
        assertEquals("Cerveza Lager", encontrada.getNombreCerveza());
    }
    
    @Test
    @DisplayName("Debería ser sensible a mayúsculas y minúsculas")
    void testRecuperarMaquinaCaseSensitive() {
        // El código debería ser exactamente igual
        Maquina encontrada = negocio.recuperarMaquina("m-001");
        
        assertNull(encontrada, "La búsqueda debería ser sensible a mayúsculas/minúsculas");
    }
    
    @Test
    @DisplayName("Debería retornar null para cadena vacía")
    void testRecuperarMaquinaConCadenaVacia() {
        Maquina encontrada = negocio.recuperarMaquina("");
        
        assertNull(encontrada, "Debería retornar null para cadena vacía");
    }
    
    @Test
    @DisplayName("Debería retornar null para null")
    void testRecuperarMaquinaConNull() {
        Maquina encontrada = negocio.recuperarMaquina(null);
        
        assertNull(encontrada, "Debería retornar null cuando el parámetro es null");
    }
    
    @Test
    @DisplayName("Debería encontrar la máquina correcta cuando hay códigos similares")
    void testRecuperarMaquinaConCodigosSimilares() {
        // Agregar una máquina con código similar
        Maquina maquinaSimilar = new Maquina("Cerveza Similar", "Descripción similar", 0.07);
        maquinaSimilar.setCodigo("M-0010");
        negocio.getMaquinas().add(maquinaSimilar);
        
        Maquina encontrada = negocio.recuperarMaquina("M-001");
        
        assertNotNull(encontrada);
        assertEquals("M-001", encontrada.getCodigo());
        assertEquals("Cerveza IPA", encontrada.getNombreCerveza());
    }
    
    @Nested
    @DisplayName("Pruebas con lista vacía")
    class ListaVaciaTest {
        
        @BeforeEach
        void setUpListaVacia() {
            negocio = new NegocioMejorado();
            // La lista está inicialmente vacía
        }
        
        @Test
        @DisplayName("Debería retornar null cuando la lista está vacía")
        void testRecuperarMaquinaListaVacia() {
            Maquina encontrada = negocio.recuperarMaquina("M-001");
            
            assertNull(encontrada, "Con lista vacía debería retornar null");
        }
    }
    
    @Nested
    @DisplayName("Pruebas con máquinas agregadas mediante el método agregarMaquina")
    class AgregarMaquinaTest {
        
        @Test
        @DisplayName("Debería recuperar máquina agregada con agregarMaquina")
        void testRecuperarMaquinaAgregada() {
            NegocioMejorado nuevoNegocio = new NegocioMejorado();
            nuevoNegocio.agregarMaquina("Cerveza Ruby", "Cerveza artesanal Ruby", 0.08);
            
            // Obtener el código generado automáticamente
            String codigoGenerado = nuevoNegocio.getMaquinas().get(0).getCodigo();
            
            Maquina encontrada = nuevoNegocio.recuperarMaquina(codigoGenerado);
            
            assertNotNull(encontrada);
            assertEquals("Cerveza Ruby", encontrada.getNombreCerveza());
        }
    }
}
