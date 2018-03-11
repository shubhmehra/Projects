// Generate Letter
function getTokenNumber()
{
	$("#genLeteBtn").hide();
	if ($("#tokenNumber").html() == "") {
		$.ajax({
			url : 'getTokenNumber.do',
			dataType : 'json',
			type : 'POST',
			data : {},
			beforeSend : function(xhr, opts) {
				$.blockUI();
			},
			success : function(data) {
				$('#tokenNumber').html(data);
				
				//$('#tokenNumberValue').html(""+data);
				
				$.unblockUI();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				showBootstrapWarningMessage('Something went wrong.!');
				$.unblockUI();
			}
		});
	}
}

// Generate Letter
function getGenerateLetterData(letterId, questionId, toDoMessage)
{
	var clientId = 0;
	if (toDoMessage != "change")
	{
		clientId = $("#clientName").val();
		if (clientId == 0)
		{
			showBootstrapWarningMessage('Please select Client first.!');
			return;
		}
		
		if (letterId == 0)
		{
			showBootstrapWarningMessage('Please select Letter first.!');
			return;
		}
	}
	
	var answer = "";
	if (toDoMessage != "change")
	{
		var questionType = $("#questionType").val();
		
		if (questionType == "yesNo" || questionType == "trueFalse")
			answer = $("input[name="+questionType+"Radio]:checked").val();
		else if (questionType == "text" || questionType == "numeric" || questionType == "date")
			answer = $("#"+questionType+"Value").val();
	}
	
	if (letterId != 0)
	{
		$.ajax({
			url : 'getGenerateLetterData.do',
			dataType : 'json',
			type : 'POST',
			data : {
				letterId : letterId,
				questionId : questionId,
				toDoMessage : toDoMessage,
				clientId : clientId,
				answer : answer,
				currentQuestionId : $('#questionId').val(),
				tokenNumber : $('#tokenNumber').html()
			},
			beforeSend : function(xhr, opts) {
				$.blockUI();
			},
			success : function(data) {
				if (toDoMessage == "save")
				{
					var output= data.substr(0, data.indexOf("!"));
					var url = data.substr(data.indexOf("!") + 1);
					
					showBootstrapSuccessMessage(output);
					
					if (output == "Letter Generated successfully.")
						window.location.href = url;
				}
				else
				{
					$('#questionId').val(data[0]);
					$('#questionNo').val(data[1]);
					$('#question').val(data[2]);
					
					var questionType = data[3];
					questionTypeChange(questionType);
					
					$('#questionType').val(data[3]);
					$('#defaultValue').val(data[4]);
					$('#childPosId').val(data[5]);
					$('#childNegId').val(data[6]);
					
					if (data[5] == 0 && data[6] == 0)
					{
						$("#nextBtn").hide();
						$("#genLeteBtn").show();
					}
					else
					{
						$("#nextBtn").show();
					}
					
					$('#letterName').val(letterId);
					
					var defaultValue = data[4];
					if (questionType == "yesNo" || questionType == "trueFalse")
						$("input[name="+questionType+"Radio][value="+defaultValue+"]").prop('checked', true);
					else if (questionType == "text" || questionType == "numeric" || questionType == "date")
						$("#"+questionType+"Value").val(defaultValue);
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

//Generate Letter
function cancel() {
	$("#clientName").val(0);
	clearFields();
}

// Generate Letter
function clearFields() {
	$('#generateLetterForm')[0].reset();
	$("#nextBtn").hide();
	$("#genLeteBtn").hide();
	questionTypeChange("yesNo");
	$('#letterName').val(0);
	
	$('#clientName').removeAttr('disabled');
	$('#letterName').removeAttr('disabled');
	$("#tokenNumber").html('');
}

// Generate Letter
function questionTypeChange(type) {
	var radios = ["yesNo","trueFalse","text","numeric","date"];
	var i;
	for	(i = 0; i < radios.length; i++) {
		if(radios[i] == type)
			$("#"+radios[i]+"Div").show();
		else
			$("#"+radios[i]+"Div").hide();
	}
}

// Generate Letter
function getNextQuestion()
{
	var questionType = $("#questionType").val();
	var nextQuestionId = "";
	
	var defaultValue = "";
	if (questionType == "yesNo" || questionType == "trueFalse")
	{
		defaultValue = $("input[name="+questionType+"Radio]:checked").val();
		
		if (defaultValue == "Yes" || defaultValue == "True")
			nextQuestionId = $('#childPosId').val();
		else
			nextQuestionId = $('#childNegId').val();
	}
	else if (questionType == "text" || questionType == "numeric" || questionType == "date")
	{
		defaultValue = $("#"+questionType+"Value").val();
		nextQuestionId = $('#childPosId').val();
	}
		
	getGenerateLetterData($('#letterName').val(), nextQuestionId, 'next');
	
	$('#clientName').attr('disabled','disabled');
	$('#letterName').attr('disabled','disabled');
}

//Generate Letter
function generateLetter()
{
	getGenerateLetterData($('#letterName').val(), 0, 'save');
	clearFields();
}