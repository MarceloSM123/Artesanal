package com.krakedev.artesanal.testJunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.NegocioMejorado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

public class TestConsultarValorVendido {
private NegocioMejorado negocio;
    
    @BeforeEach
    void setUp() {
        negocio = new NegocioMejorado();
    }
    
    @Test
    @DisplayName("Debería retornar 0 cuando no hay clientes registrados")
    void testConsultarValorVendidoSinClientes() {
        double totalVendido = negocio.consultarValorVendido();
        
        assertEquals(0.0, totalVendido, 0.001, 
            "Sin clientes, el total vendido debería ser 0");
    }
    
    @Test
    @DisplayName("Debería retornar 0 cuando hay clientes pero no han consumido nada")
    void testConsultarValorVendidoClientesSinConsumo() {
        // Registrar clientes sin consumo
        negocio.registrarCliente("Juan Pérez", "1234567890");
        negocio.registrarCliente("María López", "0987654321");
        
        double totalVendido = negocio.consultarValorVendido();
        
        assertEquals(0.0, totalVendido, 0.001, 
            "Clientes sin consumo deberían sumar 0");
    }
    
    @Test
    @DisplayName("Debería sumar correctamente el consumo de un solo cliente")
    void testConsultarValorVendidoUnCliente() {
        // Registrar cliente
        negocio.registrarCliente("Juan Pérez", "1234567890");
        
        // Agregar máquina
        negocio.agregarMaquina("IPA", "Cerveza IPA", 0.05);
        String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
        
        // Realizar consumo
        double consumo1 = negocio.consumirCerveza(100, codigoMaquina, 10);
        double consumo2 = negocio.consumirCerveza(100, codigoMaquina, 15);
        
        double totalVendido = negocio.consultarValorVendido();
        double esperado = consumo1 + consumo2;
        
        assertEquals(esperado, totalVendido, 0.001, 
            "El total vendido debería ser la suma de los consumos del cliente");
    }
    
    @Test
    @DisplayName("Debería sumar correctamente el consumo de múltiples clientes")
    void testConsultarValorVendidoMultiplesClientes() {
        // Agregar máquina
        negocio.agregarMaquina("IPA", "Cerveza IPA", 0.05);
        String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
        
        // Registrar y consumir para cliente 1
        negocio.registrarCliente("Juan Pérez", "1234567890");
        double consumoCliente1 = negocio.consumirCerveza(100, codigoMaquina, 20);
        
        // Registrar y consumir para cliente 2
        negocio.registrarCliente("María López", "0987654321");
        double consumoCliente2 = negocio.consumirCerveza(101, codigoMaquina, 30);
        
        // Registrar y consumir para cliente 3
        negocio.registrarCliente("Carlos Ruiz", "1112223334");
        double consumoCliente3 = negocio.consumirCerveza(102, codigoMaquina, 15);
        
        double totalVendido = negocio.consultarValorVendido();
        double esperado = consumoCliente1 + consumoCliente2 + consumoCliente3;
        
        assertEquals(esperado, totalVendido, 0.001, 
            "El total vendido debería ser la suma de todos los consumos");
    }
    
    @Test
    @DisplayName("Debería sumar correctamente cuando hay consumos con decimales")
    void testConsultarValorVendidoConDecimales() {
        // Agregar máquina con precio específico
        negocio.agregarMaquina("Premium", "Cerveza Premium", 0.075);
        String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
        
        // Registrar cliente
        negocio.registrarCliente("Ana García", "5556667778");
        
        // Consumir cantidades con decimales
        double consumo1 = negocio.consumirCerveza(100, codigoMaquina, 10.5);
        double consumo2 = negocio.consumirCerveza(100, codigoMaquina, 7.25);
        double consumo3 = negocio.consumirCerveza(100, codigoMaquina, 3.33);
        
        double totalVendido = negocio.consultarValorVendido();
        double esperado = consumo1 + consumo2 + consumo3;
        
        assertEquals(esperado, totalVendido, 0.001, 
            "El total vendido debería manejar correctamente decimales");
    }
    
