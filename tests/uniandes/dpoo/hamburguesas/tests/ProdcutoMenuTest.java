package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProdcutoMenuTest {
	private ProductoMenu producto1;

    @BeforeEach
    void setUp() throws Exception {
    	producto1 = new ProductoMenu("Hamburguesa", 17000);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa", producto1.getNombre(), "El nombre del producto no es el esperado.");
    }

    @Test
    void testGetPrecio() {
    	assertEquals(17000, producto1.getPrecio(), "El precio del producto no es el esperado.");
    }
    
    @Test
    void testGenerarFactura() {
    	assertEquals("Hamburguesa\n            17000\n", producto1.generarTextoFactura(), "La factura del producto no es la esperada.");
    }
}