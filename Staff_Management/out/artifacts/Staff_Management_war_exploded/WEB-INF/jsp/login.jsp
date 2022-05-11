<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>企业员工管理系统</title>
<link href="resources/login/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/login/style.css">
<link rel="stylesheet" type="text/css" href="resources/login/userpanel.css">
<link rel="stylesheet" href="resources/layui/css/layui.css">
<script src="resources/layui/layui.js" charset="utf-8"></script>
<script src="resources/jquery.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="resources/login/jquery.ui.all.css">
 -->
<script>
	 	 layui.use( 'layer',
				function() {
					var layer = layui.layer;
					 //触发事件
					  var active = {
					   offset: function(othis){
					      var type = othis.data('type')
					      ,text = othis.text();
					      layer.open({
					        type: 1
					        ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
					        ,id: 'layerDemo'+type //防止重复弹出
					        ,content: '<div style="padding: 40px 70px;"><span class="pwmsg">&nbsp;&nbsp;&nbsp;&nbsp;亲爱的员工,你好。请及时联系部门经理或超级管理员找回密码！</span></div>'
					        ,btn: '确认关闭'
					        ,btnAlign: 'c' //按钮居中
					        ,shade: 0.5 //显示遮罩
					        ,yes: function(){
					          layer.closeAll();
					        }
					      });
					    }
					  };
					  
					  $('#layerDemo .layui-btn').on('click', function(){
					    var othis = $(this), method = othis.data('method');
					    active[method] ? active[method].call(this, othis) : '';
					  });
			    }) 
					function check(){
						 var id = $("#id").val();
						 var password = $("#password").val();
						if (id == "" || password == "") {
							layer.msg('id和密码不能为空');
							return false;
						}
						else if (!(/(^[1-9]\d*$)/.test(id))) {
							layer.msg('请输入合法id');
							return false;
						} else {
							return true;
						}
					}
		
</script>
  <style type="text/css">
    html,body{
				width: 100%;
				height: 100%;
			}
   .mui-content{
				background: url(resources/login/1.jpg) bottom center no-repeat #efeff4 ;
				background-size: 100% 100%;
				width: 100%;
				height: 100%;
				overflow: hidden;
			}
    .font{
      font-size: 50px;
      margin-bottom: 42px;
      text-align: center;
      color: #1E90FF/* #6699FF */;
    }
    .pwmsg{
      font-size:20px;
      color:red;
    }
  </style>
</head>

<body class="mui-content">

<div class="login_m">   
<div class="font">员工管理系统</div>


<form action="login" method="post">
<div class="login_boder">
<div class="login_padding" id="login_model">
  <div class="layui-icon layui-icon-username" style="margin-bottom:5px;">&nbsp;工号：</div>
  <label>
    <input type="text" name="id" autocomplete="off" id="id" class="txt_input txt_input2" value="">
  </label>
  <div class="layui-icon layui-icon-password" style="margin-bottom:5px;">&nbsp;密码：</div>
  <label>
    <input type="password" name="password" id="password" class="txt_input" value="">
  </label>
<!--   <p class="forgot"><a id="iforget" href="javascript:void(0);">忘记密码？</a></p> -->
  <div class="rem_sub">
<!--   <div class="rem_sub_l">
  <input type="checkbox" name="checkbox" id="save_me">
   <label for="checkbox">Remember me</label>
   </div> -->
    <label>
     <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">    
      <input type="submit" class="layui-btn layui-btn-normal" style="width:140px; margin-top:20px;" name="submit" onclick="return check();" value="登陆" style="opacity: 0.7;">      
       <a href="javascript:void(0);" data-method="offset" data-type="auto" class="layui-btn layui-btn-normal layui-btn-danger" style="width:140px; margin-top:20px;float: right;"><span style="color:white;">忘记密码</span></a>
       </div>
    </label>
    <c:if test="${!empty error}">
    <div style="color:red;text-align: center;margin-top:10px;">${error}</div>
    </c:if>
  </div>
</div>
</div>
</form>
</div>

</body>
</html>