<#-- common-style -->
<#macro commonStyle>
	<link rel="icon" href="${request.contextPath}/static/favicon.ico" />
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/static/assets/css/layui.css">
</#macro>

<#-- common-script -->
<#macro commonScript>
	<script src="${request.contextPath}/static/assets/layui.js"></script>
</#macro>

<#-- common-header -->
<#macro commonHeader>
	<div class="layui-header custom-header">

        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item slide-sidebar" lay-unselect>
                <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">${user.username}</a>
                <dl class="layui-nav-child">
                    <dd><a href="">帮助中心</a></dd>
                    <dd><a href="${request.contextPath}/logout">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</#macro>

<#-- common-left -->
<#macro commonLeft>
	<div class="layui-side custom-admin">
        <div class="layui-side-scroll">

            <div class="custom-logo">
                <img src="${request.contextPath}/static/img/logo.png" alt=""/>
                <h1>Admin Pro</h1>
            </div>
            <ul id="Nav" class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe609;</i>
                        <em>主页</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/console">控制台</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe857;</i>
                        <em>组件</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="views/form.html">表单</a></dd>
                        <dd>
                            <a href="javascript:;">页面</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="login.html">登录页</a>
                                </dd>
                            </dl>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <i class="layui-icon">&#xe612;</i>
                        <em>用户管理</em>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="user/list">用户组</a></dd>
                        <dd><a href="views/operaterule.html">权限配置</a></dd>
                    </dl>
                </li>
            </ul>

        </div>
    </div>
</#macro>

<#-- common-body -->
<#macro commonControl>
	<div class="layui-body">
        <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
            <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
            <div id="appTabPage" class="layui-tab-content"></div>
        </div>
    </div>
</#macro>

<!-- All rights reserved. -->
<#macro commonFooter>
	<div class="layui-footer">
		<p>分布式微服务开源技术社区<a href="#" target="_blank"></a>
			<strong>Copyright &copy; 2015-${.now?string('yyyy')} &nbsp;
				All rights reserved.
			</strong>
		</p>
	</div>
</#macro>