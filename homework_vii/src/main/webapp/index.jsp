<%@ page import="rs.webprogramming.homework_vi.repository.AviationCompanyRepository" %>
<%@ page import="rs.webprogramming.homework_vi.repository.AviationCompanyRepositoryMySql" %>
<%@ page import="rs.webprogramming.homework_vi.model.AviationCompany" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homework_vii</title>
</head>
<body>
<%
    AviationCompanyRepository aviationCompanyRepository = new AviationCompanyRepositoryMySql();
    List<AviationCompany> allAviationCompanies = aviationCompanyRepository.getAll();

    pageContext.setAttribute("companies", allAviationCompanies);
%>
<h1>Welcome to your airline service</h1>
<form id="create-ticket">
    <label for="from">From:</label><br>
    <input type="text" id="from" name="from"><br>
    <label for="to">To:</label><br>
    <input type="text" id="to" name="to"><br>
    <label for="depart">Depart: (yyyy-mm-dd hh:mm:ss)</label><br>
    <input type="text" id="depart" name="depart"><br>
    <label for="return">Return: (yyyy-mm-dd hh:mm:ss)</label><br>
    <input type="text" id="return" name="return">
    <br><br>

    <select id="company">
        <c:forEach var="cmp" items="${companies}">
            <option value="${cmp.id}}"><c:out value="${cmp.name}"/></option>
        </c:forEach>
    </select>

    <select id="create-ticket-type">
        <option value="one-way">One way</option>
        <option value="with-return">With return</option>
    </select>

    <input type="submit" value="Create ticket">
</form>
<hr>
<select id="ticket-type">
    <option value="all">All tickets</option>
    <option value="one-way">One way tickets</option>
    <option value="with-return">Tickets with return</option>
</select>

<table id="tickets">
    <thead>
    <tr>
        <th>One-way</th>
        <th>From</th>
        <th>To</th>
        <th>Depart</th>
        <th>Return</th>
        <th>Company</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
<script src="js/scripts.js"></script>
</body>
</html>
