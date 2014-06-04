<div class="register">
    <g:hasErrors bean="${command}">
        <div class="alert alert-danger">
            <ul>
                <g:renderErrors bean="${command}" />
            </ul>
        </div>
    </g:hasErrors>

    <g:form class="form-horizontal" controller="register" action="register">
        <div class="form-group">
            <label for="username" class="control-label col-xs-4">
                <g:message code="ub.register.username.label"/>
            </label>
            <div class="col-xs-4">
                <g:textField name="username" class="form-control" value="${params?.username}" />
            </div>
        </div>
        <div class="form-group">
            <label for="mail" class="control-label col-xs-4">
                <g:message code="ub.register.mail.label"/>
            </label>
            <div class="col-xs-6">
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
            <div class="col-xs-4"></div>
            <div class="col-xs-4">
                <img src="${createLink(controller: 'simpleCaptcha', action: 'captcha')}"/>
            </div>
        </div>
        <div class="form-group">
            <label for='captcha' class="control-label col-xs-4"><g:message code="ub.register.captcha.label" /> </label>
            <div class="col-xs-4">
                <g:textField name="captcha" class="form-control" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="ub.register.button" /> </button>
            </div>
        </div>
    </g:form>
</div>