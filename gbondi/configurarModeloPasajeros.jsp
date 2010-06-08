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
                            <li><a href="/gBondi/gbondi/PreSetup.action">Setup</a></li>
                            <li><a href="/gBondi/gbondi/PreConfigurarColectivo.action">Colectivos</a></li>
                            <li class="current_page_item"><a href="/gBondi/gbondi/PreConfigurarPasajeros.action">Pasajeros</a></li>
                            <li><a href="#">Varios</a></li>
                            <li><a href="#">Acerca de</a></li>
                    </ul>
            </div>
            <!-- end #menu -->
            <br />
            <s:form action="ConfigurarPasajeros" method="get">
                <table class="datos">
                    <tr>
                        <td>
                            <label>Tiempo Bajar Colectivo (media y desvio)&nbsp;</label>
                        </td>
                        <td>
                            <s:textfield name="tiempoBajarColectivoMedia" template="text2" size="2"  />&nbsp
                            <s:textfield name="tiempoBajarColectivoDesvio" template="text2" size="2"  />&nbsp;(seg.)
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Tiempo Subir Colectivo (media y desvio)&nbsp;</label>
                        </td>
                        <td>
                            <s:textfield name="tiempoSubirColectivoMedia" template="text2" size="2"  />&nbsp
                            <s:textfield name="tiempoSubirColectivoDesvio" template="text2" size="2"  />&nbsp;(seg.)
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Tiempo de impaciencia&nbsp;</label>
                        </td>
                        <td>
                            <s:textfield name="tiempoImpaciencia" template="text2" size="2"  />&nbsp;(seg.)
                        </td>
                    </tr>

                    <s:submit cssClass="formboton" value="Modificar modelo"/>
                </table>
            </s:form>

        </div>
    </body>
</html>

