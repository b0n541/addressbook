package com.vaadin.tutorial.addressbook.view;

import com.vaadin.tutorial.addressbook.event.ContactSelectEvent;

public interface ContactEditor extends View<ContactEditor.Listener>
{
    public interface Listener
    {

    }

    public void handleContactSelectEvent(ContactSelectEvent event);
}
