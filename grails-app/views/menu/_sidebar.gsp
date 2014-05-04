<sec:ifLoggedIn>
    <div class="well">
        <h4>Menu</h4>
        <ul class="nav">
            <li><g:link controller="manage" action="timetoeat"><g:message code="ub.timetoeat.sidebar.label"/></g:link></li>
            <li><g:link controller="manage" action="validation"><g:message code="ub.validation.sidebar.label"/></g:link></li>
            <li><g:link controller="manage" action="manage"><g:message code="ub.manage.sidebar.label"/></g:link></li>
        </ul>
    </div>
</sec:ifLoggedIn>