<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>OpenDev单点认证中心</title>
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/layui.css">
    <link rel="stylesheet" href="${request.contextPath}/static/css/login.css">
</head>
<body class="login-wrap">
<div class="login-container">
    <form class="login-form" action="${request.contextPath}/doLogin">
        <div class="input-group">
            <input type="text" id="username" name="username" class="input-field">
            <label for="username" class="input-label">
                <span class="label-title">用户名</span>
            </label>
        </div>
        <div class="input-group">
            <input type="password" id="password" name="password" class="input-field">
            <label for="password" class="input-label">
                <span class="label-title">密码</span>
            </label>
        </div>
        <#if errorMsg?exists>
             <p style="color: red;">${errorMsg}</p>
        </#if>
        <input type="hidden" name="redirect_url" value="${redirect_url!''}"/>
        <button type="submit" class="login-button">登录<i class="ai ai-enter"></i></button>
    </form>
</div>
</body>
<script src="${request.contextPath}/static/assets/layui.js"></script>
<script src="${request.contextPath}/static/js/login.js"></script>
</html>