
<%@ page import="org.broadinstitute.eln.ComplyProcess" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'complyProcess.label', default: 'ComplyProcess')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-complyProcess" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-complyProcess" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list complyProcess">
			
				<g:if test="${complyProcessInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="complyProcess.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${complyProcessInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.mailRecipients}">
				<li class="fieldcontain">
					<span id="mailRecipients-label" class="property-label"><g:message code="complyProcess.mailRecipients.label" default="Mail Recipients" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.mailSubjectLine}">
				<li class="fieldcontain">
					<span id="mailSubjectLine-label" class="property-label"><g:message code="complyProcess.mailSubjectLine.label" default="Mail Subject Line" /></span>
					
						<span class="property-value" aria-labelledby="mailSubjectLine-label"><g:fieldValue bean="${complyProcessInstance}" field="mailSubjectLine"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.statesOfInterest}">
				<li class="fieldcontain">
					<span id="statesOfInterest-label" class="property-label"><g:message code="complyProcess.statesOfInterest.label" default="States Of Interest" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.referencedSubgroups}">
				<li class="fieldcontain">
					<span id="referencedSubgroups-label" class="property-label"><g:message code="complyProcess.referencedSubgroups.label" default="Referenced Subgroups" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.earliestDateOfInterest}">
				<li class="fieldcontain">
					<span id="earliestDateOfInterest-label" class="property-label"><g:message code="complyProcess.earliestDateOfInterest.label" default="Earliest Date Of Interest" /></span>
					
						<span class="property-value" aria-labelledby="earliestDateOfInterest-label"><g:fieldValue bean="${complyProcessInstance}" field="earliestDateOfInterest"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${complyProcessInstance?.daysAllowedForSigning}">
				<li class="fieldcontain">
					<span id="daysAllowedForSigning-label" class="property-label"><g:message code="complyProcess.daysAllowedForSigning.label" default="Days Allowed For Signing" /></span>
					
						<span class="property-value" aria-labelledby="daysAllowedForSigning-label"><g:fieldValue bean="${complyProcessInstance}" field="daysAllowedForSigning"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${complyProcessInstance?.id}" />
					<g:link class="edit" action="edit" id="${complyProcessInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
