package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.*;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedidoExitoso() {
        assertDoesNotThrow(() -> restaurante.iniciarPedido("Cliente Test", "Dirección Test"));
        assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no debería ser null");
    }

    @Test
    public void testIniciarPedidoYaHayPedido() {
        assertDoesNotThrow(() -> restaurante.iniciarPedido("Cliente Test", "Dirección Test"));
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> restaurante.iniciarPedido("Otro Cliente", "Otra Dirección"));
    }

    @Test
    public void testCerrarYGuardarPedido() {
        try {
            restaurante.iniciarPedido("Cliente Test", "Dirección Test");
            restaurante.cerrarYGuardarPedido();

            
            assertNull(restaurante.getPedidoEnCurso(), "No debería haber un pedido en curso después de cerrarlo");

            File factura = new File("./facturas/factura_" + (restaurante.getPedidos().size() - 1) + ".txt");
            assertTrue(factura.exists(), "El archivo de la factura debería existir");
            
            factura.delete();
        } catch (IOException | YaHayUnPedidoEnCursoException | NoHayPedidoEnCursoException e) {
            fail("Error durante la prueba: " + e.getMessage());
        }
    }

    @Test
    public void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> restaurante.cerrarYGuardarPedido(), 
                     "Debería lanzar NoHayPedidoEnCursoException si no hay un pedido activo");
    }

    @Test
    public void testCargarInformacionRestaurante() {
        File archivoIngredientes = new File("ruta/archivoIngredientes.txt");
        File archivoMenu = new File("ruta/archivoMenu.txt");
        File archivoCombos = new File("ruta/archivoCombos.txt");

        try {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            
            assertFalse(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes no debería estar vacía");
            assertFalse(restaurante.getMenuBase().isEmpty(), "La lista del menú base no debería estar vacía");
            assertFalse(restaurante.getMenuCombos().isEmpty(), "La lista de combos no debería estar vacía");
        } catch (Exception e) {
            fail("Excepción inesperada durante la carga de información: " + e.getMessage());
        }
    }

    @Test
    public void testIngredienteRepetidoException() {
        File archivoIngredientes = new File("ruta/ingredientesDuplicados.txt");
        assertThrows(IngredienteRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(archivoIngredientes, new File("ruta/vacio.txt"), new File("ruta/vacio.txt")),
                     "Debería lanzar IngredienteRepetidoException si hay ingredientes duplicados");
    }

    @Test
    public void testProductoRepetidoException() {
        File archivoMenu = new File("ruta/productosDuplicados.txt"); // Archivo con productos duplicados
        assertThrows(ProductoRepetidoException.class, () -> restaurante.cargarInformacionRestaurante(new File("ruta/vacio.txt"), archivoMenu, new File("ruta/vacio.txt")),
                     "Debería lanzar ProductoRepetidoException si hay productos duplicados");
    }
}