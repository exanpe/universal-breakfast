<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<g:layoutHead/>
        <asset:stylesheet src="font-awesome.min.css" />
        <asset:stylesheet src="hopscotch/hopscotch.min.css" />
        <asset:stylesheet src="bootstrap-datetimepicker/datetimepicker.css" />
        <asset:stylesheet src="jquery-checkbox-radio/red.css" />
        <asset:stylesheet src="ub.less" />

        <asset:javascript src="jquery.js" />
        <asset:javascript src="bootstrap.js" />
        <asset:javascript src="bootstrap-datetimepicker/moment.js" />
        <asset:javascript src="bootstrap-datetimepicker/datetimepicker.js" />
        <asset:javascript src="jquery-checkbox-radio/icheck.min.js" />
        <asset:javascript src="plugins/respond.js" />
        <asset:javascript src="plugins/scrolltopcontrol.js" />
        <asset:javascript src="hopscotch/hopscotch.min.js" />
        <script type="text/javascript" src="${createLink(controller: "messages", action: "scripts")}"></script>
        <asset:javascript src="ub.js" />
		<r:layoutResources />

        <ga:trackPageview />
	</head>
	<body class="back">

        <div id="wrap">

        <!-- Nav bar -->
        <g:render template="/menu/nav" />

        <!-- Header title -->
        <div class="section-header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"><g:layoutTitle default="My Title"/></h1>
                    </div>
                </div>
            </div>
        </div>

        <div class="main">

            <div class="row">

                <!-- Left Sidebar -->
                <div class="col-md-3 col-sm-3 sidebar">
                    <g:render template="/menu/sidebar" />
                </div>

                <!-- Main content -->
                <div class="content col-md-9 col-sm-9">

                    <g:if test='${flash.message}'>
                        <div class='alert alert-success'>
                            <g:message code="${flash.message}" />
                        </div>
                    </g:if>

		            <g:layoutBody/>
                </div>

            </div>
        </div>

        </div>

        <!-- Footer -->
        <g:render template="/menu/footer" />

        <ub:autoHelp/>

        <r:layoutResources />
	</body>
</html>
