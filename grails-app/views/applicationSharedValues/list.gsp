
<%@ page import="org.broadinstitute.eln.ApplicationSharedValues" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'applicationSharedValues.label', default: 'ApplicationSharedValues')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-applicationSharedValues" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-applicationSharedValues" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="earliestDateConsidered" title="${message(code: 'applicationSharedValues.earliestDateConsidered.label', default: 'Earliest Date Considered')}" />
					
						<g:sortableColumn property="daysAllowedForSigning" title="${message(code: 'applicationSharedValues.daysAllowedForSigning.label', default: 'Days Allowed For Signing')}" />
					
						<g:sortableColumn property="daysAllowedForCountersigning" title="${message(code: 'applicationSharedValues.daysAllowedForCountersigning.label', default: 'Days Allowed For Countersigning')}" />
					
						<g:sortableColumn property="sendEmailToIndividuals" title="${message(code: 'applicationSharedValues.sendEmailToIndividuals.label', default: 'Send Email To Individuals')}" />
					
						<g:sortableColumn property="sendEmailToManagers" title="${message(code: 'applicationSharedValues.sendEmailToManagers.label', default: 'Send Email To Managers')}" />
					
						<g:sortableColumn property="pathwayToStoreErocForIndividuals" title="${message(code: 'applicationSharedValues.pathwayToStoreErocForIndividuals.label', default: 'Pathway To Store Eroc For Individuals')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${applicationSharedValuesInstanceList}" status="i" var="applicationSharedValuesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${applicationSharedValuesInstance.id}">${fieldValue(bean: applicationSharedValuesInstance, field: "earliestDateConsidered")}</g:link></td>
					
						<td>${fieldValue(bean: applicationSharedValuesInstance, field: "daysAllowedForSigning")}</td>
					
						<td>${fieldValue(bean: applicationSharedValuesInstance, field: "daysAllowedForCountersigning")}</td>
					
						<td><g:formatBoolean boolean="${applicationSharedValuesInstance.sendEmailToIndividuals}" /></td>
					
						<td><g:formatBoolean boolean="${applicationSharedValuesInstance.sendEmailToManagers}" /></td>
					
						<td>${fieldValue(bean: applicationSharedValuesInstance, field: "pathwayToStoreErocForIndividuals")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${applicationSharedValuesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
