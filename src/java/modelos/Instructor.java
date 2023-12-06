/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sena
 */
public class Instructor {
    int idInstructor;
    String nombreInstructor;
    BigInteger celularInstructor;
    Date inicioContrato;
    Date finalContrato;
    String correoInstructor;;
    int paginacion=10;

    public int getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(int idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public BigInteger getCelularInstructor() {
        return celularInstructor;
    }

    public void setCelularInstructor(BigInteger celularInstructor) {
        this.celularInstructor = celularInstructor;
    }

    public Date getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Date inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Date getFinalContrato() {
        return finalContrato;
    }

    public void setFinalContrato(Date finalContrato) {
        this.finalContrato = finalContrato;
    }

    public String getCorreoInstructor() {
        return correoInstructor;
    }

    public void setCorreoInstructor(String correoInstructor) {
        this.correoInstructor = correoInstructor;
    }

    @Override
    public String toString() {
        return "Instructor{" + "idInstructor=" + idInstructor + ", nombreInstructor=" + nombreInstructor + ", celularInstructor=" + celularInstructor + ", inicioContrato=" + inicioContrato + ", finalContrato=" + finalContrato + ", correoInstructor=" + correoInstructor + ", paginacion=" + paginacion + '}';
    }
    
    
    
    public ArrayList listar (int pagina){
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaInstructor = new ArrayList();
        Instructor elInstructor;
        String listado = "SELECT * FROM "+this.getClass().getSimpleName();
        System.out.println("listado ficha "+listado);
        if (pagina>0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM "+this.getClass().getSimpleName()+
                    " ORDER BY idInstructor LIMIT "+paginacionMin+","+paginacionMax;
        }
        
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                elInstructor = new Instructor();
                elInstructor.setIdInstructor(rs.getInt("idInstructor"));

                                
                elInstructor.setNombreInstructor(rs.getString("nombreInstructor"));                
                elInstructor.setInicioContrato(rs.getDate("inicioContrato"));
                elInstructor.setFinalContrato(rs.getDate("finalContrato"));
                elInstructor.setCorreoInstructor(rs.getString("correoInstructor"));
                listaInstructor.add(elInstructor);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar Instructor:"+ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaInstructor;
    }  
}
