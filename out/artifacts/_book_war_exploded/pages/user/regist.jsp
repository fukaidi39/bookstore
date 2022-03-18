<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含base标签,css样式,jquery文件--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
	<script type="text/javascript">
		$(function (){
			//给验证码的图片，绑定单击事件
			$("#code_img").click(function (){
				//当前正在响应的验证码图片dom对象,src可读可写，在最后加入时间戳可以防止缓存
				this.src = "${basePath}kaptcha.jpg?d="+new Date();
			});

			//注册按钮绑定单击事件
			$("#sub_btn").click(function (){
				//验证用户名： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
				var regex = /^[a-z0-9_-]{5,12}$/;
				var username = $("#username").val();
				var password = $("#password").val();
				var repwd = $("#repwd").val();
				var email = $("#email").val();
				if(!regex.test(username)){
					$("span.errorMsg").text("输入用户名不合法！");
					return false;
				}
				//验证密码： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
				if(!regex.test(password)){
					$("span.errorMsg").text("输入密码不合法！");
					return false;
				}
				//验证确认密码： 和密码相同
				if(repwd != password){
					$("span.errorMsg").text("前后密码不一致！");
					return false;
				}
				//邮箱验证： xxxxx@xxx.com
				var emailRegex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!emailRegex.test(email)){
					$("span.errorMsg").text("输入电子邮件不合法！")
					return false;
				}
				//验证码： 现在只需要验证用户已输入。 因为还没讲到服务器。 验证码生成
				var codeText = $("#code").val();
				//去除前后重复 $.trim()方法去除前后空格
				codeText = $.trim(codeText);
				if(codeText == null || codeText == ""){
					$("span.errorMsg").text("请输入正确的验证码！")
					return false;
				}
				//警告框清空
				$("span.errorMsg").text("")

			});
		});

	</script>


</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${requestScope.msg==null? "":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off"
										   tabindex="1" name="username" id="username" value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off"
										   tabindex="1" name="password" id="password" value="${requestScope.password}"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off"
										   tabindex="1" name="repwd" id="repwd" value="${requestScope.password}"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off"
										   tabindex="1" name="email" id="email" value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 130px; height: 34px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>