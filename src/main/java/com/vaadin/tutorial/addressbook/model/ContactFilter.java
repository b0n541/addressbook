package com.vaadin.tutorial.addressbook.model;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;

/*
 * A custom filter for searching names and companies in the
 * contactContainer.
 */
public class ContactFilter implements Filter {

	private static final long serialVersionUID = 1L;

	private static final String FNAME = "First Name";
	private static final String LNAME = "Last Name";
	private static final String COMPANY = "Company";

	private String needle;

	public ContactFilter(String needle) {
		this.needle = needle.toLowerCase();
	}

	@Override
	public boolean passesFilter(Object itemId, Item item) {
		String haystack = ("" + item.getItemProperty(FNAME).getValue()
				+ item.getItemProperty(LNAME).getValue() + item
				.getItemProperty(COMPANY).getValue()).toLowerCase();
		return haystack.contains(needle);
	}

	@Override
	public boolean appliesToProperty(Object id) {
		return true;
	}
}
