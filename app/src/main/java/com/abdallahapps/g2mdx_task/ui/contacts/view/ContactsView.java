package com.abdallahapps.g2mdx_task.ui.contacts.view;

import com.abdallahapps.g2mdx_task.model.data.dto.Contact;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseView;

import java.util.List;

public interface ContactsView extends BaseView {

    void onSuccessLoadContacts(List<Contact> contacts);
}
