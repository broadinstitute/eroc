<%@ page import="org.broadinstitute.eln.ApplicationSharedValues" %>



<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'earliestDateConsidered', 'error')} required">
	<label for="earliestDateConsidered">
		<g:message code="applicationSharedValues.earliestDateConsidered.label" default="Earliest Date Considered" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="earliestDateConsidered" required="" value="${applicationSharedValuesInstance?.earliestDateConsidered}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'daysAllowedForSigning', 'error')} required">
	<label for="daysAllowedForSigning">
		<g:message code="applicationSharedValues.daysAllowedForSigning.label" default="Days Allowed For Signing" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="daysAllowedForSigning" min="0" required="" value="${fieldValue(bean: applicationSharedValuesInstance, field: 'daysAllowedForSigning')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'daysAllowedForCountersigning', 'error')} required">
	<label for="daysAllowedForCountersigning">
		<g:message code="applicationSharedValues.daysAllowedForCountersigning.label" default="Days Allowed For Countersigning" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="daysAllowedForCountersigning" min="0" required="" value="${fieldValue(bean: applicationSharedValuesInstance, field: 'daysAllowedForCountersigning')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'sendEmailToIndividuals', 'error')} ">
	<label for="sendEmailToIndividuals">
		<g:message code="applicationSharedValues.sendEmailToIndividuals.label" default="Send Email To Individuals" />
		
	</label>
	<g:checkBox name="sendEmailToIndividuals" value="${applicationSharedValuesInstance?.sendEmailToIndividuals}" />
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'sendEmailToManagers', 'error')} ">
	<label for="sendEmailToManagers">
		<g:message code="applicationSharedValues.sendEmailToManagers.label" default="Send Email To Managers" />
		
	</label>
	<g:checkBox name="sendEmailToManagers" value="${applicationSharedValuesInstance?.sendEmailToManagers}" />
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'pathwayToStoreErocForIndividuals', 'error')} required">
	<label for="pathwayToStoreErocForIndividuals">
		<g:message code="applicationSharedValues.pathwayToStoreErocForIndividuals.label" default="Pathway To Store Eroc For Individuals" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pathwayToStoreErocForIndividuals" required="" value="${applicationSharedValuesInstance?.pathwayToStoreErocForIndividuals}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'pathwayToStoreErocForManagers', 'error')} required">
	<label for="pathwayToStoreErocForManagers">
		<g:message code="applicationSharedValues.pathwayToStoreErocForManagers.label" default="Pathway To Store Eroc For Managers" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pathwayToStoreErocForManagers" required="" value="${applicationSharedValuesInstance?.pathwayToStoreErocForManagers}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'daysBeforeIndividualWarning', 'error')} required">
	<label for="daysBeforeIndividualWarning">
		<g:message code="applicationSharedValues.daysBeforeIndividualWarning.label" default="Days Before Individual Warning" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="daysBeforeIndividualWarning" required="" value="${fieldValue(bean: applicationSharedValuesInstance, field: 'daysBeforeIndividualWarning')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'erocAdministratorEmailAddress', 'error')} ">
	<label for="erocAdministratorEmailAddress">
		<g:message code="applicationSharedValues.erocAdministratorEmailAddress.label" default="Eroc Administrator Email Address" />
		
	</label>
	<g:textField name="erocAdministratorEmailAddress" value="${applicationSharedValuesInstance?.erocAdministratorEmailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'pilotsToReceiveIndividualEmail', 'error')} required">
	<label for="pilotsToReceiveIndividualEmail">
		<g:message code="applicationSharedValues.pilotsToReceiveIndividualEmail.label" default="Pilots To Receive Individual Email" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'pilotsToReceiveManagerEmail', 'error')} required">
	<label for="pilotsToReceiveManagerEmail">
		<g:message code="applicationSharedValues.pilotsToReceiveManagerEmail.label" default="Pilots To Receive Manager Email" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'timeIntervalOverWhichUsersAreNotified', 'error')} required">
	<label for="timeIntervalOverWhichUsersAreNotified">
		<g:message code="applicationSharedValues.timeIntervalOverWhichUsersAreNotified.label" default="Time Interval Over Which Users Are Notified" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="timeIntervalOverWhichUsersAreNotified" required="" value="${fieldValue(bean: applicationSharedValuesInstance, field: 'timeIntervalOverWhichUsersAreNotified')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'writeArchiveFilesForIndividuals', 'error')} ">
	<label for="writeArchiveFilesForIndividuals">
		<g:message code="applicationSharedValues.writeArchiveFilesForIndividuals.label" default="Write Archive Files For Individuals" />
		
	</label>
	<g:checkBox name="writeArchiveFilesForIndividuals" value="${applicationSharedValuesInstance?.writeArchiveFilesForIndividuals}" />
</div>

<div class="fieldcontain ${hasErrors(bean: applicationSharedValuesInstance, field: 'writeArchiveFilesForManagers', 'error')} ">
	<label for="writeArchiveFilesForManagers">
		<g:message code="applicationSharedValues.writeArchiveFilesForManagers.label" default="Write Archive Files For Managers" />
		
	</label>
	<g:checkBox name="writeArchiveFilesForManagers" value="${applicationSharedValuesInstance?.writeArchiveFilesForManagers}" />
</div>

