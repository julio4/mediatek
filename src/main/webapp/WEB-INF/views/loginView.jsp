<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="parts/header.jsp"></jsp:include>
<jsp:include page="parts/navbar.jsp"></jsp:include>

<h3>Login</h3>

<form method="POST">
    <label for="username">
        Identifiant
        <input name="username" id="username">
    </label>
    <label for="password">
        Mot de passe
        <input name="password" id="password" type="password">
    </label>
    <div id="error-container">
        <span>${error}</span>
    </div>
    <button type="submit">Se connecter</button>
</form>

<jsp:include page="parts/footer.jsp"></jsp:include>
