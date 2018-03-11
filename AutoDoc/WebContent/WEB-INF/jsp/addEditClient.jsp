<script type="text/javascript" src="script/js/clientsInit.js"></script>
<script type="text/javascript" src="script/js/clientsLogic.js"></script>

<link type="text/css" rel="stylesheet" href="css/bootstrap-toggle.min.css">
<script type="text/javascript" src="js/scripts/bootstrap-toggle.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		InitializeAddEditClient();
		$('#editClientInformationBtn').attr('disabled','disabled');
		getCountryList('countryCode');
		hideAddEditInformation();
		$('#enableBtn').hide();
		
		//resizeJqGridWidth('clientListGrid', 'gridDivId');
	});
	/* function addNewTextbox(){
	    $("input:text:last").after("<br><input type='text' name='text2'>");
	} */
</script>

<style>
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
    }
    .white_content {
        display: none;
        position: absolute;
        top: 20%;
        left: 35%;
        width: 40%;
        height: 64%;
        padding: 16px;
        border: 10px;
        border-style:groove;
        border-color:green;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
	.toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px; padding-left: 150px;}
	.toggle.ios .toggle-handle { border-radius: 20px; padding-left: 20px;}
</style>

<div id="enableDisableConfirmationDialog"></div>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12" id="clientInformationGridHeader">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Client Information</h3>
		</div>
		<!-- /.col-lg-12 -->
		
		<div class="col-lg-12" id="addClientInformationHeader">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Add New Client</h3>
		</div>
		<!-- /.col-lg-12 -->
		
		<div class="col-lg-12" id="editClientInformationHeader">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Edit Client Information</h3>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
				<div class="alert alert-danger" id="errorMessageDiv" style="display: none;"></div>
					<div class="row">
						<div class="col-lg-6" style="width: 100%;" id="clientInformationGrid">
							<table width="100%">
								<tr>
									<td width="65%">
										<!-- Grid related div -->
										<div class="row">
											<div class="panel-heading" id="panel_menu">
												<button type="button" class="btn btn-warning" value="Edit" id="editClientInformationBtn" name="editClientInformation" onClick="editClientInformation();"><i class="fa fa-pencil">&nbsp;Edit</i></button> 
												<button type="button" class="btn btn-primary" value="Add" id="addClientBtn" name="addClient" onClick="addClient();"><i class="fa fa-plus">&nbsp;Add</i></button>
												
												<input checked id="enableDisableToggle" data-toggle="toggle" data-on="Enabled Clients" data-onstyle="success" data-off="Disabled Clients" data-offstyle="danger" data-style="ios" type="checkbox"/>
												
												<button type="button" class="btn btn-danger" value="Disable" id="disableBtn" name="disableBtn" onClick="enableDisableClient(0);" style="margin-left: 60%;"><i class="fa">Disable</i></button>
												<button type="button" class="btn btn-success" value="Enable" id="enableBtn" name="enableBtn" onClick="enableDisableClient(1);" style="margin-left: 60%;"><i class="fa">Enable</i></button>
												
												<!-- we can use below code instead of the above line.
												<input id="toggle-two" type="checkbox">
												<script>
												  $(function() {
												    $('#toggle-two').bootstrapToggle({
												      on: 'Enabled',
												      off: 'Disabled'
												    });
												  })
												</script> -->
											</div>
											
											<div style="border-top:solid; border-top-color: black; padding-top: 20px; padding-right: 15px;">
												<div class="col-lg-12" id="gridDivId">
													<table id="clientListGrid"></table>
												</div>
												<div class="col-lg-12">
													<div id="clientListPager"></div>
												</div>
											</div>
										</div>
										<!-- Grid related div till here -->
									</td>
								</tr>
							</table>
							<!-- /.col-lg-6 (nested) -->
							<!-- /.col-lg-6 (nested) -->
						</div>
						
						<div class="col-lg-6" style="width: 100%;" id="addClientInformation">
							<form id="addClientForm">
								<table width="100%">
									<tr>
										<td>
											<input class="form-control" type="hidden" name="hiddenClientId" id="hiddenClientId" />
											<div class="form-group">
												<label class="control-label">File No<span class="mandatoryField"> (Only Numeric values)&nbsp;*</span></label>
												<input class="form-control" type="text" name="fileNo" id="fileNo" tabindex="1"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">City<span class="mandatoryField">&nbsp;*</span></label>
												<input class="form-control" type="text" name="city" id="city" tabindex="8"/>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Company Name<span class="mandatoryField">&nbsp;*</span></label>
												<input class="form-control" type="text" name="clientName" id="clientName" tabindex="2"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">Country<span class="mandatoryField">&nbsp;*</span></label>
												<select name="countryCode" id="countryCode" class="form-control" tabindex="9"
																style="margin-bottom: 10px; width: 60%;">
												</select>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Group Names<span class="mandatoryField"> (Separate by Semicolon)&nbsp;</span></label>
												<input class="form-control" type="text" name="groupNames" id="groupNames" tabindex="3"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">Post Code</label>
												<input class="form-control" type="text" name="postCode" id="postCode" tabindex="10"/>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Partner Name</label>
												<input class="form-control" type="text" name="partnerName" id="partnerName" tabindex="4"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">Telephone No<span class="mandatoryField"> (Only Numeric values)</span></label>
												<input class="form-control" type="text" name="telephoneNo" id="telephoneNo" tabindex="11"/>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Contact Name</label>
												<input class="form-control" type="text" name="contactName" id="contactName" tabindex="5"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">Email</label>
												<input class="form-control" type="text" name="emailId" id="emailId" tabindex="12"/>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Address Line1<span class="mandatoryField">&nbsp;*</span></label>
												<input class="form-control" type="text" name="addressLine1" id="addressLine1" tabindex="6"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div class="form-group">
												<label class="control-label">Incorporation Date<span class="mandatoryField"> (mm/dd/yyyy)&nbsp;*</span></label>
												<input class="form-control" readonly="readonly" type="text" name="incorporationDate" id="incorporationDate" tabindex="13"/>
											</div>
										</td>
									</tr>
									
									<tr>
										<td>
											<div class="form-group">
												<label class="control-label">Address Line2</label>
												<input class="form-control" type="text" name="addressLine2" id="addressLine2" tabindex="7"/>
											</div>
										</td>
										
										<td style="padding-left: 10px;">
											<div>
												<button type="button" class="btn btn-success" value="Save" id="saveBtn" name="saveBtn" onClick="addClientData();" tabindex="14"><i class="fa fa-floppy-o"></i>&nbsp;Save</button>
												<button type="button" class="btn btn-warning" value="Clear" id="clearBtn" name="clearBtn" onClick="clearFields();" tabindex="15"><i class="fa fa-eraser"></i>&nbsp;Clear</button>
												<button type="button" class="btn btn-primary" value="Cancel" id="cancel" name="cancel" onClick="hideAddEditInformation();" tabindex="16"><i class="fa fa-times"></i>&nbsp;Cancel</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
							<!-- /.col-lg-6 (nested) -->
							<!-- /.col-lg-6 (nested) -->
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->