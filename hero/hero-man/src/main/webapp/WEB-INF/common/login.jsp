<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="taglibs.jsp"%>
<html>
<head>
    <title>用户登录-权限管理平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userLogin.css" type="text/css" media="screen"/>
    <style type="text/css">
        .errorhide {
            display: none;
        }

        .error {
            color: red;
            padding: 5px 20px 0px 20px;
        }
    </style>
    <script type="text/javascript">
        function keyDown(event) {
            if (event.keyCode == 13) {
                event.returnValue = false;
                event.cancel = true;
                Form.loginBtn.click();
            }
        }

        function login(){
            var userName = document.getElementById('userName').value;
            var password = document.getElementById('password').value;
            if (userName == '' || userName == undefined) {
                alert("请输入用户名");
                return false;
            }
            if (password == '' || password == undefined) {
                alert("请输入密码");
                return false;
            }
            return true;
        }
        
    </script>
</head>
<body id="splash">
<div id="" class="login ">
    <div class="model-logo"></div>
    <div class="modal-header">
        <h3>登录</h3>
    </div>
    <c:if test="${error != null}">
        <div class="error">
            登陆失败:${error}
        </div>
    </c:if>
    <form method="POST" id="loginForm" onsubmit="return login();">
    <div class="modal-body clearfix">
            <fieldset>
                <div class="control-group form-field clearfix">
                    <label for="userName">用户名</label> <span class="help-block"></span>

                    <div class="input">
                        <input type="text" name="username" id="userName"/>
                    </div>
                </div>

                <div class="control-group form-field clearfix">
                    <label for="password">密码</label> <span class="help-block"></span>

                    <div class="input">
                        <input type="password" name="password" id="password"/>
                    </div>
                </div>
            </fieldset>
        </div>
        <div class="modal-footer">
            <button type="submit" name="loginBtn" class="btn btn-primary pull-right">登 录</button>
        </div>

    </form>
</div>

<div id="footer"></div>
</body>
</html>
