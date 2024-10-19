/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ronyc
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexionBD {
    //public int opcionMenu;
    
    private static final String URL = "jdbc:mysql://localhost:3306/BDBarPirata";
    private static final String USER = "root";
    private static final String PASSWORD = "SnT!ag0";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }// fin conectar()
    
    public static void insertarProducto(String codigo,String nombre, double precio, int cantidad, String fecha) {
        //ConexionBD updProducto = new ConexionBD();
        //updProducto.datosManage();  
    String query = "INSERT INTO producto (idProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?, ?)";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, codigo);
        pst.setString(2, nombre);
        pst.setDouble(3, precio);
        pst.setInt(4, cantidad);
        pst.setDate(5, java.sql.Date.valueOf(fecha));
        pst.executeUpdate();
        System.out.println("Producto insertado correctamente");
    } catch (SQLException e) {
    }
}// fin insertarProducto()
    
    /*
    public static void buscarProducto(String idProducto){
        String query = "select * from producto WHERE idProducto = ?";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            
            boolean hayResultados = false;
            System.out.println("Código: " + rs.getString("idProducto"));
            System.out.println("Nombre: " + rs.getString("nombreProducto"));
            System.out.println("Precio: " + rs.getDouble("precioUnitario"));
            System.out.println("Cantidad: " + rs.getInt("cantidadProducto"));
            System.out.println("Fecha de Vencimiento: " + rs.getDate("fechaVencimiento"));     
            System.out.println("");
        } catch (SQLException e) {
        
    }//fin catch
        
    }//FINbUSCARpRODUCTO
    */
    
    
        public static void buscarProducto(String codigoProducto) {
        String query = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Código: " + rs.getString("idProducto"));
                System.out.println("Nombre: " + rs.getString("nombreProducto"));
                System.out.println("Precio: " + rs.getDouble("precioUnitario"));
                System.out.println("Cantidad: " + rs.getInt("cantidadProducto"));
                System.out.println("Fecha de Vencimiento: " + rs.getDate("fechaVencimiento"));
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
    }
    
    public static void listarProductos() {
    String query = "select * from producto;";  
    try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
        boolean hayResultados = false;
        while (rs.next()) {
            hayResultados = true; 
            System.out.println("Código: " + rs.getString("idProducto"));
            System.out.println("Nombre: " + rs.getString("nombreProducto"));
            System.out.println("Precio: " + rs.getDouble("precioUnitario"));
            System.out.println("Cantidad: " + rs.getInt("cantidadProducto"));
            System.out.println("Fecha de Vencimiento: " + rs.getDate("fechaVencimiento"));     
            System.out.println("");
        }
        if (!hayResultados) {
            System.out.println("No hay productos disponibles.");
        }//fin if
     
    } catch (SQLException e) {
        
    }//fin catch
}// fin listarProductos()
    
    public static void actualizarProducto(String idProducto, String nombre, double precio) {
    String query = "UPDATE producto SET nombreProducto = ?, precioUnitario = ? WHERE idProducto = ?";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, nombre);
        pst.setDouble(2, precio);
        pst.setString(3, idProducto);
        pst.executeUpdate();
        System.out.println("Producto actualizado correctamente");
    } catch (SQLException e) {
    }
}// fin actualizarProducto
    
public static void eliminarProducto(String idProducto) {
    String query = "DELETE FROM producto WHERE idProducto = ?";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, idProducto);
        pst.executeUpdate();
        System.out.println("Producto eliminado correctamente");
    } catch (SQLException e) {
        //e.printStackTrace();
    }
}// fin eliminarProducto


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionMenu;
        
        do{
        System.out.println("*******************************");
        System.out.println("**  Ingrese un número para   **");
        System.out.println("**  seleccionar una opción   **");
        System.out.println("**  del munú principal...    **");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("****   MENU PRINCIPAL   *******");
        System.out.println("** (1)...Ingresar Porducto   **");
        System.out.println("** (2)...Mostrar Porducto    **");
        System.out.println("** (3)...Buscar Porducto     **");
        System.out.println("** (4)...Modificar Porducto  **");
        System.out.println("** (5)...Eliminar Porducto   **");
        System.out.println("** (6)...Salir del menú      **");
        System.out.println("*******************************");
        // Leer el valor entero ingresado por el usuario
        opcionMenu = scanner.nextInt();
        //scanner.nextLine(); // Limpiar el buffer
        switch (opcionMenu) {
            case 1:
                scanner.nextLine(); // Limpiar el buffer
                System.out.println("Ingrese código del producto:");
                String codigo = scanner.nextLine();
                System.out.println("Ingrese nombre del producto:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese precio unitario:");
                double precio = scanner.nextDouble();
                System.out.println("Ingrese cantidad:");
                int cantidad = scanner.nextInt();
                System.out.println("Ingrese fecha de vencimiento (YYYY-MM-DD):");
                String fecha = scanner.nextLine();
                insertarProducto(codigo, nombre, precio, cantidad, fecha);
                //insertarProducto(codigo,nombre,precio,cantidad,fecha)
                break;
            case 2:
                listarProductos();
                break;
            case 3:
                scanner.nextLine(); // Limpiar el buffer
                System.out.println("Ingrese código del producto:");
                String codigoProductosh = scanner.nextLine();
                buscarProducto(codigoProductosh);
                
                break;
            case 4:
                scanner.nextLine(); // Limpiar el buffer
                System.out.println("Ingrese código del producto:");
                String codigoProducto = scanner.nextLine();
                System.out.println("Ingrese nombre del producto:");
                String nombreP = scanner.nextLine();
                System.out.println("Ingrese precio unitario:");
                double precioP = scanner.nextDouble();
                actualizarProducto(codigoProducto, nombreP, precioP);
                break;
            case 5:
                scanner.nextLine(); // Limpiar el buffer
                System.out.println("Ingrese código del producto:");
                String codigoSch = scanner.nextLine();
                eliminarProducto(codigoSch); 
                break;
            case 6:
                System.out.println("**    Operación cancelada por      ***");
                System.out.println("**    el usuario.                  ***");
                System.out.println("**    ****SALIENDO....****         ***");
                System.out.println("**************************************");
                break;
        }//FinSwitch
             } while (opcionMenu <= 5);
        
        
        
        
        
        
        //Scanner scanner = new Scanner(System.in);
        
        //conectar();
        //insertarProducto("VRG100", "Virogrip", 50, 150, "2024-10-31");
        //listarProductos();
        //actualizarProducto("VRG100","Virogrip funcional",150.99);
        //eliminarProducto("VRG100");
        //menuOpt(); for mi 
        //listarProductos();
        
    }
}// fin main
