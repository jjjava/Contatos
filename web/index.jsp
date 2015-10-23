<%-- 
    Document   : index
    Created on : 15/06/2015, 09:34:43
    Author     : hudson.sales
--%>
<%@page import="br.com.hudson.HsStringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Array"%>
<%@page import="br.com.hudson.Bs"%>
<%@page import="java.util.List"%>
<%@page import="br.com.hudson.Contato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="-1" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;"/>
        <title>Free</title>
        <link href="estilo_free.css" media="screen" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script>
            $(document).keydown(function () {
                var i = $("#search");
                var val = i.val();
                if (val.length > 2) {
                    $.get("auto.jsp?search=" + val, function (data) {
                        var items = data.split("\n");
                        i.autocomplete({source: items});
                    });
                }
            });
        </script>
    </head>
    <body>
        <div id="topo">
            <div class="content">
                <div class="logo">LOGO</div>
            </div>
        </div>
        <div id="resultado">
            <form id="formResult" action="">
                <input type="text" name="search" id="search" value=""/> 
                <a onclick="document.getElementById('formResult').submit();"><i class="fa fa-search fa-lg"></i></a>
            </form>
            <%
                int pageNumber = 1;
                String param = request.getParameter("search");
                if (request.getParameter("page") != null) {
                    session.setAttribute("page", request.getParameter("page"));
                    pageNumber = Integer.parseInt(request.getParameter("page"));
                } else {
                    session.setAttribute("page", "1");
                }
                session.setAttribute("list", new Bs().getContatos(param, pageNumber));
                if (session.getAttribute("size") == null) {
                    session.setAttribute("size", new Bs().getNumber(param));
                } else {
                    session.setAttribute("size", new Bs().getNumber(param));
                }
                int size = ((Integer) session.getAttribute("size"));
            %>
            <h1 class="resultado"><%=HsStringUtil.getStatus(pageNumber)%> de <%=session.getAttribute("size")%> resultados para "<%=param%>" </h1>
        </div>
        <div id="paginacao">
            <div class="content">
                <ul class="menu">
                    <li><a href="index.jsp">Arquivos</a></li>
                    <li><a href="email.jsp">Emails</a></li>
                    <li><a href="">Imagens</a></li>
                    <li><a href="">Database</a></li>
                </ul>
                <div class="right">
                    <ul class="numeros">
                        <%
                            int total = ((Integer) session.getAttribute("size"));
                            int numberOfPages = total / 10;
                            int rest = total % 10;
                             rest = rest /10;
                            if(rest ==0){
                                rest++;
                            }
                            numberOfPages = numberOfPages + rest;
                            for (int k = 1; k <= numberOfPages; k++) {
                                if (pageNumber != k) {
                                    out.print("<li><a href=\"index.jsp?search=" + param + "&page=" + k + "\">" + k + "</a></li>");
                                } else {
                                    out.print("<li><a class=\"select\" href=\"index.jsp?search=" + param + "&page=" + k + "\">" + k + "</a></li>");
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
        <div id="lista">
            <%
                List<Contato> l = (java.util.List) session.getAttribute("list");
                for (Contato c : l) {
                    out.print("<div id=\"item\">");
                    out.print("<span class=\"titulo\">" + c.getNome());
                    out.print("<span class=\"texto\">");
                    out.print(c.getId());
                    out.print("<br>");

                    out.print(c.getTel());
                    out.print("<br>");
                    out.print(c.getEmail());
                    out.print("<br>");
                    out.print("<a href=\"file:/c:/Temp/lib/ftp.jar\">teste </a>");
                    out.print("</span>");
                    out.print("</div>");
                }
            %>
        </div>
    </body>
</html>