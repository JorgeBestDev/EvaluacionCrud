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
    <jsp:useBean id="elAprendiz" class="modelos.Aprendiz" scope="request" />
    <jsp:useBean id="laFicha" class="modelos.Ficha" scope="request" />
    <jsp:useBean id="elEstado" class="modelos.Estado" scope="request" />
    <jsp:useBean id="laEvaluacion" class="modelos.Evaluacion" scope="request" />
    <jsp:useBean id="elInstructor" class="modelos.Instructor" scope="request" />
    <jsp:useBean id="laCompetencia" class="modelos.Competencia" scope="request" />
    <body>
        <c:if test="${param.insertarMensaje == 'true'}">
            <div class="mensaje">
                <p>Insertado Correctamente</p>
            </div>
        </c:if>
        <c:if test="${param.modificarMensaje == 'true'}">
            <div class="mensaje">
                <p>Modificado Correctamente</p>
            </div>
        </c:if>
        <c:if test="${param.eliminarMensaje == 'true'}">
            <div class="mensaje">
                <p>Eliminado Correctamente</p>
            </div>
        </c:if>
        <form style="width: 75%" action="ControladorAprendiz" method="post">
            <input type="hidden" name="fIdAprendiz" value="${elAprendiz.idAprendiz}">

            <label for="Ficha" class="m-2 form-label">Ficha</label>
            <select id="Ficha" class="input-form m-2 form-control" name="fIdFicha">
                <c:forEach var="ficha" items="${laFicha.listar(0)}">
                    <option value="${ficha.idFicha}">${ficha.nombreFicha}</option>
                </c:forEach>
            </select>

            <label for="Competencia " class="m-2 form-label">Competencia</label>
            <select id="Competencia" class="input-form m-2 form-control" name="fIdCompetencia">
                <c:forEach var="competencia" items="${laCompetencia.listar(0)}">
                    <option value="${competencia.idCompetencia}">${competencia.nombreCompetencia}</option>
                </c:forEach>
            </select>

            <label for="Estado " class="m-2 form-label">Estado</label>
            <select id="Estado" class="input-form m-2 form-control" name="fEstado">
                <c:forEach var="estado" items="${elEstado.listar(0)}">
                    <option value="${estado.idEstado}">
                        ${estado.estado}
                    </option>
                </c:forEach>
            </select>

            <label for="Nota" class="m-2 form-label">Nota</label>
            <input id="Nota" required type="number" class="input-form m-2 form-control" name="fNota">

            <label for="Instructor " class="m-2 form-label">Instructor</label>
            <select id="Instructor" class="input-form m-2 form-control" name="fIdInstructor">
                <c:forEach var="instructor" items="${elInstructor.listar(0)}">
                    <option value="${instructor.idInstructor}">${instructor.nombreInstructor}</option>
                </c:forEach>
            </select>

            <label for="NombreAprendiz" class="m-2 form-label">Nombre Aprendiz</label>
            <input id="NombreAprendiz" required type="text" class="input-form m-2 form-control" name="fNombreAprendiz">

            <label for="CelularAprendiz" class="m-2 form-label">Celular Aprendiz</label>
            <input id="CelularAprendiz" required type="number" class="input-form m-2 form-control" name="fCelularAprendiz">

            <label for="CorreoAprendiz" class="m-2 form-label">Correo Aprendiz</label>
            <input id="CorreoAprendiz" required type="email" class="input-form m-2 form-control" name="fCorreoAprendiz">



            <button class="btn btn-dark m-4" type="submit" name="fAccion" value="insertar">Insertar</button>
            <button class="btn btn-dark m-4" type="reset" name="fAccion" value="Limpiar">Limpiar</button>
        </form>






        <script src="js/bootstrap.bundle.js"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
<%--<form style="width: 75%" class="mt-5 mb-5" action="ControladorAprendiz" method="post">

            <c:choose>
                <c:when test="${empty elAprendiz.listar(0)}">

                </c:when>
                <c:otherwise>
                    <c:forEach items="${elAprendiz.listar(0)}" var="elAprendiz">
                        <table class="table">
                            <tr>
                                <th></th>
                                <th>Ficha</th>
                                <th>Evaluacion</th>
                                <th>Nombre</th>
                                <th>Celular</th>
                                <th>Correo</th>
                            </tr>
                            <tr>
                                <td><input class="input-form m-2 form-control" type="hidden" name="fIdAprendiz" value="${elAprendiz.idAprendiz}"></td>
                                <td>
                                    <select id="Ficha" class="input-form m-2 form-control" name="fIdFicha">
                                        <c:forEach var="ficha" items="${laFicha.listar(0)}">
                                            <option value="${ficha.idFicha}" ${ficha.idFicha eq elAprendiz.ficha_idFicha.idFicha ? 'selected' : ''}>${ficha.nombreFicha}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select id="Evaluacion" class="input-form m-2 form-control" name="fIdEvaluacion">
                                        <c:forEach var="evaluacion" items="${laEvaluacion.listar(0)}">
                                            <option value="${evaluacion.idEvaluacion}" ${evaluacion.idEvaluacion eq elAprendiz.evaluacion_idEvaluacion.idEvaluacion ? 'selected' : ''}>
                                                ${evaluacion.aprobada ? 'Aprobado' : 'No Aprobado'}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><input class="input-form form-control" type="text" name="fNombreAprendiz" value="${elAprendiz.nombreAprendiz}"></td>
                                <td><input class="input-form form-control" type="number" name="fCelularAprendiz" value="${elAprendiz.celularAprendiz}"></td>
                                <td><input class="input-form form-control" type="email" name="fCorreoAprendiz" value="${elAprendiz.correoAprendiz}"></td>
                                <td><button class="btn btn-dark" type="submit" name="fAccion" value="modificar" onclick="return confirm('¿Estás seguro de que deseas Modificar el Aprendiz?')">Modificar</button></td>
                                <td><button class="btn btn-danger" type="submit" name="fAccion" value="eliminar" onclick="return confirm('¿Estás seguro de que deseas Eliminar el Aprendiz?')">Eliminar</button></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </form>--%>
