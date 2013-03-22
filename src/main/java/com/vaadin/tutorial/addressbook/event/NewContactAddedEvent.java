package com.vaadin.tutorial.addressbook.event;

public class NewContactAddedEvent {

	public final Object contactId;

	public NewContactAddedEvent(Object contactId) {
		this.contactId = contactId;
	}
}
