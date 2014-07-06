<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.manage.title" default="Manage"/></title>
</head>
<body>

    <blockquote>
        <p><g:message code="ub.manage.help" /></p>
    </blockquote>


<g:if test="${total == 0}">
    ${message(code : 'ub.manage.members.empty')}
</g:if>
<g:else>

    <div class="row">
        <div class="col-sm-6">
            <g:paginate total="${total}" max="${grailsApplication.config.ub.members.perPage}"/>
        </div>
        <div class="col-sm-6 text-right">
            <g:link class="btn btn-default action" action="create"><i class="fa fa-plus fa-inverse"></i>&nbsp;&nbsp;<g:message code="ub.manage.members.add" /></g:link>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>${g.message('code' : 'ub.manage.table.member')}</th>
            <th>${g.message('code' : 'ub.manage.table.position')}</th>
            <th>${g.message('code' : 'ub.manage.table.member.lastBreakfast')}</th>
            <th>${g.message('code' : 'ub.manage.table.member.isactive')}</th>
            <th>${g.message('code' : 'ub.manage.table.member.actions')}</th>
        </tr>
        </thead>
        <tbody>
        <g:each status="i" in="${members}" var="m">
            <!-- Alternate CSS classes for the rows. -->
            <tr>
                <td><g:link controller="manage" action="show" id="${m.id}">${m.name}</g:link></td>
                <td>${i + 1}</td>
                <td><g:formatDate date="${m.lastBreakfast}"/></td>
                <td>
                    <g:if test="${m.active}">
                        <i class="fa fa-check"></i>
                    </g:if>
                </td>
                <td>
                    <g:link controller="manage" action="edit" id="${m.id}" class="btn btn-info"><i class="fa fa-edit" title="${message(code: 'default.button.edit.label')}"></i></g:link>
                    <g:link controller="manage" action="toggle" id="${m.id}" class="btn btn-info">
                        <i class="fa fa-ban" title="${message(code: 'default.button.disable.label')}"></i>
                    </g:link>
                    <g:link controller="manage" action="delete" id="${m.id}" class="btn btn-default" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-trash-o fa-inverse" title="${message(code: 'default.button.delete.label')}"></i></g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <g:paginate total="${total}"  max="${grailsApplication.config.ub.paginate.items.perPage}"/>
</g:else>

</body>
</html>
