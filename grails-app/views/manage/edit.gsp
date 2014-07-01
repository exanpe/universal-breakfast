<%@ page import="fr.exanpe.universal.breakfast.domain.Member" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.manage.member.edit.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.manage.member.edit.help" /></p>
</blockquote>

<div>
    <ub:errors obj="${memberInstance}"/>
    <g:form class="form-horizontal" controller="manage" action="update" method="PUT" >
        <g:hiddenField name="version" value="${memberInstance?.version}" />
        <g:hiddenField name="id" value="${memberInstance?.id}" />
        <g:render template="form"/>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <g:link class="btn btn-info" controller="manage" action="show" id="${memberInstance?.id}"><g:message code="default.button.cancel.label" /> </g:link>
                <g:actionSubmit class="btn btn-default action" action="update" value="${message(code: 'default.button.update.label')}" />
            </div>
        </div>
    </g:form>
</div>
</body>
</html>