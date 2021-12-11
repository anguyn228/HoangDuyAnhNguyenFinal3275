<%--
  Created by IntelliJ IDEA.
  User: anguy
  Date: 2021-12-11
  Time: 9:14 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Saving Investment Record</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>


        .btn {

            width: 100%;
        }
    </style>
</head>
<body>
<h5>This following are the saving records...</h5>

<div class="container2">


    <table class="table table-striped">
        <thead>
        <tr>
            <th>Customer number</th>
            <th>Customer Name</th>
            <th>Customer Deposit</th>
            <th>Number of years</th>
            <th>Saving Type</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${todos}" var="todo">



            <tr>


                <td>${todo.custno}</td>
                <td>${todo.custname}</td>
                <td>${todo.cdep}</td>
                <td>${todo.nyears}</td>
                <td>${todo.savtype}</td>
                <td>    <a type="button" class="btn btn-primary"
                           href="update-todo?id=${todo.custno}" >Edit</a> </td>



                <td>    <a type="button" class="btn btn-primary"
                           href="delete-todo?id=${todo.custno}" >Delete</a> </td>



                <td>    <a type="button" class="btn btn-primary" onmouseout="myFunction()"
                           href="see-todo?id=${todo.custno}" >Projected Investment</a> </td>


            </tr>


        </c:forEach>
        <a class="btn btn-success" href="add-todo">Add</a>
        </tbody>
    </table>
</div>
<script>

    function myFunction() {

        document.getElementById("mes").innerHTML = "";
    }

</script>
</body>
</html>
