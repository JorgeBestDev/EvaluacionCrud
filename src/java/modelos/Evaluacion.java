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
public class Evaluacion {

    int idEvaluacion;
    int idInstructor;
    Instructor Instructor_idInstructor;
    int idEstado;
    Estado Estado_idEstado;
    Float nota;
    int paginacion = 10;

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Instructor getInstructor_idInstructor() {
        return Instructor_idInstructor;
    }

    public void setInstructor_idInstructor(Instructor Instructor_idInstructor) {
        this.Instructor_idInstructor = Instructor_idInstructor;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Estado getEstado_idEstado() {
        return Estado_idEstado;
    }

    public void setEstado_idEstado(Estado Estado_idEstado) {
        this.Estado_idEstado = Estado_idEstado;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Evaluacion{" + "idEvaluacion=" + idEvaluacion + ", Instructor_idInstructor=" + Instructor_idInstructor + ", Estado_idEstado="+Estado_idEstado+", nota=" + nota + ", paginacion=" + paginacion + '}';
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaEvaluacion = new ArrayList();
        Evaluacion laEvaluacion;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName();
        System.out.println("listado ficha " + listado);
        if (pagina > 0)
        {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idEvaluacion LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try
        {
            ResultSet rs = st.executeQuery(listado);
            System.out.println("listado Evaluacion ejecutado");

            while (rs.next())
            {
                laEvaluacion = new Evaluacion();
                laEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                laEvaluacion.setIdInstructor(rs.getInt("Instructor_idInstructor"));
                laEvaluacion.setIdEstado(rs.getInt("Estado_idEstado"));
                laEvaluacion.setNota(rs.getFloat("nota"));

                listaEvaluacion.add(laEvaluacion);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al listar Evaluacion:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaEvaluacion;
    }

}
