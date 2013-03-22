package com.vaadin.tutorial.addressbook.view;

import com.vaadin.data.Item;

public interface ContactEditor extends View<ContactEditor.Listener>
{
    public interface Listener
    {
        /**
         * Removes the selected contact.
         */
        void removeSelectedContact();
    }

    /**
     * Sets the selected contact.
     * 
     * @param contact
     *            Selected contact
     */
    void setSelectedContact(Item contact);
}
