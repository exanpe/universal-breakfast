<sec:ifLoggedIn>
    <div class="well">
        <h4>
            <g:message code="ub.sidebar.breakfast"/>
        </h4>
        <ul class="nav">
            <li class="tour_prepare"><g:link controller="prepare"><g:message code="ub.prepare.sidebar.label"/></g:link></li>
            <li class="tour_gather"><g:link controller="gather"><g:message code="ub.gather.sidebar.label"/></g:link></li>
            <li class="tour_complete"><g:link controller="complete"><g:message code="ub.complete.sidebar.label"/></g:link></li>
        </ul>
    </div>
    <div class="well tour_settings">
        <h4>
            <g:message code="ub.sidebar.team"/>
        </h4>
        <ul class="nav">
            <li class="tour_members"><g:link controller="manage"><g:message code="ub.manage.sidebar.label"/></g:link></li>
            <li><g:link controller="account"><g:message code="ub.account.sidebar.label"/></g:link></li>
            <li><g:link controller="history"><g:message code="ub.history.sidebar.label"/></g:link></li>
        </ul>
    </div>
</sec:ifLoggedIn>