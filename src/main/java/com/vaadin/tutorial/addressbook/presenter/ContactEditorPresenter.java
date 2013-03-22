package com.vaadin.tutorial.addressbook.presenter;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.tutorial.addressbook.event.ContactSelectEvent;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;
import com.vaadin.tutorial.addressbook.view.ContactEditor;

public class ContactEditorPresenter implements ContactEditor.Listener
{
    private final ContactEditor    view;
    private final AddressbookModel model;
    private final EventBus         eventBus;

    public ContactEditorPresenter(ContactEditor view, AddressbookModel model, EventBus eventBus)
    {
        this.view = view;
        view.register(this);

        this.model = model;

        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void onContactSelected(ContactSelectEvent event)
    {
        view.setSelectedContact(event.contact);
    }
}
