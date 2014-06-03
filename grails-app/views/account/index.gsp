<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.account.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.account.help" /></p>
</blockquote>

    <h2 class="section-title"><g:message code="ub.account.settings.title" /> </h2>
    <div>

        <g:hasErrors bean="${command}">
            <div class="alert alert-danger">
                <ul>
                    <g:renderErrors bean="${command}" />
                </ul>
            </div>
        </g:hasErrors>

        <g:form class="form-horizontal" controller="account" action="update">
            <div class="form-group">
                <label for="mail" class="control-label col-xs-4">
                    <g:message code="ub.register.mail.label"/>
                </label>
                <div class="col-xs-4">
                    <g:field type="email" name="mail" class="form-control" value="${params?.mail}" />
                </div>
            </div>
            <div class="form-group">
                <label for='password' class="control-label col-xs-4">
                    <g:message code="ub.register.password.label"/>
                </label>
                <div class="col-xs-4">
                    <g:passwordField name="password" class="form-control" value="${params?.password}" />
                </div>
            </div>
            <div class="form-group">
                <label for='password2' class="control-label col-xs-4">
                    <g:message code="ub.register.confirm.password.label"/>
                </label>
                <div class="col-xs-4">
                    <g:passwordField name="password2" class="form-control" value="${params?.password2}" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-4 col-xs-4">
                    <button type="submit" class="btn btn-primary"><g:message code="default.button.update.label" /> </button>
                </div>
            </div>
        </g:form>
    </div>

    <h2 class="section-title"><g:message code="ub.account.delete.title" /> </h2>
    <g:form class="form-horizontal" controller="account" action="delete">
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="ub.account.delete.button" /> </button>
            </div>
        </div>
    </g:form>
</body>
</html>