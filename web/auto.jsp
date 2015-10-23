<%@page import="java.util.List"%>
<%@page import="br.com.hudson.Bs"%>
<%
    String value = request.getParameter("search");
    Bs bs = new Bs();
    List<String> auto = bs.autoComplete(value);
    
    for(String s : auto){
        out.print(s);
    }
%>

