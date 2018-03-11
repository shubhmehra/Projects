function InitializeTaxability() {
	showHideSaveEditButtons("hide");
	$('#dateValue').datepicker({
	    autoclose: true,
	    todayHighlight: true,
	    dateFormat: 'dd/mm/yy',
	    showButtonPanel: true,
	    changeMonth: true,
	    changeYear: true
	});
	
	/*var nullFormatter = function(cellvalue, options, rowObject) {
	    if(cellvalue === undefined || cellvalue == null || cellvalue === 'null')
	        return '';
	    else
	    	return $.fn.fmatter.call(this, "date", cellvalue, options, rowObject);
	};*/
	
	//taxabilityListGrid
	jQuery("#taxabilityListGrid").jqGrid({
		url:'getTaxabilityList.do',
		datatype : 'json',
		mtype: 'POST',
		/*postData : {enabled : '1'},*/
		colNames : ['Sr. no.', 'Question Id', 'Letter Id', 'Letter Name', 'Question No' , 'Question', 'Question Type', 'Default Value', 'Value for Yes/True' , 'Value for No/False', 'Created By'],
		colModel : [
	            	{name : 'srNo',index : 'srNo', align:'right', width:45, sortable:false},
		            {name : 'questionId',index : 'questionId', hidden : true, sortable:false},
		            {name : 'letterId',index : 'letterId', align:'right', hidden : true, sortable:false},
		            {name : 'letterName',index : 'letter_id', width:300, sortable:true}, //Here in index letter_id is used for sorting related purpose
		            {name : 'questionNo',index : 'questionNo', width:70, sortable:false},
		            {name : 'question',index : 'question', width:350, sortable:false},
		            {name : 'questionType',index : 'questionType', width:100, sortable:false},
		            {name : 'defaultValue',index : 'defaultValue', sortable:false},
		            {name : 'yes_true_value',index : 'yes_true_value', sortable:false, hidden:true},
		            {name : 'no_false_value',index : 'no_false_value', sortable:false, hidden:true},
		            {name : 'createdBy',index : 'createdBy', sortable:false, hidden:true},
		           ],
		rowNum: 10,
		rowList: [ 10, 20, 50 ],
		pager: '#taxabilityListPager',
		viewrecords: true,
		//loadonce: true, // to enable sorting on client side
		sortable: true, //to enable sorting
		shrinkToFit:true,
		autowidth: true,
		//width: 500,
		//autoheight: true,
		height: 338,
		sortname: 'letter_id',
		sortorder: 'asc',
		caption: 'List of Taxability',
		loadComplete : function() {
			
		},
		onCellSelect : function(rowid, iCol, cellcontent) {
			var myGrid = $('#taxabilityListGrid');
			selectedRowId = rowid;
			clearFields();
			showHideSaveEditButtons("show");
			$("#idForEdit").val(myGrid.jqGrid ('getCell', rowid, 'questionId'));
			$('#letterName').val(myGrid.jqGrid ('getCell', rowid, 'letterId'));
			$("#questionNo").val(myGrid.jqGrid ('getCell', rowid, 'questionNo'));
			$("#question").val(myGrid.jqGrid ('getCell', rowid, 'question'));
			
			var questionType = myGrid.jqGrid ('getCell', rowid, 'questionType');
			$("input[name=questionType][value="+questionType+"]").prop('checked', true);
			
			var defaultValue = myGrid.jqGrid ('getCell', rowid, 'defaultValue');
			if (questionType == "yesNo" || questionType == "trueFalse")
				$("input[name="+questionType+"Radio][value="+defaultValue+"]").prop('checked', true);
			else if (questionType == "text" || questionType == "numeric" || questionType == "date")
				$("#"+questionType+"Value").val(defaultValue);
			
			/*if (questionType == "yesNo")
			{
				$("#yesValue").val(myGrid.jqGrid ('getCell', rowid, 'yes_true_value'));
				$("#noValue").val(myGrid.jqGrid ('getCell', rowid, 'no_false_value'));
			}
			else if (questionType == "trueFalse")
			{
				$("#trueValue").val(myGrid.jqGrid ('getCell', rowid, 'yes_true_value'));
				$("#falseValue").val(myGrid.jqGrid ('getCell', rowid, 'no_false_value'));
			}*/
			
			questionTypeChange(questionType);
			$("[name='"+questionType+"NextQuestionDiv']").show();
		},
		beforeRequest : function(postdata, formid) {
			$.blockUI();
		},
		gridComplete: function(){
			$.unblockUI();
		},
	});
	//jQuery("#taxabilityListGrid").jqGrid("setFrozenColumns");
	jQuery("#taxabilityListGrid").jqGrid('navGrid', '#taxabilityListPager', {view: false, edit : false, add : false, del : false, search : false});
	$('#taxabilityListPager').css("height", "auto");
}