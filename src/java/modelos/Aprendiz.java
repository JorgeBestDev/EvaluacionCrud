/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigInteger;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Sena
 */
public class Aprendiz {

    int idAprendiz;
    Ficha Ficha_idFicha;
    Evaluacion Evaluacion_idEvaluacion;
    String nombreAprendiz;
    BigInteger celularAprendiz;
    String correoAprendiz;
    int paginacion = 10;

    public int getIdAprendiz() {
        return idAprendiz;
    }

    public void setIdAprendiz(int idAprendiz) {
        this.idAprendiz = idAprendiz;
    }

    public Ficha getFicha_idFicha() {
        return Ficha_idFicha;
    }

    public void setFicha_idFicha(Ficha Ficha_idFicha) {
        this.Ficha_idFicha = Ficha_idFicha;
    }

    public Evaluacion getEvaluacion_idEvaluacion() {
        return Evaluacion_idEvaluacion;
    }

    public void setEvaluacion_idEvaluacion(Evaluacion Evaluacion_idEvaluacion) {
        this.Evaluacion_idEvaluacion = Evaluacion_idEvaluacion;
    }

    public String getNombreAprendiz() {
        return nombreAprendiz;
    }

    public void setNombreAprendiz(String nombreAprendiz) {
        this.nombreAprendiz = nombreAprendiz;
    }

    public BigInteger getCelularAprendiz() {
        return celularAprendiz;
    }

    public void setCelularAprendiz(BigInteger celularAprendiz) {
        this.celularAprendiz = celularAprendiz;
    }

    public String getCorreoAprendiz() {
        return correoAprendiz;
    }

    public void setCorreoAprendiz(String correoAprendiz) {
        this.correoAprendiz = correoAprendiz;
    }

    @Override
    public String toString() {
        return "Aprendiz{" + "idAprendiz=" + idAprendiz + ", Ficha_idFicha=" + Ficha_idFicha + ", Evaluacion_idEvaluacion=" + Evaluacion_idEvaluacion + ", nombreAprendiz=" + nombreAprendiz + ", celularAprendiz=" + celularAprendiz + ", correoAprendiz=" + correoAprendiz + ", paginacion=" + paginacion + '}';
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaAprendiz = new ArrayList();
        Aprendiz elAprendiz;
        System.out.println("entra al listar");
        String listado = "SELECT * "
        + "FROM aprendiz "
        + "JOIN evaluacion ON aprendiz.Evaluacion_idEvaluacion = evaluacion.idEvaluacion "
        + "JOIN ficha ON aprendiz.Ficha_idFicha = ficha.idFicha "
        + "ORDER BY idAprendiz ";

        System.out.println("Listado Aprendiz: " + listado);
        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idAprendiz LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            System.out.println("listado aprendiz ejecutado");

            while (rs.next())
            {
                elAprendiz = new Aprendiz();
                elAprendiz.setIdAprendiz(rs.getInt("idAprendiz"));

                

                Ficha laFicha = new Ficha();
                laFicha.setIdFicha(rs.getInt("idFicha"));
                laFicha.setNombreFicha("nombreFicha");
                elAprendiz.setFicha_idFicha(laFicha);
                
                Evaluacion laEvaluacion = new Evaluacion();
                laEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                elAprendiz.setEvaluacion_idEvaluacion(laEvaluacion);


                elAprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                elAprendiz.setCelularAprendiz(BigInteger.valueOf(rs.getLong("celularAprendiz")));
                elAprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));
                listaAprendiz.add(elAprendiz);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar Aprendiz:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAprendiz;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            String sql2 = "INSERT INTO evaluacion(idEvaluacion, nota) VALUES (" + getEvaluacion_idEvaluacion().getIdEvaluacion() + "," + getEvaluacion_idEvaluacion().getNota() + ")";
            String sql = "INSERT INTO aprendiz (idAprendiz, Ficha_idFicha, Evaluacion_idEvaluacion , nombreAprendiz, celularAprendiz, correoAprendiz) VALUES "
                    + "('" + getIdAprendiz() + "','" + getFicha_idFicha().getIdFicha() + "','" + getEvaluacion_idEvaluacion() + "', '" + getNombreAprendiz() + "','" + getCelularAprendiz() + "','" + getCorreoAprendiz() + "')";
            st.executeUpdate(sql);
            st.executeUpdate(sql2);
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar Aprendiz:" + ex.getLocalizedMessage());
        }
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            String sql = "UPDATE Aprendiz SET Ficha_idFicha='" + getFicha_idFicha().idFicha + "', Evaluacion_idEvaluacion='" + getEvaluacion_idEvaluacion().idEvaluacion + "',"
                    + "nombreAprendiz = '" + getNombreAprendiz() + "', celularAprendiz='" + getCelularAprendiz() + "', correoAprendiz='" + getCorreoAprendiz() + ""
                    + "' WHERE idAprendiz = '" + getIdAprendiz() + "'";
            st.executeUpdate(sql);
            System.out.println("APRENDIZ MODIFICADO");
        } catch (SQLException ex)
        {
            System.err.println("Error al modificar Aprendiz: " + ex.getLocalizedMessage());
        }
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try
        {
            String sql = "DELETE FROM Aprendiz WHERE idAprendiz = '" + getIdAprendiz() + "'";
            st.executeUpdate(sql);
            System.out.println("APRENDIZ ELIMINADO");
        } catch (SQLException ex)
        {
            System.err.println("Error al eliminar Aprendiz: " + ex.getLocalizedMessage());
        }
    }
}
