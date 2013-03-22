package com.vaadin.tutorial.addressbook.event;


public class ContactFilterEvent {
	public final ContactFilter filter;

	public ContactFilterEvent(ContactFilter filter) {
		this.filter = filter;
	}
}
