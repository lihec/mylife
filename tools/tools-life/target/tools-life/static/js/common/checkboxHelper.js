Array.prototype.indexOf = function (value) {
	var i;
	for (i=0; i < this.length; i++) {
		if (this[i] === value) {
			return i;  
		}  
	}  
	return -1;  
};
Array.prototype.contains = function(obj){
	var i = this.length;
	while(i--){
		if(this[i] == obj)
			return true
	}
	return false;
}
var checkedArray = new Array();	
var ChkHelper = function(){
	return{
		getChecksVal : function(chkName,checked){
			checked = checked==null?true:false;
			var ched = checked==true?':checked':'';
			var vals='';
			$(":checkbox[name="+chkName+"]"+ched).each(
				function(){
					var chkval=$(this).val();
					if(vals=='')
						vals = chkval;
					else
						vals =vals + "," + chkval;
				}
			);
			return vals;
		},
		setCheckAll : function(selectAllId,checksName,arrayFalg){
			if(!arrayFalg){
				$('#'+selectAllId).click(function() {
					ChkHelper.checkAll($(this).context.checked,checksName);
				});
			}else{
				$('#'+selectAllId).click(function() {
					ChkHelper.checkAllNoArray($(this).context.checked,checksName);
				});
			}
			this.clear(selectAllId);
		},
		checkAll : function(checked,checksName){
			$(":checkbox[name="+checksName+"]").each(
				function(){
					$(this).context.checked = checked;
					if(!checkedArray.contains($(this).val())){
						if(checked){
							checkedArray.push($(this).val());
						}
					}else{
						if(!checked)
						checkedArray.splice(checkedArray.indexOf($(this).val()),1);
					}
					$('#checkedCount').html(checkedArray.length);
				}
			);
		},

		checkAllNoArray : function(checked,checksName){
			$(":checkbox[name="+checksName+"]").each(
				function(){
					$(this).context.checked = checked;
				}
			);
		},
		setAutoCheckAll : function(selectAllId,checksName,arrayFalg){
			this.setCheckAll(selectAllId, checksName,arrayFalg);

			$(":checkbox[name="+checksName+"]").each(function(){
					$(this).click(function(){
						var checked = $(this).context.checked;
						var isAllChecked = true;
						$(":checkbox[name="+checksName+"]").each(function(){
							if($(this).context.checked != true)
								isAllChecked = false;
						});
						$.each($('#'+selectAllId),function(){
							$(this).context.checked = isAllChecked;
						});
					})
					
				}	
			);
			if($(":checkbox[name="+checksName+"]").length>0){
				var isAllChecked = true;
				$(":checkbox[name="+checksName+"]").each(function(){
					if($(this).context.checked != true)
						isAllChecked = false;
				});
				
				$.each($('#'+selectAllId),function(){
					$(this).context.checked = isAllChecked;
				});
			}
		},
		setCheckedArray : function(checksName){
			$(":checkbox[name="+checksName+"]").click(function() {
				var checked = $(this).context.checked;
				if(!checkedArray.contains($(this).val())){
					if(checked){
						checkedArray.push($(this).val());
					}
				}else{
					if(!checked)
					checkedArray.splice(checkedArray.indexOf($(this).val()),1);
				}
				$('#checkedCount').html(checkedArray.length);
			});
		},
		getCheckedArray : function(joinStr){
			if(joinStr)
				return checkedArray.join(joinStr);
			else
				return checkedArray;
		},
		clear : function(IDorName,type,isClearValue){
			type = type==null?'id':'name';
			isClearValue = isClearValue==null?false:isClearValue
			if(type=='id'){
				$('#'+IDorName).attr('checked',false);
			}else{
				$("input[type=checkbox][name="+checksName+"]").each(
					function(){
						$(this).attr('checked',false);
						if(isClearValue)
							$(this).val('');
					}
				)
			}
		}
		
	}
}();