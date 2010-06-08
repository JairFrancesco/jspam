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
                            <li class="current_page_item"><a href="#">Colectivos</a></li>
                            <li><a href="/gBondi/gbondi/PreConfigurarPasajeros.action">Pasajeros</a></li>
                            <li><a href="#">Varios</a></li>
                            <li><a href="#">Acerca de</a></li>
                    </ul>
            </div>            
            <!-- end #menu -->
            <br />
            <s:form action="ConfigurarColectivo" method="get">
                <table class="datos">
                        <tr>
                            <td>
                                <label for="cantidadUnidades">Cantidad unidades &nbsp;</label>
                            </td>
                            <td>                               
                               <s:textfield name="cantidadUnidades" size="5" template="text2" />&nbsp;(unid.)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="capacidadUnidad">Capacidad &nbsp;</label>
                            </td>
                            <td>
                                <s:textfield name="capacidadUnidad" template="text2" size="5"  />&nbsp;(unid.)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="tiempoRetorno">Tiempo Retorno a Terminal (media y desvio)&nbsp;</label>
                            </td>
                            <td>
                                <s:textfield name="tiempoRetornoMedia" template="text2" size="5"  />&nbsp
                                <s:textfield name="tiempoRetornoDesvio" template="text2" size="5"  />&nbsp;(seg.)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="frecuencia6a7">Frecuencia 6 a 7&nbsp;</label>
                            </td>
                            <td>
                                <s:textfield name="frecuencia6a7" template="text2" size="5"  />&nbsp;(seg.)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="frecuencia6a7">Frecuencia 7 a 10&nbsp;</label>
                            </td>
                            <td>
                                <s:textfield name="frecuencia7a10" template="text2" size="5"  />&nbsp;(seg.)
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="frecuencia6a7">Frecuencia 10 a 12&nbsp;</label>
                            </td>
                            <td>
                                <s:textfield name="frecuencia10a12" template="text2" size="5"  />&nbsp;(seg.)
                            </td>
                        </tr>
                        
                
                
                <s:submit cssClass="formboton" value="Modificar modelo"/>
                </table>
            </s:form>
            
        </div>
    </body>
</html>

