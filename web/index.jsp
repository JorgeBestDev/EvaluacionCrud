<%-- 
    Document   : index
    Created on : 5/12/2023, 9:35:54 a. m.
    Author     : Sena
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="modelos.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/vnd.icon" href="assets/favicon.ico">
        <title>JSP Page</title>
    </head>
    <jsp:useBean id="unAprendiz" class="modelos.Aprendiz" scope="request" />
    <body>
        <form style="width: 75%" action="ControladorAprendiz" method="post">
            <input type="hidden" name="fIdAprendiz" value="${unAprendiz.idAprendiz}">

            <label for="Ficha" class="m-2 form-label">Ficha</label>
            <select id="Ficha" class="input-form m-2 form-control" name="fIdFicha">
                <c:forEach var="ficha" items="${listaFichas}">
                    <option value="${ficha.idFicha}">${ficha.nombreFicha}</option>
                </c:forEach>
            </select>

            <label for="Competencia " class="m-2 form-label">Competencia</label>
            <select id="Competencia" class="input-form m-2 form-control" name="fIdCompetencia">
                <c:forEach var="competencia" items="${listaCompetencias}">
                    <option value="${competencia.idCompetencia}">${competencia.nombreCompetencia}</option>
                </c:forEach>
            </select>

            <label for="Evaluacion " class="m-2 form-label">Evaluacion</label>
            <select id="Evaluacion" class="input-form m-2 form-control" name="fIdEvaluacion">
                <c:forEach var="evaluacion" items="${listaEvaluaciones}">
                    <option value="${evaluacion.idEvaluacion}">${evaluacion.aprobada}</option>
                </c:forEach>
            </select>

            <label for="Instructor " class="m-2 form-label">Instructor</label>
            <select id="Instructor" class="input-form m-2 form-control" name="fIdInstructor">
                <c:forEach var="instructor" items="${listaInstructores}">
                    <option value="${instructor.idInstructor}">${instructor.nombreInstructor}</option>
                </c:forEach>
            </select>

            <label for="NombreAprendiz" class="m-2 form-label">Nombre Aprendiz</label>
            <input id="NombreAprendiz" type="text" class="input-form m-2 form-control" value="${unAprendiz.nombreAprendiz}" name="fNombreAprendiz">

            <label for="CelularAprendiz" class="m-2 form-label">Celular Aprendiz</label>
            <input id="CelularAprendiz" type="text" class="input-form m-2 form-control" value="${unAprendiz.celularAprendiz}" name="fCelularAprendiz">

            <label for="CorreoAprendiz" class="m-2 form-label">Correo Aprendiz</label>
            <input id="CorreoAprendiz" type="text" class="input-form m-2 form-control" value="${unAprendiz.correoAprendiz}" name="fCorreoAprendiz">



            <button class="btn btn-dark m-4" type="submit" name="fAccion" value="insertar">Insertar</button>
            <button class="btn btn-dark m-4" type="reset" name="fAccion" value="Limpiar">Limpiar</button>
        </form>





        <script src="js/bootstrap.bundle.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
