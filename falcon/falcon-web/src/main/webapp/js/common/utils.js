/**
 * 封装一些常用的工具方法
 */
jQuery.wyutil = {
	
	/**
	 * 对话框标题
	 */
	dialog_title:'<div class="widget-header widget-header-small"><h4 class="smaller"><i class="icon-ok"></i>{0}</h4></div>',
	
	/**
	 * 替换字符串中{}占位符
	 * @returns
	 */
	format: function() {
		if (arguments.length == 0){
			return null;
		}
		var str = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			var re = new RegExp('\\{' + (i-1) + '\\}', 'gm');
			str = str.replace(re, arguments[i]);
		}
		return str;
	},
	
	/**
	 * 获取form中值(json格式)
	 * @param form
	 * @returns
	 */
	getValues: function(form) {
		return this.convertArray(form.serializeArray()); 
	},
	
	/**
	 * 用json数据重置form表单
	 * TODO firefox下radio选中不标识问题
	 * @param form
	 * @param data
	 */
	setValues: function(form, data) {
		//text|hidden|textarea|radio|checkbox|option
		$.each(data, function(key, value){
			var eleType = $('[name='+key+']', form);  
			switch (eleType.attr("type")){
			//case "text" :   
			case "hidden":  
				eleType.val(value);   
				break;
			case "radio" : 
				eleType.each(function(){
					if ($(this).attr('value') == value){
						$(this).prop("checked",true);
					} else {
						$(this).prop('checked',false);
					}
				});
				break;
			case "checkbox":
				eleType.each(function(){
					if ($(this).attr('value') == value){
						$(this).prop('checked', true); 
					}else {
						$(this).prop('checked',false);
					}
				});
				break;
			default:
				eleType.val(value); 
			}  
		});  
	},
	
	/**
	 * 
	 * @param o
	 */
	convertArray: function(o){
		var v = {}; 
		for (var i in o) { 
			if (typeof (v[o[i].name]) == 'undefined') {
				v[o[i].name] = o[i].value;
			}
			else {
				v[o[i].name] += "," + o[i].value; 
			}
		} 
		return v; 
	},
	
	/**
	 * 计算字符串长度(汉字为2个字符)
	 * @param str
	 */
	countCharacters: function(str) {
		var totalCount = 0; 
	    for (var i=0; i<str.length; i++) { 
	        var c = str.charCodeAt(i); 
	        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)){ 
	             totalCount++; 
	        } 
	        else{      
	            totalCount+=2; 
	        } 
	     } 
	    return totalCount;
	}
	
}