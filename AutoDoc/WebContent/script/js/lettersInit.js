function InitializeLetters() {
	$("#updateBtn").hide();
	
	jQuery("#lettersListGrid").jqGrid({
		url:'getLettersList.do',
		datatype : 'json',
		mtype: 'POST',
		postData : {letterStatus : 'NEW'},
		colNames : ['Sr. no.', 'Letter Id', 'Letter Name', 'Status', 'Created Date', 'Created By', 'Updated Date', 'Updated By'],
		colModel : [
	            	{name : 'srNo',index : 'srNo', align:'right', width:15},
		            {name : 'letterId',index : 'letterId', hidden : true},
		            {name : 'letterName',index : 'letterName'},
		            {name : 'status',index : 'status', hidden : true},
		            {name : 'createdDate',index : 'createdDate', formatter: 'format', formatoptions: {srcformat: 'ISO8601Short', newformat:'d-m-Y'}, hidden : true},
		            {name : 'createdBy',index : 'createdBy', hidden : true},
		            {name : 'updatedDate',index : 'updatedDate', hidden : true},
		            {name : 'updatedBy',index : 'updatedBy', hidden : true},
				   ],
		rowNum : 10,
		rowList : [ 10, 20, 50 ],
		pager : '#lettersListPager',
		viewrecords : true,
		sortable: true,
		//shrinkToFit:false,
		autowidth: true,
		//width: 500,
		height: 322,
		sortname: 'letterId',
		sortorder: 'asc',
		caption: 'List of Letters',
		onCellSelect : function(rowid, iCol, cellcontent) {
			selectedRowId = rowid;
			$('#hiddenLetterId').val('');
			
			$("#hiddenLetterId").val($('#lettersListGrid').jqGrid ('getCell', rowid, 'letterId'));
			$("#letterName").val($('#lettersListGrid').jqGrid ('getCell', rowid, 'letterName'));
			
			$("#updateBtn").show();
			$("#saveBtn").hide();
		},
		beforeRequest : function(postdata, formid) {
			$.blockUI();
		},
		gridComplete: function(){
			$.unblockUI();
		}
	});
	jQuery("#lettersListGrid").jqGrid('navGrid', '#lettersListPager', { edit : false, add : false, del : false, search : false });
}