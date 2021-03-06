<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:message code="ub.name" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="${message(code:"ub.meta.description")}" />
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<g:layoutHead />
        <asset:stylesheet src="font-awesome.min.css" />
        <asset:stylesheet src="ub.less" />

        <asset:javascript src="jquery.js" />
        <asset:javascript src="bootstrap.js" />
        <asset:javascript src="bootstrap.js" />
        <asset:javascript src="plugins/respond.js" />
        <asset:javascript src="plugins/scrolltopcontrol.js" />
        <asset:javascript src="ub.js" />
		<r:layoutResources />

        <ga:trackPageview />
	</head>
	<body class="main">

        <div id="wrap">
            <section id="home"></section>
            <g:render template="/menu/nav" />
            <g:layoutBody/>
        </div>

        <!-- Contact -->
        <div class="section-colored home" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 col-sm-8">
                        <h2><g:message code="ub.home.contact.or.question"/></h2>
                    </div>
                    <div class="col-md-3 col-sm-4">
                        <g:link controller="home" action="contact" class="btn btn-danger btn-lg">
                            <g:message code="ub.contact.link.label"/>
                        </g:link>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <g:render template="/menu/footer" />

        <r:layoutResources />

	</body>
</html>
