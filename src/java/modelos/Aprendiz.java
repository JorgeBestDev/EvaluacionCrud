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
    Ficha Ficha_Competencia_idCompetencia;
    Evaluacion Evaluacion_idEvaluacion;
    Evaluacion Evaluacion_Instructor_idInstructor;
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

    public Ficha getFicha_Competencia_idCompetencia() {
        return Ficha_Competencia_idCompetencia;
    }

    public void setFicha_Competencia_idCompetencia(Ficha Ficha_Competencia_idCompetencia) {
        this.Ficha_Competencia_idCompetencia = Ficha_Competencia_idCompetencia;
    }

    public Evaluacion getEvaluacion_idEvaluacion() {
        return Evaluacion_idEvaluacion;
    }

    public void setEvaluacion_idEvaluacion(Evaluacion Evaluacion_idEvaluacion) {
        this.Evaluacion_idEvaluacion = Evaluacion_idEvaluacion;
    }

    public Evaluacion getEvaluacion_Instructor_idInstructor() {
        return Evaluacion_Instructor_idInstructor;
    }

    public void setEvaluacion_Instructor_idInstructor(Evaluacion Evaluacion_Instructor_idInstructor) {
        this.Evaluacion_Instructor_idInstructor = Evaluacion_Instructor_idInstructor;
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
        return "Aprendiz{" + "idAprendiz=" + idAprendiz + ", Ficha_idFicha=" + Ficha_idFicha + ", Ficha_Competencia_idCompetencia=" + Ficha_Competencia_idCompetencia + ", Evaluacion_idEvaluacion=" + Evaluacion_idEvaluacion + ", Evaluacion_Instructor_idInstructor=" + Evaluacion_Instructor_idInstructor + ", nombreAprendiz=" + nombreAprendiz + ", celularAprendiz=" + celularAprendiz + ", correoAprendiz=" + correoAprendiz + ", paginacion=" + paginacion + '}';
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaAprendiz = new ArrayList();
        Aprendiz elAprendiz;
        System.out.println("entra al listar");
        String listado = "SELECT \n"
                + "    instructor.idInstructor, \n"
                + "    instructor.nombreInstructor, \n"
                + "    evaluacion.idEvaluacion, \n"
                + "    evaluacion.aprobada,\n"
                + "    competencia.idCompetencia, \n"
                + "    competencia.nombreCompetencia, \n"
                + "    ficha.idFicha, \n"
                + "    ficha.nombreFicha\n"
                + "FROM aprendiz\n"
                + "JOIN evaluacion ON '"+getEvaluacion_idEvaluacion()+"' = evaluacion.idEvaluacion\n"
                + "JOIN instructor ON '"+getEvaluacion_Instructor_idInstructor()+"' = instructor.idInstructor\n"
                + "JOIN ficha ON '"+getFicha_idFicha()+"' = ficha.idFicha\n"
                + "JOIN competencia ON '"+getFicha_Competencia_idCompetencia()+"' = competencia.idCompetencia ORDER BY idAprendiz";
        System.out.println("Listado Aprendiz: " + listado);
        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName()
                    + " ORDER BY idAprendiz LIMIT " + paginacionMin + "," + paginacionMax;
        }

        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elAprendiz = new Aprendiz();
                elAprendiz.setIdAprendiz(rs.getInt("idAprendiz"));

                Ficha laFicha = new Ficha();
                laFicha.setIdFicha(rs.getInt("idFicha"));
                laFicha.setNombreFicha("nombreFicha");
                elAprendiz.setFicha_idFicha(laFicha);

                Competencia unaCompetencia = new Competencia();
                unaCompetencia.setIdCompetencia(rs.getInt("idCompetencia"));
                unaCompetencia.setNombreCompetencia(rs.getString("nombreCompetencia"));
                laFicha.setCompetencia_idCompetencia(unaCompetencia);

                Evaluacion laEvaluacion = new Evaluacion();
                laEvaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
                elAprendiz.setEvaluacion_idEvaluacion(laEvaluacion);

                Instructor unInstructor = new Instructor();
                unInstructor.setIdInstructor(rs.getInt("idInstructor"));
                unInstructor.setNombreInstructor(rs.getString("nombreInstructor"));
                laEvaluacion.setInstructor_idInstructor(unInstructor);

                elAprendiz.setNombreAprendiz(rs.getString("nombreAprendiz"));
                elAprendiz.setCelularAprendiz(BigInteger.valueOf(rs.getLong("celularAprendiz")));
                elAprendiz.setCorreoAprendiz(rs.getString("correoAprendiz"));
                listaAprendiz.add(elAprendiz);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar Aprendiz:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaAprendiz;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            String sql = "INSERT INTO aprendiz (idAprendiz, Ficha_idFicha, Ficha_Competencia_idCompetencia, Evaluacion_idEvaluacion, Evaluacion_Instructor_idInstructor , nombreAprendiz, celularAprendiz, correoAprendiz) VALUES "
                    + "('" + getIdAprendiz() + "','" + getFicha_idFicha() + "','" + getFicha_Competencia_idCompetencia() + "','" + getEvaluacion_idEvaluacion() + "','" + getEvaluacion_Instructor_idInstructor() + " , '" + getNombreAprendiz() + "','" + getCelularAprendiz() + "','" + getCorreoAprendiz() + "')";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al insertar Aprendiz:" + ex.getLocalizedMessage());
        }
    }

    public void modificar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            String sql = "UPDATE competencia SET Ficha_idFicha='" + getFicha_idFicha() + "', Ficha_Competencia_idCompetencia='" + getFicha_Competencia_idCompetencia() + "', Evaluacion_idEvaluacion='" + getEvaluacion_idEvaluacion() + "',"
                    + " Evaluacion_Instructor_idInstructor='" + getEvaluacion_Instructor_idInstructor() + "' , nombreAprendiz = '" + getNombreAprendiz() + "', celularAprendiz='" + getCelularAprendiz() + "', correoAprendiz='" + getCorreoAprendiz() + ""
                    + "' WHERE idAprendiz = '" + getIdAprendiz() + "'";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al modificar Aprendiz: " + ex.getLocalizedMessage());
        }
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            String sql = "DELETE FROM competencia WHERE idAprendiz = '" + getIdAprendiz() + "'";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println("Error al eliminar Aprendiz: " + ex.getLocalizedMessage());
        }
    }
}
