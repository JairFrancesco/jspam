<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
    <head>
        <link href="<c:url value='/resources/style.css'/>" rel="stylesheet" type="text/css" media="screen" />

        <title>gBondi</title>
    </head>

    <body>
        <div id="wrapper">
            <div id="header">
                <div id="logo">
                    <h1><a href="/gBondi">gBondi</a></h1>
                    <p> simulador de la linea de colectivo 12</p>
                </div>
            </div>
            <!-- end #header -->            
            <div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#">Setup</a></li>
                    <li><a href="/gBondi/gbondi/PreConfigurarColectivo.action">Colectivos</a></li>
                    <li><a href="/gBondi/gbondi/PreConfigurarPasajeros.action">Pasajeros</a></li>
                    <li><a href="#">Varios</a></li>
                    <li><a href="#">Acerca de</a></li>
                </ul>
            </div>
            <!-- end #menu -->
            <br />
            <s:form action="Setup">
                <table class="datos">
                    <tr>
                        <td>
                            <label for="archivoModelo">Archivo Modelo &nbsp;</label>
                        </td>
                        <td>
                            <s:textfield name="archivoModelo" size="40" template="text2" />
                        </td>
                    </tr>

                    <s:submit cssClass="formboton" value="Guardar configuracion"/>
                </table>
            </s:form>
 
        </div>
    </body>
</html>

