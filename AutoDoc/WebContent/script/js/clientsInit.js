function InitializeAddEditClient() {
	$('#incorporationDate').datepicker( {
        changeMonth: true,
        changeYear: true,
        changeDay: true,
        showButtonPanel: true,
    });
	
	var nullFormatter = function(cellvalue, options, rowObject) {
	    if(cellvalue === undefined || cellvalue == null || cellvalue === 'null')
	        return '';
	    else
	    	return $.fn.fmatter.call(this, "date", cellvalue, options, rowObject);
	};
	
	jQuery("#clientListGrid").jqGrid({
		url:'getClientList.do',
		datatype : 'json',
		mtype: 'POST',
		postData : {enabled : '1'},
		/*clientId, fileNo, clientName, contactName, createdDate, createdBy, updatedDate, updatedBy, incorporationDate, yearEnd, status, telephoneNo, emailId, addressLine1, addressLine2, city, countryCode, postCode*/
		colNames : ['Sr. no.', 'Client Id', 'Name', 'Group Names', 'File No.', 'Incorporation Date (YYYY-MM-DD)', 'Year End',
		            'Contact Person', 'Partner Name', 'Status', 'Tel. no.',
		            'Email Id', 'Address Line1', 'Address Line2', 'City', 'Country Code', 'Postcode'],
		colModel : [
	            	{name : 'srNo',index : 'srNo', align:'right', width:65},
		            {name : 'clientId',index : 'clientId', hidden : true},
		            {name : 'clientName',index : 'clientName'},
		            {name : 'groupNames',index : 'groupNames'},
		            
		            {name : 'fileNo',index : 'fileNo', align:'right'},
		            {name : 'incorporationDate', index : 'incorporationDate', align:'center', formatter: nullFormatter, formatoptions: {srcformat: 'ISO8601Short', newformat:'Y-m-d'}},
		            {name : 'yearEnd',index : 'yearEnd', align:'right'},
		            
		            {name : 'contactName',index : 'contactName'},
		            {name : 'partnerName',index : 'partnerName'},
		            {name : 'status',index : 'status', hidden : true},
		            {name : 'telephoneNo',index : 'telephoneNo'/*, hidden : true*/},
		            {name : 'emailId',index : 'emailId'},
		            {name : 'addressLine1',index : 'addressLine1'/*, hidden : true*/},
		            {name : 'addressLine2',index : 'addressLine2'/*, hidden : true*/},
		            {name : 'city',index : 'city'/*, hidden : true*/},
		            {name : 'countryCode',index : 'countryCode', hidden : true},
		            {name : 'postCode',index : 'postCode'/*, hidden : true*/},
		           ],
		rowNum: 10,
		rowList: [ 10, 20, 50 ],
		pager: '#clientListPager',
		viewrecords: true,
		//loadonce: true, // to enable sorting on client side
		sortable: true, //to enable sorting
		shrinkToFit:false,
		autowidth: true,
		//width: 500,
		//autoheight: true,
		height: 338,
		sortname: 'clientId',
		sortorder: 'asc',
		caption: 'List of Clients',
		loadComplete : function() {
			/*var myGrid = $('#clientListGrid');
			var colorArr = myGrid.jqGrid('getDataIDs');
			for (var int = 0; int <= colorArr.length; int++) {
				var colorCode = myGrid.jqGrid('getCell',colorArr[int],'COLOR_CODE');
				$("tr.jqgrow"+"#"+colorArr[int]).css("background",""+colorCode+"");
			}*/
		},
		onCellSelect : function(rowid, iCol, cellcontent) {
			selectedRowId = rowid;
			
			if ($('#clientListGrid').jqGrid ('getCell', rowid, 'status') == '1')
				$('#editClientInformationBtn').removeAttr('disabled');
		},
		beforeRequest : function(postdata, formid) {
			$.blockUI();
		},
		gridComplete: function(){
			$.unblockUI();
		},
	});
	//jQuery("#clientListGrid").jqGrid("setFrozenColumns");
	jQuery("#clientListGrid").jqGrid('navGrid', '#clientListPager', {view: false, edit : false, add : false, del : false, search : false});
	$('#clientListPager').css("height", "auto");
	
	//Function for toggle change event @20th Aug, 2015
	$(function() {
		$('#enableDisableToggle').change(function() {
			getClientListByEnabledStatus($(this).prop('checked'));
		});
	});
}