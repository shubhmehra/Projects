var selectedRowId;
var currentDate = new Date();

function hideAddEditInformation() {
	$("#addClientInformation").hide();
	$("#addClientInformationHeader").hide();
	$("#editClientInformationHeader").hide();
	
	$("#clientInformationGrid").show();
	$("#clientInformationGridHeader").show();
	$('#editClientInformationBtn').attr('disabled','disabled');
}

function hideGridInformation(addOrEdit) {
	$("#clientInformationGrid").hide();
	$("#clientInformationGridHeader").hide();
	
	$("#addClientInformation").show();
	$("#"+addOrEdit+"ClientInformationHeader").show();
}

function addClient() {
	$('#hiddenClientId').val('');
	$('#addClientForm')[0].reset();
	$("incorporationDate").datepicker(currentDate);
	hideGridInformation('add');
	$('#clearBtn').show();
	$('#fileNo').focus();
}

function addClientData() {
	var fileNo = $("#fileNo").val();
	var clientName = $("#clientName").val();
	/*var contactName = $("#contactName").val();*/
	var addressLine1 = $("#addressLine1").val();
	/*var addressLine2 = $("#addressLine2").val();*/
	var city = $("#city").val();
	/*var postCode = $("#postCode").val();*/
	var telephoneNo = $("#telephoneNo").val();
	var emailId = $("#emailId").val();
	var incorporationDate = $("#incorporationDate").val();
	
	var errorMsg = "";
	var emailPattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var focusElement = "";
	
	if (fileNo == "") {
		errorMsg = "File number should not be blank.!";
		focusElement = "fileNo";
	} else if (isNaN(fileNo)) { //<-It checks if the fileNo is numeric or not
		errorMsg = "File number must be numeric only.!";
		focusElement = "fileNo";
	} else if (clientName == "") {
		errorMsg = "Client Name should not be blank.!";
		focusElement = "clientName";
	} /*else if (contactName == "") {
		errorMsg = "Contact Name should not be blank.!";
		focusElement = "contactName";
	} */else if (addressLine1 == "") {
		errorMsg = "Address Line1 should not be blank.!";
		focusElement = "addressLine1";
	} else if (city == "") {
		errorMsg = "City should not be blank.!";
		focusElement = "city";
	} else if (isNaN(telephoneNo)) { //<-It checks if the fileNo is numeric or not
		errorMsg = "File number must be numeric only.!";
		focusElement = "fileNo";
	} else if (emailId != "") {
		if (!emailPattern.test(emailId)) {
			errorMsg = "Wrong email pattern.!";
			focusElement = "emailId";
		}
	} else if (incorporationDate == "") {
		errorMsg = "Incorporation Date should not be blank.!";
		focusElement = "incorporationDate";
		$("#incorporationDate").val('');
	}
	
	if (errorMsg != "") {
		showBootstrapWarningMessage(errorMsg);
		$("#"+focusElement).focus();
	} else {
		$.ajax({
			url : 'addEditClientInformation.do',
			dataType : 'json',
			type : 'POST',
			data : $('#addClientForm').serialize()+"&clientIdForEdit="+$('#hiddenClientId').val(),
			beforeSend : function(xhr, opts) {
				$.blockUI();
			},
			success : function(data) {
				if (data.indexOf('Successfully') != -1)
				{
					showBootstrapSuccessMessage(data);
					
					reloadGrid('clientListGrid');
					hideAddEditInformation();
					$('#addClientForm')[0].reset();
				}
				else
				{
					showBootstrapWarningMessage(data);
				}
				//getAdminListByEnabledStatus();
				$.unblockUI();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				showBootstrapWarningMessage('Something went wrong.!');
				$.unblockUI();
			}
		});
	}
}

function getClientListByEnabledStatus(status) {
	$('#editClientInformationBtn').attr('disabled','disabled');
	
	if (status == "false" || status == false) {
		sortByEnabled = '0';
		$('#enableBtn').show();
		$('#disableBtn').hide();
	}
	else {
		sortByEnabled = '1';
		$('#enableBtn').hide();
		$('#disableBtn').show();
	}
	
	jQuery('#clientListGrid').jqGrid('setGridParam', {
		datatype : 'json',
		mtype: 'POST',
		url :'getClientList.do',
		postData: {enabled : sortByEnabled},
	}).trigger('reloadGrid', [ {page : 1} ]);
}

