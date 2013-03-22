package com.vaadin.tutorial.addressbook.presenter;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.data.Item;
import com.vaadin.tutorial.addressbook.event.ContactFilter;
import com.vaadin.tutorial.addressbook.event.ContactSelectEvent;
import com.vaadin.tutorial.addressbook.event.InitContactListEvent;
import com.vaadin.tutorial.addressbook.event.RemoveSelectedContactEvent;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;
import com.vaadin.tutorial.addressbook.view.ContactList;

public class ContactListPresenter implements ContactList.Listener
{
    private final ContactList      view;
    private final AddressbookModel model;
    private final EventBus         eventBus;

    public ContactListPresenter(ContactList view, AddressbookModel model, EventBus eventBus)
    {
        this.view = view;
        view.register(this);

        this.model = model;

        this.eventBus = eventBus;
        eventBus.register(this);
    }

    @Subscribe
    public void onInitContactList(InitContactListEvent event)
    {
        view.setDataModel(model);
    }

    @Subscribe
    public void onRemoveSelectedContact(RemoveSelectedContactEvent event)
    {
        view.removeSelectedContact();
    }

    @Override
    public void contactSelected(Item contact)
    {
        eventBus.post(new ContactSelectEvent(contact));
    }

    @Override
    public void searchStringChanged(String text)
    {
        model.setContactFilter(new ContactFilter(text));
    }

    @Override
    public void addNewContact()
    {
        model.removeAllContainerFilters();
        Object contact = model.addItemAt(0);

        view.addNewContact(contact);
    }
}
