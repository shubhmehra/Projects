// Generate Letter
function clearFields() {
	$('#tokenNumber').val("");
}

// Generate Letter
function generateLetter()
{
	var tokenNumber = $("#tokenNumber").val();
	if (tokenNumber < 1)
	{
		showBootstrapWarningMessage('Please enter appropriate token number.!');
		return;
	}
	else
	{
		$.ajax({
			url : 'generateLetter.do',
			dataType : 'json',
			type : 'POST',
			data : {
				tokenNumber : tokenNumber
			},
			beforeSend : function(xhr, opts) {
				$.blockUI();
			},
			success : function(data) {
				if (data.indexOf('Invalid') != -1)
				{
					showBootstrapWarningMessage(data);
				}
				else if (data.indexOf('Letter Generated successfully') != -1)
				{
					var url = data.substr(data.indexOf("!") + 1);
					window.location.href = url;
				}
				else
				{
					window.location.href = data;
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