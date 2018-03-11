<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>
			<tiles:insertAttribute name="title"/>
		</title>
		
		<link rel="shortcut icon" href="images/favicon.png">
		
		<!-- Bootstrap & jqGrid Core CSS -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" >
		
	    <!-- MetisMenu CSS -->
	    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
	    
	    <script type="text/javascript" src="js/scripts/jquery-1.11.3.min.js"></script>
	    
	    <!-- Custom Fonts -->
	    <link href="css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon" href="images/favicon.png">
	    <link rel="bookmark" href="images/favicon.png">
	    
	    <script type="text/javascript" src="js/scripts/jquery-ui.min.js"></script>
	
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="js/js/plugins/metisMenu/metisMenu.min.js"></script>
		
	    <!-- Custom Theme JavaScript -->
	    <!-- <script src="js/js/sb-admin-2.js"></script> -->
	    <script src="js/js/sb-admin-2.min.js"></script>
	</head>
	
	<body>
		<div class="ui-layout-north" style="overflow: hidden;">
			<tiles:insertAttribute name="header"/>
		</div>
		
		<div class="ui-layout-center" id="bodyContent">
			<tiles:insertAttribute name="body"/>
		</div>
		
		<div class="ui-layout-south">
			<tiles:insertAttribute name="footer"/>
		</div>
	</body>
</html>