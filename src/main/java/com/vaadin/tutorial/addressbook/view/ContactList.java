package com.vaadin.tutorial.addressbook.view;

import com.vaadin.tutorial.addressbook.event.InitContactListEvent;
import com.vaadin.tutorial.addressbook.event.NewContactAddedEvent;
import com.vaadin.tutorial.addressbook.event.RemoveSelectedContactEvent;

public interface ContactList extends View<ContactList.Listener>
{
    public interface Listener
    {

    }

    public void handleInitContactListEvent(InitContactListEvent event);

    public void handleNewContactAddedEvent(NewContactAddedEvent event);

    public void handleRemoveSelectedContactEvent(RemoveSelectedContactEvent event);
}
