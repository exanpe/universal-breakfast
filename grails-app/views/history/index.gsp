<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.history.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.history.help" /></p>
</blockquote>

<g:if test="${total == 0}">

   ${message(code : 'ub.history.empty')}

</g:if>
<g:else>

    <div class="row">
        <div class="col-sm-6">
            <g:paginate total="${total}" max="${grailsApplication.config.ub.history.perPage}"/>
        </div>
        <div class="col-sm-6">
            <g:link class="btn btn-primary active right" action="clear">${g.message(code:'ub.history.clear')}</g:link>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>${g.message('code' : 'ub.history.table.number')}</th>
                <th>${g.message('code' : 'ub.history.table.date')}</th>
                <th>${g.message('code' : 'ub.history.table.providers')}</th>
                <th>${g.message('code' : 'ub.history.table.attendees')}</th>
            </tr>
        </thead>
        <tbody>
        <g:each status="i" in="${histories}" var="h">
            <!-- Alternate CSS classes for the rows. -->
            <tr>
                <td>${total - ((int)params.offset?params.offset.toInteger():0)-i}</td>
                <td><g:formatDate date="${h.date}"/></td>
                <td>${h.providers}</td>
                <td>${h.numAttendees}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <g:paginate total="${total}"  max="${grailsApplication.config.ub.history.perPage}"/>
    TODO : Table of history pagined by 20 ordered by date desc and "clear all" button with alert

</g:else>

</body>
</html>