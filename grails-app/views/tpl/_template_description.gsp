<div class="templateDescription">
<g:message code="ub.template.description.help"/>
<ul>
    <g:each in="${props}" var="prop">
        <li>${prop.templateKey} : ${g.message(code : prop.descriptionMsgCode)}</li>
    </g:each>
</ul>
</div>