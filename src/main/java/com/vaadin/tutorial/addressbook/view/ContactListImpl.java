package com.vaadin.tutorial.addressbook.view;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.tutorial.addressbook.event.AddNewContactEvent;
import com.vaadin.tutorial.addressbook.event.ContactFilterEvent;
import com.vaadin.tutorial.addressbook.event.ContactSelectEvent;
import com.vaadin.tutorial.addressbook.event.InitContactListEvent;
import com.vaadin.tutorial.addressbook.event.NewContactAddedEvent;
import com.vaadin.tutorial.addressbook.event.RemoveSelectedContactEvent;
import com.vaadin.tutorial.addressbook.model.ContactFilter;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ContactListImpl extends VerticalLayout implements ContactList {

	private static final long serialVersionUID = 1L;

	private static final String FNAME = "First Name";
	private static final String LNAME = "Last Name";
	private static final String COMPANY = "Company";

	private final EventBus eventBus;

	private final Table contactList = new Table();
	private final TextField searchField = new TextField();
	private final Button addNewContactButton = new Button("New");

	public ContactListImpl(EventBus eventBus) {

		this.eventBus = eventBus;

		eventBus.register(this);

		initLayout();
		initSearch();
		initButtonAction();
	}

	@Override
	@Subscribe
	public void handleInitContactListEvent(InitContactListEvent event) {
		contactList.setContainerDataSource(event.contactList);
		contactList.setVisibleColumns(new String[] { FNAME, LNAME, COMPANY });
		contactList.setSelectable(true);
		contactList.setImmediate(true);

		contactList.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				Object contactId = contactList.getValue();

				/*
				 * When a contact is selected from the list, we want to show
				 * that in our editor on the right. This is nicely done by the
				 * FieldGroup that binds all the fields to the corresponding
				 * Properties in our contact at once.
				 */
				if (contactId != null)
					eventBus.post(new ContactSelectEvent(contactList
							.getItem(contactId)));
			}
		});
	}

	@Override
	@Subscribe
	public void handleNewContactAddedEvent(NewContactAddedEvent event) {
		/*
		 * Each Item has a set of Properties that hold values. Here we set a
		 * couple of those.
		 */
		contactList.getContainerProperty(event.contactId, FNAME)
				.setValue("New");
		contactList.getContainerProperty(event.contactId, LNAME).setValue(
				"Contact");

		/* Lets choose the newly created contact to edit it. */
		contactList.select(event.contactId);
	}

	@Override
	@Subscribe
	public void handleRemoveSelectedContactEvent(
			RemoveSelectedContactEvent event) {
		Object contactId = contactList.getValue();
		contactList.removeItem(contactId);
	}

	private void initLayout() {

		addComponent(contactList);

		HorizontalLayout bottomLeftLayout = new HorizontalLayout();
		bottomLeftLayout.addComponent(searchField);
		bottomLeftLayout.addComponent(addNewContactButton);

		/*
		 * On the left side, expand the size of the contactList so that it uses
		 * all the space left after from bottomLeftLayout
		 */
		setExpandRatio(contactList, 1);
		contactList.setSizeFull();

		/*
		 * In the bottomLeftLayout, searchField takes all the width there is
		 * after adding addNewContactButton. The height of the layout is defined
		 * by the tallest component.
		 */
		bottomLeftLayout.setWidth("100%");
		searchField.setWidth("100%");
		bottomLeftLayout.setExpandRatio(searchField, 1);
		bottomLeftLayout.setSpacing(true);

		addComponent(bottomLeftLayout);

		setMargin(true);
		setSpacing(true);

		/* Set the contents in the left of the split panel to use all the space */
		setSizeFull();
	}

	private void initSearch() {

		/*
		 * We want to show a subtle prompt in the search field. We could also
		 * set a caption that would be shown above the field or description to
		 * be shown in a tooltip.
		 */
		searchField.setInputPrompt("Search contacts");

		/*
		 * Granularity for sending events over the wire can be controlled. By
		 * default simple changes like writing a text in TextField are sent to
		 * server with the next Ajax call. You can set your component to be
		 * immediate to send the changes to server immediately after focus
		 * leaves the field. Here we choose to send the text over the wire as
		 * soon as user stops writing for a moment.
		 */
		searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		/*
		 * When the event happens, we handle it in the anonymous inner class.
		 * You may choose to use separate controllers (in MVC) or presenters (in
		 * MVP) instead. In the end, the preferred application architecture is
		 * up to you.
		 */
		searchField.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {

				/* Reset the filter for the contactContainer. */
				eventBus.post(new ContactFilterEvent(new ContactFilter(event
						.getText())));
			}
		});
	}

	private void initButtonAction() {
		addNewContactButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				eventBus.post(new AddNewContactEvent());
			}
		});
	}
}
