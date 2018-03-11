<script type="text/javascript" src="script/js/taxabilityInit.js"></script>
<script type="text/javascript" src="script/js/taxabilityLogic.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		InitializeTaxability();
		getLetterNames('letterName', 'normal');
		clearFields();
		
		//resizeJqGridWidth('taxabilityListGrid', 'gridDivId');
	});
</script>

<!-- <div id="conform"></div> -->
<div id="deleteConfirmationDialog"></div>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12" id="addClientHeaderDiv">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Add Question</h3>
		</div>
		<!-- /.col-lg-12 -->
		
		<div class="col-lg-12" id="updateClientHeaderDiv">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Update Question</h3>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row" style="padding-bottom: 10px;">
						<div class="col-lg-10">
							<form id="addQuestionForm">
								<div>
									<input type="hidden" id="idForEdit" name="idForEdit">
								</div>
								<div>
									<div class="form-group">
										<label class="control-label">Letter Name<span class="mandatoryField">&nbsp;*</span></label>
										<select name="letterName" id="letterName" class="form-control"
												style="margin-bottom: 10px; width: 100%;">
										</select>
									</div>
								</div>
								
								<div>
									<div class="form-group">
										<label class="control-label">Question No.<span class="mandatoryField"> &nbsp;*</span></label>
										<input type="text" name="questionNo" id="questionNo" class="form-control">
									</div>
								</div>
								
								<div>
									<div class="form-group">
										<label class="control-label">Question<span class="mandatoryField">&nbsp;*</span></label>
										<textarea class="form-control" name="question" id="question"></textarea>
									</div>
								</div>
								
								<div>
									<div class="form-group">
										<label class="control-label">Question Type<span class="mandatoryField">&nbsp;*</span></label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="questionType" value="yesNo" checked="checked" onchange="questionTypeChange(this.value);">&nbsp;<label class="control-label">Yes/No</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="questionType" value="trueFalse" onchange="questionTypeChange(this.value);">&nbsp;<label class="control-label">True/False</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="questionType" value="text" onchange="questionTypeChange(this.value);">&nbsp;<label class="control-label">Text</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="questionType" value="numeric" onchange="questionTypeChange(this.value);">&nbsp;<label class="control-label">Numeric</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="questionType" value="date" onchange="questionTypeChange(this.value);">&nbsp;<label class="control-label">Date</label>
									</div>
								</div>
								
								<div>
									<div id="yesNoDiv">
										<label>Default Value<span class="mandatoryField">&nbsp;*</span></label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="yesNoRadio" id="yesValue" value="Yes" checked="checked">&nbsp;<label class="control-label">Yes</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="yesNoRadio" id="noValue" value="No">&nbsp;<label class="control-label">No</label>
										
										<!-- <br>
										<label>Data for yes</label>
										
										<textarea class="form-control" name="yesValue" id="yesValue"></textarea>
										
										<label>Data for no</label>
										<textarea class="form-control" name="noValue" id="noValue"></textarea> -->
									</div>
									
									<div id="trueFalseDiv">
										<label>Default Value<span class="mandatoryField">&nbsp;*</span></label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="trueFalseRadio" id="trueValue" value="True" checked="checked">&nbsp;<label class="control-label">True</label>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="trueFalseRadio" id="falseValue" value="False">&nbsp;<label class="control-label">False</label>
										
										<!-- <br>
										<label>Data for true</label>
										
										<textarea class="form-control" name="trueValue" id="trueValue"></textarea>
										
										<label>Data for false</label>
										<textarea class="form-control" name="falseValue" id="falseValue"></textarea> -->
									</div>
									
									<div id="textDiv">
										<label>Default Value<span class="mandatoryField"> &nbsp;*</span></label>
										
										<textarea rows="5" cols="2" name="textValue" id="textValue" class="form-control"></textarea>
									</div>
									
									<div id="numericDiv">
										<label>Default Value<span class="mandatoryField"> (Only Numeric values)&nbsp;*</span></label>
										
										<input type="text" name="numericValue" id="numericValue" class="form-control">
									</div>
									
									<div id="dateDiv">
										<label>Default Value<span class="mandatoryField"> (dd/mm/yyyy)&nbsp;*</span></label>
										
										<input type="text" name="dateValue" id="dateValue" class="form-control" readonly="readonly" >
									</div>
								</div>
								
								<div style="margin-top: 20px;">
									<button type="button" class="btn btn-success" value="Save" id="saveBtn" name="saveBtn" onClick="addQATemplateData('show');"><i class="fa fa-floppy-o"></i>&nbsp;Save</button>
									<button type="button" class="btn btn-success" value="Edit" id="updateBtn" name="updateBtn" onClick="addQATemplateData('hide');"><i class="fa fa-pencil-square-o"></i>&nbsp;Update</button>
									<button type="button" class="btn btn-warning" value="Clear" id="clearBtn" name="clearBtn" onClick="clearFields();"><i class="fa fa-eraser"></i>&nbsp;Clear</button>
									<button type="button" class="btn btn-primary" value="Cancel" id="cancelBtn" name="cancelBtn" onClick="cancel();"><i class="fa fa-times"></i>&nbsp;Cancel</button>
									<button type="button" class="btn btn-danger" value="Delete" id="deleteBtn" name="deleteBtn" onClick="deleteTaxabilityData();"><i class="fa fa-trash-o fa-lg"></i>&nbsp;Delete</button>
								</div>
							</form>
							<!-- /.col-lg-6 (nested) -->
							<!-- /.col-lg-6 (nested) -->
						</div>
						<!-- /.row (nested) -->
					</div>
					
					<div style="border-top:solid; border-top-color: black; padding-top: 10px; padding-right: 0px;">
						<!-- <table id="taxabilityListGrid"></table>
						<div id="taxabilityListPager"></div> -->
						<div class="col-lg-12" id="gridDivId">
							<table id="taxabilityListGrid"></table>
						</div>
						<div class="col-lg-12">
							<div id="taxabilityListPager"></div>
						</div>
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