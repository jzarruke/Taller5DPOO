package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProdcutoAjustadoTest {
	private ProductoAjustado producto1;
	private Ingrediente ingredienteAgregar;
	private Ingrediente ingredienteEliminar;

    @BeforeEach
    void setUp() throws Exception {
    	producto1 = new ProductoAjustado(new ProductoMenu("Hamburguesa", 17000));
    	ingredienteAgregar = new Ingrediente("Tocino", 1500);
    	ingredienteEliminar = new Ingrediente("Tomate", 1000);
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
    	assertEquals(18500, producto1.getPrecio(), "El precio del producto no es el esperado.");
    }
    
    @Test
    void testGenerarFactura() {
    	assertEquals("Hamburguesa\n            17000\n    +Tocino                1500    -Tomate            18500\n", producto1.generarTextoFactura(), "La factura del producto no es la esperada.");
    }
}