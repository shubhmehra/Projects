var selectedRowId;
//LetterGeneration
function addQATemplateData(status) {
	var idForEdit = $("#idForEdit").val();
	var letterName = $("#letterName").val();
	var questionNo = $("#questionNo").val();
	var question = $.trim($("#question").val());
	var questionType = $("input[name=questionType]:checked").val();
	var defaultValue = "";
	var regex = /[0-9]|\./;
	var errorMsg = "";
	var focusElement = "";
	var yesTrueValue = "";
	var noFalseValue = "";
	
	if (questionType == "yesNo" || questionType == "trueFalse")
	{
		defaultValue = $("input[name="+questionType+"Radio]:checked").val();
		yesTrueValue = $("#yesValue").val();
		noFalseValue = $("#noValue").val();
	}
	else if (questionType == "text" || questionType == "numeric" || questionType == "date")
	{
		defaultValue = $("#"+questionType+"Value").val();
		//yesTrueValue = $("#trueValue").val();
		//noFalseValue = $("#falseValue").val();
	}
	
	if (letterName == "0") {
		errorMsg = "Please select Letter Name.!";
		focusElement = "letterName";
	} else if (questionNo == "") {
		errorMsg = "Question No should not be blank.!";
		focusElement = "questionNo";
	} else if (question == "") {
		errorMsg = "Question should not be blank.!";
		focusElement = "question";
	} else if (defaultValue == "") {
		errorMsg = "Default Value should not be blank.!";
		focusElement = "addressLine1";
	} else if (defaultValue != "") {
		if(questionType == "numeric" && !regex.test($("#numericValue").val()))
			errorMsg = "Default Value must be numeric.!";
	}
	
	if (errorMsg != "") {
		showBootstrapWarningMessage(errorMsg);
		$("#"+focusElement).focus();
	} else {
		$.ajax({
			url : 'addEditQuestionAnswerTemplate.do',
			dataType : 'json',
			type : 'POST',
			data : {
				letterId : letterName,
				questionNo : questionNo,
				question : question,
				questionType : questionType,
				defaultValue : defaultValue,
				idForEdit : idForEdit,
				yesTrueValue : yesTrueValue,
				noFalseValue : noFalseValue
			},/*$('#addQuestionForm').serialize()+"&clientIdForEdit="+$('#hiddenClientId').val(),*/
			beforeSend : function(xhr, opts) {
				$.blockUI();
			},
			success : function(data) {
				if (data[0].indexOf('Successfully') != -1)
				{
					showBootstrapSuccessMessage(data[0]);
					reloadGrid('taxabilityListGrid');
					clearFields();
					if (status == 'show')
					{
						$('#letterName').val(data[1]).change();
						$('#questionNo').val(data[2]);
					}
					showHideSaveEditButtons('hide');
				} else {
					showBootstrapWarningMessage(data[0]);
				}
				$.unblockUI();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				showBootstrapWarningMessage('Something went wrong.!');
				$.unblockUI();
			}
		});
	}
}

//LetterGeneration
function clearFields() {
	$('#addQuestionForm')[0].reset();
	$('#idForEdit').val("");
	questionTypeChange("yesNo");
}

//LetterGeneration
function questionTypeChange(type) {
	var radios = ["yesNo","trueFalse","text","numeric","date"];
	var i;
	for	(i = 0; i < radios.length; i++) {
		if(radios[i] == type)
		{
			$("#"+radios[i]+"Div").show();
		}
		else
		{
			$("#"+radios[i]+"Div").hide();
		}
	}
}

//LetterGeneration
function showHideSaveEditButtons(status) {
	if (status == "show") {
		$("#updateBtn").show();
		$("#cancelBtn").show();
		$("#deleteBtn").show();
		$("#saveBtn").hide();
		$("#clearBtn").hide();
		$("#addClientHeaderDiv").hide();
		$("#updateClientHeaderDiv").show();
	} else {
		$("#updateBtn").hide();
		$("#cancelBtn").hide();
		$("#deleteBtn").hide();
		$("#saveBtn").show();
		$("#clearBtn").show();
		$("#addClientHeaderDiv").show();
		$("#updateClientHeaderDiv").hide();
	}
}

//LetterGeneration
function cancel() {
	clearFields();
	showHideSaveEditButtons("hide");
	jQuery("#taxabilityListGrid").jqGrid('resetSelection');
}

//LetterGeneration
function deleteTaxabilityData() {
	var myGrid = $('#taxabilityListGrid');
	var selectedQuestionId = $("#idForEdit").val();
	var selectedQuestionNo = myGrid.jqGrid ('getCell', selectedRowId, 'questionNo');
	
	if (selectedQuestionId == "" || selectedQuestionId == null)
		showBootstrapWarningMessage("Please Select any row to delete.!");
	else
		deleteConfirmation(selectedQuestionId, selectedQuestionNo);
}

//LetterGeneration
function deleteConfirmation(selectedQuestionId, selectedQuestionNo) {
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
				confirmDeleteYes(selectedQuestionId, selectedQuestionNo);
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

//LetterGeneration
function confirmDeleteYes(selectedQuestionId, selectedQuestionNo) {
	$.ajax({
		url: "deleteTaxabilityData.do",
		dataType: 'json',
		type: 'post',
		data: {selectedQuestionId: selectedQuestionId},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			if (data.indexOf('Success') != -1)
				showBootstrapSuccessMessage("Question - '" + selectedQuestionNo + "' deleted Successfully.!");
			else
				showBootstrapWarningMessage(data);
			
			clearFields();
			reloadGrid('taxabilityListGrid');
			showHideSaveEditButtons('hide');
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			showBootstrapWarningMessage('Something went wrong.!');
			$.unblockUI();
		}
	});
}