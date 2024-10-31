package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ArrayList<ProductoMenu> ListaProductos;
	private Combo combo1;

    @BeforeEach
    void setUp() throws Exception {
    	producto1 = new ProductoMenu("Hamburguesa", 17000);
    	producto2 = new ProductoMenu("Papas", 1500);
    	ListaProductos = new ArrayList<ProductoMenu>();
    	ListaProductos.add(producto1);
    	ListaProductos.add(producto2);
    	combo1 = new Combo("Hamburguesa y papas", 0.1, ListaProductos);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa y papas", combo1.getNombre(), "El nombre del combo no es el esperado.");
    }

    @Test
    void testGetPrecio() {
    	assertEquals(16650, combo1.getPrecio(), "El precio del combo no es el esperado.");
    }
    
    @Test
    void testGenerarFactura() {
    	assertEquals("Combo Hamburguesa y papas\n Descuento: 0.1\n            16650\n", combo1.generarTextoFactura(), "La factura del combo no es la esperada.");
    }
}