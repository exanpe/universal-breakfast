<sec:ifLoggedIn>
    <div class="well">
        <h4>Breakfast</h4>
        <ul class="nav">
            <li><g:link controller="prepare"><g:message code="ub.prepare.sidebar.label"/></g:link></li>
            <li><g:link controller="gather"><g:message code="ub.gather.sidebar.label"/></g:link></li>
            <li><g:link controller="complete"><g:message code="ub.complete.sidebar.label"/></g:link></li>
        </ul>
    </div>
    <div class="well">
        <h4>Team</h4>
        <ul class="nav">
            <li><g:link controller="manage"><g:message code="ub.manage.sidebar.label"/></g:link></li>
            <li><g:link controller="account"><g:message code="ub.account.sidebar.label"/></g:link></li>
            <li><g:link controller="history"><g:message code="ub.history.sidebar.label"/></g:link></li>
        </ul>
    </div>
</sec:ifLoggedIn>