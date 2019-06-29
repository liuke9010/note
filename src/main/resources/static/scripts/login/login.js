//login.html主处理
$(function(){//页面载入完毕
		//给登录按钮绑定单击处理,调用login.js中的方法
		$("#login").click(checkLogin);
		//单击回车键登录
		$("#enterLogin").keydown(function(event){
			if(event.keyCode==13){
				checkLogin();
			}
		});
		//给注册按钮绑定单击处理，调用register.js中的方法
		$("#regist_button").click(registerCheck);
			
	
	});

//登录页面的判断
function checkLogin(){
		//获取请求参数
		var name=$("#count").val().trim();
		var password=$("#password").val().trim();
		//检测参数格式
		//清空之前的提示信息
		$("#count_span").html("");
		$("#password_span").html("");
		var ok=true;//设置开关，判断是否通过检测
		if(name==""){
			ok=false;
			$("#count_span").html("用户名为空");
		}
		if(password==""){
			ok=false;
			$("#password_span").html("密码为空");
		}
		//发送Ajax请求

		//如果通过检测，则发送请求
		if(ok){
			$.ajax({
				url:basepath+"/user/login.do",
				type:"post",
				data:{"name":name,"password":password},
				dataType:"json",
				success:function(result){
					if(result.status==0){//登陆成功
						var user=result.data;//得到user信息
						//将用户的信息写入cookie
						//用户名，数据，保存时间（单位小时）
						addCookie("uid",user.cn_user_id,2);
						addCookie("uname",user.cn_user_name,2);
						
						window.location.href="edit.html";//跳转到编辑界面
					}else if(result.status==1){//用户名错误
						$("#count_span").html(result.msg);
					}else if(result.status==2){//密码错误
						$("#password_span").html(result.msg);
					}
				},
				error:function(){
					alert("登录异常");
				}
				
			});
		}
	}

	//注册页面的判断
	function registerCheck(){
		//获取请求参数
		var name=$("#regist_username").val().trim();
		var nick=$("#nickname").val().trim();
		var password=$("#regist_password").val().trim();
		var password1=$("#final_password").val().trim();
		//格式检查
		//检查格式之前，先清空原有的提示信息
		$("#warning_1 span").empty();
		$("#warning_2 span").empty();
		$("#warning_3 span").empty();
		//判断是否通过检测
		var pass=true;
		if(name==""){//判断用户名是否为空
			pass=false;
			$("#warning_1").show();
			console.log("**");
			$("#warning_1 span").html("用户名为空");
			
		}
		
		if(password==""){//判断密码是否为空
			pass=false;
			$("#warning_2").show();
			$("#warning_2 span").html("密码为空");
		}else if(password.length<6){//判断密码长度
			pass=false;
			$("#warning_2").show();
			$("#warning_2 span").html("密码长度太短");
		}
		
		if(password1==""){//判断确认密码是否为空
			pass=false;
			$("#warning_3").show();
			$("#warning_3 span").html("密码为空");
		}else if(password1.length<6){
			pass=false;
			$("#warning_3").show();
			$("#warning_3 span").html("确认密码过短");
		}else if(password1!=password){//确认密码是否前后相同
			pass=false;
			$("#warning_3").show();
			$("#warning_3 span").html("密码前后不一致");
		}

		//发送Ajax请求
		if(pass){
			$.ajax({
				url:basepath+"/user/add.do",
				type:"post",
				data:{"name":name,"password":password,"nick":nick},
				dataType:"json",
				success:function(result){
					if(result.status==0){//注册成功
						alert(result.msg);//提示成功
						$("#back").click();//触发返回按钮，转向登录界面
						
					}else if(result.status==1){//用户名被占用
						$("#warning_1").show();
						console.log("**");
						$("#warning_1 span").html(result.msg);
					}
					
				},
				error:function(){
					alert("注册异常");
				}
			});
		}
					
	}

		