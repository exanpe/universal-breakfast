<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
    </head>
    <body>

        <div class="carousel slide" id="carousel" data-interval="false">
            <div class="carousel-inner">
                <div class="item active">
                    <div class="fill"></div>
                    <div class="carousel-caption">
                        <h2><g:message code="ub.home.title" /></h2>
                        <g:if test='${flash.message}'>
                            <div class='alert alert-${flash.status ?: "info"}'>
                                <span>${flash.message}</span>
                            </div>
                        </g:if>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="1"><g:message code="ub.home.login.label" /></a>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="2"><g:message code="ub.home.register.label" /></a>
                    </div>
                </div>

                <!-- Sign in -->
                <div class="item">
                    <div class="fill"></div>
                    <div class="carousel-caption">
                        <h2><g:message code="ub.home.title" /></h2>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="0"><g:message code="ub.home.label" /></a>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="2"><g:message code="ub.home.register.label" /></a>
                        <div class="col-md-7 col-sm-7 sign-form">
                            <g:render template="/login/login" />
                        </div>
                    </div>
                </div>

                <!-- Sign up -->
                <div class="item">
                    <div class="fill"></div>
                    <div class="carousel-caption">
                        <h2><g:message code="ub.home.title" /></h2>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="0"><g:message code="ub.home.label" /></a>
                        <a href="#" class="button" data-target="#carousel" data-slide-to="1"><g:message code="ub.home.login.label" /></a>
                        <div class="col-md-7 col-sm-7 sign-form">
                            <g:render template="/login/register" />
                        </div>
                    </div>
                </div>
            </div>
        </div>

	</body>
</html>
