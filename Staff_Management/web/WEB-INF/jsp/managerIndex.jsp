<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh-cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>后台管理中心</title>
  <link rel="stylesheet" href="resources/employeeResources/css/pintuer.css">
  <link rel="stylesheet" href="resources/employeeResources/css/admin.css">
  <script src="resources/employeeResources/js/jquery.js"></script>
  <link rel="stylesheet" href="resources/layui/css/layui.css">
  <script src="resources/layui/layui.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="" id="demo1" class="radius-circle rotate-hover" height="50" alt="" />员工信息中心</h1>
  </div>
  <div class="head-l">
    <a class="button button-little bg-green" href="personalInfo.html" target="right"><span class="icon-home"></span> 个人中心</a> &nbsp;&nbsp;
    <a href="modifyInfoE.html" target="right" class="button button-little bg-blue"><span class="icon-wrench"></span> 修改信息</a>&nbsp;&nbsp;
    <a href="listAllE.html" target="right" class="button button-little bg-yellow"><span class="icon-file-text"></span> 员工信息</a> &nbsp;&nbsp;
    <a href="announcementR.html" target="right" class="button button-little bg-green"><span class="icon-wrench"></span> 查看公告</a> &nbsp;&nbsp;
    <a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a>
  </div>
  <div style="float: right;line-height: 63px;font-size: 22px;margin-right: 40px;color: #FFFFF0;"><span id="name"></span></div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>管理列表</strong></div>
  <h2><span class="icon-user"></span>主页</h2>
  <ul style="display:block">
    <li><a href="punchClock.html" target="right"><span class="icon-caret-right"></span>自我考勤</a></li>
    <li><a href="attendanceInfoE.html" target="right"><span class="icon-caret-right"></span>考勤记录</a></li>
    <li><a href="leaveRecordE.html" target="right"><span class="icon-caret-right"></span>请假记录</a></li>
    <li><a href="overtimeRecordE.html" target="right"><span class="icon-caret-right"></span>加班记录</a></li>
    <li><a href="travelRecordE.html" target="right"><span class="icon-caret-right"></span>出差记录</a></li>
  </ul>
  <h2><span class="icon-pencil-square-o"></span>审批</h2>
  <ul style="display:block">
    <li><a href="leaveApprovalE.html" target="right"><span class="icon-caret-right"></span>请假审批</a></li>
    <li><a href="overtimeapprovalE.html" target="right"><span class="icon-caret-right"></span>加班审批</a></li>
    <li><a href="travelApprovalE.html" target="right"><span class="icon-caret-right"></span>出差审批</a></li>
  </ul>
  <h2><span class="icon-chevron-circle-right"></span>申请</h2>
  <ul>
    <li><a href="leaveForm.html" target="right"><span class="icon-caret-right"></span>请假申请</a></li>
    <li><a href="overtimeTable.html" target="right"><span class="icon-caret-right"></span>加班申请</a></li>
    <li><a href="travelFrom.html" target="right"><span class="icon-caret-right"></span>出差申请</a></li>
  </ul>
  <h2><span class="icon-file"></span>意见</h2>
  <ul>
    <li><a href="overtimeTable.html" target="right"><span class="icon-caret-right"></span>意见管理</a></li>
  </ul>
</div>
<script type="text/javascript">
    $(function(){
        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function(){
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
    window.onload = function () {
        getURL();
        getname();
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
                $('#name').html('欢迎您，'+data.data[0].name);
            },
            error: function () {
                console.log('获取数据错误');
            }
        })
    }
</script>
<ul class="bread">
  <li><a href="punchClockE.html" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">自我考勤</a></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="punchClock.html" name="right" width="100%" height="100%"></iframe>
</div>
</div>
</body>
</html>