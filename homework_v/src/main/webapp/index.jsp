<%@ page import="rs.webprogramming.homework_v.model.Scope" %>
<%@ page import="rs.webprogramming.homework_v.model.application.ApplicationDataWrapper" %>
<%@ page import="rs.webprogramming.homework_v.model.request.RequestDataWrapper" %>
<%@ page import="rs.webprogramming.homework_v.model.session.SessionDataWrapper" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homework_v</title>
</head>
<body>
<jsp:useBean id="applicationData" scope="application" class="rs.webprogramming.homework_v.model.application.ApplicationData"/>
<jsp:useBean id="requestData" scope="request" class="rs.webprogramming.homework_v.model.request.RequestData"/>
<jsp:useBean id="sessionData" scope="session" class="rs.webprogramming.homework_v.model.session.SessionData"/>

    <div>
        <h2>Data entry: </h2>
        <form method="POST" action="${pageContext.request.contextPath}/index.jsp">
            <label for="scope">Scope: </label>
            <select id="scope" name="scope">
                <option value="APPLICATION">Application</option>
                <option value="REQUEST">Request</option>
                <option value="SESSION">Session</option>
            </select><br>
            <label for="key">Key:</label><br>
            <input type="text" id="key" name="key" value="some key"><br>
            <label for="data">Last name:</label><br>
            <input type="text" id="data" name="data" value="some data"><br><br>
            <input type="submit" value="Submit">
        </form>
    </div>

<%
    HttpSession httpSession = request.getSession();

    if(request.getMethod().equals("POST")) {
        Scope scope = Scope.valueOf(request.getParameter("scope"));
        String key = request.getParameter("key");
        String data = request.getParameter("data");

        switch (scope) {
            case APPLICATION:
                applicationData.getApplicationData().add(new ApplicationDataWrapper(key, data));
                break;
            case REQUEST:
                requestData.getRequestData().add(new RequestDataWrapper(key, data));
                break;
            case SESSION:
                sessionData.getSessionData().add(new SessionDataWrapper(key, data));

        }

//        This code is commented out for the purpose of the homework. It's bad practise, pattern POST-REDIRECT-GET
//        should be used in situations like this.
//        response.sendRedirect("http://localhost:8080/index.jsp");
    }
%>

<%
    if (applicationData.getApplicationData().size() > 0) {
%>
    <div>
        <hr>
        <h3>Application scope: </h3>
        <%
            for (ApplicationDataWrapper applicationDataWrapper: applicationData.getApplicationData()) {
        %>
        <%= applicationDataWrapper.toString() %><br>
        <%
            }
        %>
        <hr>
    </div>
<%
    }
%>
<%
    if (requestData.getRequestData().size() > 0) {
%>
    <div>
        <hr>
        <h3>Request scope: </h3>
        <%
            for (RequestDataWrapper requestDataWrapper: requestData.getRequestData()) {
        %>
        <%= requestDataWrapper.toString() %><br>
        <%
            }
        %>
        <hr>
    </div>
<%
    }
%>
<%
    if (sessionData.getSessionData().size() > 0) {
%>
    <div>
        <hr>
        <h4>JSessionId: </h4> <%= httpSession.getId() %>
        <h3>Session scope: </h3>
        <%
            for (SessionDataWrapper sessionDataWrapper: sessionData.getSessionData()) {
        %>
        <%= sessionDataWrapper.toString() %> <br>
        <%
            }
        %>
        <hr>
    </div>
<%
    }
%>
</body>
</html>
