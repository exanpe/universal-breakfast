<sec:ifLoggedIn>
    <div class="well">
        <h4>Breakfast</h4>
        <ul class="nav">
            <li><g:link controller="prepare"><g:message code="ub.prepare.sidebar.label"/></g:link></li>
            <li><g:link controller="getTogether"><g:message code="ub.getTogether.sidebar.label"/></g:link></li>
            <li><g:link controller="complete"><g:message code="ub.complete.sidebar.label"/></g:link></li>
        </ul>
    </div>
    <div class="well">
        <h4>Team</h4>
        <ul class="nav">
            <li><g:link controller="manage"><g:message code="ub.manage.sidebar.label"/></g:link></li>
            <li><g:link controller="manage">TODO manage account</g:link></li>
            <li><g:link controller="manage">TODO Breakfast history</g:link></li>
        </ul>
    </div>
</sec:ifLoggedIn>