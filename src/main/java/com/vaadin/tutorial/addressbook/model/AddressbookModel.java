package com.vaadin.tutorial.addressbook.model;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.tutorial.addressbook.AddressbookUI;
import com.vaadin.tutorial.addressbook.event.NewContactAddedEvent;

/**
 * Data model for {@link AddressbookUI}
 */
public class AddressbookModel extends IndexedContainer
{

    private static final long     serialVersionUID = 1L;

    private final EventBus        eventBus;

    private static final String   FNAME            = "First Name";
    private static final String   LNAME            = "Last Name";
    private static final String   COMPANY          = "Company";
    private static final String[] fieldNames       = new String[]
                                                   { FNAME, LNAME, COMPANY, "Mobile Phone", "Work Phone",
            "Home Phone", "Work Email", "Home Email", "Street", "City", "Zip", "State", "Country" };

    public AddressbookModel(EventBus eventBus)
    {
        this.eventBus = eventBus;

        initData();
    }

    public void setContactFilter(ContactFilter filter)
    {
        removeAllContainerFilters();
        addContainerFilter(filter);
    }

    public void handleAddNewContactEvent()
    {
        /*
         * Rows in the Container data model are called Item. Here we add a new
         * row in the beginning of the list.
         */
        removeAllContainerFilters();
        Object contactId = addItemAt(0);

        eventBus.post(new NewContactAddedEvent(contactId));
    }

    private void initData()
    {
        for (String p : fieldNames)
        {
            addContainerProperty(p, String.class, "");
        }

        /* Create dummy data by randomly combining first and last names */
        String[] fnames =
        { "Peter", "Alice", "Joshua", "Mike", "Olivia", "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik",
                "Rene", "Lisa", "Marge" };
        String[] lnames =
        { "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates",
                "Rowling", "Barks", "Ross", "Schneider", "Tate" };
        for (int i = 0; i < 1000; i++)
        {
            Object id = addItem();
            getContainerProperty(id, FNAME).setValue(fnames[(int) (fnames.length * Math.random())]);
            getContainerProperty(id, LNAME).setValue(lnames[(int) (lnames.length * Math.random())]);
        }
    }
}
