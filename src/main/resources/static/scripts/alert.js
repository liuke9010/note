/**
 *封装对话框处理
 */
//弹出创建笔记本对话框
function alertAddBookWindow(){
	//单击是弹出alert_notebook.html对话框
	$("#can").load("alert/alert_notebook.html");
	//背景，灰色背景遮挡
	$(".opacity_bg").show();
};
//关闭对话框
function closeAlertWindow(){
	//关闭操作
	$("#can").empty();//清空对话框div
	$(".opacity_bg").hide();//隐藏背景
	
};
//弹出重命名笔记本对话框
function alertRenameBookWindow(){
	//单击是弹出alert_rename.html对话框
	$("#can").load("alert/alert_rename.html");
	//背景，灰色背景遮挡
	$(".opacity_bg").show();
};
//弹出创建笔记对话框
function alertAddNoteWindow(){
	//判断是否选中笔记本
	var $a=$("#book_ul a.checked");
	if($a.length==0){
		alert("请选择笔记本");
	}else{
		//单击是弹出alert_note.html对话框
		$("#can").load("alert/alert_note.html");
		//背景，灰色背景遮挡
		$(".opacity_bg").show();
	}
	
};

//创建删除笔记本对话框
function alertDeleteNoteBookWindow(){
	//单击是弹出alert_notebook.html对话框
	$("#can").load("alert/alert_delete_notebook.html");
	//背景，灰色背景遮挡
	$(".opacity_bg").show();
};
//创建删除笔记对话框
function alertDeleteNoteWindow(){
	//单击是弹出alert_delete_note.html对话框
	$("#can").load("alert/alert_delete_note.html");
	//背景，灰色背景遮挡
	$(".opacity_bg").show();
};
//创建转移笔记对话框
function alertMoveNoteWindow(){
	//单击是弹出alert_move.html对话框
	$("#can").load("alert/alert_move.html",function(){
		//获取book_ul中所有li
		var books=$("#book_ul li");
		//循环li,获取出笔记本ID和名称
		for(var i=0;i<books.length;i++){
			var $li=$(books[i]);//获取li元素并转化为jQuery对象
			var bookId=$li.data("bookId");//获取笔记本id
			var bookName=$li.text().trim();//获取笔记本名称
			//利用笔记本ID和名称生成<option>
			var sopt='';
			sopt+='<option value="'+bookId+'">';
			sopt+=bookName;
			sopt+='</option>';
			//将<option>添加到对话框的<select>中
			$("#moveSelect").append(sopt);
		}
		
	});
	//背景，灰色背景遮挡
	$(".opacity_bg").show();
};



