<%@ page import="fr.exanpe.universal.breakfast.domain.WorkflowState" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.complete.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.complete.help" /></p>
</blockquote>


<div class="complete">

    <ub:errors obj="${command}"/>

    <g:if test="${flash?.complete == 1}">
        <div class="alert alert-success">
            <g:message code="ub.complete.success"/>
        </div>
    </g:if>
    <g:elseif test="${team.workflowState == WorkflowState.COMPLETE}">
        <div class="alert alert-warning">
            <g:message code="ub.complete.already.complete"/>
        </div>
    </g:elseif>

    <g:form class="form" controller="complete" action="complete">

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">
                        <g:message code="ub.complete.suppliers.label"/>
                    </label>
                    <div class="input-group">
                        <g:each in="${members}" var="member" status="i">
                            <div class="checkbox">
                                <label for="supplier_${i}">
                                    <g:checkBox id="supplier_${i}" name="suppliers" value="${i}" class="icb" checked="${command?.hasSuppliers(i)}"/>
                                    ${member.name}
                                </label>
                            </div>
                        </g:each>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label class="control-label">
                        <g:message code="ub.complete.attendees.label"/>
                    </label>
                    <div class="input-group">
                        <g:each in="${members}" var="member" status="i">
                            <div class="checkbox">
                                <label for="attendees_${i}">
                                    <g:checkBox id="attendees_${i}" name="suppliers" value="${i}" class="icb" checked="${command?.hasAttendees(i)}"/>
                                    ${member.name}
                                </label>
                            </div>
                        </g:each>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="ub.complete.button" /> </button>
            </div>
        </div>
    </g:form>

</div>

</body>
</html>