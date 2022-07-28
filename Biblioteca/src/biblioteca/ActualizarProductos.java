package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActualizarProductos {
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
    public void ConsultarLibro(String codigo, JTextField campo_Tipo, JTextField campo_Titulo, JTextField campo_Estado){
        String orden = "SELECT * FROM biblioteca.libro WHERE Id_Libro = ('" + codigo + "') ";

        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                campo_Titulo.setText(rs.getString("Titulo"));
                campo_Tipo.setText(rs.getString("Tipo"));
                campo_Estado.setText(rs.getString("Estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Libro no encontrado", "Consulta Libro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ActualizarLibro(String codigo, String nuevoEstado){
        String orden = "UPDATE biblioteca.libro SET Estado = '" + nuevoEstado + "' WHERE Id_Libro = ('" + codigo + "')";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Estado de producto correctamente actualizado","Actualizar Producto", JOptionPane.INFORMATION_MESSAGE);
                        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ConsultarPeriodico(String codigo, JTextField campo_Tipo, JTextField campo_Titulo, JTextField campo_Estado){
        String orden = "SELECT * FROM biblioteca.periodico WHERE Id_Periodico = ('" + codigo + "') ";

        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                campo_Titulo.setText("No aplica");
                campo_Tipo.setText(rs.getString("Tipo"));
                campo_Estado.setText(rs.getString("Estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Periodico no encontrado", "Consulta Periodico", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ActualizarPeriodico(String codigo, String nuevoEstado){
        String orden = "UPDATE biblioteca.periodico SET Estado = '" + nuevoEstado + "' WHERE Id_Periodico = ('" + codigo + "')";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Estado de producto correctamente actualizado","Actualizar Producto", JOptionPane.INFORMATION_MESSAGE);
                        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
