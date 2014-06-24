<%@ page import="fr.exanpe.universal.breakfast.domain.WorkflowState" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.gather.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.gather.help" /></p>
</blockquote>

<div class="gather">

    <g:if test="${flash?.sent == 1}">
        <div class="alert alert-success">
            <g:message code="ub.gather.success"/>
        </div>
    </g:if>
    <g:elseif test="${team.workflowState == WorkflowState.GATHER}">
        <div class="alert alert-warning">
            <g:message code="ub.gather.already.sent"/>
        </div>
    </g:elseif>

    <g:if test="${configuration.sendMail}">

        <g:form class="form-horizontal" controller="gather" action="gather">

            <div class="form-group">
                <label for="message" class="control-label col-xs-4">
                    <g:message code="ub.gather.message.label"/>
                </label>
                <div class="col-xs-8 input-group">
                    <g:textArea name="message" class="form-control" value="${params?.message}"/>
                </div>
            </div>

            <div class="form-group">
                <label for="location" class="control-label col-xs-4">
                    <g:message code="ub.gather.location.label"/>
                </label>
                <div class="col-xs-8 input-group">
                    <g:textField name="location" class="form-control" value="${params?.location}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-4 col-xs-4">
                    <button type="submit" class="btn btn-primary"><g:message code="ub.gather.button" /> </button>
                </div>
            </div>
        </g:form>
    </g:if>
    <g:else>
        <div class="alert alert-danger">
            <g:message code="ub.mail.disabled"/>
        </div>
    </g:else>
</div>

</body>
</html>