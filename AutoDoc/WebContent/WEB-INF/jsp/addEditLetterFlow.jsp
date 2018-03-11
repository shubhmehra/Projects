<script type="text/javascript" src="script/js/letterFlowLogic.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
	th{
		color: #337AB7;
		font-weight: bolder;
		font-size: medium;
		text-align: center;
	}
	table, th, td {
	    border: 1px solid black;
	    border-collapse: collapse;
	}
	th, td {
	    padding: 5px;
	}
	tr:nth-child(even) {background-color: #f2f2f2}
</style>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h3 class="page-header" style="color: #337AB7; font-weight: bold;">Set Flow of Letter</h3>
		</div>
		<!-- /.col-lg-12 -->
		
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div style="margin-left: 2%; margin-right: 2%;">
							<form:form modelAttribute="questionFlowSet" action="getQuesListForLetteFlow.do">
								<c:choose>
			    					<c:when test="${not empty questionFlowSet}">
		    							<%-- <select name="selLetterId" class="form-control">
											<option value="NONE">--- Select status ---</option>
											<c:forEach var="letterNamesList" items="${letterNamesList}">
												<option>${letterNamesList.getValue()}</option>
											</c:forEach>
										</select> --%>
										
										<label class="control-label">Letter Name<span class="mandatoryField">&nbsp;*</span></label>
										<form:select path="letterId" class="form-control" onchange="this.form.submit();">
											<form:option value="0">-- Select Letter Name --</form:option>
											<form:options items="${letterNamesList}" itemLabel="value" itemValue="id"/>
										</form:select>
										
										<c:choose>
											<c:when test="${not empty questionFlowSet.questionFlowSettingList}">
												<table style="margin-top: 2%;">
													<!-- <thead>Select which question needs to be asked after which one.</thead> -->
													
													<th hidden>QuestionId</th>
													<th>Question No</th>
													<th>Question</th>
													<th>Question Type</th>
													<th>Default Value</th>
													<th>Child question id for (Yes/True/Date/Text/Numeric)</th>
													<th>Child question id for (No/False)</th>
													
													<c:forEach items="${questionFlowSet.questionFlowSettingList}" var="listData">
														<tr>
															<td hidden><form:hidden path="questionId" value="${listData.questionId}"/></td>
															<td><form:label path="questionNo">${listData.questionNo}</form:label></td>
															<td><form:label path="question">${listData.question}</form:label></td>
															<td><form:label path="questionType">${listData.questionType}</form:label></td>
															<td><form:label path="defaultValue">${listData.defaultValue}</form:label></td>
														
															<td>
																<c:choose>
																	<c:when test="${not empty listData.positiveAnsChildIds}">
																		<form:select path="positiveAnsChildId" class="form-control">
																			<%-- <form:option value="0">Select Next Question for +ve answer</form:option> --%>
																			<form:options items="${listData.positiveAnsChildIds}" itemLabel="value" itemValue="id"/>
																		</form:select>
																	</c:when>
																	<c:otherwise>
																		<div hidden>
																			<form:select path="positiveAnsChildId" class="form-control">
																				<form:option value="0">Select Next Question for +ve answer</form:option>
																				<form:options items="${listData.positiveAnsChildIds}" itemLabel="value" itemValue="id"/>
																			</form:select>
																		</div>
																	</c:otherwise>
																</c:choose>
															</td>
															<td>
																<c:choose>
																	<c:when test='${not empty listData.negativeAnsChildIds and (listData.questionType == "yesNo" or listData.questionType == "trueFalse")}'>
																		<form:select path="negativeAnsChildId" class="form-control">
																			<%-- <form:option value="0">Select Next Question for -ve answer</form:option> --%>
																			<form:options items="${listData.negativeAnsChildIds}" itemLabel="value" itemValue="id"/>
																		</form:select>
																	</c:when>
																	<c:otherwise>
																		<div hidden>
																			<form:select path="negativeAnsChildId" class="form-control">
																				<form:option value="0">Select Next Question for -ve answer</form:option>
																				<form:options items="${listData.negativeAnsChildIds}" itemLabel="value" itemValue="id"/>
																			</form:select>
																		</div>
																	</c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>
												</table>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
								
								<div style="margin-top: 1%;">
									<form:button class="btn btn-success" name="saveBtn" id="saveBtn" value="Save" onClick="this.form.submit();"><i class="fa fa-floppy-o"></i>&nbsp;Save</form:button>
								</div>
							</form:form>
						</div>
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
<!-- page-wrapper -->