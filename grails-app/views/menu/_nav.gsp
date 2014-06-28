<!-- Navbar -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <g:link controller="home" action="index" class="navbar-brand">
                <span id="logo" class="fa-stack">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="logo-center fa fa-coffee fa-stack-1x"></i>
                </span>
                <g:message code="ub.name" />
            </g:link>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><g:link controller="home" action="index"><g:message code="default.home.label"/></g:link></li>
                <sec:ifNotLoggedIn>
                    <li><g:link controller="home" action="contact"><g:message code="ub.contact.title"/></g:link></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><a href="#" onclick="hopscotch.startTour(tour)"><g:message code="ub.help.label"/></a></li>
                    <li><g:link controller="manage"><ub:teamName /></g:link></li>
                    <li><a href="${createLink(controller: 'logout')}"><i class="fa fa-power-off"></i></a></li>
                </sec:ifLoggedIn>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav>
