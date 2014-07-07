<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="ub.home.password.reset.title" /></title>
</head>
<body>

<div class="single">

<div class="section-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><g:message code="ub.home.password.reset.title"/></h1>
            </div>
        </div>
    </div>
</div>

<div class="content container container-int">
    <div class="row">
        <div class="col-md-12 col-sm-12">

            <blockquote>
                <p><g:message code="ub.home.password.reset.help" /></p>
            </blockquote>

            <div>
                <ub:errors obj="${command}"/>
                <g:form class="form-horizontal" controller="home" action="sendResetPassword">
                    <div class="form-group">
                        <label for="mail" class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="ub.register.mail.label"/>
                            </ub:required>
                        </label>
                        <div class="col-xs-4">
                            <g:field type="email" name="mail" class="form-control" value="" />
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
                                <g:message code="registerCommand.captcha.label" />
                            </ub:required>
                        </label>
                        <div class="col-xs-4">
                            <g:textField name="captcha" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-4 col-xs-4">
                            <button type="submit" class="btn btn-primary"><g:message code="default.button.send.label" /> </button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>