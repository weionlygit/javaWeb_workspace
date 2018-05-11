<%@ page import="java.util.List" %>
<%@ page import="entity.Emp" %>
<%@ page import="entity.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>emplist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style2.css" />
</head>
<body>
<div id="wrap">
    <div id="top_content">
        <div id="header">
            <div id="rightheader">
                <p>
                    <%--<%--%>
                        <%--User user = (User) session.getAttribute("user");--%>
                        <%--if(user!=null){--%>
                            <%--out.print(user.getName());--%>
                        <%--}else{--%>
                    <%--%>--%>
                    <c:if test="${sessionScope.user!=null}" var="boo">
                        ${user.name}
                    </c:if>
                    <c:if test="${!boo}">
                        <span>请登录</span>
                    </c:if>

                    <%--<%--%>
                        <%--}--%>
                    <%--%>--%>
                    <br />
                </p>
            </div>
            <div id="topheader">
                <h1 id="title">
                    <a href="#">main</a>
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                Welcome!
            </h1>
            <table class="table">
                <tr class="table_header">
                    <td>
                        ID
                    </td>
                    <td>
                        Name
                    </td>
                    <td>
                        Salary
                    </td>
                    <td>
                        Job
                    </td>
                    <td>
                        Operation
                    </td>
                </tr>
                <%--<%--%>
                    <%--List<Emp> list = (List<Emp>) request.getAttribute("emplist");--%>

                    <%--for (int i = 0; i < list.size(); i++) {--%>
                        <%--if(i%2==0){--%>
                <%--%>--%>
                <c:forEach items="${emplist}" varStatus="index" var="emp">
                    <c:if test="${index.index%2==0}" var="bool">
                        <tr class="row1">
                            <td>
                                <%--<%out.print(list.get(i).getId());%>--%>${emp.id}
                            </td>
                            <td>
                                ${emp.name}
                            </td>
                            <td>
                                ${emp.salary}
                            </td>
                            <td>
                                ${emp.job}
                            </td>
                            <td>
                                <a href="delEmp?id=<%--<%out.print(list.get(i).getId());%>--%>${emp.id}">delete emp</a>&nbsp;
                                <a href="UpdateEmpView?id=${emp.id}">update emp</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="bool">
                        <tr class="row2">
                            <td>
                                    <%--<%out.print(list.get(i).getId());%>--%>${emp.id}
                            </td>
                            <td>
                                    ${emp.name}
                            </td>
                            <td>
                                    ${emp.salary}
                            </td>
                            <td>
                                    ${emp.job}
                            </td>
                            <td>
                                <a href="delEmp?id=<%--<%out.print(list.get(i).getId());%>--%>${emp.id}">delete emp</a>&nbsp;
                                <a href="UpdateEmpView?id=${emp.id}">update emp</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>


            </table>
            <p>
                <input type="button" class="button" value="Add Employee" onclick="location='addEmp.jsp'"/>
            </p>
        </div>
    </div>
    <div id="footer">
        <div id="footer_bg">
            ABC@126.com
        </div>
    </div>
</div>
</body>
</html>
