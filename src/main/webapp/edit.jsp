<%@page import="com.rupp.sample.domain.TestDomain"%>
<%@page import="com.rupp.sample.jdbc.TestDaoImpl"%>

<%
String id = request.getParameter("id");
TestDaoImpl daoImpl = new TestDaoImpl();
TestDomain domain = daoImpl.readDataById(id ==null? null: Integer.valueOf(id));
%>

<html>
<body>


 <form action="/updateDataServlet" method="POST">
        <%
         if (id != null) {
         %>
         <h2>update record </h2>
         Id : <%=id%>
         <input type="hidden" name="id" value="<%=id%>"/>
       <%     }   else  {%>
         <h2>create new record </h2>
       <% } %>
       
        <br />    Message: <input type="text" name="message" value="<%=domain !=null ? domain.getMessage() :' ' %>"/>

         <input type="submit" value="Submit" />
      </form>


</body>
</html>
