<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="include/iconfont.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/search.css">

<title>在线订票</title>
</head>
<body>

	<div class="head">
		<div class="counter">

			<div class="logo-img"><a href="index.jsp"></a></div>
			<div class="search">
				<!--<form action="">  -->
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
          a.addEventListener('click',function(){
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
                  cl.addEventListener('click',function(){
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
                   const login_form = document.querySelector('#login-box form')
                   login_form.addEventListener('submit',function(e){
                   let phone = document.querySelector('#login_phone')
                   let password = document.querySelector('#login_password')
                   if(name.value == "" || password.value == ""){
                        phone.placeholder = "输入正确手机号!"
                        password.placeholder = "输入正确密码！"
                        e.preventDefault()
                        }
                    })
                     
               </script>
				</div>


				<a href="#" id="regist_a">注册</a>
				<script>
               const re_a = document.querySelector('#regist_a')
               re_a.addEventListener('click',function(){
               const dv = document.querySelector('#regist-box')
               dv.style.display = "block";
               })
        </script>
				<div class="regist-box" id="regist-box">
					<div class="close">
						<span class="iconfont">&#xe889;</span>
					</div>
					<script>
                   const re_cl = document.querySelector('#regist-box .close');
                   re_cl.addEventListener('click',function(){
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
                        const re_form = document.querySelector('#regist-box form')
                        re_form.addEventListener('submit',function(e){
                            let name = document.querySelector('#re_name')
                            let password = document.querySelector('#re_password')
                            let psw = document.querySelector('#re_password1')
                            let phone = document.querySelector('#re_phone')
                            if(name.value == "" || password.value == "" || phone.value == ""|| psw.value == "" ){
                                name.placeholder = "用户名不能为空!"
                                password.placeholder = "密码不能为空!"
                                phone.placeholder = "手机号不能为空!"
                                psw.placeholder="确认密码不能为空！"
                                e.preventDefault()
                            }
                            if(password.value.length<8||password.value.length>20){
                            	password.value = ""
                            	password.placeholder = "密码长度必须为 8-20位"
                                e.preventDefault()
                            }
                            if(psw.value != password.value){
                                psw.value = ""
                                psw.placeholder = "两次密码不一致!"
                                e.preventDefault()
                            }
                            if(phone.value.length != 11 || !Number(phone.value)){
                                phone.value = ""
                                phone.placeholder = "手机号存在错误!"
                                e.preventDefault()
                            }
                            <%--考虑使用正则表达式限制密码--%>                            
                        })
                </script>
				</div>

			</div>


			<div class="regist-message">
				<span class="iconfont">&#xe7ce</span> <span><%=session.getAttribute("userinfo")%></span>
				<span><%=session.getAttribute("islogin")%></span>
				<script>    
                	const tip = document.querySelector('.regist-message span:nth-child(3)')
                    const rems = document.querySelector('.regist-message')
                    const re = document.querySelector('.regist') 
                    judge=tip.textContent                   
                    if(judge == "loginsuccess"){
                    	rems.style.display = 'block'
                        re.style.display = 'none'
                        tip.innerHTML =''
                    }else{
                    	 rems.style.display = 'none'
                         re.style.display = 'block'
                    }
                </script>
			</div>


			<div class="shopping">
				<form action="Person" method="post">
					<input type="text" name="username"
						value="<%=session.getAttribute("userinfo")%>"
						style="display: none;"> 
					<input type="text" name="islogin"
						value="<%=session.getAttribute("islogin")%>"
						style="display: none;">
					<input type="text" name="usertel" value="<%=session.getAttribute("usertel")%>" style="display: none;">
					<button>个人主页</button>
				</form>
				<script>
                        const btn = document.querySelector('.shopping button')                      
                            btn.addEventListener('click', function(){
                            	if(<%=session.getAttribute("islogin")%>!="loginsuccess")
                                {
                            		 alert('请先登录或注册');                    	
                                }   
                               
                            })                     
       </script>
			</div>

		</div>

	</div>



	<div class="">
		<div class="counter">

			<!-- 表头 -->
			<div class="trick-head">
				<div>
					<span>出发日期和时间</span>
				</div>
				<div>
					<span>出发地-目的地</span><br> <span>始发站-终点站</span>
				</div>
				<div>
					<span>耗时</span>
				</div>
				<div>
					<span>票价</span>
				</div>
			</div>
			<!-- 表格内容 -->
			<div class="tirck-li">
				<ul>

					<c:forEach items="${bustickets}" var="busticket">

						<li>

							<div>
								<span>${busticket.fromDate}/${busticket.fromTime}</span>
							</div>
							<div>
								<span>${busticket.fromCity}-${busticket.toCity}</span><br>
								<span>${busticket.fromStation}-${busticket.toStation}</span>
							</div>
							<div>
								<span>${busticket.costTime}</span>
							</div>
							<div>
								<span>${busticket.price}￥</span>
							</div>
							<div>
								<form action="Order" method="post">
									<input type="text" name="id" value="${busticket.id}"
										style="display: none;"> <input type="text"
										name="usertel" value="<%=session.getAttribute("usertel")%>"
										style="display: none;"> <input type="text"
										name="userislogin"
										value="<%=session.getAttribute("islogin")%>"
										style="display: none;">
									<button>订票</button>
								</form>
							</div>

						</li>

					</c:forEach>

				</ul>

				<script>
                        const btn2 = document.querySelectorAll('.tirck-li button')
                        for(let i=0;i<btn2.length;i++){
                            btn2[i].addEventListener('click', function(){
                            	if(<%=session.getAttribute("islogin")%>
					!= "loginsuccess") {
												alert('请先登录或注册');
											} else {
												const dobox = document
														.querySelector('.do_box')
												dobox.style.display = block;
											}
										})
					}
				</script>

			</div>

			<%
			if (request.getAttribute("empty") == "empty") {
				out.println("<script>alert('暂无可售车票！')</script>");
			}
			%>

		</div>

		</div>
		    <div class="elevent">
        <div class="circle">
            <span class="iconfont">&#xe858</span>
        </div>
    </div>
    <script>
        window.addEventListener('scroll',function(){
            const n = document.documentElement.scrollTop
            const elevent = document.querySelector('.elevent')
            elevent.style.opacity = n>=100 ? 1 : 0
        })
        const up = document.querySelector('.elevent .circle span')
        up.addEventListener('click',function(){
            // document.documentElement.scrollTop = 0
            window.scrollTo(0, 0)
        })
    </script>
		
	</div>

</body>
</html>