package com.vaadin.tutorial.addressbook.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ContactEditorImpl extends FormLayout implements ContactEditor
{
    private static final long                  serialVersionUID    = 1L;

    private final List<ContactEditor.Listener> listeners           = new ArrayList<ContactEditor.Listener>();

    private final Button                       removeContactButton = new Button("Remove this contact");
    private final FieldGroup                   editorFields        = new FieldGroup();

    private static final String                FNAME               = "First Name";
    private static final String                LNAME               = "Last Name";
    private static final String                COMPANY             = "Company";
    private static final String[]              fieldNames          = new String[]
                                                                   { FNAME, LNAME, COMPANY, "Mobile Phone",
            "Work Phone", "Home Phone", "Work Email", "Home Email", "Street", "City", "Zip", "State",
            "Country"                                             };

    public ContactEditorImpl()
    {
        initLayout();
        initButtonAction();
    }

    @Override
    public void register(ContactEditor.Listener listener)
    {
        listeners.add(listener);
    }

    @Override
    public void setSelectedContact(Item contact)
    {
        editorFields.setItemDataSource(contact);
        setVisible(true);
    }

    private void initLayout()
    {
        addComponent(removeContactButton);

        /* User interface can be created dynamically to reflect underlying data. */
        for (String fieldName : fieldNames)
        {
            TextField field = new TextField(fieldName);
            addComponent(field);
            field.setWidth("100%");

            /*
             * We use a FieldGroup to connect multiple components to a data
             * source at once.
             */
            editorFields.bind(field, fieldName);
        }

        /*
         * Data can be buffered in the user interface. When doing so, commit()
         * writes the changes to the data source. Here we choose to write the
         * changes automatically without calling commit().
         */
        editorFields.setBuffered(false);

        /* Put a little margin around the fields in the right side editor */
        setMargin(true);
        setVisible(false);
    }

    private void initButtonAction()
    {
        removeContactButton.addClickListener(new ClickListener()
        {
            @Override
            public void buttonClick(ClickEvent event)
            {
                for (ContactEditor.Listener listener : listeners)
                {
                    listener.removeSelectedContact();
                }
            }
        });
    }
}
