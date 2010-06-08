
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cargando...</title>
    </head>

    <body onload="cargar()">

    </body>


    <script>
        function cargar(){
            window.location = "http://localhost:8080/gBondi/gbondi/PreSetup.action";
        }
    </script>
</html>
