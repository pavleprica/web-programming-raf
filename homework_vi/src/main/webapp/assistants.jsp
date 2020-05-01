<%@ page import="rs.webprogramming.homework_vi.model.Assistant" %>
<%@ page import="java.util.List" %>
<%@ page import="rs.webprogramming.homework_vi.repository.AssistantRepository" %>
<%@ page import="rs.webprogramming.homework_vi.repository.MySqlAssistantRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assistant grading</title>
</head>
<body>
<h3>
    Assistants average score:
</h3>
<%
    AssistantRepository assistantRepository = new MySqlAssistantRepository();
    List<Assistant> assistants = assistantRepository.getAssistantAverage();

    pageContext.setAttribute("assistants", assistants);
%>

<ul>
    <c:forEach var="assistant" items="${assistants}">
        <li>Assistant: <c:out value="${assistant}"/></li>
    </c:forEach>
</ul>

</body>
</html>
