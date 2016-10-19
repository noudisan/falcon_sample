/**
button 	定义可点击按钮（多数情况下，用于通过 JavaScript 启动脚本）。
checkbox 	定义复选框。
file 	定义输入字段和 "浏览"按钮，供文件上传。
hidden 	定义隐藏的输入字段。
image 	定义图像形式的提交按钮。
password 	定义密码字段。该字段中的字符被掩码。
radio 	定义单选按钮。
reset 	定义重置按钮。重置按钮会清除表单中的所有数据。
submit 	定义提交按钮。提交按钮会把表单数据发送到服务器。
text 	定义单行的输入字段，用户可在其中输入文本。默认宽度为 20 个字符。
**/
(function ($) {
	$.fn.toJson = function () {
		var json=eval('(' + "{}" + ')');
	    
		//text,password,textarea,hidden,select
		this.find(":text,:password,textarea,select,input:hidden").not(":radio,:checkbox").each(function(){
			var key=this.name;
			var value=$(this).val();
			json[key]=value;
		});
		
		//radio
		this.find(":radio").each(function(){
			var key=this.name;
			var value=this.value;
			if(this.checked){
				json[key]=value;
			}
		});
				
		//checkbox
		this.find(":checkbox:checked").each(function(){
			var key=this.name;
			var value=this.value;
			if(jQuery.isEmptyObject(json[key]))
			{
				json[key]=[];
			}
			json[key].push(value);
		});	
		
		return JSON.stringify(json);
	};
})(jQuery);