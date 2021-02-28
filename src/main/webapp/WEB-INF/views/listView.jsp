<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">

    <jsp:include page="parts/alerts.jsp"></jsp:include>

    <div class="card">
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Type</th>
                    <th>Titre</th>
                    <th>Autheur</th>
                    <th>Opération</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="list">
                    <tr>
                        <td>${list.getId()}</td>
                        <td>${list.getType()}</td>
                        <td>${list.getTitre()}</td>
                        <td>${list.getAutheur()}</td>
                        <td>
                            <form id="form_${list.getId()}" method="POST" action="delete">
                                <input type="hidden" name="id" value="${list.getId()}" />
                                <input form="form_${list.getId()}" type="submit" value="Supprimer" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>