<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>企业员工管理系统</title>
    <link rel="stylesheet" href="resources/layui/css/layui.css">
    <script src="resources/layui/layui.js"></script>
    <script src="resources/jquery.min.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        .imgstyle {
            width: 25px;
            height: 25px;
            margin-top: -5px;
            margin-left: -5px;
            margin-right: 3px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div id="add-user-layer" style="display: none; padding:30px 30px;">
    <form id="add-user-form" class="layui-form layui-form-pane"
          method="post">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 130px;text-align: left;">签到时间：</label>
            <div class="layui-input-inline">
                <input type="text" id="starttime" class="layui-input" lay-verify="required" autocomplete="off"
                       name="starttime" placeholder="HH:mm:ss" style="width: 260px;">
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 35px;">
            <label class="layui-form-label" style="width: 130px;text-align: left;">最迟签到时间：</label>
            <div class="layui-input-inline">
                <input type="text" id="lateststarttime" name="lateststarttime" lay-verify="required" autocomplete="off"
                       class="layui-input" placeholder="HH:mm:ss" style="width: 260px;">
            </div>
        </div>
        <div class="layui-inline" style="margin-top: 35px;">
            <label class="layui-form-label" style="width: 130px;text-align: left;">签退时间：</label>
            <div class="layui-input-inline">
                <input type="text" id="endtime" name="endtime" lay-verify="required" autocomplete="off"
                       class="layui-input" placeholder="HH:mm:ss" style="width: 260px;">
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 35px;">
            <div class="layui-input-block" style="margin-left: 130px;">
                <button class="layui-btn layui-icon layui-icon-ok" lay-submit
                        lay-filter="update-form-submit" style="width: 122px;">提交
                </button>
                <button type="reset"
                        class="layui-btn layui-btn-danger layui-icon layui-icon-refresh" style="width: 122px;">重置
                </button>
            </div>
        </div>
        <div style=" margin-top: 30px;color: red;">*提示：超过最迟签到时间签到,按缺勤处理。</div>
    </form>
