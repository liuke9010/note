/**
 * 根据笔记本ID加载笔记列表
*/
function loadBookNotes(){
	//切换列表显示
	$("#pc_part_2").show();//笔记列表视图
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();//搜索结果视图
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
	
	//设置笔记本li选中效果,先清除
	$("#book_ul a").removeClass("checked");//清除原有样式
	$(this).find("a").addClass("checked");//添加选中效果
	//获取请求参数
	var bookId=$(this).data("bookId");
	//格式检查(涉及用户输入的参数时需要检查)
	//发送Ajax请求
	$.ajax({
		url:basepath+"/note/loadnotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			//清空原有笔记列表,以下两种方式都可以
			//$("#note_ul").empty();
			$("#note_ul li").remove();
				
			//获取返回的笔记列表的集合
				var notes=result.data; 
			//循环生成笔记列表
				for(var i=0;i<notes.length;i++){
					//获取笔记ID
					var noteId=notes[i].cn_note_id;
					//获取笔记title
					var noteTitle=notes[i].cn_note_title;
					
					//构建列表元素
					var sli="";
					sli+="<li class='online'>";
					sli+="	<a>";
					sli+="		<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteTitle;
					sli+="		<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					sli+="	</a>";
					sli+="	<div class='note_menu' tabindex='-1'>";
					sli+="		<dl>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>";
					sli+="		</dl>";
					sli+="	</div>";
					sli+="</li>";
					
					
					//将noteId绑定到li元素上
					var $li=$(sli);
					$li.data("noteId",noteId);
					//将li元素添加到ul列表中
					$("#note_ul").append($li);
					
					//将新添加的元素判断是否该增加已分享图标
					var typeId=notes[i].cn_note_type_id;
					if(typeId=='2'){//添加分享图标
						var img="<i class='fa fa-sitemap'></i>";
						var $li=$("#note_ul li:last");//获取新加的li元素
						$li.find(".btn_slide_down").before(img);
					}
				}
			}
		},
		error:function(){
			alert("加载笔记列表失败");
		}
	});
}
/**
 * 根据笔记ID加载笔记信息
*/
function loadNote(){
		//切换显示窗口
		$("#pc_part_3").show();//搜索结果预览区
		$("#pc_part_5").hide();//编辑笔记笔记显示区
	
	
		//设置选中状态
		$("#note_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//获取请求参数
		var noteId=$(this).data("noteId");
		
		//检查格式(这里不用)
		//发送Ajax请求
		$.ajax({
			url:basepath+"/note/load.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//获取笔记标题
					var title=result.data.cn_note_title;
					//获取笔记内容
					var body=result.data.cn_note_body;
					//设置到编辑区域
					$("#input_note_title").val(title);//设置标题
					//设置文本内容
						um.setContent(body);
				}
			},
			error:function(){
				alert("加载笔记异常");
			}
		});
		
	}

/**
 * 保存笔记
 */


function upDateNote(){
		//获取请求参数
		var $li=$("#note_ul a.checked").parent();//根据加了checked标记的a元素找到父类li
		var noteId=$li.data("noteId");//获取当前选中项li元素绑定的笔记id（难点）
		var title=$("#input_note_title").val().trim();//获取当前标题
		var body=um.getContent();//获取笔记内容(难点)
		//清除之前的提示信息
		$("#note_title_span").html("");
		//格式检查
		if($li.length==0){//判断是否选择到笔记
			alert("请选择需要保存的笔记");
		}else if(title==""){//检查标题是否为空
			$("#note_title_span").html("<font color='red'>标题不能为空</font>");
			
		}else{//有选中的笔记
		
			//发送Ajax请求
			$.ajax({
				url:basepath+"/note/update.do",
				type:"post",
				data:{"noteId":noteId,"title":title,"body":body},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						//更新列表li中的标题（难点）,将a中的元素整体替换掉
						var sli="";
						sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+title;
						sli+="<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
						//将li元素中的a整体替换掉
						$li.find("a").html(sli);
						//提示成功
						alert(result.msg);
					}
				},
				error:function(){
					alert("保存失败");
				}
				
			});
		}
	};
/**
 * 创建笔记
 */
