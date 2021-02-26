<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! int i; %>

<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">
    <h3>Index</h3>

    <%while ( i <= 3){ %>
        <%i++;%>
    <%}%>
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>