<%@ page import="fr.exanpe.universal.breakfast.domain.Member" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.manage.member.create.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.manage.member.create.help" /></p>
</blockquote>

<div>
    <ub:errors obj="${memberInstance}"/>
    <g:form class="form-horizontal" controller="manage" action="save" method="PUT" >
        <g:hiddenField name="id" value="${memberInstance?.id}" />
        <g:render template="form"/>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <g:link class="btn btn-info" controller="manage" action="index"><g:message code="default.button.cancel.label" /> </g:link>
                <g:submitButton name="create" class="btn btn-default action" value="${message(code: 'default.button.create.label', default: 'Create')}" />
            </div>
        </div>
    </g:form>
</div>
</body>
</html>