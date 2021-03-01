<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">

    <jsp:include page="parts/alerts.jsp"></jsp:include>

        <div class="row equal my-4">

            <div class="col-12 col-md-6 col-lg-3 mb-4 mb-lg-0">
                <div class="card rounded shadow-sm border-0">
                    <div class="card-body p-4 text-center">
                        <p class="card-title">Documents</p>
                        <h2 class="card-text">${list.size()}</h2>
                    </div>
                </div>
            </div>

            <div class="col-12 col-md-6 col-lg-3 mb-4 mb-lg-0">
                <div class="card rounded shadow-sm border-0">
                    <div class="card-body p-4 text-center">
                        <p class="card-title">Emprunté</p>
                        <h2 class="card-text">${emprunts}</h2>
                    </div>
                </div>
            </div>

            <a href="${pageContext.request.contextPath}/add">
                <div class="col-12 col-md-6 col-lg-6 mb-4 mb-lg-0">
                    <div class="card rounded shadow-sm border-0 bg-success">
                        <div class="card-body p-4 text-center">
                            <i class="far fa-plus-square"></i>
                        </div>
                    </div>
                </div>
            </a>

        </div>

    <jsp:include page="parts/list.jsp"></jsp:include>

</div>

<jsp:include page="parts/footer.jsp"></jsp:include>