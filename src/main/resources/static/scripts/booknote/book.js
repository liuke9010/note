/**
 * 根据用户ID加载笔记本列表
 */
function loadUserBooks(){
	//获取请求参数
		var userId=getCookie("uid");
		//检查格式
		if(userId==null){//判断是否为空，为空则需要重新登陆
			window.location.href="login.html";
		}else{
		//发送ajax请求
			$.ajax({
				url:basepath+"/book/loadbooks.do",
				type:"post",
				data:{"userId":userId},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						//获取返回的笔记本集合
						var books=result.data;
						//循环生成列表元素
						for(var i=0;i<books.length;i++){
							//获取笔记本ID
							var bookId=books[i].cn_notebook_id;
							//获取笔记本Name
							var bookName=books[i].cn_notebook_name;
							//构建列表li元素
							var sli="";
							sli+='<li class="online">';
							sli+='	<a>';
							sli+='		<i class="fa fa-book" title="online" rel="tooltip-bottom">';
							sli+='		</i>'+ bookName;
							sli+='	</a>';
							sli+='</li>';	
						//将bookId绑定到li元素上
						var $li=$(sli);
						$li.data("bookId",bookId);
						//将li元素添加到ul列表中
						$("#book_ul").append($li);
						
						}
					}
				},
				error:function(){
					alert("加载笔记本失败");
				}
			});
		}
};

//创建笔记本
function addNoteBook(){//单击创建按钮
		//获取请求参数
		var userId=getCookie("uid");//获取当前用户的id
		var bookName=$("#input_notebook").val().trim();//获取笔记本名称
		//格式检查
		var ok=true;
		if(bookName==""){
			//笔记本名称为空，给出提示
			ok=false;
			$("#book_title_span").html("<font color='red'>笔记本名称不能为空</font>");
		}
		if(userId==null){//获取用户id失败，刷新页面
			ok=false;
			window.location.href="login.html";
		}
		//发送Ajax请求
		if(ok){
			$.ajax({
				url:basepath+"/book/add.do",
				type:"post",
				data:{"userId":userId,"bookName":bookName},
				dataType:"json",
				success:function(result){
					//关闭对话框
					closeAlertWindow();
					//生成笔记本li元素
					var bookId=result.data.cn_notebook_id;
					var bookName=result.data.cn_notebook_name;
					//构建列表li元素
						var sli="";
						sli+='<li class="online">';
						sli+='	<a>';
						sli+='		<i class="fa fa-book" title="online" rel="tooltip-bottom">';
						sli+='		</i>'+ bookName;
						sli+='	</a>';
						sli+='</li>';	
					//将bookId绑定到li元素上
					var $li=$(sli);
					$li.data("bookId",bookId);
					//将li元素添加到ul列表中
					$("#book_ul").append($li);
					
					//提示成功
					alert(result.msg);	
				},
				error:function(){
					alert("创建笔记本失败");
				}
			});
		}
		
	};