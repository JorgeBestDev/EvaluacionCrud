/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import modelos.Aprendiz;
import modelos.Competencia;
import modelos.Evaluacion;
import modelos.Ficha;
import modelos.Instructor;

/**
 *
 * @author Sena
 */
@WebServlet(name = "ControladorAprendiz", urlPatterns
        = {
            "/ControladorAprendiz"
        })
public class ControladorAprendiz extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAprendiz = request.getParameter("fIdAprendiz");
        String idFicha = request.getParameter("fIdFicha");
        String idCompetencia = request.getParameter("fIdCompetencia");
        String idEvaluacion = request.getParameter("fIdEvaluacion");
        String idInstructor = request.getParameter("fIdInstructor");
        String nombreAprendiz = request.getParameter("fNombreAprendiz");
        String celularAprendiz = request.getParameter("fCelularAprendiz");
        String correoAprendiz = request.getParameter("fCorreoAprendiz");

        String accion = request.getParameter("fAccion");

        int idA = 0;
        try {
            idA = Integer.parseInt(idAprendiz);
        } catch (NumberFormatException nfe) {
            System.err.println("Error en parsear idAprendiz " + nfe.getLocalizedMessage());
        }
        int idF = 0;
        try {
            idF = Integer.parseInt(idFicha);
        } catch (NumberFormatException nfe) {
            System.err.println("Error en parsear idFicha " + nfe.getLocalizedMessage());
        }
        int idC = 0;
        try {
            idC = Integer.parseInt(idCompetencia);
        } catch (NumberFormatException nfe) {
            System.err.println("Error en parsear idCompetencia " + nfe.getLocalizedMessage());
        }
        int idE = 0;
        try {
            idE = Integer.parseInt(idEvaluacion);
        } catch (NumberFormatException nfe) {
            System.err.println("Error en parsear idEvaluacion " + nfe.getLocalizedMessage());
        }
        int idI = 0;
        try {
            idI = Integer.parseInt(idInstructor);
        } catch (NumberFormatException nfe) {
            System.err.println("Error en parsear idInstructor " + nfe.getLocalizedMessage());
        }

        BigInteger celApre = null;

        if (celularAprendiz != null && !celularAprendiz.isEmpty()) {
            celApre = new BigInteger(celularAprendiz);
        }

        Ficha fichasListadas = new Ficha();
        ArrayList<Ficha> listaFichas = fichasListadas.listar(0);
        request.setAttribute("listaFichas", listaFichas);

        Competencia competenciasListadas = new Competencia();
        ArrayList<Competencia> listaCompetencias = competenciasListadas.listar(0);
        request.setAttribute("listaCompetencias", listaCompetencias);

        Evaluacion evaluacionesListadas = new Evaluacion();
        ArrayList<Evaluacion> listaEvaluaciones = evaluacionesListadas.listar(0);
        request.setAttribute("listaEvaluaciones", listaEvaluaciones);

        Instructor instructoresListados = new Instructor();
        ArrayList<Instructor> listaInstructores = instructoresListados.listar(0);
        request.setAttribute("listaInstructores", listaInstructores);

        Aprendiz aprendicesListados = new Aprendiz();
        ArrayList<Aprendiz> listaAprendices = aprendicesListados.listar(0);
        request.setAttribute("aprendicesListados", listaAprendices);

        Aprendiz unAprendiz = new Aprendiz();
        unAprendiz.setIdAprendiz(idA);

        Ficha ficha = new Ficha();
        ficha.setIdFicha(idF);
        unAprendiz.setFicha_idFicha(ficha);
        unAprendiz.setFicha_Competencia_idCompetencia(ficha);

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setIdEvaluacion(idE);
        unAprendiz.setEvaluacion_idEvaluacion(evaluacion);
        unAprendiz.setEvaluacion_Instructor_idInstructor(evaluacion);

        unAprendiz.setNombreAprendiz(nombreAprendiz);
        unAprendiz.setCelularAprendiz(celApre);
        unAprendiz.setCorreoAprendiz(correoAprendiz);

        request.setAttribute("unAprendiz", unAprendiz);

        switch (accion.toLowerCase()) {
            case "insertar" -> {
                unAprendiz.insertar();
            }
            case "modificar" -> {
                unAprendiz.modificar();
            }
            case "eliminar" -> {
                unAprendiz.eliminar();
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
