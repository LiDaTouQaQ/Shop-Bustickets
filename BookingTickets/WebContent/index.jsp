<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="include/iconfont.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/base.css">
<title>在线订票</title>
</head>
<body>

<div class="head">
  <div class="counter">
  
    <div class="logo-img"><a href="index.jsp"></a></div>
    <div class="search">
      <!--<form action="">-->
        <input type="text" placeholder="搜索车票/火车票/飞机票">
        <button><span class="iconfont">&#xe9a0</span></button> 
      <!--</form>  -->
    </div>
     
    <div class="regist">
       <a href="#"><span class="iconfont">&#xe7ca</span>登录</a><span class="rod">|</span>
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
                      <span>手机号</span>
                  	  <input name="logintel" id="login_phone" type="text" placeholder="输入手机号"> 
                  </div>
                  <div>
                       <span>密码</span>
                       <input name="loginpsw" id="login_password" type="password" placeholder="输入密码">
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
          <%
          //if(request.getAttribute("login")!="")
          //{           
                  if(request.getAttribute("login")=="loginerror")
               		{
               			out.print("<script>alert('登录失败！');</script>");
               		} 
          // }
           %>


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
                        <span>用户名</span>
                        <input name="name" id="re_name" type="text" placeholder="输入用户名"> 
                   </div>
                   <div>
                        <span>手机号</span>
                        <input name="tel" id="re_phone" type="text" placeholder="输入手机号"> 
                   </div>
                   <div>
                        <span>密码</span>
                        <input name="psw" id="re_password" type="password" placeholder="输入密码">
                   </div>
                   <div>
                         <span>确认密码</span>
                         <input type="password" placeholder="确认密码" id="re_password1">
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
                            if(name.value.length>20){
                            	name.value = ""
                                name.placeholder = "用户名长度超过20位"
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
    	   <%
           if(request.getAttribute("tip")=="error")
           {      	    
       			out.print("<script>alert('手机号码已经注册过，注册失败！');</script>");       			
           }
    	   %>
    </div>

	
	<div class="regist-message">
                <span class="iconfont">&#xe7ce</span>
                <span><%=session.getAttribute("userinfo")%></span>
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
    		 <input type="text" name="username" value="<%=session.getAttribute("userinfo")%>" style="display: none;">
    		 <input type="text" name="islogin" value="<%=session.getAttribute("islogin")%>" style="display: none;">
    		 <input type="text" name="usertel" value="<%=session.getAttribute("usertel")%>" style="display: none;">
    		 <button>个人主页</button>		 
    	</form>
    </div>
    <!-- 登录失败之后点击个人中心不再有提示 -->
    <script>
                        const btns = document.querySelector('.shopping button') 
                            btns.addEventListener('click', function(){                         	
                            	if(<%=session.getAttribute("islogin")%>=="loginerror"||<%=session.getAttribute("islogin")%>==null)
                                {
                            		 alert('请先登录或注册');                          		
                                }                              	
                            })   

       </script>
    
  </div>
  
</div>


<div class="center">
  <div class="counter">
    <div class="font-box">
      <div class="tran">
      
        <div class="head-box">车票选购</div>
        <div class="tran-box">       
          <form action="SearchBusTickets" method="post">      
          <div class="search-box">
            <span>出发地</span>
             <select name="select_from" id="">
              <optgroup label="选择出发地">
               	<option>上海</option>              
                <option>杭州</option>
     			<option>闵行</option>
                <option>杭州上城区</option>
           		<option>杭州於潜镇</option>
             	<option>杭州拱墅</option>
              </optgroup>                     
            </select>                    
          </div>
          <div class="search-box">
            <span>目的地</span>
            <select name="select_to" id="">
              <optgroup label="选择目的地">
              	   <option>杭州</option>
              	   <option>合肥</option>
              	   <option>沈阳</option>
                   <option>成都</option>
                   <option>北京</option>
                   <option>株洲</option>
                   <option>长沙</option>
                   <option>上海</option>
                   <option>杭州上城区</option>
              </optgroup>
            </select>    
          </div>
          <div class="search-box">
            <span>出发日期</span>
            <input type="date" name="date" value="">
            <script>
                    const date = document.querySelector('input[name="date"]')
                    let d = new Date()
                    year = d.getFullYear()
                    month = d.getMonth() > 10 ? d.getMonth()+1 : "0" +(d.getMonth()+1)
                    day = d.getDay() > 10 ? d.getDay() : "0" +d.getDay()
                    d = year + "-"+month +"-"+day
                    console.log(d)
                    date.value = d
            </script>
          </div>
          <button>搜索</button>
          </form>
        </div>
        
      </div>
    </div>
    <div class="circle-picture"></div>
    <script>
    </script>
  </div>
</div>

 <%
           if(request.getAttribute("ordertip")=="error")
           {      	    
       			out.print("<script>alert('系统出错，订票失败请稍后再试！');</script>");       			
           }
 		   if(request.getAttribute("ordertip")=="success")
 		   {      	    
				out.print("<script>alert('订票成功，可在我的订单查看！');</script>");       			
 		   }
%>

</body>
</html>