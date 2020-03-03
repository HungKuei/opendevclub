<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#import "../common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <@netCommon.commonScript />
    <link rel="stylesheet" href="${request.contextPath}/static/css/admin.css">
</head>
<body class="layui-view-body">
<div class="layui-content">
    <div class="layui-row layui-col-space20">
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>总用户数</span>
                            </div>
                            <div class="total">126,560</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                            fsdfdsf
                        </div>
                    </div>
                    <div class="chart-footer">
                        <div class="field">
                            <span>日注册量</span>
                            <span>321</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>总用户数</span>
                            </div>
                            <div class="total">126,560</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                            fsdfdsf
                        </div>
                    </div>
                    <div class="chart-footer">
                        <div class="field">
                            <span>日注册量</span>
                            <span>321</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>总用户数</span>
                            </div>
                            <div class="total">126,560</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                            fsdfdsf
                        </div>
                    </div>
                    <div class="chart-footer">
                        <div class="field">
                            <span>日注册量</span>
                            <span>321</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>总用户数</span>
                            </div>
                            <div class="total">126,560</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                            fsdfdsf
                        </div>
                    </div>
                    <div class="chart-footer">
                        <div class="field">
                            <span>日注册量</span>
                            <span>321</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-sm12 layui-col-md12">
            <div class="layui-card">
                <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">网站设置</li>
                        <li>用户管理</li>
                        <li>权限分配</li>
                        <li>商品管理</li>
                        <li>订单管理</li>
                    </ul>
                    <div class="layui-tab-content"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
</script>
</body>
</html>