function clearFields() {
	$('#addClientForm')[0].reset();
}

function editClientInformation() {
	var myGrid = $('#clientListGrid');
	
	var clientId = myGrid.jqGrid('getCell', selectedRowId, 'clientId');
	if (clientId == "" || clientId == null) {
		showBootstrapWarningMessage('Please select any row to edit client information.!');
	} else {
		$('#hiddenClientId').val(clientId);
		$('#fileNo').val(myGrid.jqGrid('getCell', selectedRowId, 'fileNo'));
		$('#clientName').val(myGrid.jqGrid('getCell', selectedRowId, 'clientName'));
		$('#contactName').val(myGrid.jqGrid('getCell', selectedRowId, 'contactName'));
		$('#partnerName').val(myGrid.jqGrid('getCell', selectedRowId, 'partnerName'));
		$('#groupNames').val(myGrid.jqGrid('getCell', selectedRowId, 'groupNames'));
		$('#addressLine1').val(myGrid.jqGrid('getCell', selectedRowId, 'addressLine1'));
		$('#addressLine2').val(myGrid.jqGrid('getCell', selectedRowId, 'addressLine2'));
		$('#city').val(myGrid.jqGrid('getCell', selectedRowId, 'city'));
		$('#countryCode').val(myGrid.jqGrid('getCell', selectedRowId, 'countryCode'));
		$('#postCode').val(myGrid.jqGrid('getCell', selectedRowId, 'postCode'));
		$('#telephoneNo').val(myGrid.jqGrid('getCell', selectedRowId, 'telephoneNo'));
		$('#emailId').val(myGrid.jqGrid('getCell', selectedRowId, 'emailId'));
		
		var dateInFormatted = (myGrid.jqGrid('getCell', selectedRowId, 'incorporationDate')).split("-");
		var newDate = dateInFormatted[1]+"/"+dateInFormatted[2]+"/"+dateInFormatted[0];
		
		//alert(myGrid.jqGrid('getCell', selectedRowId, 'incorporationDate')+"<--->"+dateInFormatted+"<----->"+newDate);
		
		$("#incorporationDate").datepicker('setDate', newDate);
		
		hideGridInformation('edit');
		$('#clearBtn').hide();
	}
	$('#fileNo').focus();
}

function enableDisableClient(value) {
	var myGrid = $('#clientListGrid');
	var selectedClientId = myGrid.jqGrid ('getCell', selectedRowId, 'clientId');
	
	var changeStatus = "enable";
	if (value == 0)
		changeStatus = "disable";
	
	if (selectedClientId == "" || selectedClientId == null)
		showBootstrapWarningMessage('Please select any row to '+changeStatus+' client.!');
	else
	{
		$("#enableDisableConfirmationDialog").html("Are You Sure.?");
		
		$("#enableDisableConfirmationDialog").dialog({
			resizable : false,
			modal : true,
			title : "Confirm",
			/*message: 'Do you really want to '+changeStatus+' selected client.!',*/
			height : 200,
			width : 400,
			buttons : {
				"Yes" : function() {
					$(this).dialog('close');
					$.ajax({
						url: "enableDisableClient.do",
						dataType: 'json',
						type: 'post',
						data: {selectedClientId: selectedClientId, changeStatus: value},
						beforeSend: function(xhr, opts) {
							$.blockUI();
						},
						success: function (data) {
							showBootstrapSuccessMessage(data);
							reloadGrid('clientListGrid');
							$.unblockUI();
						},
						error: function (xhr, ajaxOptions, thrownError) {
							showBootstrapWarningMessage('Something went wrong.!');
							$.unblockUI();
						}
					});
				},
				"No" : function() {
					$(this).dialog('close');
				}
			}
		});
		
		/*BootstrapDialog.confirm({
			type: BootstrapDialog.TYPE_INFO,
	        title: 'CONFIRMATION',
	        message: 'Do you really want to '+show+' selected client.!',
	        closable: true,
	        draggable: false,
	        btnCancelLabel: 'NO',
	        btnOKLabel: 'YES',
	        buttons: [{
	            label: 'NO',
	            close: function() {
	            	alert(13);
	                dialog.close();
	          }
	        },{
	            label: 'YES',
	            action: function(dialog) {
	            	alert(selectedClientId+"====>"+value);
	          }
	        }]
	    });*/
	}
}