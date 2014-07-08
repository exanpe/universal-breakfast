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
    <p><g:message code="ub.manage.member.show.help" /></p>
</blockquote>

    <div class="form-horizontal ub-view">
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.name.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                ${memberInstance?.name}
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.mail.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                ${memberInstance?.mail}
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.active.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                <i class="fa fa-lg ${memberInstance?.active ? 'fa-check' : '' }"></i>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.lastBreakfast.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                <g:formatDate date="${memberInstance?.lastBreakfast}" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.breakfastCount.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                ${memberInstance?.breakfastCount}
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.absenceCount.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                ${memberInstance?.absenceCount}
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.dateCreated.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                <g:formatDate date="${memberInstance?.dateCreated}" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">
                <g:message code="ub.manage.member.lastUpdated.label"/>
            </label>
            <div class="col-xs-6 form-control-static">
                <g:formatDate date="${memberInstance?.lastUpdated}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <br />
                <g:form url="[controller: 'manage', action:'delete']" method="DELETE">
                    <g:link class="btn btn-info" controller="manage" action="index"><g:message code="default.button.back.label" /></g:link>
                    <g:link class="btn btn-info" controller="manage" action="edit" id="${memberInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="btn btn-default action" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </g:form>
            </div>
        </div>
    </div>
    <br />

</body>
</html>
