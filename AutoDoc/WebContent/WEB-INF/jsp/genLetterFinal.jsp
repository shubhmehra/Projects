<script type="text/javascript" src="script/js/genLetterLogic.js"></script>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Generate Letter</h3>
		</div>
		
		<!-- <h1 style="color: red; font-weight: bolder;">Coming Soon...</h1> -->
		<div class="col-lg-4">
			<form id="generateLetterForm">
				<div id="numericDiv">
					<label>Token Number<span class="mandatoryField">(Only Numeric values)&nbsp;*</span></label>
					
					<input type="number" name="tokenNumber" id="tokenNumber" class="form-control">
				</div>

				<div style="margin-top: 20px;">
					<button type="button" class="btn btn-success" value="GenerateLetter" id="genLeteBtn" name="genLeteBtn" onClick="generateLetter();"><i class="fa fa-file-word-o"></i>&nbsp;Generate Letter</button>
					<button type="button" class="btn btn-warning" value="Clear" id="clearBtn" name="clearBtn" onClick="clearFields();"><i class="fa fa-eraser"></i>&nbsp;Clear</button>
				</div>
			</form>
		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->