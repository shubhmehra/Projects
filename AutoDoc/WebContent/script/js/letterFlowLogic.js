function letterNameChange(letterId) {
	alert(letterId);
	$.ajax({
		url: 'getQuestionList.do',
		dataType: 'json',
		type: 'POST',
		data: {letterId: letterId},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			$.unblockUI();
		}
	});
}