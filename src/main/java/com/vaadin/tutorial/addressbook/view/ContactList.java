package com.vaadin.tutorial.addressbook.view;

import com.vaadin.data.Item;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;

public interface ContactList extends View<ContactList.Listener>
{
    public interface Listener
    {
        /**
         * A contact was selected in the contact list.
         * 
         * @param item
         *            Selected contact
         */
        public void contactSelected(Item item);

        /**
         * The search string was changed.
         * 
         * @param text
         *            New search string
         */
        public void searchStringChanged(String text);

        /**
         * Adds new contact.
         */
        public void addNewContact();
    }

    /**
     * Sets the data model for the contact list.
     * 
     * @param model
     *            Data model
     */
    public void setDataModel(AddressbookModel model);

    /**
     * Gets the selected contact.
     */
    public void removeSelectedContact();

    /**
     * Adds a new contact.
     * 
     * @param object
     *            New contact
     */
    void addNewContact(Object object);
}
