<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title><g:message code="ub.planning.title"/></title>
</head>
<body>

<div class="single">
    <div class="section-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><g:message code="ub.planning.title"/></h1>
                </div>
            </div>
        </div>
    </div>

    <div class="content container container-int">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2">

                <g:form class="form form-horizontal" action="index">
                    <div class="form-group">
                        <label for="teamName" class="control-label col-xs-4">
                            <ub:required>
                                <g:message code="ub.planning.teamName.label"/>
                            </ub:required>
                        </label>
                        <div class="col-xs-4">
                            <g:textField id="teamName" name="teamName" class="form-control" value="${params.teamName}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-4 col-xs-2">
                            <button type="submit" class="btn btn-primary"><g:message code="ub.planning.button" /> </button>
                        </div>
                    </div>
                </g:form>

                <g:if test="${team}">
                    <blockquote>
                        <p>
                            <g:message code="ub.planning.team.label"/> :

                            <g:if test="${team.configuration.cardEnabled}">
                                <g:link uri="/card/${team.teamName}">${team.teamName}</g:link>
                            </g:if>
                            <g:else>
                                ${team.teamName}
                            </g:else>

                            <br/>
                        </p>
                    </blockquote>
                    <blockquote>
                        <p>
                            <g:if test="${team.breakfastScheduledDate && team.breakfastScheduledDate == new Date().clearTime()}">
                                <g:message code="ub.planning.scheduled.today"/>
                            </g:if>
                            <g:elseif test="${team.breakfastScheduledDate && team.breakfastScheduledDate > new Date().clearTime()}">
                                <g:message code="ub.planning.scheduled.info" args="[g.formatDate(date : team.breakfastScheduledDate)]"/>
                            </g:elseif>
                            <g:else>
                                <g:message code="ub.planning.no.scheduled"/>
                            </g:else>
                        </p>
                    </blockquote>

                    <ul class="list-group">
                        <g:each in="${members}" var="member" status="i">
                            <li class="list-group-item member-active-${member.active} member-preparing-${member.preparing}">
                                <span class="badge" style="float:none">${i+1}</span>

                                <g:if test="${team.configuration.cardEnabled}">
                                    <g:link uri="/card/${team.teamName}/${member.name}">
                                        ${member.name}
                                    </g:link>
                                </g:if>
                                <g:else>
                                    ${member.name}
                                </g:else>
                            </li>
                        </g:each>
                    </ul>
                </g:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
