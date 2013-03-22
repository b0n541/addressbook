package com.vaadin.tutorial.addressbook.event;

import com.vaadin.tutorial.addressbook.model.ContactFilter;

public class ContactFilterEvent {
	public final ContactFilter filter;

	public ContactFilterEvent(ContactFilter filter) {
		this.filter = filter;
	}
}
