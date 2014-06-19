<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="ub.contact.title"/></title>
</head>
<body>

    <div class="section-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><g:message code="ub.contact.title"/></h1>
                </div>
            </div>
        </div>
    </div>

    <div class="container container-int">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-xs-12 jumbotron">
                <h1><g:message code="ub.contact.feelfree"/></h1>

                <ul class="list-group contact">
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-envelope"></i><g:message code="ub.contact.mail"/>
                    <br/><a href="mailto:${mail}">${mail}</a>
                    </li>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-github"></i><g:message code="ub.contact.github"/><br/>
                        <a href="${github}">${github}</a>
                    </li>
                </ul>

            </div>
        </div>
    </div>

</body>
</html>
