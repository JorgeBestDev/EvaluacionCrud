/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sena
 */
public class Ficha {

    int idFicha;
    String nombreFicha;
    Date fechaInicio;
    Date fechaCierre;
    Competencia Competencia_idCompetencia;
    int paginacion = 10;

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getNombreFicha() {
        return nombreFicha;
    }

    public void setNombreFicha(String nombreFicha) {
        this.nombreFicha = nombreFicha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Competencia getCompetencia_idCompetencia() {
        return Competencia_idCompetencia;
    }

    public void setCompetencia_idCompetencia(Competencia Competencia_idCompetencia) {
        this.Competencia_idCompetencia = Competencia_idCompetencia;
    }

    @Override
    public String toString() {
        return "Ficha{" + "idFicha=" + idFicha + ", nombreFicha=" + nombreFicha + ", fechaInicio=" + fechaInicio + ", fechaCierre=" + fechaCierre + ", Competencia_idCompetencia=" + Competencia_idCompetencia + ", paginacion=" + paginacion + '}';
    }
    
    

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaFichas = new ArrayList();
        Ficha laFicha;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName() + " JOIN competencia ON '"+getCompetencia_idCompetencia()+"'=competencia.idCompetencia";
        System.out.println("listado ficha "+listado);
        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idFicha LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next())
            {
                laFicha = new Ficha();
                laFicha.setIdFicha(rs.getInt("idFicha"));
                laFicha.setNombreFicha(rs.getString("nombreFicha"));
                laFicha.setFechaInicio(rs.getDate("fechaInicio"));
                laFicha.setFechaCierre(rs.getDate("fechaCierre"));
                
                Competencia unaCompetencia = new Competencia();
                unaCompetencia.setIdCompetencia(rs.getInt("idCompetencia"));
                unaCompetencia.setNombreCompetencia(rs.getString("nombreCompetencia"));
                laFicha.setCompetencia_idCompetencia(unaCompetencia);

                listaFichas.add(laFicha);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar Ficha:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaFichas;
    }
}
