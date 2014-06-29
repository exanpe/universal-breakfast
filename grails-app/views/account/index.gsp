<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="back" />
    <title><g:message code="ub.account.title" /></title>
</head>
<body>

<blockquote>
    <p><g:message code="ub.account.help" /></p>
</blockquote>

    <h2 class="section-title"><g:message code="ub.account.settings.title" /> </h2>
    <div>

        <ub:errors obj="${command}"/>

        <g:form class="form-horizontal" controller="account" action="update">
            <div class="form-group">
                <label for="mail" class="control-label col-xs-4">
                    <ub:required>
                        <g:message code="ub.register.mail.label"/>
                    </ub:required>
                </label>
                <div class="col-xs-4">
                    <g:field type="email" name="mail" class="form-control" value="${params?.mail}" />
                </div>
            </div>
            <div class="form-group">
                <label for='password' class="control-label col-xs-4">
                    <ub:required>
                        <g:message code="ub.register.password.label"/>
                    </ub:required>
                </label>
                <div class="col-xs-4">
                    <g:passwordField name="password" class="form-control" value="${params?.password}" />
                </div>
            </div>
            <div class="form-group">
                <label for='password2' class="control-label col-xs-4">
                    <ub:required>
                        <g:message code="ub.register.confirm.password.label"/>
                    </ub:required>
                </label>
                <div class="col-xs-4">
                    <g:passwordField name="password2" class="form-control" value="${params?.password2}" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-4 col-xs-4">
                    <button type="submit" class="btn btn-primary"><g:message code="default.button.update.label" /> </button>
                </div>
            </div>
        </g:form>
    </div>

    <h2 class="section-title"><g:message code="ub.account.mail.title" /> </h2>
    <g:form class="form-horizontal mail" controller="account" action="mail">
        <div class="form-group">
            <label for='sendMail' class="control-label col-xs-4">
                <ub:required>
                    <g:message code="ub.account.mail.send.label"/>
                </ub:required>
            </label>
            <div class="col-xs-4">
                <g:checkBox id="sendMail" class="icb"  name="sendMail" value="${team.configuration.sendMail}" />
            </div>
        </div>
        <div id="mailCustomization">
            <div class="form-group">
                <div class="col-md-8 col-md-offset-4">
                    <ub:templateDescription template="prepare"/>
                </div>
            </div>
            <div class="form-group">
                <label for='prepareMailSubject' class="control-label col-md-4">
                    <g:message code="ub.account.mail.prepareSubject.label"/>
                </label>
                <div class="col-md-8">
                    <g:textField name="prepareMailSubject" id="prepareMailSubject" value="${team.configuration.prepareMailSubject}" class="col-xs-12 form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for='prepareMail' class="control-label col-md-4">
                    <g:message code="ub.account.mail.prepare.label"/>
                </label>
                <div class="col-md-8">
                    <g:textArea name="prepareMail" id="prepareMail" value="${team.configuration.prepareMail}" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-8 col-md-offset-4">
                    <ub:templateDescription template="gather"/>
                </div>
            </div>
            <div class="form-group">
                <label for='gatheringMailSubject' class="control-label col-md-4">
                    <g:message code="ub.account.mail.gatheringSubject.label"/>
                </label>
                <div class="col-md-8">
                    <g:textField name="gatheringMailSubject" id="gatheringMailSubject" value="${team.configuration.gatheringMailSubject}" class="col-xs-12 form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label for='gatheringMail' class="control-label col-md-4">
                    <g:message code="ub.account.mail.gathering.label"/>
                </label>
                <div class="col-md-8">
                    <g:textArea name="gatheringMail" id="gatheringMail" value="${team.configuration.gatheringMail}" class="form-control"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="default.button.update.label" /> </button>
            </div>
        </div>
    </g:form>

    <h2 class="section-title"><g:message code="ub.account.privacy.title" /> </h2>
    <g:form class="form-horizontal" controller="account" action="privacy">
        <div class="form-group">
            <label for='cardEnabled' class="control-label col-xs-4">
                <ub:required>
                    <g:message code="ub.account.privacy.card.label"/>
                </ub:required>
            </label>
            <div class="col-xs-4">
                <g:checkBox id="cardEnabled" class="icb"  name="cardEnabled" value="${team.configuration.cardEnabled}" />
            </div>
        </div>
        <div class="form-group">
            <label for='planningEnabled' class="control-label col-xs-4">
                <ub:required>
                    <g:message code="ub.account.privacy.planning.label"/>
                </ub:required>
            </label>
            <div class="col-xs-4">
                <g:checkBox id="planningEnabled" class="icb"  name="planningEnabled" value="${team.configuration.planningEnabled}" />
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button type="submit" class="btn btn-primary"><g:message code="default.button.update.label" /> </button>
            </div>
        </div>
    </g:form>

    <h2 class="section-title"><g:message code="ub.account.delete.title" /> </h2>
    <g:form class="form-horizontal" controller="account" action="delete">
        <div class="form-group">
            <div class="col-xs-offset-4 col-xs-4">
                <button id="delete-account" type="submit" class="btn btn-primary" data-toggle="modal" data-target="#delete-confirm"><g:message code="ub.account.delete.button" /> </button>
            </div>
        </div>
    </g:form>

    <div id="delete-confirm" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><g:message code="ub.account.delete.confirm.title" /> </h4>
            </div>
            <div class="modal-body">
                <g:message code="ub.account.delete.confirm.body.label" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal"><g:message code="ub.account.delete.confirm.no.label" /></button>
                <button type="button" id="confirm-delete-button" class="btn btn-primary"><g:message code="ub.account.delete.confirm.yes.label" /></button>
            </div>
        </div>
       </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function(){
            if(!$('#sendMail').is(":checked")){
                $('#mailCustomization').css('display', 'none')
            }

            $('#sendMail').on('ifChecked', function(event){
                $('#mailCustomization').slideDown();
            });
            $('#sendMail').on('ifUnchecked', function(event){
                $('#mailCustomization').slideUp();
            });

            $('#delete-account').click(function(e) {
                e.stopPropagation();
                $('#delete-confirm').modal('show');
                return false;
            });

            $('#confirm-delete-button').click(function(e) {
                $('#delete-account').closest('form').submit();
                return;
            });
        })
    </script>

</body>
</html>