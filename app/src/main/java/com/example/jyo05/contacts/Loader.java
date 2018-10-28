package com.example.jyo05.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.icu.util.IslamicCalendar;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Oh, Joon young (speldipn)
 * @Since: 2018-10-29
 */
public class Loader {

    public List<Contact> getData(Context ctx) {
        List<Contact> list = new ArrayList<>();

        ContentResolver resolver = ctx.getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projections = {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor cursor = resolver.query(uri, projections, null, null, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                Contact contact = new Contact();
                int idx = cursor.getColumnIndex(projections[0]);
                contact.id = cursor.getString(idx);
                idx = cursor.getColumnIndex(projections[1]);
                contact.name = cursor.getString(idx);
                idx = cursor.getColumnIndex(projections[2]);
                contact.phone = cursor.getString(idx);
                list.add(contact);
            }
            cursor.close();
        }

        return list;
    }
}
