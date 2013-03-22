package com.vaadin.tutorial.addressbook.event;

import com.vaadin.data.util.IndexedContainer;

public class InitContactListEvent {

	public final IndexedContainer contactList;

	public InitContactListEvent(IndexedContainer contactList) {
		this.contactList = contactList;
	}
}
