<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE >
<html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="css/style2.css" />
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script>
        function login(){

            var nameW= $("[name='name']").val();
            var pwdQ= $("[name='pwd']").val();
            $.ajax({
                "url":"loginpp",
                "type":"post",
                "data":"username="+nameW+"&password="+pwdQ,
                "success":function (data) {
                    //网络传输会添加乱七八糟，去掉 用户名也可以用这个
                    data =data.trim();
                    if(data=="true"){
                        location.href="emplist";
                    }else{
                        alert("密码错误或用户名不存在");
                    }
                }
            });
        }
    </script>
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
                login
            </h1>
            <form action="loginpp" method="post">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <tr>
                        <td valign="middle" align="right">
                            username:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="name" id="nameWei" />
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            password:
                        </td>
                        <td valign="middle" align="left">
                            <input type="password" class="inputgri" name="pwd" id="passPeng"/>
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="button" class="button" value="Submit &raquo;" onclick="login()" />
                    <a href="regist.jsp">注册</a>
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

