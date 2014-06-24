<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="ub.card.member.title"/></title>
</head>
<body>

    <div class="section-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><g:message code="ub.card.member.title"/></h1>
                </div>
            </div>
        </div>
    </div>

    <div class="container container-int">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2 col-xs-12 jumbotron">
                <h1>${member.name}</h1>

                <ul class="list-group contact">
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-coffee"></i>
                        <g:message code="ub.member.team"/>
                        <span class="pull-right">
                           ${team.teamName}
                        </span>
                    </li>
                    <li class="list-group-item">
                        <g:if test="${member.active == true}">
                            <i class="fa fa-lg fa-thumbs-o-up"></i> <g:message code="ub.member.active"/>
                        </g:if>
                        <g:else>
                            <i class="fa fa-lg fa-ban"></i> <g:message code="ub.member.inactive"/>
                        </g:else>
                    </li>
                    <%-- today or later --%>
                    <g:if test="${member.preparing && team.breakfastScheduledDate >= new Date().clearTime()}">
                        <li class="list-group-item">
                            <i class="fa fa-lg fa-info-circle"></i>
                            <b>
                                <g:message code="ub.member.preparing"/>
                            </b>
                        </li>
                    </g:if>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-calendar"></i>
                        <g:if test="${member.lastBreakfast}">
                            <g:message code="ub.member.lastBreakfast"/>
                            <span class="pull-right">
                                <g:formatDate date="${member.lastBreakfast}"/>
                            </span>
                        </g:if>
                        <g:else>
                            <g:message code="ub.member.noBreakfast"/>
                        </g:else>
                    </li>
                    <g:if test="${member.lastBreakfast}">
                        <li class="list-group-item">
                            <i class="fa fa-lg fa-list"></i>
                            <g:message code="ub.member.breakfastCount"/>
                            <span class="pull-right">
                                ${member.breakfastCount}
                            </span>
                        </li>
                    </g:if>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-ellipsis-h"></i>
                        <g:message code="ub.member.breakfastAttended"/>
                        <span class="pull-right">
                            ${member.attendingCount}
                        </span>
                    </li>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-ellipsis-h"></i>
                        <g:message code="ub.member.breakfastMissed"/>
                        <span class="pull-right">
                            ${member.absenceCount}
                        </span>
                    </li>
                    <li class="list-group-item">
                        <i class="fa fa-lg fa-calendar-o"></i>
                        <g:message code="ub.member.since"/>
                        <span class="pull-right">
                            <g:formatDate date="${member.dateCreated}"/>
                        </span>
                    </li>
                </ul>

            </div>
        </div>
    </div>

</body>
</html>
