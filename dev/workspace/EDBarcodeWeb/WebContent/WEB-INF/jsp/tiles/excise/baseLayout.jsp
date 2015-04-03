<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
<meta name="viewport" content="width=device-width" />
<title>Excise</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="<c:url value='/css/baiwa.css'/>">
<link rel="stylesheet" href="<c:url value='/css/excise.css'/>">
<%-- <link rel="stylesheet" href="<c:url value='/stylesheets/foundation.min.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/stylesheets/main.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/stylesheets/app.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/kendo/styles/kendo.common.min.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/kendo/styles/kendo.default.min.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/kendo/styles/kendo.dataviz.min.css'/>"> --%>
<%-- <link rel="stylesheet" href="<c:url value='/kendo/styles/kendo.dataviz.default.min.css'/>"> --%>

<%-- <script type="text/javascript" src='<c:url value="/javascripts/modernizr.foundation.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/jquery/jquery-1.7.2.min.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/jquery/jquery-ui-1.8.14.custom.min.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/jquery/jquery.blockUI.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/common-validate-number.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/jquery/jquery.easy-confirm-dialog.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/js/tiny_mce/jquery.tinymce.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/kendo/js/angular.min.js"/>'></script> --%>
<%-- <script type="text/javascript" src='<c:url value="/kendo/js/kendo.all.min.js"/>'></script>	 --%>

<script type="text/javascript" src='<c:url value="/js/jquery/jquery-1.9.1.min.js"/>'></script>

<script type="text/javascript">
	var contextPath = '${contextPath}';
</script>

<!-- IE Fix for HTML5 Tags -->
<!--[if lt IE 9]>   
    <script type="text/javascript" src='<c:url value="http://html5shiv.googlecode.com/svn/trunk/html5.js"/>'></script>
  <![endif]-->
</head>
<body>
	
	<div class="head_div">
	<tiles:insertAttribute name="header"/>
	</div>
	
	<div class="right_main" style="margin-top: 2px;">
	<%@include file="/WEB-INF/jsp/tiles/excise/pageInfo.jsp" %>
	<tiles:insertAttribute name="main"/>
	</div>
    
	<div class="foot_div">
	<tiles:insertAttribute name="footer"/>
	</div>
	
</body>
</html>