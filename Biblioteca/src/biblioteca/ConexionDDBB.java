package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConexionDDBB {
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
    public void RegistrarLibro(String nombre, String fechaPublicacion, String editor, String escritor, String producto){    
        String orden = "INSERT INTO biblioteca.libro (Titulo, Autor, Fecha_Publicacion, Editorial, Tipo, Estado) VALUES ('" + nombre + "','" + escritor + "','" + fechaPublicacion + "','" + editor  + "','" + producto + "','" + "Disponible"+ "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente","Agregar Producto", JOptionPane.INFORMATION_MESSAGE);
            //rs = stmt.executeQuery("SELECT * FROM biblioteca.");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void RegistrarPerdiodico(String fechaPublicacion, String editor, String producto){
        String orden = "INSERT INTO biblioteca.periodico (Fecha_Publicacion, Editorial, Tipo, Estado) VALUES ('" + fechaPublicacion + "','" + editor  + "','" + producto + "','" + "Disponible" + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente","Agregar Producto", JOptionPane.INFORMATION_MESSAGE);
            //rs = stmt.executeQuery("SELECT * FROM biblioteca.");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void RegistrarMultimedia(String nombre, String fechaPublicacion, String editor, String escritor, String producto){
        String orden = "INSERT INTO biblioteca.multimedia (Titulo, Autor, Fecha_Publicacion, Editorial, Tipo) VALUES ('" + nombre + "','" + escritor + "','" + fechaPublicacion + "','" + editor  + "','" + producto +  "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente","Agregar Producto", JOptionPane.INFORMATION_MESSAGE);
            //rs = stmt.executeQuery("SELECT * FROM biblioteca.");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void RegistrarAutores(String escritor, String paisEscritor, String fechaNacimiento){
        String orden = "INSERT INTO biblioteca.autores (Nombre, Nacionalidad, Fecha_Nacimiento) VALUES ('" + escritor + "','" + paisEscritor + "','" + fechaNacimiento + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
            //rs = stmt.executeQuery("SELECT * FROM biblioteca.");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ConsultarLibro(String idConsulta, JTextField campo_Código, JTextField campo_Titulo, JTextField camipo_Autor, JTextField campo_Fecha, JTextField campo_Editorial, JTextField campo_Tipo, JTextField campo_Estado){
        String orden = "SELECT * FROM biblioteca.libro WHERE Id_Libro = ('" + idConsulta + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            //stmt.executeUpdate(orden);
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                campo_Código.setText(rs.getString("Id_Libro"));
                campo_Titulo.setText(rs.getString("Titulo"));
                camipo_Autor.setText(rs.getString("Autor"));
                campo_Fecha.setText(rs.getString("Fecha_Publicacion"));
                campo_Editorial.setText(rs.getString("Editorial"));
                campo_Tipo.setText(rs.getString("Tipo"));
                campo_Estado.setText(rs.getString("Estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Libro no encontrado", "Consulta Libro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ConsultarPeriodico(String idConsulta, JTextField campo_Código, JTextField campo_Titulo, JTextField camipo_Autor, JTextField campo_Fecha, JTextField campo_Editorial, JTextField campo_Tipo, JTextField campo_Estado){
        String orden = "SELECT * FROM biblioteca.Periodico WHERE Id_Periodico = ('" + idConsulta + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            //stmt.executeUpdate(orden);
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                campo_Código.setText(rs.getString("Id_Periodico"));
                campo_Titulo.setText("No aplica");
                camipo_Autor.setText("No aplica");
                campo_Fecha.setText(rs.getString("Fecha_Publicacion"));
                campo_Editorial.setText(rs.getString("Editorial"));
                campo_Tipo.setText(rs.getString("Tipo"));
                campo_Estado.setText(rs.getString("Estado"));
            } else {
                JOptionPane.showMessageDialog(null, "Periodico no encontrado", "Consulta Periodico", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ConsultarMultimedia(String idConsulta, JTextField campo_Código, JTextField campo_Titulo, JTextField camipo_Autor, JTextField campo_Fecha, JTextField campo_Editorial, JTextField campo_Tipo, JTextField campo_Estado){
        String orden = "SELECT * FROM biblioteca.Multimedia WHERE Id_Multimedia = ('" + idConsulta + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            //stmt.executeUpdate(orden);
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                campo_Código.setText(rs.getString("Id_Multimedia"));
                campo_Titulo.setText(rs.getString("Titulo"));
                camipo_Autor.setText(rs.getString("Autor"));
                campo_Fecha.setText(rs.getString("Fecha_Publicacion"));
                campo_Editorial.setText(rs.getString("Editorial"));
                campo_Tipo.setText(rs.getString("Tipo"));
                campo_Estado.setText("No aplica");
            } else {
                JOptionPane.showMessageDialog(null, "Archivo Multimedia no encontrado", "Consulta Multimedia", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ConsultaAutores(String escritor){
        String orden = "SELECT * FROM biblioteca.Autores WHERE Nombre = ('" + escritor + "') ";
        ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            //stmt.executeUpdate(orden);
            rs = stmt.executeQuery(orden);
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Nombre: " + rs.getString("Nombre") + "\n"
                + "Nacionalidad: " + rs.getString("Nacionalidad") + "\n"
                + "Fecha de nacimiento: " + rs.getString("Fecha_Nacimiento"), "información de Autor", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se encuentra el autor");
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
