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
        <div class="col-md-12 col-sm-12">

            <blockquote>
                <p><g:message code="ub.contact.feelfree" /></p>
            </blockquote>

            <div class="register">
                <ub:errors obj="${command}"/>

                <g:form class="form-horizontal" controller="home" action="sendMessage">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="contactCommand.name.label"/>
                            </ub:required>
                        </label>
                        <div class="col-xs-6">
                            <g:field type="text" name="name" class="form-control" value="${params?.name}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="mail" class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="contactCommand.mail.label"/>
                            </ub:required>
                        </label>
                        <div class="col-xs-6">
                            <g:field type="email" name="mail" class="form-control" value="${params?.mail}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message" class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="contactCommand.message.label"/>
                            </ub:required>
                        </label>
                        <div class="col-xs-6">
                            <g:textArea id="message" name="message" rows="6" class="form-control" value="${params?.message}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4"></div>
                        <div class="col-xs-4">
                            <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for='captcha' class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="contactCommand.captcha.label" />
                            </ub:required>
                        </label>
                        <div class="col-xs-4">
                            <g:textField name="captcha" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-4 col-xs-4">
                            <button type="submit" class="btn btn-primary"><g:message code="ub.contact.send.button" /> </button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
