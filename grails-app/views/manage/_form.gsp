<%@ page import="fr.exanpe.universal.breakfast.domain.Member" %>

<div class="form-group">
    <label for="name" class="control-label col-xs-4">
        <ub:required>
            <g:message code="ub.manage.member.name.label"/>
        </ub:required>
    </label>
    <div class="col-xs-4">
        <g:textField name="name" class="form-control" value="${memberInstance?.name}"/>
    </div>
</div>

<div class="form-group">
    <label for="mail" class="control-label col-xs-4">
        <g:message code="ub.manage.member.mail.label" />
    </label>
    <div class="col-xs-4">
        <g:textField name="mail" class="form-control" value="${memberInstance?.mail}"/>
    </div>
</div>

<div class="form-group">
    <label for="active" class="control-label col-xs-4">
        <g:message code="ub.manage.member.active.label"/>
    </label>
    <div class="col-xs-4">
        <g:checkBox name="active" class="icb" value="${memberInstance?.active}" />
    </div>
</div>
