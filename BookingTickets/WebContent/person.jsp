<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/person.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="include/iconfont.css">
<link rel="stylesheet" href="css/base.css">
<title>Document</title>
</head>
<body>
	<div class="head">
		<div class="counter">

			<div class="logo-img">
				<a href="index.jsp"></a>
			</div>
			<div class="search">
				<!--<form action="">-->
				<input type="text" placeholder="搜索车票/火车票/飞机票">
				<button>
					<span class="iconfont">&#xe9a0</span>
				</button>
				<!--</form>  -->
			</div>

			<div class="regist">
				<a href="#"><span class="iconfont">&#xe7ca</span>登录</a><span
					class="rod">|</span>
				<script>
					const a = document.querySelector('.regist a:first-child')
					a.addEventListener('click', function() {
						const dv = document.querySelector('#login-box')
						dv.style.display = "block";
					})
				</script>
				<div class="login-box" id="login-box">
					<div class="close">
						<span class="iconfont">&#xe889;</span>
					</div>
					<script>
						const cl = document.querySelector('.login-box .close');
						cl.addEventListener('click', function() {
							const dv = document.querySelector('#login-box')
							dv.style.display = "none";
						})
					</script>
					<form action="Login" method="post">
						<div>
							<span>手机号</span> <input name="logintel" id="login_phone"
								type="text" placeholder="输入手机号">
						</div>
						<div>
							<span>密码</span> <input name="loginpsw" id="login_password"
								type="password" placeholder="输入密码">
						</div>
						<button id="login-button">登录</button>
					</form>
					<script>
						const login_form = document
								.querySelector('#login-box form')
						login_form.addEventListener('submit', function(e) {
							let phone = document.querySelector('#login_phone')
							let password = document
									.querySelector('#login_password')
							if (name.value == "" || password.value == "") {
								phone.placeholder = "输入正确手机号!"
								password.placeholder = "输入正确密码！"
								e.preventDefault()
							}
						})
					</script>
				</div>
				<%
				//if(request.getAttribute("login")!="")
				//{           
				if (request.getAttribute("login") == "loginerror") {
					out.print("<script>alert('登录失败！');</script>");
				}
				// }
				%>


				<a href="#" id="regist_a">注册</a>
				<script>
					const re_a = document.querySelector('#regist_a')
					re_a.addEventListener('click', function() {
						const dv = document.querySelector('#regist-box')
						dv.style.display = "block";
					})
				</script>
				<div class="login-box regist-box" id="regist-box">
					<div class="close">
						<span class="iconfont">&#xe889;</span>
					</div>
					<script>
						const re_cl = document
								.querySelector('#regist-box .close');
						re_cl.addEventListener('click', function() {
							const dv2 = document.querySelector('#regist-box')
							dv2.style.display = "none"
						})
					</script>
					<form action="Register" method="post">
						<div>
							<span>用户名</span> <input name="name" id="re_name" type="text"
								placeholder="输入用户名">
						</div>
						<div>
							<span>手机号</span> <input name="tel" id="re_phone" type="text"
								placeholder="输入手机号">
						</div>
						<div>
							<span>密码</span> <input name="psw" id="re_password"
								type="password" placeholder="输入密码">
						</div>
						<div>
							<span>确认密码</span> <input type="password" placeholder="确认密码"
								id="re_password1">
						</div>
						<button id="login-button">注册</button>
					</form>
					<script>
						const re_form = document
								.querySelector('#regist-box form')
						re_form
								.addEventListener(
										'submit',
										function(e) {
											let name = document
													.querySelector('#re_name')
											let password = document
													.querySelector('#re_password')
											let psw = document
													.querySelector('#re_password1')
											let phone = document
													.querySelector('#re_phone')
											if (name.value == ""
													|| password.value == ""
													|| phone.value == ""
													|| psw.value == "") {
												name.placeholder = "用户名不能为空!"
												password.placeholder = "密码不能为空!"
												phone.placeholder = "手机号不能为空!"
												psw.placeholder = "确认密码不能为空！"
												e.preventDefault()
											}
											if (password.value.length<8||password.value.length>20) {
												password.value = ""
												password.placeholder = "密码长度必须为 8-20位"
												e.preventDefault()
											}
											if (psw.value != password.value) {
												psw.value = ""
												psw.placeholder = "两次密码不一致!"
												e.preventDefault()
											}
											if (phone.value.length != 11
													|| !Number(phone.value)) {
												phone.value = ""
												phone.placeholder = "手机号存在错误!"
												e.preventDefault()
											}
					<%--考虑使用正则表达式限制密码--%>
						})
					</script>
				</div>
				<%
				if (request.getAttribute("tip") == "error") {
					out.print("<script>alert('手机号码已经注册过，注册失败！');</script>");
				}
				%>
			</div>


			<div class="regist-message">
				<span class="iconfont">&#xe7ce</span> <span><%=session.getAttribute("userinfo")%></span>
				<span><%=session.getAttribute("islogin")%></span>
				<script>
					const tip = document
							.querySelector('.regist-message span:nth-child(3)')
					const rems = document.querySelector('.regist-message')
					const re = document.querySelector('.regist')
					judge = tip.textContent
					if (judge == "loginsuccess") {
						rems.style.display = 'block'
						re.style.display = 'none'
						tip.innerHTML = ''
					} else {
						rems.style.display = 'none'
						re.style.display = 'block'
					}
				</script>
			</div>



			<!-- 登录失败之后点击个人中心不再有提示 -->
			<script>
				const btns = document.querySelector('.shopping button')
				btns.addEventListener('click', function() {
					if (
			<%=session.getAttribute("islogin")%>
				== "loginerror"
							||
			<%=session.getAttribute("islogin")%>
				== null) {
						alert('请先登录或注册');
					}
				})
			</script>

		</div>
	</div>
	</div>

	<div>

		<div class="counter">
			<div class="work_line">
				<ul>
					<li>
						<div class="title now_display">个人信息</div>
						<div class="message_box now_display">
							<div class="m_title">个人信息</div>
							<div>
								用户名<span><%=session.getAttribute("userinfo")%></span>
							</div>
							<div>
								手机号<span><%=session.getAttribute("usertel")%></span>
							</div>
						</div>
					</li>
					<li>
						<div class="title">订票信息</div>
						<div class="message_box">
							<div class="m_title">订票信息</div>
							<div>
								全部订单


								<table>
									<tr>
										<td>订单号</td>
										<td>城市</td>
										<td>车站</td>
										<td>时间</td>
										<td>票价</td>
										<td>订票人</td>
										<td>联系电话</td>
										<td>数量</td>
										<td>耗时</td>
										<td>下单时间</td>
										<td>订单状态</td>
									</tr>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td>${order.id}</td>
											<td>${order.city}</td>
											<td>${order.station}</td>
											<td>${order.time}</td>
											<td>${order.price}</td>
											<td>${order.user}</td>
											<td>${order.tel}</td>
											<td>${order.num}</td>
											<td>${order.costtime}</td>
											<td>${order.ordertime}</td>
											<td>${order.state}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</li>
					<li>
						<div class="title">修改密码</div>
						<div class="message_box">
							<div class="m_title">修改密码</div>
							<form action="ChangePsw" method="post">
								<div>
									手机号<span><%=session.getAttribute("usertel")%></span>
								</div>
								<div>
									旧密码<input type="password" name="psw" id="ppp"></input>
								</div>
								<div>
									新密码<input type="password" name="newpsw" id="newpsw"></input>
								</div>
								<button id="btu">确定</button>
								<script>
									const psw = document
											.querySelector('#newpsw')
									const p = document.querySelector('#ppp')
									const btu = document.querySelector('#btu')
									console.log(psw)
									btu
											.addEventListener(
													'click',
													function(e) {
														if (psw.value.length<8||psw.value.length>20) {
															psw.value = ""
															psw.placeholder = "密码长度为8-20位"
															e.preventDefault()
														}
														if (psw.value === ppp.value) {
															psw.value = ""
															ppp.value = ""
															ppp.placeholder = "新旧密码一样"
															psw.placeholder = "新旧密码一样"
															e.preventDefault()
														}
													})
								</script>

							</form>
						</div>
					</li>
				</ul>
				<script>
					const ul = document.querySelector('ul')
					ul
							.addEventListener(
									'click',
									function(e) {
										if (e.target.tagName === "DIV"

												&& (e.target.className == "title" || e.target.className == "title now_display")) {
											const old = document
													.querySelector('.title.now_display')
											const old2 = document
													.querySelector('.message_box.now_display')
											if (old && old2) {
												old.classList
														.remove('now_display')
												old2.classList
														.remove('now_display')
											}
											e.target.classList
													.add('now_display')
											e.target.parentNode
													.querySelector('.message_box').classList
													.add('now_display')

										}
									})
				</script>
			</div>
		</div>
		<%
		if (request.getAttribute("changetip") == "success") {
			out.print("<script>alert('密码修改成功！');</script>");
		}
		if (request.getAttribute("changetip") == "error") {
			out.print("<script>alert('密码或用户名错误，密码修改失败！');</script>");
		}
		%>
	</div>
</body>
</html>