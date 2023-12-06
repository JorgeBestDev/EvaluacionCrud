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
public class Estado {
    int idEstado;
    String estado;
    int paginacion=10;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estado{" + "idEstado=" + idEstado + ", estado=" + estado + '}';
    }
    
    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaEstado = new ArrayList();
        Estado elEstado;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName();
        System.out.println("listado ficha " + listado);
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
            System.out.println("listado competencia ejecutado");

            while (rs.next())
            {
                elEstado = new Estado();
                elEstado.setIdEstado(rs.getInt("idEstado"));
                elEstado.setEstado(rs.getString("estado"));

                listaEstado.add(elEstado);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar Competencia:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaEstado;
    }


}
