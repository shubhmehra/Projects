<!-- xmlns:sec=http://www.springframework.org/security/tags -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String s1= (String) request.getAttribute("javax.servlet.forward.request_uri");
	String[] splits = s1.split("/");
	String s2=splits[2];
%>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<sec:authorize access="hasAnyRole('ADMIN','SUPER') and isAuthenticated()">
					<a class="navbar-brand" href="clients.do"><img src="images/logo.png" width="40%"></a>
				</sec:authorize>
				<sec:authorize access="hasRole('USER') and isAuthenticated()">
					<a class="navbar-brand" href="welcomePage.do"><img src="images/logo.png" width="40%"></a>
				</sec:authorize>
			</div>
			<!-- /.navbar-header -->
			<div class="navbar-header" style="margin-left: 30%;">
				<label style="color:#808080; vertical-align: middle; margin-top: 15px; font-size: medium;">Auto Documentation</label>
			</div>
			<sec:authorize access="authenticated">
				<ul class="nav navbar-top-links navbar-right">
					<!-- /.dropdown -->
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						Welcome, 
							<i class="fa fa-user fa-fw"></i>
								<sec:authentication property="name" />
							<i class="fa fa-caret-down"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li>
								<c:url var="logoutUrl" value="/j_spring_security_logout" />
								<a href="${logoutUrl}"><i class="fa fa-sign-out fa-fw"></i>Log out</a>
							</li>
						</ul> <!-- /.dropdown-user --></li>
					<!-- /.dropdown -->
				</ul>
			</sec:authorize>
			<!-- /.navbar-top-links -->
			
			<sec:authorize access="hasAnyRole('ADMIN','SUPER') and isAuthenticated()">
				<div class="navbar-default sidebar">
					<ul class="nav" id="side-menu">
						<!-- icons are used from - http://fortawesome.github.io/Font-Awesome/icons/ -->
					 	<li><a href="clients.do" <%if(s2.contains("clients") || s2.contains("welcomePage")){ %>class="active" <%} %>><i class="fa fa-users fa-lg"></i><label class="fa-lg">&nbsp;Clients</label></a></li>
				        <li><a href="letters.do" <%if(s2.contains("letters")){ %>class="active" <%} %>><i class="fa fa-file-pdf-o fa-lg"></i><label class="fa-lg">&nbsp;&nbsp;Letters</label></a></li>
				        <li><a href="taxability.do" <%if(s2.contains("taxability")){ %>class="active" <%} %>><i class="fa fa-pencil-square fa-lg"></i><label class="fa-lg">&nbsp;&nbsp;Taxability</label></a></li>
				        <li><a href="letterFlow.do" <%if(s2.contains("letterFlow") || s2.contains("LetteFlow")){ %>class="active" <%} %>><i class="fa fa-cogs"></i><label class="fa-lg">&nbsp;&nbsp;Letter Flow</label></a></li>
					</ul>
					<!-- /.nav-second-level -->
				</div>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('USER') and isAuthenticated()">
				<div class="navbar-default sidebar">
					<ul class="nav" id="side-menu">
					 	<li><a href="welcomePage.do" <%if(s2.contains("welcomePage")){ %>class="active" <%} %>><i class="fa fa-question fa-lg"></i><label class="fa-lg">&nbsp;Questionnaire</label></a></li>
				        <li><a href="genLetter.do" <%if(s2.contains("genLetter")){ %>class="active" <%} %>><i class="fa fa-file-pdf-o fa-lg"></i><label class="fa-lg">&nbsp;Generate Letter</label></a></li>
					</ul>
					<!-- /.nav-second-level -->
				</div>
			</sec:authorize>
			<!-- /.navbar-static-side -->
		</nav>