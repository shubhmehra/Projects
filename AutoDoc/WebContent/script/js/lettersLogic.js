var selectedRowId;

function addLetter() {
	var letterName = $.trim($("#letterName").val());
	
	if (letterName == "") {
		showBootstrapWarningMessage("Please enter Letter Name to save.!");
	} else {
		$.ajax({
			url: 'addEditLetters.do',
			dataType: 'json',
			type: 'POST',
			data: $('#addLetterForm').serialize()+"&letterIdForEdit="+$('#hiddenLetterId').val(),
			beforeSend: function(xhr, opts) {
				$.blockUI();
			},
			success: function (data) {
				if (data.indexOf('Successfully') != -1)
				{
					showBootstrapSuccessMessage(data);
					clearFields();
					reloadGrid('lettersListGrid');
				}
				else
					showBootstrapWarningMessage(data);
				$.unblockUI();
			},
			error: function (xhr, ajaxOptions, thrownError) {
				showBootstrapWarningMessage('Something went wrong.!');
				$.unblockUI();
			}
		});
	}
}

function clearFields() {
	$('#addLetterForm')[0].reset();
	$('#hiddenLetterId').val('');
	$("#updateBtn").hide();
	$("#saveBtn").show();
}

/*To delete the category - Start from here*/
function deleteLetterData() {
	var myGrid = $('#lettersListGrid');
	var selectedLetterId = myGrid.jqGrid ('getCell', selectedRowId, 'letterId');
	var selectedLetterName = myGrid.jqGrid ('getCell', selectedRowId, 'letterName');
	
	if (selectedLetterId == "" || selectedLetterId == null)
		showBootstrapWarningMessage("Please Select any row to delete.!");
	else
		deleteConfirmation(selectedLetterId, selectedLetterName);
}

function deleteConfirmation(selectedLetterId, selectedLetterName) {
	$("#deleteConfirmationDialog").html("Are You Sure.?");
	
	// Define the Dialog and its properties.
	$("#deleteConfirmationDialog").dialog({
		resizable : false,
		modal : true,
		title : "Confirm",
		height : 200,
		width : 400,
		buttons : {
			"Yes" : function() {
				confirmDeleteYes(selectedLetterId, selectedLetterName);
				$(this).dialog('close');
			},
			"No" : function() {
				$(this).dialog('close');
			}
		}
	});
	//Below line is used to remove the close icon from the confirmation dialog
	//$(".ui-dialog-titlebar-close").remove();
}

function confirmDeleteYes(selectedLetterId, selectedLetterName) {
	$.ajax({
		url: "deleteLetterData.do", //deleteCategoryData
		dataType: 'json',
		type: 'post',
		data: {selectedLetterId: selectedLetterId},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			if (data.indexOf('Success') != -1)
				showBootstrapSuccessMessage("Letter - '"+selectedLetterName+"' deleted Successfully.!");
			else
				showBootstrapWarningMessage(data);
			
			clearFields();
			reloadGrid('lettersListGrid');
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			showBootstrapWarningMessage('Something went wrong.!');
			$.unblockUI();
		}
	});
}
/*To delete the category - Till here*/