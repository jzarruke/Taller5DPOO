package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ProductoMenu producto3;
	private ProductoMenu producto4;
	private ArrayList<ProductoMenu> ListaProductosCombo1;
	private ArrayList<ProductoMenu> ListaProductosCombo2;
	private Combo combo1;
	private Combo combo2;
	private Pedido pedido1;
	private File archivo;

    @BeforeEach
    void setUp() throws Exception {
    	producto1 = new ProductoMenu("Hamburguesa Triple", 25000);
    	producto2 = new ProductoMenu("Papas Grandes", 2500);
    	ListaProductosCombo1 = new ArrayList<ProductoMenu>();
    	ListaProductosCombo1.add(producto1);
    	ListaProductosCombo1.add(producto2);
    	combo1 = new Combo("Hamburguesa y papas XL", 0.1, ListaProductosCombo1);
    	producto3 = new ProductoMenu("Hamburguesa Doble", 23000);
    	producto4 = new ProductoMenu("Papas Medianas", 2000);
    	ListaProductosCombo2 = new ArrayList<ProductoMenu>();
    	ListaProductosCombo2.add(producto3);
    	ListaProductosCombo2.add(producto4);
    	combo2 = new Combo("Hamburguesa y papas L", 0.15, ListaProductosCombo2);
    	pedido1 = new Pedido("Jeronimo Vasquez", "Carrera 7#88-62, Casa 14");
    	pedido1.agregarProducto(combo1);
    	pedido1.agregarProducto(combo2);
    }

    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void testGetIdPedido() {
        assertEquals(1, pedido1.getIdPedido(), "El ID del pedido no es el esperado.");
    }

    @Test
    void testGetNombreCliente() {
        assertEquals("Jeronimo Vasquez", pedido1.getNombreCliente(), "El nombre del cliente no es el esperado.");
    }
    
    @Test
    void testGetPrecioTotal() {
    	assertEquals(54740, pedido1.getPrecioTotalPedido(), "El precio total del pedido no es el esperado.");
    }
    
    @Test
    void testGenerarTextoFactura() {
    	assertEquals("Cliente: Jeronimo Vasquez\nDirección: Carrera 7#88-62, Casa 14\n----------------\n"
    			+ "Combo Hamburguesa y papas XL\n Descuento: 0.1\n            24750\n"
    			+ "Combo Hamburguesa y papas L\n Descuento: 0.15\n            21250\n"
    			+ "----------------\nPrecio Neto:  46000\nIVA:          8740\nPrecio Total: 54740\n",
    			pedido1.generarTextoFactura(), "La factura del pedido no es la esperada.");
    }
    
    @Test
    void testGuardarFactura() {
    	try {
    		archivo = File.createTempFile("Factura1", ".txt");
    		pedido1.guardarFactura(archivo);
    		
    		assertEquals("Cliente: Jeronimo Vasquez\nDirección: Carrera 7#88-62, Casa 14\n----------------\n"
    			+ "Combo Hamburguesa y papas XL\n Descuento: 0.1\n            24750\n"
    			+ "Combo Hamburguesa y papas L\n Descuento: 0.15\n            21250\n"
    			+ "----------------\nPrecio Neto:  46000\nIVA:          8740\nPrecio Total: 54740\n",
    			Files.readString(Path.of(archivo.getPath())), "El contenido de la factura guardada no es el esperado.");
    	} catch (FileNotFoundException e) {
			fail("No se ha podido crear el archivo para guardar la factura.");
		} catch (IOException e) {
			fail("Hubo un error al leer el archivo de la factura.");
		} finally {
			if (archivo != null) {
				archivo.delete();
			}
		}
    }
}