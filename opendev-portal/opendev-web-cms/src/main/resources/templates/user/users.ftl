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
    <div class="layui-row">
        <div class="layui-card">
            <div class="layui-card-body">
                <div class="form-box">
                    <div class="layui-form layui-form-item">
                        <div class="layui-inline">
                            <div class="layui-form-mid">用户名:</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">邮箱:</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <input type="text" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">性别:</div>
                            <div class="layui-input-inline" style="width: 100px;">
                                <select name="sex">
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </div>
                            <button class="layui-btn layui-btn-blue">查询</button>
                            <button class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                    <table class="layui-hide" id="userTable" lay-filter="userTable"></table>
                    <script type="text/html" id="groupBtn">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
                        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
        var layer = layui.layer //弹层
            ,table = layui.table //表格

        //向世界问个好
        layer.msg('Hello World');

        //监听Tab切换
        /*element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });*/

        //执行一个 table 实例
        table.render({
            elem: '#userTable'
            ,height: 420
            ,url: '/user/list' //数据接口
            ,parseData (res) {
                if (res.code === 200) {
                    res.code = 0
                }
                return res
            }
            ,method: 'post'
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            //,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'userId', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名', width:80}
                ,{field: 'nickName', title: '用户昵称', width:80}
                ,{field: 'mobile', title: '手机号', width: 90}
                ,{field: 'sex', title: '性别', width:80,
                    templet: function (res) {
                        var sex = res.sex;
                        if (sex == 0) {
                            return '男'
                        }else{
                            return '女'
                        }
                    }}
                ,{field: 'email', title: '邮箱', width: 150}
                ,{field: 'birthday', title: '生日', width:150}
                ,{field: 'avatar', title: '头像', width: 150}
                ,{field: 'qqOpenId', title: 'qq授权码', width: 150}
                ,{field: 'wxOpenId', title: '微信授权码', width: 150}
                ,{field: 'status', title: '用户状态', width: 80,
                    templet: function (res) {
                        var status = res.status;
                        if (status == 0) {
                            return '正常'
                        }else{
                            return '异常'
                        }
                    }}
                ,{field: 'createTime', title: '注册时间', width: 150, sort: true}
                ,{field: 'updateTime', title: '更新时间', width: 150, sort: true}
                ,{fixed: 'right', title: '操作', width: 165, align:'center', toolbar: '#groupBtn'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(userTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    layer.open({
                        type: 1,
                        area: ['420px', '240px'], //宽高
                        content: 'html内容'
                    });
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(userTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
            }
        });

        //执行一个轮播实例
        /*carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: 200
            ,arrow: 'none' //不显示箭头
            ,anim: 'fade' //切换动画方式
        });*/

        //将日期直接嵌套在指定容器中
        /*var dateIns = laydate.render({
            elem: '#laydateDemo'
            ,position: 'static'
            ,calendar: true //是否开启公历重要节日
            ,mark: { //标记重要日子
                '0-10-14': '生日'
                ,'2020-01-18': '小年'
                ,'2020-01-24': '除夕'
                ,'2020-01-25': '春节'
                ,'2020-02-01': '上班'
            }
            ,done: function(value, date, endDate){
                if(date.year == 2017 && date.month == 11 && date.date == 30){
                    dateIns.hint('一不小心就月底了呢');
                }
            }
            ,change: function(value, date, endDate){
                layer.msg(value)
            }
        });*/

        //分页
        /*laypage.render({
            elem: 'pageDemo' //分页容器的id
            ,count: 100 //总页数
            ,skin: '#1E9FFF' //自定义选中色值
            //,skip: true //开启跳页
            ,jump: function(obj, first){
                if(!first){
                    layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                }
            }
        });*/

        //上传
        /*upload.render({
            elem: '#uploadDemo'
            ,url: 'https://httpbin.org/post' //改成您自己的上传接口
            ,done: function(res){
                layer.msg('上传成功');
                layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.files.file);
                console.log(res)
            }
        });*/

        //滑块
        /*var sliderInst = slider.render({
            elem: '#sliderDemo'
            ,input: true //输入框
        });*/

        //底部信息
        /*var footerTpl = lay('#footer')[0].innerHTML;
        lay('#footer').html(layui.laytpl(footerTpl).render({}))
            .removeClass('layui-hide');*/
    });
</script>
</body>
</html>