    @Test
    @DisplayName("Debería retornar el valor correcto después de consumos secuenciales")
    void testConsultarValorVendidoDespuesDeCadaConsumo() {
        // Agregar máquina
        negocio.agregarMaquina("Lager", "Cerveza Lager", 0.04);
        String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
        
        // Registrar cliente
        negocio.registrarCliente("Pedro Gómez", "9998887776");
        
        // Verificar después de primer consumo
        double consumo1 = negocio.consumirCerveza(100, codigoMaquina, 25);
        assertEquals(consumo1, negocio.consultarValorVendido(), 0.001);
        
        // Verificar después de segundo consumo
        double consumo2 = negocio.consumirCerveza(100, codigoMaquina, 35);
        assertEquals(consumo1 + consumo2, negocio.consultarValorVendido(), 0.001);
        
        // Verificar después de tercer consumo
        double consumo3 = negocio.consumirCerveza(100, codigoMaquina, 40);
        assertEquals(consumo1 + consumo2 + consumo3, negocio.consultarValorVendido(), 0.001);
    }
    
    
    @Nested
    @DisplayName("Pruebas de integración con diferentes tipos de cerveza")
    class IntegracionMultiplesMaquinasTest {
        
        @BeforeEach
        void setupMultiplesMaquinas() {
            // Agregar múltiples máquinas con diferentes precios
            negocio.agregarMaquina("IPA", "India Pale Ale", 0.05);
            negocio.agregarMaquina("Stout", "Cerveza Negra", 0.07);
            negocio.agregarMaquina("Lager", "Cerveza Rubia", 0.04);
            negocio.agregarMaquina("Porter", "Cerveza Ambar", 0.06);
        }
        
        @Test
        @DisplayName("Debería sumar consumos de diferentes máquinas")
        void testConsumosMultiplesMaquinas() {
            // Registrar cliente
            negocio.registrarCliente("Cliente Multi", "1234567890");
            
            // Consumir de diferentes máquinas
            String codigoIPA = negocio.getMaquinas().get(0).getCodigo();
            String codigoStout = negocio.getMaquinas().get(1).getCodigo();
            String codigoLager = negocio.getMaquinas().get(2).getCodigo();
            
            double consumoIPA = negocio.consumirCerveza(100, codigoIPA, 30);
            double consumoStout = negocio.consumirCerveza(100, codigoStout, 20);
            double consumoLager = negocio.consumirCerveza(100, codigoLager, 50);
            
            double totalVendido = negocio.consultarValorVendido();
            double esperado = consumoIPA + consumoStout + consumoLager;
            
            assertEquals(esperado, totalVendido, 0.001);
        }
        
        @Test
        @DisplayName("Debería sumar consumos de múltiples clientes con diferentes máquinas")
        void testMultiplesClientesMultiplesMaquinas() {
            String codigoIPA = negocio.getMaquinas().get(0).getCodigo();
            String codigoStout = negocio.getMaquinas().get(1).getCodigo();
            
            // Cliente 1
            negocio.registrarCliente("Cliente 1", "1111111111");
            double consumo1 = negocio.consumirCerveza(100, codigoIPA, 25);
            
            // Cliente 2
            negocio.registrarCliente("Cliente 2", "2222222222");
            double consumo2 = negocio.consumirCerveza(101, codigoIPA, 35);
            double consumo2b = negocio.consumirCerveza(101, codigoStout, 15);
            
            // Cliente 3
            negocio.registrarCliente("Cliente 3", "3333333333");
            double consumo3 = negocio.consumirCerveza(102, codigoStout, 40);
            
            double totalVendido = negocio.consultarValorVendido();
            double esperado = consumo1 + consumo2 + consumo2b + consumo3;
            
            assertEquals(esperado, totalVendido, 0.001);
        }
    }
    
    @Nested
    @DisplayName("Pruebas de casos límite")
    class CasosLimiteTest {
        
        @Test
        @DisplayName("Debería acumular correctamente con muchos clientes")
        void testMuchosClientes() {
            // Agregar una máquina
            negocio.agregarMaquina("Light", "Cerveza Light", 0.03);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            
            double totalEsperado = 0;
            
            // Registrar 50 clientes con diferentes consumos
            for (int i = 0; i < 50; i++) {
                negocio.registrarCliente("Cliente " + i, String.valueOf(1000000000 + i));
                double consumo = negocio.consumirCerveza(100 + i, codigoMaquina, i * 2);
                totalEsperado += consumo;
            }
            
            double totalVendido = negocio.consultarValorVendido();
            
            assertEquals(totalEsperado, totalVendido, 0.001, 
                "El total debería acumular correctamente 50 clientes");
        }
        
