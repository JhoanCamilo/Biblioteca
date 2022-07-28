package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ConexionUsuario {
    String Usuario = "root";
    String Clave = "ArangoMonsalve2001";
    String URL = "jdbc:mysql://localhost:3306/biblioteca";
    Connection con;
    Statement stmt;
    ResultSet rs;

    public void ValidarDriver(){
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public void DatosUsuario(String id, String titulo){
        String orden = "SELECT Id_Usuarios, Nombre, Fecha_Limite FROM biblioteca.usuarios WHERE Id_Usuarios = ('" + id + "')";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if (rs.next()) {
                String idUsuario = rs.getString("Id_Usuarios");
                String UserName = rs.getString("Nombre");
                String fechaLimite = rs.getString("Fecha_Limite");
                System.out.println(idUsuario + " " + UserName + " " + fechaLimite + " " + titulo);
                AgregarLibro(idUsuario, UserName, fechaLimite, titulo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PonerLimite(String id, String fechaEntrega){
        String orden = "UPDATE biblioteca.usuarios SET Fecha_Limite = " + fechaEntrega + " WHERE Id_Usuarios = ('" + id + "')";
        
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void TraerLibro(String titulo){
        String orden = "SELECT Titulo FROM biblioteca.libro WHERE Titulo = ('" + titulo + "')";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if (rs.next()) {
                String TituloLibro = rs.getString("Titulo");
                System.out.println(TituloLibro);
                PrestarLibro(TituloLibro);
            }
            else{
                JOptionPane.showMessageDialog(null, "Libro no encontrado", "Error al prestar libro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void TraerPeriodico(String titulo){
        String orden = "SELECT Id_Periodico, Fecha_Publicacion FROM biblioteca.periodico WHERE Id_Periodico = ('" + titulo + "')";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if (rs.next()) {
                String idPeriodico = rs.getString("Id_Periodico");
                String fecha = rs.getString("Fecha_Publicacion");
                System.out.println(idPeriodico + " " + fecha);
                PrestarPeriodico(idPeriodico);
            }
            else{
                JOptionPane.showMessageDialog(null, "Periodico no encontrado", "Error al prestar periodico", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ListarLibros(String consulta, JTable tablaLista){
        String orden = "SELECT Id_Libro, Titulo, Autor, Fecha_Publicacion, Editorial, Tipo FROM libro WHERE Estado = 'Disponible'";
        DefaultTableModel modelo = new DefaultTableModel(); 
        tablaLista.setModel(modelo);

        ValidarDriver();
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery(orden);

                ResultSetMetaData rsMd = rs.getMetaData();
                int CantColumnas = rsMd.getColumnCount();
                modelo.addColumn("ID LIBRO");
                modelo.addColumn("Titulo");
                modelo.addColumn("Autor");
                modelo.addColumn("Fecha de Publicacion");
                modelo.addColumn("Editorial");
                modelo.addColumn("Tipo");
    
                while (rs.next()) {
                    Object[] filas = new Object[CantColumnas];       
                    
                    for (int i = 0; i < CantColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void ListarPeriodico(String consulta, JTable tablaLista){
        String orden = "SELECT Id_Periodico, Fecha_Publicacion, Editorial, Tipo FROM periodico where Estado = 'Disponible'";
        DefaultTableModel modelo = new DefaultTableModel(); 
        tablaLista.setModel(modelo);

        ValidarDriver();
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery(orden);

                ResultSetMetaData rsMd = rs.getMetaData();
                int CantColumnas = rsMd.getColumnCount();
                modelo.addColumn("ID Periodico");
                modelo.addColumn("Fecha de Publicacion");
                modelo.addColumn("Editorial");
                modelo.addColumn("Tipo");
    
                while (rs.next()) {
                    Object[] filas = new Object[CantColumnas];       
                    
                    for (int i = 0; i < CantColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public void ListarMultimedia(String consulta, JTable tablaLista){
        String orden = "SELECT Id_Multimedia, Titulo, Autor, Fecha_Publicacion, Editorial, Tipo FROM multimedia";
        DefaultTableModel modelo = new DefaultTableModel(); 
        tablaLista.setModel(modelo);

        ValidarDriver();
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery(orden);

                ResultSetMetaData rsMd = rs.getMetaData();
                int CantColumnas = rsMd.getColumnCount();
                modelo.addColumn("ID Multimedia");
                modelo.addColumn("Titulo");
                modelo.addColumn("Autor");
                modelo.addColumn("Fecha de Publicacion");
                modelo.addColumn("Editorial");
                modelo.addColumn("Tipo");
    
                while (rs.next()) {
                    Object[] filas = new Object[CantColumnas];       
                    
                    for (int i = 0; i < CantColumnas; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    /*public void CalcularRetraso(){
        String orden = "SELECT Id_Multimedia, Titulo, Autor, Fecha_Publicacion, Editorial, Tipo FROM multimedia";

        String fechaFinal = "2022-05-03";
        //String fechaActual = "2022-05-03";
        double Tarifa = 2000;

        LocalDate Devolucion = LocalDate.parse(fechaFinal);
        //LocalDate currentDate = LocalDate.parse(fechaActual);
        LocalDate currentDate = LocalDate.now();

        long DiasRetraso = ChronoUnit.DAYS.between(Devolucion, currentDate);

        if (DiasRetraso > 0) {
            System.out.println("El usuario se encuentra retrasado " + DiasRetraso +" dias, no se le pueden prestar libros.");
            System.out.println("Debe pagar " + DiasRetraso * Tarifa +" pesos de multa.");
        }
        else if (DiasRetraso == 0) {
            System.out.println("El usuario debe entregar uno o varios libros hoy");
        }
        else{
            System.out.println("Se puede prestar libros.");
        }
    }*/
    public void DiasFaltantes(JLabel dias, JTextField campo_Limite, JTextField campo_Dias, JTextField campo_Multa){
        String orden = "SELECT * FROM usuarios WHERE Id_Usuarios = 1";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if (rs.next()) {
                campo_Limite.setText(rs.getString("Fecha_Limite"));
                String fechaFinal = rs.getString("Fecha_Limite");
                double Tarifa = 2000;
                
                if (fechaFinal == null) {
                    campo_Limite.setText("Disponible");
                    dias.setText("Prestamos:");
                    campo_Dias.setText("Disponible");
                    campo_Multa.setText("0");
                } else {
                    LocalDate Devolucion = LocalDate.parse(fechaFinal);
                    LocalDate currentDate = LocalDate.now();

                    long DiasParaDevolucion = ChronoUnit.DAYS.between(Devolucion, currentDate) * -1;
                    long DiasRetraso = ChronoUnit.DAYS.between(Devolucion, currentDate);
                    String Devuelta = Long.toString(DiasParaDevolucion);
                    String Retraso = Long.toString(DiasRetraso);
                    if (DiasParaDevolucion > 0) {
                        dias.setText("Dias para la devolución:");
                        campo_Dias.setText(Devuelta);
                        campo_Multa.setText("0");
                    }
                    else if (DiasParaDevolucion == 0) {
                        dias.setText("Dias para la devolución:");
                        campo_Dias.setText(Devuelta);
                        campo_Multa.setText("0");
                    }
                    else{
                        dias.setText("Dias de retraso:");
                        campo_Dias.setText(Retraso);
                        campo_Multa.setText(DiasRetraso*Tarifa + "");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Evaluacion(String id, JButton prestamo){
        String orden = "SELECT * FROM usuarios WHERE Id_Usuarios = ('" + id + "')";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if (rs.next()) {
                String fechaFinal = rs.getString("Fecha_Limite");
                double Tarifa = 2000;
                
                if (fechaFinal == null) {
                    JOptionPane.showMessageDialog(null, "El usuario es apto para realizar un prestamo", "Autorización de usuario", JOptionPane.INFORMATION_MESSAGE);
                    prestamo.setEnabled(true);
                } else {
                    
                    LocalDate Devolucion = LocalDate.parse(fechaFinal);
                    LocalDate currentDate = LocalDate.now();

                    long DiasRetraso = ChronoUnit.DAYS.between(Devolucion, currentDate);
                    if (DiasRetraso > 0) {
                        JOptionPane.showInternalMessageDialog(null, "El usuario se encuentra retrasado " + DiasRetraso +" dias, no se le pueden prestar libros.\n"+
                        "Debe pagar " + DiasRetraso * Tarifa +" pesos de multa.", "Usuario con retraso", JOptionPane.ERROR_MESSAGE);
                        prestamo.setEnabled(false);
                    }
                    else if (DiasRetraso == 0) {
                        prestamo.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "El usuario debe entregar uno o varios productos hoy, No se pueden realizar prestamos", "Dia de entrega", JOptionPane.WARNING_MESSAGE);
                        prestamo.setEnabled(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "El usuario ya tiene un prestamos pendiente", "Prestamo autorizado", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void PrestarLibro(String tituloLibro){
        String orden = "UPDATE biblioteca.libro SET Estado = 'Prestado' WHERE Titulo = '" + tituloLibro + "'";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);

            JOptionPane.showMessageDialog(null, "Libro prestado correctamente", "Prestamo realizado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrestarPeriodico(String idPeriodico){
        String orden = "UPDATE biblioteca.libro SET Estado = 'Prestado' WHERE Titulo = '" + idPeriodico + "'" ;

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);

            JOptionPane.showMessageDialog(null, "Periodico prestado correctamente", "Prestamo realizado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void AgregarLibro(String idUsuario, String userName, String fechaEntrega, String titulo){
        String orden = "INSERT INTO biblioteca.prestamos (Id_Usuarios, Nombre, Libros_Prestados, Fecha_Limite) VALUES ('" + idUsuario + "','" + userName + "','" + titulo + "','" + fechaEntrega + "')";

        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Prestamos realizado", "Prestamos exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}