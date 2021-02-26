<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="core.jsp"></jsp:include>

</head>
<body>

<nav class="navbar navbar-expand-md navbar-light bg-white border-bottom shadow-sm mb-4">
    <img class="navbar-brand" src="${pageContext.request.contextPath}/assets/img/left-default.png" alt="Mediatek"  height="49">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Accueil</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/list">Liste des documents</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/add">Ajouter un document</a>
            </li>
        </ul>
        <div class="mt-3">
            <p class="mt-1">Bonjour <b>${user}</b></p>
            <a href="logout">
                <img src="${pageContext.request.contextPath}/assets/img/logout.png" height="39" alt="Déconnexion">
            </a>
        </div>
    </div>
</nav>