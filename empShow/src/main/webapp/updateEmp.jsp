<%@ page import="entity.Emp" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.EmpMapper" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>update Emp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="css/style2.css" />
</head>

<body>
<div id="wrap">
    <div id="top_content">
        <div id="header">
            <div id="rightheader">
                <p>
                    2009/11/20
                    <br />
                </p>
            </div>
            <div id="topheader">
                <h1 id="title">
                    <a href="#">Main</a>
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                update Emp info:
            </h1>
            <form action="update" method="post">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <%--<tr>--%>
                        <%--<td valign="middle" align="right">--%>
                            <%--id:--%>
                        <%--</td>--%>
                        <%--<td valign="middle" align="left">--%>
                            <%--1--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                        <%--<%--%>
                            <%--Emp emp= (Emp) request.getAttribute("empClick");--%>
                        <%--%>--%>
<%--这里的empClic是updateEmpView找到后传过来的数据  在request中 ${}里面的内容就是要去查找到东西--%>
                        <input type="hidden" value="${empClick.id}" name="id">
                    <tr>
                        <td valign="middle" align="right">
                            Name:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="name" value="${empClick.name}"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            Salary:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="salary" value="${empClick.salary}"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            Job:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="job" value="${empClick.job}"/>
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="submit" class="button" value="Confirm" />
                </p>
            </form>
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
