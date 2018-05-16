<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>regist</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style2.css" />
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script>
       var usernamewe;
       var passwordwe;
       var passwordAgainwe;
       var authcodewe;

        function change(){
            var boo=true;
            var time=20;
            var inter;
            if(boo){
                document.getElementById("num").src="image?"+Math.random();
                document.getElementById("time").innerHTML=--time;
                inter = setInterval(function () {
                    document.getElementById("time").innerHTML=--time;
                        if(time==0){
                            clearInterval(inter);
                            document.getElementById("time").innerHTML="";
                            boo=true;
                            time=20;
                        }
                    },1000);
                boo=false;
            }
        }

        function inName(){
            var username = $("[name='userNameW']").val();
            var showWarn = $("#showWarnName");
            $.ajax({
                "url":"checkUserName",
                "type":"get",
                "async":"false",
                "data":"username="+username,
                "success":function (data) {
                    //传过来已经 知识true 或者false  网络乱七八糟传输 用来去空格
                    data.trim();
                    //是"" 不是null
                    if(username.trim()==""){
                        showWarn.html("不能为空").css("color","red");
                        usernamewe=false;
                    }else{
                        if(data=="true"){
                            showWarn.html("√").css("color","#579D3E");
                            usernamewe=true;
                            isSub();
                        }else{
                            showWarn.html("用户名已存在").css("color","red");
                            usernamewe=false;
                        }
                    }

                }
            });

        }

        function inPassword() {
            var showWarn = $("#showWarnPwd");
            var pwd = $("[name='pwd']").val();
            var pwdTest=/^[a-zA-Z]{1}[0-9a-zA-Z]{5,19}$/;
            var res= pwdTest.test(pwd);
            if(pwd==""){
                showWarn.html("不能为空").css("color","red");
                passwordwe=false;
            }else{
                if(res==true){
                    showWarn.html("√").css("color","#579D3E");
                    passwordwe=true;

                }else{
                    showWarn.html("密码长度6-20位，字母开始").css("color","red");
                    passwordwe=false;
                }
            }
            isSub();
        }

        function inPasswordAgain() {
            var showWarn =$("#showWarnPwd2");
            var pwd2 = $("[name='pwd2']").val();
            var pwd =$("[name='pwd']").val();
            if(pwd2==""){
                showWarn.html("不能为空").css("color","red");
                passwordAgainwe=false;
            }else{
                if(pwd==pwd2){
                    showWarn.html("√").css("color","#579D3E");
                    passwordAgainwe=true;

                }else{
                    showWarn.html("密码输入不一致").css("color","red");
                    passwordAgainwe=false;
                }
            }
            isSub();
        }

        function inAuthcode(){
            var showWarn =$("#AuthcodeWarn");
            var authCode =$("[name='number']").val();
        //    js拿不到session 所以只能发到servlet
            $.ajax({
               "url":"checkAuthcode",
               "type":"post",
                "async":"false",
               "data":"authCode="+authCode,
               "success":function (data) {
                   if(data.trim()=="true"){
                       showWarn.html("√").css("color","#579D3E");
                       authcodewe=true;
                       isSub();
                   }else{
                       showWarn.html("验证码错误").css("color","red");
                       authcodewe=false;
                   }
               }
            });

        }

        function checkImg(){
            var path=$("[name='file']").val();
            var suffix=path.substring(path.indexOf("."));
            if(suffix==".jpg") {
                //    图片预览
                var img = document.getElementById("imgHead").files[0];
                var reader = new FileReader();
                reader.readAsDataURL(img);
                reader.onload = function () {
                    $("#weimg").attr("src", this.result);
                }
            }else{
                $("#imgWarn").html("只支持jpg").css("color","red");
            }

        }

        function isSub(){
            if(usernamewe==true&&passwordwe==true&&passwordAgainwe==true&&authcodewe==true){
                $("#subCir").removeAttr("disabled");
            }
        }
    //    图片上传校验
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
                注册
            </h1>
            <form action="addUser" method="post" enctype="multipart/form-data">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <tr>
                        <td valign="middle" align="right">
                            用户名:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="userNameW" onblur="inName()"/>
                            <span id="showWarnName"></span>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            真实姓名:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="name" />
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            密码:
                        </td>
                        <td valign="middle" align="left">
                            <input type="password" class="inputgri" name="pwd" onblur="inPassword()"/>
                            <span id="showWarnPwd"></span>
                        </td>
                    </tr><tr>
                    <td valign="middle" align="right">
                        确认密码:
                    </td>
                    <td valign="middle" align="left">
                        <input type="password" class="inputgri" name="pwd2" onblur="inPasswordAgain()"/>
                        <span id="showWarnPwd2"></span>
                    </td>
                </tr>
                    <tr>
                        <td valign="middle" align="right">
                            性别:
                        </td>
                        <td valign="middle" align="left">
                            男
                            <input type="radio" class="inputgri" name="sex" value="男" checked="checked"/>
                            女
                            <input type="radio" class="inputgri" name="sex" value="女"/>
                        </td>
                    </tr>

                    <tr>
                        <td valign="middle" align="right">
                            验证码:
                            <img id="num" src="image" />
                            <span id="time"></span>
                            <%--换一张验证码功能--%>
                            <a href="javascript:;" onclick="change()">换一张</a>
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="number" onblur="inAuthcode()"/>
                            <span id="AuthcodeWarn"></span>
                        </td>
                    </tr>

                    <tr>
                        <td valign="middle" align="right">
                            头像上传:
                        </td>
                        <td>
                            <input type="file" name="file" onchange="checkImg()" id="imgHead">
                            <span id="imgWarn"></span>
                            <img src="" alt="头像上传" id="weimg" width="40px" height="55px" border="#FDFDFD solid 1px">
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="submit" class="button" value="Submit &raquo;" disabled="disabled" id="subCir"/>
                </p>
            </form>
<%--可以执行同一个过滤器   action="register"--%>
            <%--<form action="filePic" method="post" enctype="multipart/form-data">--%>
                <%--头像上传：--%>
                <%--<input type="file" name="file">--%>
                <%--<input type="submit" value="确定">--%>
            <%--</form>--%>

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
