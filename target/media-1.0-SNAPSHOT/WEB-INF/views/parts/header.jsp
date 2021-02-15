<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div style="float: right; padding: 10px; text-align: right;">

    <!-- User store in session with attribute: loginedUser -->
    Hello <b>${loginedUser.userName}</b>
    <br/>
    Search <input name="search">

</div>
