<script type="text/javascript" src="script/js/lettersInit.js"></script>
<script type="text/javascript" src="script/js/lettersLogic.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		InitializeLetters();
		$('#hiddenLetterId').val('');
		
		//resizeJqGridWidth('lettersListGrid', 'gridDivId');
	});
</script>

<div id="deleteConfirmationDialog"></div>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Letter Information</h3>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					
					<div class="row">
						<div class="col-lg-6" style="width: 100%;">
							<table border="0" width="100%">
								<tr>
									<td width="35%" style="padding-right: 10px;">
										<form id="addLetterForm">
											<div class="form-group">
												<div class="form-group">
													<input class="form-control" type="hidden" name="hiddenLetterId" id="hiddenLetterId" />
												</div>
												
												<div class="form-group">
													<label class="control-label">Letter Name<span class="mandatoryField">&nbsp;*</span></label>
													<input class="form-control" type="text" name="letterName" id="letterName" title="Name of the letter" />
												</div>
												
												<div>
													<button type="button" class="btn btn-success" value="Save" id="saveBtn" name="saveBtn" onClick="addLetter();"><i class="fa fa-floppy-o"></i>&nbsp;Save</button>
													<button type="button" class="btn btn-success" value="Edit" id="updateBtn" name="updateBtn" onClick="addLetter();"><i class="fa fa-pencil-square-o"></i>&nbsp;Update</button>
													<button type="button" class="btn btn-warning" value="Clear" id="clearBtn" name="clearBtn" onClick="clearFields();"><i class="fa fa-eraser"></i>&nbsp;Clear</button>
												</div>
											</div>
										</form>
									</td>
									
									<td width="65%" style="border-left:solid; border-left-color: black;padding-left: 10px;padding-right: 5px;">
										<!-- Grid related div -->
										<div class="row">
											<div class="panel-heading" id="panel_menu">
												<button type="button" class="btn btn-danger" value="Delete" id="delete" name="delete" onClick="deleteLetterData();"><i class="fa fa-trash-o fa-lg"></i>&nbsp;Delete</button>
											</div>
											
											<div class="col-sm-12" id="gridDivId">
												<table id="lettersListGrid"></table>
											</div>
											<div class="col-sm-12">
												<div id="lettersListPager"></div>
											</div>
										</div>
										<!-- Grid related div till here-->
									</td>
								</tr>
							</table>
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