<%@ page import="fr.exanpe.universal.breakfast.domain.WorkflowState" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.prepare.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.prepare.help" /></p>
</blockquote>

<div class="prepare">
    <ub:errors obj="${command}"/>

    <g:if test="${flash?.sent == 1}">
        <div class="alert alert-success">
            <g:message code="ub.prepare.success"/>
        </div>
    </g:if>
    <g:elseif test="${team.workflowState == WorkflowState.PREPARE}">
        <div class="alert alert-warning">
            <g:message code="ub.prepare.already.warn" args="[g.formatDate(date : team.lastPrepare)]"/>
        </div>
    </g:elseif>

    <g:form class="form-horizontal" controller="prepare" action="prepare">
        <div class="form-group">
                <label for="date" class="control-label col-xs-4">
                    <ub:required>
                        <g:message code="prepareCommand.date.label"/>
                    </ub:required>
                </label>
            <div class="col-xs-2 input-group">
                <g:textField id="date" name="date" class="form-control date-marker" value="${command?.date?g.formatDate(date : command.date):''}" data-date-format="${g.message(code : 'default.date.format').toString().toUpperCase()}"/>
                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
            </div>
        </div>
        <div class="form-group">
            <label for="suppliers" class="control-label col-xs-4">
                <ub:required>
                    <g:message code="prepareCommand.suppliers.label"/>
                </ub:required>
            </label>
            <div class="col-xs-8 input-group">
                <g:each in="${members}" var="member" status="i">
                    <div class="checkbox">
                        <label for="supplier_${i}">
                            <g:checkBox id="supplier_${i}" name="suppliers" value="${i}" class="icb" checked="${command?.has(i)}"/>
                            ${member.name}
                        </label>
                    </div>
                </g:each>
            </div>
        </div>
        <div class="form-group">
            <label for="message" class="control-label col-xs-4">
                <g:message code="ub.prepare.message.label"/>
            </label>
            <div class="col-xs-8 input-group">
                <g:textArea id="message" name="message" class="form-control" value="${command?.message}"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="ub.prepare.button" /> </button>
            </div>
        </div>
    </g:form>
</div>

</body>
</html>