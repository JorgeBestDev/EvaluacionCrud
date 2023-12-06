/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Conexion {

    private Connection conexion = null;

    public Conexion() {
        System.out.println("se ejecuta la conexion");
    }

    public Statement conectar() {
        Statement st = null;
        try
        {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/BDEvaluacion");
            conexion = ds.getConnection("admin1", "admin123._.");
            st = conexion.createStatement();
            System.out.println("Conexión establecida correctamente");
        } catch (NamingException ex)
        {
            System.err.println("Error al iniciar contexto: " + ex.getLocalizedMessage());
        } catch (SQLException | RuntimeException ex)
        {
            System.err.println("Error al conectarse con la base de datos: " + ex.getLocalizedMessage());
        }
        return st;
    }

    public void desconectar() {
        try
        {
            if (conexion != null)
            {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al cerrar la BD: " + ex.getLocalizedMessage());
        }
    }
}
