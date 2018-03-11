<script type="text/javascript" src="script/js/generateLetterInit.js"></script>
<script type="text/javascript" src="script/js/generateLetterLogic.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		InitializeGenerateLetter();
		getLetterNames('letterName', 'unique');
		getClientNames('clientName', 'active');
		clearFields();
	});
</script>

<!-- Below margin is set to 0(Zero) to remove the left side bar space -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Questionnaire</h3>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<div class="col-lg-5">
								<label class="control-label">Client Name<span class="mandatoryField">&nbsp;*</span></label>
								<select name="clientName" id="clientName" class="form-control" style="width: 100%;"></select>
							</div>
							<!-- /.col-lg-5 -->
							
							<div class="col-lg-5">
								<label class="control-label">Letter Name<span class="mandatoryField">&nbsp;*</span></label>
								<select name="letterName" id="letterName" class="form-control" style="width: 100%;"
									onchange="getGenerateLetterData(this.value, 0, 'change');getTokenNumber();">
								</select>
							</div>
							<!-- /.col-lg-5 -->
						</div>
						<!-- /.form-group -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel panel-default -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row" style="padding-bottom: 10px;">
						<div class="col-lg-10">
							<form id="generateLetterForm">
								<div>
									<input type="hidden" id="questionId" name="questionId">
									<input type="hidden" name="questionNo" id="questionNo">
									<input type="hidden" id="questionType" />
									<input type="hidden" id="childPosId" />
									<input type="hidden" id="childNegId" />
								</div>
								
								<div>
									<div class="form-group" style="color: #337AB7; font-weight: bolder; font-size: large; text-decoration: underline;">
										<label class="control-label" style="text-decoration: underline;">Your Token Number for this Letter is : </label>
										<label class="control-label" id="tokenNumber" style="color: #FF0000; text-decoration: underline;"></label>
										<!-- <input type="text" id="tokenNumber" disabled="disabled"/> -->
									</div>
								</div>

								<div>
									<div class="form-group">
										<label class="control-label">Question</label>
										
										<textarea class="form-control" name="question" id="question" disabled="disabled"></textarea>
									</div>
								</div>

								<div>
									<div id="yesNoDiv">
										<label>Answer<span class="mandatoryField">&nbsp;*</span> </label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="yesNoRadio" value="Yes" checked="checked">&nbsp;<label class="control-label">Yes</label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="yesNoRadio" value="No">&nbsp;<label class="control-label">No</label>
									</div>

									<div id="trueFalseDiv">
										<label>Answer<span class="mandatoryField">&nbsp;*</span></label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="trueFalseRadio" value="True" checked="checked">&nbsp;<label class="control-label">True</label>
										
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="trueFalseRadio" value="False">&nbsp;<label class="control-label">False</label>
									</div>

									<div id="textDiv">
										<label>Answer<span class="mandatoryField">&nbsp;*</span></label>

										<textarea rows="5" cols="2" name="textValue" id="textValue" class="form-control"></textarea>
									</div>

									<div id="numericDiv">
										<label>Answer<span class="mandatoryField">(Only Numeric values)&nbsp;*</span></label>
										
										<input type="text" name="numericValue" id="numericValue" class="form-control">
									</div>

									<div id="dateDiv">
										<label>Answer<span class="mandatoryField">(dd/mm/yyyy)&nbsp;*</span></label>
										
										<input type="text" name="dateValue" id="dateValue" class="form-control" readonly="readonly">
									</div>
								</div>

								<div style="margin-top: 20px;">
									<!-- <button type="button" class="btn btn-info" value="Previous" id="previousBtn" name="previousBtn" onClick="previousBtn();"><i class="fa fa-step-backward"></i>&nbsp;Previous</button> -->
									<button type="button" class="btn btn-primary" value="Cancel" id="cancelBtn" name="cancelBtn" onClick="cancel();"><i class="fa fa-times"></i>&nbsp;Cancel</button>
									
									<button type="button" class="btn btn-info" value="Next" id="nextBtn" name="nextBtn" onClick="getNextQuestion();">Next&nbsp;<i class="fa fa-step-forward"></i></button>
									<button type="button" class="btn btn-success" value="GenerateLetter" id="genLeteBtn" name="genLeteBtn" onClick="generateLetter();"><i class="fa fa-file-word-o"></i>&nbsp;Generate Letter</button>
								</div>
							</form>
						</div>
						<!-- /.col-lg-10 (nested) -->
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
</div>
<!-- /#page-wrapper -->