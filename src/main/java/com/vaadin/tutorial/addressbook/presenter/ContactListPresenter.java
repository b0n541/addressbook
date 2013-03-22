package com.vaadin.tutorial.addressbook.presenter;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;
import com.vaadin.tutorial.addressbook.view.ContactList;

public class ContactListPresenter
{
    private final ContactList      view;
    private final AddressbookModel model;
    private final EventBus         eventBus;

    public ContactListPresenter(ContactList view, AddressbookModel model, EventBus eventBus)
    {
        this.view = view;

        this.model = model;

        this.eventBus = eventBus;
        eventBus.register(this);
    }
}