        @Test
        @DisplayName("Debería mantener el total después de consumos fallidos")
        void testTotalConsumosFallidos() {
            // Agregar máquina
            negocio.agregarMaquina("Test", "Cerveza Test", 0.05);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            
            // Registrar cliente
            negocio.registrarCliente("Cliente Válido", "1234567890");
            
            // Consumo exitoso
            double consumoExitoso = negocio.consumirCerveza(100, codigoMaquina, 20);
            
            // Consumos fallidos (máquina no existe, cliente no existe)
            negocio.consumirCerveza(100, "CODIGO_INVALIDO", 10);
            negocio.consumirCerveza(999, codigoMaquina, 10);
            
            double totalVendido = negocio.consultarValorVendido();
            
            assertEquals(consumoExitoso, totalVendido, 0.001,
                "Los consumos fallidos no deberían afectar el total");
        }
    }
    
    @Nested
    @DisplayName("Pruebas de precisión numérica")
    class PrecisionNumericaTest {
        
        @Test
        @DisplayName("Debería mantener precisión con valores pequeños")
        void testPrecisionValoresPequenos() {
            negocio.agregarMaquina("Precise", "Cerveza Precisa", 0.001);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            negocio.registrarCliente("Cliente Preciso", "1234567890");
            
            double consumo1 = negocio.consumirCerveza(100, codigoMaquina, 0.1);
            double consumo2 = negocio.consumirCerveza(100, codigoMaquina, 0.2);
            double consumo3 = negocio.consumirCerveza(100, codigoMaquina, 0.3);
            
            double totalVendido = negocio.consultarValorVendido();
            double esperado = consumo1 + consumo2 + consumo3;
            
            assertEquals(esperado, totalVendido, 0.000001);
        }
        
        @Test
        @DisplayName("Debería sumar correctamente muchos consumos pequeños")
        @RepeatedTest(5)
        void testMuchosConsumosPequenos() {
            negocio.agregarMaquina("Micro", "Micro Cerveza", 0.0001);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            negocio.registrarCliente("Cliente Micro", "1234567890");
            
            double totalEsperado = 0;
            
            // Realizar 100 consumos pequeños
            for (int i = 0; i < 100; i++) {
                double consumo = negocio.consumirCerveza(100, codigoMaquina, 0.01);
                totalEsperado += consumo;
            }
            
            double totalVendido = negocio.consultarValorVendido();
            
            assertEquals(totalEsperado, totalVendido, 0.000001);
        }
    }
    
    @Nested
    @DisplayName("Pruebas de consistencia de datos")
    class ConsistenciaTest {
        
        @Test
        @DisplayName("El total vendido no debería cambiar si no hay nuevos consumos")
        void testTotalVendidoConstanteSinConsumos() {
            // Configuración inicial
            negocio.agregarMaquina("Constante", "Cerveza Constante", 0.05);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            negocio.registrarCliente("Cliente Constante", "1234567890");
            
            // Consumo inicial
            double consumoInicial = negocio.consumirCerveza(100, codigoMaquina, 50);
            double totalDespuesConsumo = negocio.consultarValorVendido();
            
            // Verificar múltiples veces sin nuevos consumos
            for (int i = 0; i < 10; i++) {
                assertEquals(totalDespuesConsumo, negocio.consultarValorVendido(), 0.001,
                    "El total no debería cambiar sin nuevos consumos");
            }
        }
        
        @Test
        @DisplayName("El total debería ser consistente con los consumos individuales")
        void testConsistenciaConSumaIndividual() {
            negocio.agregarMaquina("Consistente", "Cerveza Consistente", 0.06);
            String codigoMaquina = negocio.getMaquinas().get(0).getCodigo();
            
            // Registrar múltiples clientes y consumos
            negocio.registrarCliente("Cliente A", "1111111111");
            negocio.registrarCliente("Cliente B", "2222222222");
            negocio.registrarCliente("Cliente C", "3333333333");
            
            double consumoA1 = negocio.consumirCerveza(100, codigoMaquina, 10);
            double consumoA2 = negocio.consumirCerveza(100, codigoMaquina, 20);
            double consumoB1 = negocio.consumirCerveza(101, codigoMaquina, 15);
            double consumoC1 = negocio.consumirCerveza(102, codigoMaquina, 25);
            double consumoC2 = negocio.consumirCerveza(102, codigoMaquina, 30);
            
            double sumaIndividual = consumoA1 + consumoA2 + consumoB1 + consumoC1 + consumoC2;
            double totalVendido = negocio.consultarValorVendido();
            
            assertEquals(sumaIndividual, totalVendido, 0.001,
                "El total debe ser consistente con la suma de consumos individuales");
        }
    }
}
