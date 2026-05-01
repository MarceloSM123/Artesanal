package com.krakedev.artesanal.testJunit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestIaVaciar {
private Maquina maquina;
    
    @BeforeEach
    public void setUp() {
        // Crear una máquina con datos de prueba
        maquina = new Maquina("Pilsener", "Cerveza rubia", 0.05, 10000);
        // Llenar la máquina para tener cantidadActual > 0
        maquina.llenarMaquina();
    }
    
    @Test
    public void testVaciarMaquina() {
        // Verificar estado inicial ANTES de vaciar
        double cantidadActualAntes = maquina.getCantidadActual();
        double desperdicioAntes = maquina.getCantidadDesperdicio();
        
        // Verificar que hay cantidad actual para vaciar
        assertTrue(cantidadActualAntes > 0, "La máquina debería tener cantidad actual > 0 antes de vaciar");
        assertEquals(0, desperdicioAntes, "El desperdicio inicial debería ser 0");
        
        // EJECUTAR el método a probar
        maquina.vaciarMaquina();
        
        // VERIFICAR resultados DESPUÉS de vaciar
        // 1. La cantidad actual debe ser 0
        assertEquals(0, maquina.getCantidadActual(), "La cantidad actual debe ser 0 después de vaciar");
        
        // 2. El desperdicio debe ser igual a la cantidad que había antes
        assertEquals(cantidadActualAntes, maquina.getCantidadDesperdicio(), 
                     "El desperdicio debe ser igual a la cantidad que había antes de vaciar");
    }
    
    @Test
    public void testVaciarMaquinaConCantidadActualCero() {
        // Crear una máquina nueva (cantidadActual = 0)
        Maquina maquinaVacia = new Maquina("Club", "Cerveza rubia", 0.05);
        
        // Verificar estado inicial
        assertEquals(0, maquinaVacia.getCantidadActual());
        assertEquals(0, maquinaVacia.getCantidadDesperdicio());
        
        // Ejecutar método
        maquinaVacia.vaciarMaquina();
        
        // Verificar que sigue igual cuando ya está vacía
        assertEquals(0, maquinaVacia.getCantidadActual(), "Sigue siendo 0");
        assertEquals(0, maquinaVacia.getCantidadDesperdicio(), "Desperdicio sigue siendo 0");
    }
    
    @Test
    public void testVaciarMaquinaDespuesDeServir() {
        // Servir algo de cerveza primero
        double cantidadServida = 500;
        maquina.servirCerveza(cantidadServida);
        
        double cantidadAntesDeVaciar = maquina.getCantidadActual();
        
        // Vaciar la máquina
        maquina.vaciarMaquina();
        
        // Verificar
        assertEquals(0, maquina.getCantidadActual());
        assertEquals(cantidadAntesDeVaciar, maquina.getCantidadDesperdicio());
    }
	
}
