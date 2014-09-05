<%@ page import="org.broadinstitute.eln.ComplyProcess" %>



<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="complyProcess.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${complyProcessInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'mailRecipients', 'error')} required">
	<label for="mailRecipients">
		<g:message code="complyProcess.mailRecipients.label" default="Mail Recipients" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'mailSubjectLine', 'error')} ">
	<label for="mailSubjectLine">
		<g:message code="complyProcess.mailSubjectLine.label" default="Mail Subject Line" />
		
	</label>
	<g:textField name="mailSubjectLine" value="${complyProcessInstance?.mailSubjectLine}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'statesOfInterest', 'error')} required">
	<label for="statesOfInterest">
		<g:message code="complyProcess.statesOfInterest.label" default="States Of Interest" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'referencedSubgroups', 'error')} required">
	<label for="referencedSubgroups">
		<g:message code="complyProcess.referencedSubgroups.label" default="Referenced Subgroups" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'earliestDateOfInterest', 'error')} required">
	<label for="earliestDateOfInterest">
		<g:message code="complyProcess.earliestDateOfInterest.label" default="Earliest Date Of Interest" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="earliestDateOfInterest" required="" value="${complyProcessInstance?.earliestDateOfInterest}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: complyProcessInstance, field: 'daysAllowedForSigning', 'error')} required">
	<label for="daysAllowedForSigning">
		<g:message code="complyProcess.daysAllowedForSigning.label" default="Days Allowed For Signing" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="daysAllowedForSigning" min="0" required="" value="${fieldValue(bean: complyProcessInstance, field: 'daysAllowedForSigning')}"/>
</div>

