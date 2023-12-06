/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sena
 */
public class Competencia {

    int idCompetencia;
    String nombreCompetencia;
    int paginacion = 10;

    public int getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(int idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getNombreCompetencia() {
        return nombreCompetencia;
    }

    public void setNombreCompetencia(String nombreCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
    }

    @Override
    public String toString() {
        return "Competencia{" + "idCompetencia=" + idCompetencia + ", nombreCompetencia=" + nombreCompetencia + ", paginacion=" + paginacion + '}';
    }



    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaCompetencias = new ArrayList();
        Competencia laCompetencia;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName();
        System.out.println("listado ficha "+listado);
        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idCompetencia LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next())
            {
                laCompetencia = new Competencia();
                laCompetencia.setIdCompetencia(rs.getInt("idCompetencia"));
                laCompetencia.setNombreCompetencia("nombreCompetencia");

                listaCompetencias.add(laCompetencia);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar Competencia:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaCompetencias;
    }

    
}
