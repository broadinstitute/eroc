
<%@ page import="org.broadinstitute.eln.ApplicationSharedValues" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-applicationSharedValues" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-applicationSharedValues" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list applicationSharedValues">
			
				<g:if test="${applicationSharedValuesInstance?.earliestDateConsidered}">
				<li class="fieldcontain">
					<span id="earliestDateConsidered-label" class="property-label"><g:message code="applicationSharedValues.earliestDateConsidered.label" default="Earliest Date Considered" /></span>
					
						<span class="property-value" aria-labelledby="earliestDateConsidered-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="earliestDateConsidered"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.daysAllowedForSigning}">
				<li class="fieldcontain">
					<span id="daysAllowedForSigning-label" class="property-label"><g:message code="applicationSharedValues.daysAllowedForSigning.label" default="Days Allowed For Signing" /></span>
					
						<span class="property-value" aria-labelledby="daysAllowedForSigning-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="daysAllowedForSigning"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.daysAllowedForCountersigning}">
				<li class="fieldcontain">
					<span id="daysAllowedForCountersigning-label" class="property-label"><g:message code="applicationSharedValues.daysAllowedForCountersigning.label" default="Days Allowed For Countersigning" /></span>
					
						<span class="property-value" aria-labelledby="daysAllowedForCountersigning-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="daysAllowedForCountersigning"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.sendEmailToIndividuals}">
				<li class="fieldcontain">
					<span id="sendEmailToIndividuals-label" class="property-label"><g:message code="applicationSharedValues.sendEmailToIndividuals.label" default="Send Email To Individuals" /></span>
					
						<span class="property-value" aria-labelledby="sendEmailToIndividuals-label"><g:formatBoolean boolean="${applicationSharedValuesInstance?.sendEmailToIndividuals}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.sendEmailToManagers}">
				<li class="fieldcontain">
					<span id="sendEmailToManagers-label" class="property-label"><g:message code="applicationSharedValues.sendEmailToManagers.label" default="Send Email To Managers" /></span>
					
						<span class="property-value" aria-labelledby="sendEmailToManagers-label"><g:formatBoolean boolean="${applicationSharedValuesInstance?.sendEmailToManagers}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.pathwayToStoreErocForIndividuals}">
				<li class="fieldcontain">
					<span id="pathwayToStoreErocForIndividuals-label" class="property-label"><g:message code="applicationSharedValues.pathwayToStoreErocForIndividuals.label" default="Pathway To Store Eroc For Individuals" /></span>
					
						<span class="property-value" aria-labelledby="pathwayToStoreErocForIndividuals-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="pathwayToStoreErocForIndividuals"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.pathwayToStoreErocForManagers}">
				<li class="fieldcontain">
					<span id="pathwayToStoreErocForManagers-label" class="property-label"><g:message code="applicationSharedValues.pathwayToStoreErocForManagers.label" default="Pathway To Store Eroc For Managers" /></span>
					
						<span class="property-value" aria-labelledby="pathwayToStoreErocForManagers-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="pathwayToStoreErocForManagers"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.daysBeforeIndividualWarning}">
				<li class="fieldcontain">
					<span id="daysBeforeIndividualWarning-label" class="property-label"><g:message code="applicationSharedValues.daysBeforeIndividualWarning.label" default="Days Before Individual Warning" /></span>
					
						<span class="property-value" aria-labelledby="daysBeforeIndividualWarning-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="daysBeforeIndividualWarning"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.erocAdministratorEmailAddress}">
				<li class="fieldcontain">
					<span id="erocAdministratorEmailAddress-label" class="property-label"><g:message code="applicationSharedValues.erocAdministratorEmailAddress.label" default="Eroc Administrator Email Address" /></span>
					
						<span class="property-value" aria-labelledby="erocAdministratorEmailAddress-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="erocAdministratorEmailAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.pilotsToReceiveIndividualEmail}">
				<li class="fieldcontain">
					<span id="pilotsToReceiveIndividualEmail-label" class="property-label"><g:message code="applicationSharedValues.pilotsToReceiveIndividualEmail.label" default="Pilots To Receive Individual Email" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.pilotsToReceiveManagerEmail}">
				<li class="fieldcontain">
					<span id="pilotsToReceiveManagerEmail-label" class="property-label"><g:message code="applicationSharedValues.pilotsToReceiveManagerEmail.label" default="Pilots To Receive Manager Email" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.timeIntervalOverWhichUsersAreNotified}">
				<li class="fieldcontain">
					<span id="timeIntervalOverWhichUsersAreNotified-label" class="property-label"><g:message code="applicationSharedValues.timeIntervalOverWhichUsersAreNotified.label" default="Time Interval Over Which Users Are Notified" /></span>
					
						<span class="property-value" aria-labelledby="timeIntervalOverWhichUsersAreNotified-label"><g:fieldValue bean="${applicationSharedValuesInstance}" field="timeIntervalOverWhichUsersAreNotified"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.writeArchiveFilesForIndividuals}">
				<li class="fieldcontain">
					<span id="writeArchiveFilesForIndividuals-label" class="property-label"><g:message code="applicationSharedValues.writeArchiveFilesForIndividuals.label" default="Write Archive Files For Individuals" /></span>
					
						<span class="property-value" aria-labelledby="writeArchiveFilesForIndividuals-label"><g:formatBoolean boolean="${applicationSharedValuesInstance?.writeArchiveFilesForIndividuals}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationSharedValuesInstance?.writeArchiveFilesForManagers}">
				<li class="fieldcontain">
					<span id="writeArchiveFilesForManagers-label" class="property-label"><g:message code="applicationSharedValues.writeArchiveFilesForManagers.label" default="Write Archive Files For Managers" /></span>
					
						<span class="property-value" aria-labelledby="writeArchiveFilesForManagers-label"><g:formatBoolean boolean="${applicationSharedValuesInstance?.writeArchiveFilesForManagers}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${applicationSharedValuesInstance?.id}" />
					<g:link class="edit" action="edit" id="${applicationSharedValuesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link class="create" action="runIndividualReports" id="1">runIndividualReports</g:link>
                </fieldset>
			</g:form>


		</div>
	</body>
</html>
