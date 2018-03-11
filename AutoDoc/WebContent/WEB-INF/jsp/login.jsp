<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
	$(document).ready(function() {
		$('#capsLockWarning').hide();
		var len = $('#displayError').text().length;
		if(len >21){
			$('#displayError').css("display","block");
		}
	});
	
	document.onkeypress = function ( e ) {
		e = e || window.event;
		// An empty field resets the visibility.
		if (this.value === '') {
			$('#capsLockWarning').hide();
			return;
		}
		
		// We need alphabetic characters to make a match.
		var character = String.fromCharCode(e.keyCode || e.which);
		if (character.toUpperCase() === character.toLowerCase()) {
			return;
		}
		
		// SHIFT doesn't usually give us a lowercase character. Check for this
		// and for when we get a lowercase character when SHIFT is enabled. 
		if ((e.shiftKey && character.toLowerCase() === character)
				|| (!e.shiftKey && character.toUpperCase() === character)) {
			$('#capsLockWarning').show();
		} else {
			$('#capsLockWarning').hide();
		}
	};
</script>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
              	<div class="panel-body" align="center">
					<img src="images/loginLogo.png" width="80%"></h3>
                </div>
		        
			    <form name='f' action="<c:url value='j_spring_security_check' />" method="post">
					<%-- <c:if test="${not empty error}"> --%>
					<div id="capsLockWarning" style="color: red; height: 10px;font-family: sans-serif; font-size: medium;font-weight: bold;" align="center">
			        	Caps Lock is on.!<!--  This may cause problems logging in because passwords are case sensitive.! -->
			        </div>
			        
					<c:if test="${param.error != null}">
						<div class="alert alert-error" id="displayError" style="color: red; height: 10px;font-family: sans-serif; font-size: medium;font-weight: bold;" align="center">
							Failed to login.<br>
							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
								<%-- <c:if test="${fn:containsIgnoreCase(sessionScope[\"SPRING_SECURITY_LAST_EXCEPTION\"].message,'Could not get JDBC Connection')}">
									Reason : Server connection is down, try after sometime.
								</c:if>
								<c:if test="${sessionScope[SPRING_SECURITY_LAST_EXCEPTION].message eq 'Bad credentials'}">
									Reason : Username/Password entered is incorrect.
								</c:if>
								<c:if test="${sessionScope[SPRING_SECURITY_LAST_EXCEPTION].message eq 'User is disabled'}">
									Reason : Your account is disabled, please contact administrator.
								</c:if> --%>
								Reason : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
						</div>
					</c:if>
						
					<div class="panel-body">
						<fieldset>

							<div class="input-group margin-bottom-sm">
								<span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
								<input type='text' name='j_username' id="usernameTxt" placeholder="Username" class="form-control" autofocus/>
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
								<input type='password' name='j_password' id="passwordTxt" placeholder="Password" class="form-control" />
							</div>
		      				<br>
		      				<div class="input-group">
			      				<span class="input-group-addon"><i class="fa fa-sign-in"></i></span>
			      				<input name="submit" type="submit" value="Sign in" class="btn btn-lg btn-success btn-block" />
			      			</div>
		      			</fieldset>
	      			</div>
			    </form>
			    
            </div>
        </div>
    </div>
</div>