</div>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="font-size: 23px;">员工管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <%--<li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>--%>
            <li class="layui-nav-item"><a href="javascript:;" id="add-user-btn">考勤设置</a></li>
            <%--<li class="layui-nav-item"><a href="javascript:;" id="depart-btn">部门管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;" id="role-btn">角色管理</a></li>--%>
            <li class="layui-nav-item">
                <a href="javascript:;">其它管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" id="depart-btn">部门管理</a></dd>
                    <dd><a href="javascript:;" id="role-btn">角色管理</a></dd>
                    <dd><a href="javascript:;" id="staffPortrait-btn">员工头像</a></dd>
                    <%--<dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>--%>
                </dl>
            </li>
        </ul>
        <!--  <div class="layui-logo" style="margin-left: 600px; color:white;" id="txt">当前日期时间：</div> -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="" id="demo1" class="layui-nav-img">
                    <span id="name"></span>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" id="basicData">基本资料</a></dd>
                    <dd><a href="javascript:;" id="securitySet">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="login">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="test">/
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;"><img src="resources/images/zhuye.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 19px;height: 19px;margin-right: 4px;">主页</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="biao04" type="button" style="margin-left: 38px;">查看个人信息</a></dd>
                        <dd><a href="javascript:;" id="biao05" style="margin-left: 38px;">修改个人信息</a></dd>
                        <dd><a href="javascript:;" id="biao03" style="margin-left: 38px;">进行自我考勤</a></dd>
                        <dd><a href="javascript:;" id="biao06" style="margin-left: 38px;">查看考勤记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/yuangong.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 20px;height: 20px;margin-right: 4px;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="biao01" style="margin-left: 38px;">员工基本信息</a></dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="biao08" style="margin-left: 38px;">添加员工信息</a></dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="biao02" style="margin-left: 38px;">员工角色管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/leave.png" class="layui-nav-img imgstyle">请假管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="leave" style="margin-left: 38px;">请假申请</a></dd>
                        <dd><a href="javascript:;" id="leaverecord" style="margin-left: 38px;">请假记录</a></dd>
                        <dd><a href="javascript:;" id="leaveapprival" style="margin-left: 38px;">请假审批</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/overtime.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 19px;height: 19px;margin-right: 5px;">加班管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="overtime" style="margin-left: 38px;">加班申请</a></dd>
                        <dd><a href="javascript:;" id="overrecord" style="margin-left: 38px;">加班记录</a></dd>
                        <dd><a href="javascript:;" id="overtimeapproval" style="margin-left: 38px;">加班审批</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/travel.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 19px;height: 19px;margin-right: 4px;">出差管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="travel" style="margin-left: 38px;">出差申请</a></dd>
                        <dd><a href="javascript:;" id="travelrecord" style="margin-left: 38px;">出差记录</a></dd>
                        <dd><a href="javascript:;" id="travelapproval" style="margin-left: 38px;">出差审批</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/tongji.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 18px;height: 18px;margin-right: 4px;">统计管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="biao07" style="margin-left: 38px;">考勤统计</a></dd>
                        <dd>
                <li class="layui-nav-item">
                    <a href="javascript:;" style="margin-left: 38px;">图表统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="departStatistics" style="margin-left: 58px;">部门人数统计</a></dd>
                    </dl>
                </li>
                </dd>
                </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><img src="resources/images/gonggao.png" class="layui-nav-img"
                                                style="margin-left: -1px;width: 19px;height: 19px;margin-right: 4px;">公告管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="announ" style="margin-left: 38px;">公告管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding:15px; height:95%">
            <!--  内容主体区域 -->
            <iframe height="100%" src="punchClock.html" frameborder="0" width="100%"></iframe>
            <!-- 添加员工信息弹出层 -->
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 企业员工管理系统 - 底部固定区域admin
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.$;

        $('#biao01').click(function () {
            $('iframe').attr('src', 'listAll.html');
        });
        $('#biao02').click(function () {
            $('iframe').attr('src', 'roleManagement.html');
        })
        $('#biao03').click(function () {
            $('iframe').attr('src', 'punchClock.html');
        })
        $('#biao04').click(function () {
            $('iframe').attr('src', 'personalInfo.html');
        })
        $('#biao05').click(function () {
            $('iframe').attr('src', 'modifyInfo.html');
        })
        $('#biao06').click(function () {
            $('iframe').attr('src', 'attendanceInfo.html');
        })
        $('#biao07').click(function () {
            $('iframe').attr('src', 'attendanceStatistics.html');
        })
        $('#biao08').click(function () {
            $('iframe').attr('src', 'addInfo.html');
        })
        $('#overtime').click(function () {
            $('iframe').attr('src', 'overtimeTable.html');
        })
        $('#overrecord').click(function () {
            $('iframe').attr('src', 'overtimeRecord.html');
        })
        $('#basicData').click(function () {
            $('iframe').attr('src', 'personalInfo.html');
        })
        $('#securitySet').click(function () {
            $('iframe').attr('src', 'modifyInfo.html');
        })
        $('#leave').click(function () {
            $('iframe').attr('src', 'leaveForm.html');
        })
        $('#leaverecord').click(function () {
            $('iframe').attr('src', 'leaveRecord.html');
        })
        $('#travel').click(function () {
            $('iframe').attr('src', 'travelFrom.html');
        })
        $('#travelrecord').click(function () {
            $('iframe').attr('src', 'travelRecord.html');
        })
        $('#departStatistics').click(function () {
            $('iframe').attr('src', 'departmentStatistics.html');
        })
        $('#leaveapprival').click(function () {
            $('iframe').attr('src', 'leaveApproval.html');
        })
        $('#overtimeapproval').click(function () {
            $('iframe').attr('src', 'overtimeapproval.html');
        })
        $('#travelapproval').click(function () {
            $('iframe').attr('src', 'travelApproval.html');
        })
        $('#depart-btn').click(function () {
            $('iframe').attr('src', 'departManagement.html');
        })
        $('#role-btn').click(function () {
            $('iframe').attr('src', 'roleManagement01.html');
        })
        $('#announ').click(function () {
            $('iframe').attr('src', 'announcement.html');
        })
        $('#staffPortrait-btn').click(function () {
            $('iframe').attr('src', 'staffPortrait.html');
        })
    });
    window.onload = function () {
        getURL();
        getname();
    }

    function getAttendInfo() {
        $.ajax({
            url: "getAttendanceSetting",
            method: 'get',
            dataType: 'json',
            success: function (data) {
                var start = data.attendInfo.start;
                var end = data.attendInfo.end;
                var latest = data.attendInfo.latest;
                $('#starttime').val(start);
                $('#endtime').val(end);
                $('#lateststarttime').val(latest);
            }
        })
    }

    function getURL() {
        $.ajax({
            url: 'pictureURL',
            method: 'get',
            dataType: 'json',
            success: function (data) {
                var url = data.url;
                $('#demo1').attr('src', url);
            }
        })
    }

    function getname() {
        $.ajax({
            url: 'search-to-id',
            method: 'get',
            dataType: 'json',
            success: function (data) {
                $('#name').html(data.data[0].name);
            },
            error: function () {
                console.log('获取数据错误');
            }
        })
    }

    layui.use(['jquery', 'laypage', 'layer', 'table', 'form', 'laydate'], function () {
        var $ = layui.$;
        var layer = layui.layer;
        var table = layui.table;
        var form = layui.form;
        var laypage = layui.laypage;
        var laydate = layui.laydate;
        // 显示添加员工信息弹出层
        $('body').on('click', '#add-user-btn', function () {
            // 每次显示前重置表单
            $('#add-user-form')[0].reset();
            getAttendInfo();
            layer.open({
                type: 1,
                title: '考勤时间设置',
                skin: 'layui-layer-molv',
                area: ['450px', '430px'],
                content: $('#add-user-layer'),
                offset: '120px'
            });
        });
        // 表单提交
        form.on('submit(update-form-submit)', function (data) {
            //layer.confirm('确定修改考勤时间吗？', function (index) {
            // ajax方式更新
            $.ajax({
                url: "updateAttendanceSet",
                type: "post",
                data: {
                    starttime: data.field.starttime,
                    endtime: data.field.endtime,
                    lateststarttime: data.field.lateststarttime
                },
                dataType: 'json',
                success: function (data) {
                    if (data.status == 1) {
                        layer.close(layer.index);
                        form.render();
                        layer.msg('设置成功');
                    } else {
                        layer.msg('设置失败');
                    }
                },
                error: function () {
                    console.log("ajax error");
                }
            });
            /* layer.close(index);
         });*/
            // 阻止表单跳转
            return false;
        });
        //时间选择器
        laydate.render({
            elem: '#starttime'
            , type: 'time'
        });
        laydate.render({
            elem: '#endtime'
            , type: 'time'
        });
        laydate.render({
            elem: '#lateststarttime'
            , type: 'time'
        });
    })
</script>
</body>
</html>