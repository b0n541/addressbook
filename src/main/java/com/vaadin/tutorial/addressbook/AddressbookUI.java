package com.vaadin.tutorial.addressbook;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tutorial.addressbook.event.InitContactListEvent;
import com.vaadin.tutorial.addressbook.model.AddressbookModel;
import com.vaadin.tutorial.addressbook.view.ContactEditorImpl;
import com.vaadin.tutorial.addressbook.view.ContactTreeImpl;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;

/* 
 * UI class is the starting point for your app. You may deploy it with VaadinServlet
 * or VaadinPortlet by giving your UI class name a parameter. When you browse to your
 * app a web page showing your UI is automatically generated. Or you may choose to 
 * embed your UI to an existing web page. 
 */
@Title("Addressbook")
public class AddressbookUI extends UI {

	private static final long serialVersionUID = 1L;

	EventBus eventBus = new EventBus();

	/*
	 * Any component can be bound to an external data source. This example uses
	 * just a dummy in-memory list, but there are many more practical
	 * implementations.
	 */
	IndexedContainer contactContainer = new AddressbookModel(eventBus);

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(VaadinRequest request) {
		initLayout();
		eventBus.post(new InitContactListEvent(contactContainer));
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {

		/* Root of the user interface component tree is set */
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		setContent(splitPanel);

		/* Build the component tree */
		splitPanel.addComponent(new ContactTreeImpl(eventBus));
		splitPanel.addComponent(new ContactEditorImpl(eventBus));
	}
}
