$.extend($.fn.validatebox.defaults.rules, {   
    isnum: {   
        validator: function(value){  
        	var flag=window.isNaN(value);
            return flag ? false : (value >0 ? true : false);   
        },   
        message: '请输入大于0的数值'  
    }   
});  
