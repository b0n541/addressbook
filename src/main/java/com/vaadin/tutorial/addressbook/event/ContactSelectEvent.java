package com.vaadin.tutorial.addressbook.event;

import com.vaadin.data.Item;

public class ContactSelectEvent {

	public final Item contact;

	public ContactSelectEvent(Item contact) {
		this.contact = contact;
	}
}