function addNote(){
		//获取请求参数
		var userId=getCookie("uid");
		var bookId=$("#book_ul a.checked").parent().data("bookId");//获取笔记本的id
		var noteName=$("#input_note").val().trim();//获取笔记名称
		//格式检查
		var ok=true;
		if(userId==null){//获取用户id失败,刷新页面
			ok=false;
			window.location.href="login.html";
		}
		if(bookId==null){//获取笔记本id失败,刷新页面
			ok=false;
			window.location.href="login.html";
		}
		if(noteName==""){//笔记名称为空，给出提示
			ok=false;
			$("#note_title_span").html("<font color='red'>笔记本名称不能为空</font>");
		}
		//发送Ajax请求
		if(ok){
			$.ajax({
				url:basepath+"/note/addNote.do",
				type:"post",
				data:{"userId":userId,"bookId":bookId,"noteName":noteName},
				dataType:"json",
				success:function(result){
					//关闭对话框
					closeAlertWindow();
					//生成笔记li元素
					var noteId=result.data.cn_note_id;//获取服务器返回的笔记id
					var noteName=result.data.cn_note_title;
					//构建列表元素
					var sli="";
					sli+="<li class='online'>";
					sli+="	<a>";
					sli+="		<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName;
					//添加已分享小图标
					sli+="		<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					sli+="	</a>";
					sli+="	<div class='note_menu' tabindex='-1'>";
					sli+="		<dl>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";
					sli+="			<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>";
					sli+="		</dl>";
					sli+="	</div>";
					sli+="</li>";
					
					
					//将noteId绑定到li元素上
					var $li=$(sli);
					$li.data("noteId",noteId);
					//将li元素添加到ul列表中
					$("#note_ul").append($li);
					
					//提示创建成
					alert(result.msg);
					
				},
				error:function(){
					alert("创建笔记失败");
				}
			});
		}
		
	};
	
//弹出笔记菜单操作
function popNoteMenu(){
		//先隐藏所有笔记菜单
		$("#note_ul div").hide();
		//显示点击的笔记菜单
		var $menu=$(this).parent().next();
		$menu.slideDown(500);//下拉显示，显示动作完成时间1000毫秒
		//设置点击笔记选中效果
		$("#note_ul a").removeClass("checked");
		$(this).parent().addClass("checked");
		//阻止事件向li,body冒泡
		return false;//当前点击事件不会触发比当前范围更大的事件
	};
//隐藏笔记菜单
function hideNoteMenu(){
		//隐藏所有笔记菜单
		$("#note_ul div").hide();
	};
	
//删除笔记
function deleteNote(){
		//获取请求参数
		var $li=$("#note_ul a.checked").parent();//获取选中行的li
		var noteId=$li.data("noteId");
		//发送ajax请求
		$.ajax({
			url:basepath+"/note/delete.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//移除列表中删除的笔记的li元素
					$li.remove();
					//提示删除笔记成功
					alert(result.msg);
				}
				
				
			},
			error:function(){
				alert("删除笔记失败");
			}
		});
	}
//移动笔记
function moveNote(){
		//获取请求参数
		var $li=$("#note_ul a.checked").parent();//当前li
		var noteId=$li.data("noteId");//获取当前笔记的id
			//获取选中的笔记本的id(重点)，直接通过select的id就可获得当前选中项的id属性
		var bookId=$("#moveSelect").val();
		//格式检查
		
		//发送Ajax请求
		$.ajax({
			url:basepath+"/note/move.do",
			type:"post",
			data:{"noteId":noteId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//移除笔记列表的li
					$li.remove();
					//提示移动笔记成功或失败
					alert(result.msg);
				}
			},
			error:function(){
				alert("移动笔记失败");
			}
			
		});
		
	}
//分享笔记
function shareNote(){
		// 获取请求参数
		var $li=$(this).parents("li");
		var noteId=$li.data("noteId");
		//发送ajax请求
	$.ajax({
		url:basepath+"/note/share.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//添加以分享图标
				var img="<i class='fa fa-sitemap'></i>";
				$li.find(".btn_slide_down").before(img);
				
				//提示成功
				alert(result.msg);
			}else if(result.status==1){
				alert(result.msg);
			}
			
		},
		error:function(){
			alert("分享失败");
		}
	});
	
}
//分页加载分享的笔记
function searchSharePage(keyword,page){
	$.ajax({
			url:basepath+"/note/search_share.do",
			type:"post",
			data:{"keyword":keyword,"page":page},
			dataType:"json",
			success:function(result){
				//获取服务器返回的结果
				var shares=result.data;
				//循环解析生成列表li元素
				for(var i=0;i<shares.length;i++){
					var shareId=shares[i].cn_share_id;
					var shareTitle=shares[i].cn_share_title;
					var shareBody=shares[i].cn_share_body;
					//生成一个li
					var sli="";
					sli+="<li class='online'>";
					sli+="	<a>";
					sli+="		<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+shareTitle;
					sli+="		<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-star'></i></button>";
					sli+="	</a>";
					sli+="</li>";
					var $li=$(sli);
					$li.data("shareId",shareId);//绑定id，查看时使用
					//添加到搜索结果ul中,div中只有一个ul，可以不用单独添加id
					$("#pc_part_6 ul").append($li);
					
				}
			},
			error:function(){
				alert("搜索异常");
			}
		});
}
