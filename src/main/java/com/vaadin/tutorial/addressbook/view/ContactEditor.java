package com.vaadin.tutorial.addressbook.view;

import com.vaadin.data.Item;

public interface ContactEditor extends View<ContactEditor.Listener>
{
    public interface Listener
    {

    }

    void setSelectedContact(Item contact);
}
