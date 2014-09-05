
<%@ page import="org.broadinstitute.eln.ComplyProcess" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'complyProcess.label', default: 'ComplyProcess')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-complyProcess" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-complyProcess" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'complyProcess.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="mailRecipients" title="${message(code: 'complyProcess.mailRecipients.label', default: 'Mail Recipients')}" />
					
						<g:sortableColumn property="mailSubjectLine" title="${message(code: 'complyProcess.mailSubjectLine.label', default: 'Mail Subject Line')}" />
					
						<g:sortableColumn property="statesOfInterest" title="${message(code: 'complyProcess.statesOfInterest.label', default: 'States Of Interest')}" />
					
						<g:sortableColumn property="referencedSubgroups" title="${message(code: 'complyProcess.referencedSubgroups.label', default: 'Referenced Subgroups')}" />
					
						<g:sortableColumn property="earliestDateOfInterest" title="${message(code: 'complyProcess.earliestDateOfInterest.label', default: 'Earliest Date Of Interest')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${complyProcessInstanceList}" status="i" var="complyProcessInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${complyProcessInstance.id}">${fieldValue(bean: complyProcessInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: complyProcessInstance, field: "mailRecipients")}</td>
					
						<td>${fieldValue(bean: complyProcessInstance, field: "mailSubjectLine")}</td>
					
						<td>${fieldValue(bean: complyProcessInstance, field: "statesOfInterest")}</td>
					
						<td>${fieldValue(bean: complyProcessInstance, field: "referencedSubgroups")}</td>
					
						<td>${fieldValue(bean: complyProcessInstance, field: "earliestDateOfInterest")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${complyProcessInstanceTotal}" />
			</div>
            <g:form>
                <fieldset class="buttons">
                    <g:link class="create" action="runManagerReports" id="1">runManagerReports</g:link>
                </fieldset>
            </g:form>

		</div>
	</body>
</html>
