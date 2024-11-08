import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ConexionBD extends JFrame implements ActionListener {
    private static final String URL = "jdbc:mysql://localhost:3306/BDBar";
    private static final String USER = "root";
    private static final String PASSWORD = "SnT!ag0";

    private JButton btnDesp, btnNewInvent, btnInsertar, btnMostrar, btnBuscar, btnModificar, btnRepVentas, btnEliminar, btnSalir;

    public ConexionBD() {
        setTitle("Gestión de Productos");
        setSize(375, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(375,700));//Esta dimensiones del panel de gestión de productos
        panel.setLayout(null);//Deshabilitar el diseño por default del panel
        panel.setBackground(new Color(139, 135, 91)); // Gris claro//Establece color a la etiqueta.
        setIconImage(new ImageIcon(getClass().getResource("pirata47.png")).getImage());//Setea ícono - Logotipo _import javax.swing.JFrame;
        
        
        
        btnDesp = new JButton("Despachar");
        btnDesp.setBounds(5, 5,150,40);
        btnDesp.setBackground(new Color(43, 70, 73)); // Rojo
        btnDesp.setForeground(Color.WHITE); // Blanco
        
        btnNewInvent = new JButton("Nuevo Inventario");
        btnNewInvent.setBounds(200, 5,150,40);
        btnNewInvent.setBackground(new Color(43, 70, 73)); // Rojo
        btnNewInvent.setForeground(Color.WHITE); // Blanco        
        
        
        JLabel etiquetaImagen = new JLabel();
        etiquetaImagen.setBounds(20, 70, 300, 300); // Posición y tamaño de la etiqueta
        //etiquetaImagen.setIcon(new ImageIcon("C:/Users/ronyc/Documents/4to. Ciclo 2 2024/5. Progra_II/MySQL/NetBeansConnector/BDConnectorMySQL/src/Pirata.png")); // Agregar imagen
        etiquetaImagen.setIcon(new ImageIcon(new ImageIcon("C:/Users/ronyc/Documents/4to. Ciclo 2 2024/5. Progra_II/MySQL/NetBeansConnector/BDConnectorMySQL/src/Pirata.png").getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH)));
        
        
        
        btnMostrar = new JButton("Mostrar Productos");
        btnMostrar.setBounds(5, 400,150,40);
        btnMostrar.setBackground(new Color(43, 70, 73)); // Rojo
        btnMostrar.setForeground(Color.WHITE); // Blanco
        
        btnBuscar = new JButton("Buscar Producto");
        btnBuscar.setBounds(200, 400,150,40);
        btnBuscar.setBackground(new Color(43, 70, 73)); // Rojo
        btnBuscar.setForeground(Color.WHITE); // Blanco        
        
        
        btnInsertar = new JButton("Ingresar Producto");
        btnInsertar.setBounds(5, 450,150,40);
        btnInsertar.setBackground(new Color(43, 70, 73)); // Rojo
        btnInsertar.setForeground(Color.WHITE); // Blanco
        

        btnModificar = new JButton("Actualizar Precio");
        btnModificar.setBounds(200, 450,150,40);
        btnModificar.setBackground(new Color(43, 70, 73)); // Rojo
        btnModificar.setForeground(Color.WHITE); // Blanco        

        btnRepVentas = new JButton("Reporte de ventas");
        btnRepVentas.setBounds(100, 510,150,30);
        btnRepVentas.setBackground(new Color(37, 151, 151)); // Rojo
        btnRepVentas.setForeground(Color.WHITE); // Blanco        
        
        btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setBounds(100, 545,150,30);
        btnEliminar.setBackground(new Color(110, 1, 1)); // Rojo
        btnEliminar.setForeground(Color.WHITE); // Blanco
        
        btnSalir = new JButton("S A L I R");
        btnSalir.setBounds(100, 600,150,30);
        btnSalir.setBackground(new Color(43, 70, 73)); // Rojo
        btnSalir.setForeground(Color.WHITE); // Blanco
        //btnSalir.setIcon(new ImageIcon(new ImageIcon("Salir.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        //btnSalir.setIcon(new ImageIcon("Salir.png"));
        //btnSalir.setIcon(new ImageIcon("C:/Users/ronyc/Documents/4to. Ciclo 2 2024/5. Progra_II/MySQL/NetBeansConnector/BDConnectorMySQL/src/Salir.png"));

        // Añadir botones al panel
        panel.add(btnDesp);
        panel.add(btnNewInvent);
        panel.add(btnInsertar);
        panel.add(btnMostrar);
        panel.add(btnBuscar);
        panel.add(btnModificar);
        panel.add(btnRepVentas);
        panel.add(btnEliminar);
        panel.add(btnSalir);
        panel.add(etiquetaImagen);

        // Asignar ActionListener a cada botón
        btnDesp.addActionListener(this);
        btnNewInvent.addActionListener(this);
        btnInsertar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnRepVentas.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnSalir.addActionListener(this);

        add(panel);
    }

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }

    public static void insertarProducto(String codigo, String nombre, double precio, int cantidad, /*int vendido,*/ String fecha) {
        String query = "INSERT INTO producto (idProducto, nombreProducto, precioUnitario, cantidadProducto, ventaProducto, fechaVencimiento) VALUES (?, ?, ?, ?, 0, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setDouble(3, precio);
            pst.setInt(4, cantidad);
            //pst.setInt(5, vendido);
            pst.setDate(5, Date.valueOf(fecha));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto insertado correctamente", "Insertar Producto", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar producto", "Insertar Producto", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void listarProductos() {
        String query = "SELECT * FROM producto";
        JFrame frame = new JFrame("Lista de Productos");
        frame.setIconImage(new ImageIcon("C:/Users/ronyc/Documents/4to. Ciclo 2 2024/5. Progra_II/MySQL/NetBeansConnector/BDConnectorMySQL/src/Pirata47.png").getImage());
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        //frame.setBackground(new Color(139, 135, 91)); // Rojo
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Configura que se cierre solo la ventana actual (no el programa entero) cuando se cierre.

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            boolean hayProductos = false;

            while (rs.next()) {
                hayProductos = true;
                String productoInfo =
                        "<html>Código: " + rs.getString("idProducto") +
                                      "<br>Nombre: " + rs.getString("nombreProducto") +
                                      "<br>Precio: " + rs.getDouble("precioUnitario") +
                                      "<br>Cant.Disp: " + rs.getInt("cantidadProducto") +
                                      "<br>Vendidos: " + rs.getInt("ventaProducto") +
                                      "<br>Fecha de Vencimiento: " + rs.getDate("fechaVencimiento") + 
                                      "<br>--------------------------</html>";

                JLabel labelProducto = new JLabel(productoInfo);
                panel.add(labelProducto);
            }

            if (!hayProductos) {
                JLabel noProductos = new JLabel("No hay productos disponibles.");
                panel.add(noProductos);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        
         //Agrega el panel a un JScrollPane, que permitirá desplazarse hacia abajo si la lista de productos es extensa.
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public static void buscarProducto(String codigoProducto) {
        String query = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String producto = "Código: " + rs.getString("idProducto") +
                                  "\nNombre: " + rs.getString("nombreProducto") +
                                  "\nPrecio: " + rs.getDouble("precioUnitario") +
                                  "\nCant.Disp: " + rs.getInt("cantidadProducto") +
                                  "\nVendidos: " + rs.getInt("ventaProducto") +
                                  "\nFecha de Vencimiento: " + rs.getDate("fechaVencimiento");
                JOptionPane.showMessageDialog(null, producto, "Producto Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.", "Buscar Producto", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
    }

    public static void actualizarProducto(String idProducto, double precio) {
        String query = "UPDATE producto SET precioUnitario = ? WHERE idProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setDouble(1, precio);
            pst.setString(2, idProducto);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Actualizar Producto", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage(), "Actualizar Producto", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        public static void actualizarInventario(String idProducto, int cantidad) {
        String query = "UPDATE producto SET cantidadProducto = cantidadProducto +? WHERE idProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, cantidad);
            pst.setString(2, idProducto);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Actualizar Producto", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto: " + e.getMessage(), "Actualizar Producto", JOptionPane.ERROR_MESSAGE);
        }
    }


        public static void verificarYDespachar(String codigoProducto, int cantidadSolicitada) {
    // Consulta para obtener la cantidad disponible del producto
    String queryCantidad = "SELECT cantidadProducto FROM producto WHERE idProducto = ?";
    
    try (Connection con = conectar(); 
         PreparedStatement pstCantidad = con.prepareStatement(queryCantidad)) {
        
        // Configura el parámetro de la consulta
        pstCantidad.setString(1, codigoProducto);
        
        // Ejecuta la consulta para obtener la cantidad disponible
        ResultSet rs = pstCantidad.executeQuery();
        
        if (rs.next()) {
            int cantidadDisponible = rs.getInt("cantidadProducto");
            
            // Verifica si hay suficiente cantidad para despachar
            if (cantidadDisponible >= cantidadSolicitada) {
                // Llama al método de despacho para realizar la operación
                despacho(codigoProducto, cantidadSolicitada);
            } else {
                // Muestra un mensaje indicando que no hay suficiente cantidad
                JOptionPane.showMessageDialog(null, "Cantidad insuficiente. Disponible: " + cantidadDisponible, 
                                              "Error de Despacho", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Si el producto no se encuentra, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Producto no encontrado.", 
                                          "Buscar Producto", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar cantidad del producto: " + e.getMessage());
    }
}

public static void despacho(String idProducto, int cantidad) {
    // Consulta para actualizar la venta y la cantidad en inventario
    String queryDespacho = "UPDATE producto SET ventaProducto = ventaProducto + ?, cantidadProducto = cantidadProducto - ? WHERE idProducto = ?";
    
    try (Connection con = conectar(); 
         PreparedStatement pstDespacho = con.prepareStatement(queryDespacho)) {
        
        // Configura los parámetros de la consulta de despacho
        pstDespacho.setInt(1, cantidad);
        pstDespacho.setInt(2, cantidad);
        pstDespacho.setString(3, idProducto);
        
        // Ejecuta la actualización en la base de datos
        pstDespacho.executeUpdate();
        
        // Muestra un mensaje confirmando la operación de despacho
        JOptionPane.showMessageDialog(null, "Venta Operada Exitosamente", "Despacho", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al despachar el producto", "Despacho de Producto", JOptionPane.ERROR_MESSAGE);
    }
}


public static void reporteVentas() {
    String query = "SELECT * FROM producto WHERE ventaProducto > 0";
    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        
        // Ejecuta la consulta y obtiene todos los productos con ventas
        ResultSet rs = pst.executeQuery();
        
        StringBuilder reporte = new StringBuilder("Reporte de Ventas:\n\n"); // Acumulará el reporte completo
        
        // Recorre todos los productos con ventas y agrega la información al reporte
        while (rs.next()) {
            String producto = "Código: " + rs.getString("idProducto") +
                              "\nNombre: " + rs.getString("nombreProducto") +
                              "\nPrecio Unitario: " + rs.getDouble("precioUnitario") +
                              "\nCantidad Disponible: " + rs.getInt("cantidadProducto") +
                              "\nVendidos: " + rs.getInt("ventaProducto") +
                              "\nFecha de Vencimiento: " + rs.getDate("fechaVencimiento") +
                              "\n-----------------------------------\n";
            
            reporte.append(producto); // Añade la información de cada producto al reporte
        }
        
        if (reporte.length() > 25) { // 25 porque ese es el largo de "Reporte de Ventas:\n\n"
            // Crear JTextArea y JScrollPane para el reporte
            JTextArea textArea = new JTextArea(reporte.toString());
            textArea.setEditable(false); // El texto no se puede editar
            textArea.setLineWrap(true); // Ajuste de línea automático
            textArea.setWrapStyleWord(true); // Ajusta las líneas completas
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 400)); // Tamaño preferido para el área de scroll
            
            JOptionPane.showMessageDialog(null, scrollPane, "Reporte de Ventas", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron ventas registradas.", "Reporte de Ventas", JOptionPane.INFORMATION_MESSAGE);
        }
        
    } catch (SQLException e) {
        System.out.println("Error al generar el reporte de ventas: " + e.getMessage());
    }
}



        
        
        
        
        
    public static void eliminarProducto(String idProducto) {
        String query = "DELETE FROM producto WHERE idProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, idProducto);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente", "Eliminar Producto", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInsertar) {
            String codigo = JOptionPane.showInputDialog("Ingrese código del producto:");
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del producto:");
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio unitario:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad:"));
            //int vendido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad vendida:"));
            String fecha = JOptionPane.showInputDialog("Ingrese fecha de vencimiento (YYYY-MM-DD):");
            insertarProducto(codigo, nombre, precio, cantidad, /*vendido,*/ fecha);
        } else if (e.getSource() == btnMostrar) {
            listarProductos();
        } else if (e.getSource() == btnBuscar) {
            String codigo = JOptionPane.showInputDialog("Ingrese código del producto a buscar:");
            buscarProducto(codigo);
        } else if (e.getSource() == btnModificar) {
            String codigo = JOptionPane.showInputDialog("Ingrese código del producto a modificar:");
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese nuevo precio unitario:"));
            actualizarProducto(codigo, precio);
        } else if (e.getSource() == btnNewInvent) {
            String codigo = JOptionPane.showInputDialog("Ingrese código del producto a inventariar:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad que se suman al inventario:"));
            actualizarInventario(codigo, cantidad);
        } else if (e.getSource() == btnDesp) {
            String idProducto = JOptionPane.showInputDialog("Ingrese código del producto:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a despachar:"));
            //despacho(idProducto,cantidad);
            verificarYDespachar(idProducto, cantidad);
        }else if (e.getSource() == btnRepVentas) {
            //String idProducto = JOptionPane.showInputDialog("Ingrese código del producto:");
            //int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a despachar:"));
            //despacho(idProducto,cantidad);
            reporteVentas();
        }else if (e.getSource() == btnEliminar) {
            String codigo = JOptionPane.showInputDialog("Ingrese código del producto a eliminar:");
            eliminarProducto(codigo);
        } else if (e.getSource() == btnSalir) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

class LoginFrame extends JFrame implements ActionListener {
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIngresar;

    public LoginFrame() {
        setTitle("Bar EL PIRATA");
        setSize(375, 200);
        setLocationRelativeTo(null);//Establece la locación de la pantalla al centro. (Resolución)
        setIconImage(new ImageIcon(getClass().getResource("pirata47.png")).getImage());//Setea ícono - Logotipo _import javax.swing.JFrame;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);//Deshabilitar el diseño por default del panel
        panel.setPreferredSize(new Dimension(150,500));//Esta dimensiones de la pantalla de bienvenida
        panel.setBackground(new Color(43, 70, 73)); // Gris claro//Establece color a la etiqueta.
        //panel.setLayout(null);// Desactivar el layout manager

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 20, 100, 30);  // x, y, width, height
        lblUsuario.setFont(new Font("Arial",Font.PLAIN,16)); // Establece la fuente del texto |tipo|
        lblUsuario.setForeground(Color.white);//Establece el color de la letra.
        lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);//Método que establece la alineación del texto en la etiqueta
        
        //Cajas de texto para usuario
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 20, 150, 30);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(25, 60, 100, 30);
        lblContraseña.setFont(new Font("Arial",Font.PLAIN,16)); // Establece la fuente del texto |tipo|
        lblContraseña.setForeground(Color.white);//Establece el color de la letra.
        lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);//Método que establece la alineación del texto en la etiqueta
        
        //Caja de texto para contraseña
        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(150, 60, 150, 30);

    btnIngresar = new JButton("Ingresar");
    btnIngresar.setBounds(160, 100, 90, 20); // Aquí colocas el tamaño y la posición
    btnIngresar.addActionListener(this);

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContraseña);
        panel.add(txtContraseña);
        panel.add(new JLabel());
        panel.add(btnIngresar);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            String usuario = txtUsuario.getText();
            String contraseña = new String(txtContraseña.getPassword());

            // Verificar credenciales (ejemplo: usuario "admin", contraseña "1234")
            if (usuario.equals("chocr") && contraseña.equals("199652")) {
                dispose();
                ConexionBD frame = new ConexionBD();
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
