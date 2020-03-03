<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#import "./common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <@netCommon.commonScript />
    <link rel="stylesheet" href="${request.contextPath}/static/css/index.css">
    <title>OpenDev管理后台</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">

    <!-- header -->
    <@netCommon.commonHeader />

    <!-- left -->
    <@netCommon.commonLeft />

    <!-- Main content -->
    <@netCommon.commonControl />

    <!-- footer -->
    <@netCommon.commonFooter />
    <div class="mobile-mask"></div>
</div>
<script src="${request.contextPath}/static/js/index.js"></script>
</body>
</html>
