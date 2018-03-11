<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>
			<tiles:insertAttribute name="title"/>
		</title>
		
		<link rel="shortcut icon" href="images/favicon.png">
		
		<!-- Custom common CSS -->
	    <!-- <link type="text/css" rel="stylesheet" href="css/common.css"/> -->
	    
		<!-- Bootstrap & jqGrid Core CSS -->
		<!-- <link type="text/css" rel="stylesheet" href="css/jquery-ui.css" > -->
		<!-- <link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.0.custom.css"/> -->
		<!-- <link type="text/css" rel="stylesheet" href="css/bootstrap-dialog.css" > -->
		<!-- <link type="text/css" rel="stylesheet" href="css/ui.jqgrid.css"/> -->
		<!-- <link type="text/css" rel="stylesheet" href="css/jqGrid.bootstrap.css"/> -->
		
		<link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css" >
		<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.0.custom.min.css"/>
		
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" >
		
		<link type="text/css" rel="stylesheet" href="css/bootstrap-dialog.min.css" >
		<link type="text/css" rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link type="text/css" rel="stylesheet" href="css/ui.jqgrid.min.css"/>
		<link type="text/css" rel="stylesheet" href="css/jqGrid.bootstrap.min.css"/>
		
	    <!-- MetisMenu CSS -->
	    <link type="text/css" rel="stylesheet" href="css/plugins/metisMenu/metisMenu.min.css">
		
	    <!-- Custom CSS used to set Left Menu-->
	    <!-- <link type="text/css" rel="stylesheet" href="css/sb-admin-2.css"> -->
	    <link type="text/css" rel="stylesheet" href="css/sb-admin-2.min.css">
	    
	    <!-- jqGrid Core js -->
	    <script type="text/javascript" src="js/scripts/jquery-1.11.3.min.js"></script>
	    <!-- <script type="text/javascript" src="js/scripts/jquery-ui.js"></script> -->
	    <script type="text/javascript" src="js/scripts/jquery-ui.min.js"></script>
	    <script type="text/javascript" src="js/scripts/bootstrap.min.js" ></script>
	    <!-- <script type="text/javascript" src="js/scripts/bootstrap-dialog.js" ></script> -->
	    <script type="text/javascript" src="js/scripts/bootstrap-dialog.min.js" ></script>
	    <!-- <script type="text/javascript" src="js/scripts/grid.locale-en.js" ></script> -->
	    <script type="text/javascript" src="js/scripts/grid.locale-en.min.js" ></script>
		<script type="text/javascript" src="js/scripts/jquery.jqGrid.min.js"></script>
		
	    <!-- <script type="text/javascript" src="js/scripts/jquery-ui-messageBox.js"></script> -->
	    <script type="text/javascript" src="js/scripts/jquery-ui-messageBox.min.js"></script>
	    <script type="text/javascript" src="js/scripts/jquery-ui.min.js"></script>
	    <!-- <script type="text/javascript" src="js/scripts/jquery.blockUI.js"></script> -->
	    <script type="text/javascript" src="js/scripts/jquery.blockUI.min.js"></script>
		
	    <!-- Custom Fonts -->
	    <link href="css/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon" href="images/favicon.png">
	    <link rel="bookmark" href="images/favicon.png">
	    
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="js/js/plugins/metisMenu/metisMenu.min.js"></script>
		
	    <!-- Custom Theme JavaScript -->
	    <!-- <script src="js/js/sb-admin-2.js"></script> -->
	    <script src="js/js/sb-admin-2.min.js"></script>
	    <!-- Custom common function related JavaScript -->
	    <script type="text/javascript" src="script/js/commonFunctions.js"></script>
	    <style>
			/* Below css is used to make first Row i.e. HEADER of the jqGrid  divided in 2 rows if required. */
			th.ui-th-column div {
			    word-wrap: break-word; /* IE 5.5+ and CSS3 */
			    white-space: pre-wrap; /* CSS3 */
			    white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
			    white-space: -pre-wrap; /* Opera 4-6 */
			    white-space: -o-pre-wrap; /* Opera 7 */
			    overflow: hidden;
			    height: auto !important;
			    vertical-align: middle;
			}
			
			.mandatoryField {
				font: bold; color: red;
			}
		</style>
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