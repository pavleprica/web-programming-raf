/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.28
 * Generated at: 2020-04-01 17:26:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import rs.webprogramming.homework_v.model.Scope;
import rs.webprogramming.homework_v.model.application.ApplicationDataWrapper;
import rs.webprogramming.homework_v.model.request.RequestDataWrapper;
import rs.webprogramming.homework_v.model.session.SessionDataWrapper;
import java.util.HashSet;
import java.io.IOException;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("rs.webprogramming.homework_v.model.Scope");
    _jspx_imports_classes.add("java.util.HashSet");
    _jspx_imports_classes.add("rs.webprogramming.homework_v.model.request.RequestDataWrapper");
    _jspx_imports_classes.add("rs.webprogramming.homework_v.model.session.SessionDataWrapper");
    _jspx_imports_classes.add("rs.webprogramming.homework_v.model.application.ApplicationDataWrapper");
    _jspx_imports_classes.add("java.io.IOException");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Homework_v</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      rs.webprogramming.homework_v.model.application.ApplicationData applicationData = null;
      synchronized (application) {
        applicationData = (rs.webprogramming.homework_v.model.application.ApplicationData) _jspx_page_context.getAttribute("applicationData", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (applicationData == null){
          applicationData = new rs.webprogramming.homework_v.model.application.ApplicationData();
          _jspx_page_context.setAttribute("applicationData", applicationData, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write('\n');
      rs.webprogramming.homework_v.model.request.RequestData requestData = null;
      requestData = (rs.webprogramming.homework_v.model.request.RequestData) _jspx_page_context.getAttribute("requestData", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (requestData == null){
        requestData = new rs.webprogramming.homework_v.model.request.RequestData();
        _jspx_page_context.setAttribute("requestData", requestData, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');
      rs.webprogramming.homework_v.model.session.SessionData sessionData = null;
      synchronized (session) {
        sessionData = (rs.webprogramming.homework_v.model.session.SessionData) _jspx_page_context.getAttribute("sessionData", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (sessionData == null){
          sessionData = new rs.webprogramming.homework_v.model.session.SessionData();
          _jspx_page_context.setAttribute("sessionData", sessionData, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("    <div>\n");
      out.write("        <h2>Data entry: </h2>\n");
      out.write("        <form method=\"POST\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/index.jsp\">\n");
      out.write("            <label for=\"scope\">Scope: </label>\n");
      out.write("            <select id=\"scope\" name=\"scope\">\n");
      out.write("                <option value=\"APPLICATION\">Application</option>\n");
      out.write("                <option value=\"REQUEST\">Request</option>\n");
      out.write("                <option value=\"SESSION\">Session</option>\n");
      out.write("            </select><br>\n");
      out.write("            <label for=\"key\">Key:</label><br>\n");
      out.write("            <input type=\"text\" id=\"key\" name=\"key\" value=\"some key\"><br>\n");
      out.write("            <label for=\"data\">Last name:</label><br>\n");
      out.write("            <input type=\"text\" id=\"data\" name=\"data\" value=\"some data\"><br><br>\n");
      out.write("            <input type=\"submit\" value=\"Submit\">\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("\n");

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

      out.write('\n');
      out.write('\n');

    if (applicationData.getApplicationData().size() > 0) {

      out.write("\n");
      out.write("    <div>\n");
      out.write("        <hr>\n");
      out.write("        <h3>Application scope: </h3>\n");
      out.write("        ");

            for (ApplicationDataWrapper applicationDataWrapper: applicationData.getApplicationData()) {
        
      out.write("\n");
      out.write("        ");
      out.print( applicationDataWrapper.toString() );
      out.write("<br>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        <hr>\n");
      out.write("    </div>\n");

    }

      out.write('\n');

    if (requestData.getRequestData().size() > 0) {

      out.write("\n");
      out.write("    <div>\n");
      out.write("        <hr>\n");
      out.write("        <h3>Request scope: </h3>\n");
      out.write("        ");

            for (RequestDataWrapper requestDataWrapper: requestData.getRequestData()) {
        
      out.write("\n");
      out.write("        ");
      out.print( requestDataWrapper.toString() );
      out.write("<br>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        <hr>\n");
      out.write("    </div>\n");

    }

      out.write('\n');

    if (sessionData.getSessionData().size() > 0) {

      out.write("\n");
      out.write("    <div>\n");
      out.write("        <hr>\n");
      out.write("        <h4>Cookie: </h4> ");
      out.print( httpSession.getId() );
      out.write("\n");
      out.write("        <h3>Session scope: </h3>\n");
      out.write("        ");

            for (SessionDataWrapper sessionDataWrapper: sessionData.getSessionData()) {
        
      out.write("\n");
      out.write("        ");
      out.print( sessionDataWrapper.toString() );
      out.write(" <br>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        <hr>\n");
      out.write("    </div>\n");

    }

      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
