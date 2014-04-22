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
        <asset:stylesheet src="ub.less" />

		<g:javascript library="application" />
		<r:layoutResources />

	</head>
	<body>

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

        <div class="container container-int">
            <div class="row">
                <div class="col-md-12 col-sm-12">

                    <g:if test='${flash.message}'>
                        <div class='alert alert-danger'>${flash.message}</div>
                    </g:if>

		            <g:layoutBody/>
                </div>
            </div>
        </div>

        <!-- Contact -->
        <div class="section-colored home" id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-9 col-sm-8">
                        <h2>One question, one bug or simply get In touch ?</h2>
                    </div>
                    <div class="col-md-3 col-sm-4">
                        <a href="#" class="btn btn-danger btn-lg">Contact us</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <p class="col-md-10">©2014 Exanpe.</p>
                </div>
            </div>
        </div>

        <r:layoutResources />
	</body>
</html>
