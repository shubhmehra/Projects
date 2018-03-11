/*function resizeJqGridWidth(grid_id, div_id, width)
{
    $(window).bind('resize', function() {
        $('#' + grid_id).setGridWidth(width, true); //Back to original width
        $('#' + grid_id).setGridWidth($('#' + div_id).width(), true); //Resized to new width as per window
    }).trigger('resize');
}*/

function getCountryList(inputId) {
	$.ajax({
		url: 'getCountryList.do',
		dataType: 'json',
		type: 'POST',
		data: {},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			$.each(data, function(index, item) {
				$('#'+inputId+'').append('<option value="'+item[0]+'">'+ item[1]+'</option>');
			});
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			showBootstrapWarningMessage("Something went wrong.!");
			$.unblockUI();
		}
	});
}

function reloadGrid(gridName){
    jQuery("#"+gridName).trigger("reloadGrid");
}

function showBootstrapWarningMessage(errorMessage){
	BootstrapDialog.show({
        title: 'WARNING',
        message: errorMessage,
        cssClass: 'dialog-vertical-centerByTest',
        type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is true
    });
}

function showBootstrapSuccessMessage(successMessage){
	BootstrapDialog.show({
        title: 'SUCCESS',
        message: successMessage,
        cssClass: 'dialog-vertical-centerByTest',
        type: BootstrapDialog.TYPE_SUCCESS, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is true
    });
}

function getLetterNames(inputId, typeOfData) {
	$.ajax({
		url: 'getLetterNames.do',
		dataType: 'json',
		type: 'POST',
		data: {typeOfData:typeOfData},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			$('#'+inputId+'').append('<option value="0">-- Select Letter Name --</option>');
			$.each(data, function(index, item) {
				$('#'+inputId+'').append('<option value="'+item[0]+'">'+ item[1]+'</option>');
			});
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			showBootstrapWarningMessage("Something went wrong.!");
			$.unblockUI();
		}
	});
}

function getClientNames(inputId, clientStatus) {
	$.ajax({
		url: 'getClientNames.do',
		dataType: 'json',
		type: 'POST',
		data: {clientStatus:clientStatus},
		beforeSend: function(xhr, opts) {
			$.blockUI();
		},
		success: function (data) {
			$('#'+inputId+'').append('<option value="0">-- Select Client Name --</option>');
			$.each(data, function(index, item) {
				$('#'+inputId+'').append('<option value="'+item[0]+'">'+ item[1]+'</option>');
			});
			$.unblockUI();
		},
		error: function (xhr, ajaxOptions, thrownError) {
			showBootstrapWarningMessage("Something went wrong.!");
			$.unblockUI();
		}
	});
}

//LetterGeneration <- This function not allows users to press letters.
/*jQuery.fn.ForceNumericOnly =
	function()
	{
	    return this.each(function()
	    {
	        $(this).keydown(function(e)
	        {
	            var key = e.charCode || e.keyCode || 0;
	            // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
	            // home, end, period, and numpad decimal
	            return (
	                key == 8 || 
	                key == 9 ||
	                key == 13 ||
	                key == 46 ||
	                key == 110 ||
	                key == 190 ||
	                (key >= 35 && key <= 40) ||
	                (key >= 48 && key <= 57) ||
	                (key >= 96 && key <= 105));
	        });
	    });
	};*/
//$("#fileNo").ForceNumericOnly();