package com.vaadin.tutorial.addressbook.view;

import com.vaadin.tutorial.addressbook.event.InitContactListEvent;
import com.vaadin.tutorial.addressbook.event.NewContactAddedEvent;
import com.vaadin.tutorial.addressbook.event.RemoveSelectedContactEvent;

public interface ContactTree {

	public void handleInitContactListEvent(InitContactListEvent event);

	public void handleNewContactAddedEvent(NewContactAddedEvent event);

	public void handleRemoveSelectedContactEvent(
			RemoveSelectedContactEvent event);
}
