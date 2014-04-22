<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="springSecurity.login.title"/></title>
</head>
<body>

    <div class="section-header">
        <div class="container">
            <div class="row">

                <div class="col-lg-12">
                    <h1 class="page-header"><g:message code="springSecurity.login.title"/></h1>
                </div>

            </div>
        </div>
    </div>

    <div class="container container-int">
        <div class="row">
            <div class="col-md-12 col-sm-12">

                <g:if test='${flash.message}'>
                    <div class='alert alert-danger'>${flash.message}</div>
                </g:if>

                <form class="form-horizontal" action='${postUrl}' method='POST' id='loginForm'>
                    <div class="form-group">
                        <label for="username" class="control-label col-xs-4">
                            <g:message code="springSecurity.login.username.label"/>
                        </label>
                        <div class="col-xs-4">
                            <input type="username" class="form-control" name="j_username" id="username" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for='password' class="control-label col-xs-4">
                            <g:message code="springSecurity.login.password.label"/>
                        </label>
                        <div class="col-xs-4">
                            <input type="password" class="form-control" name="j_password" id="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-4 col-xs-4">
                            <div class="checkbox">
                                <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
                                <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-4 col-xs-4">
                            <button type="submit" class="btn btn-primary"><g:message code="springSecurity.login.button" /> </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
