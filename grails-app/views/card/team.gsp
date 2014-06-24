<%@ page import="fr.exanpe.universal.breakfast.domain.WorkflowState" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="ub.card.team.title"/></title>
</head>
<body>

    <div class="section-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><g:message code="ub.card.team.title"/></h1>
                </div>
            </div>
        </div>
    </div>


    <div class="container container-int">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-xs-12 jumbotron">
                <h1>${team.teamName}</h1>

                <ul class="list-group">
                    <%-- today or later --%>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-calendar"></i>
                        <g:if test="${team.breakfastScheduledDate && team.breakfastScheduledDate >= new Date().clearTime()}">
                            <g:message code="ub.team.nextBreakfast"/>
                            <span class="pull-right">
                                <g:formatDate date="${team.breakfastScheduledDate}"/>
                            </span>
                        </g:if>
                        <g:else>
                            <g:message code="ub.team.noNextBreakfast"/>
                        </g:else>
                    </li>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-users"></i>
                        <g:message code="ub.team.members.active"/>
                        <span class="pull-right">
                            ${memberCount}
                        </span>
                    </li>
                    <g:if test="${team.breakfastCount}">
                        <li class="list-group-item">
                            <i class="fa fa-lg fa-list"></i>
                            <g:message code="ub.team.breakfastCount"/>
                            <span class="pull-right">
                                ${team.breakfastCount}
                            </span>
                        </li>
                    </g:if>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-calendar-o"></i>
                        <g:message code="ub.team.created"/>
                        <span class="pull-right">
                            <g:formatDate date="${team.dateCreated}"/>
                        </span>
                    </li>
                </ul>

            </div>
        </div>
    </div>

</body>
</html>
