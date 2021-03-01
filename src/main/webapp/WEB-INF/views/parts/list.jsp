<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card">
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>Type</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Emprunté</th>
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
                        <c:if test="${list.estEmprunté()}">
                            <i class="fas fa-check"></i>
                        </c:if>
                        <c:if test="!${list.estEmprunté()}">
                            <i class="fas fa-times"></i>
                        </c:if>
                    </td>
                    <td>
                        <form id="form_${list.getId()}" method="POST" action="delete">
                            <input type="hidden" name="id" value="${list.getId()}" />
                            <input class="btn btn-outline-danger" form="form_${list.getId()}" type="submit" value="Supprimer" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>