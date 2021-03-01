<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">

    <jsp:include page="parts/alerts.jsp"></jsp:include>

    <div>
        <form class="needs-validation" method="POST">
            <div class="row">
                <div class="col-6">
                    <label for="type">Type</label>
                    <select class="custom-select d-block w-100" id="type" name="type" required>
                        <option value="">Sélection...</option>
                        <option value="0">Tous</option>
                        <option value="1">Livre</option>
                        <option value="2">DVD</option>
                        <option value="3">CD</option>
                    </select>
                    <div class="invalid-feedback">
                        Veuillez choisir un type valide.
                    </div>
                </div>
                <div class="col-6">
                    <button class="btn btn-primary" type="submit">Filtrer</button>
                </div>
            </div>
        </form>
    </div>

    <jsp:include page="parts/list.jsp"></jsp:include>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>