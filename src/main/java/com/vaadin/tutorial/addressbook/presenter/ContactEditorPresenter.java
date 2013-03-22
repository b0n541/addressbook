package com.vaadin.tutorial.addressbook.presenter;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;
import com.vaadin.tutorial.addressbook.view.ContactEditor;

public class ContactEditorPresenter
{
    private final ContactEditor    view;
    private final AddressbookModel model;
    private final EventBus         eventBus;

    public ContactEditorPresenter(ContactEditor view, AddressbookModel model, EventBus eventBus)
    {
        this.view = view;

        this.model = model;

        this.eventBus = eventBus;
        eventBus.register(this);
    }
}
