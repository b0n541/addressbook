package com.vaadin.tutorial.addressbook.view;

public interface View<T extends Object>
{
    public void register(T listener);
}
