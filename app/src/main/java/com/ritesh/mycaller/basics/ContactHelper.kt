package com.ritesh.mycaller.basics

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import com.ritesh.mycaller.models.Contact

class ContactHelper {
    fun loadContacts(context: Context): Map<String, Contact> {
        val contactMap = mutableMapOf<String, Contact>()
        val contentResolver: ContentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            "${ContactsContract.Contacts.DISPLAY_NAME} ASC"
        )

        cursor?.use {
            val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val phoneNumberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val photoUriIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
            val starIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STARRED)

            while (it.moveToNext()) {
                val id = it.getString(idIndex)
                val name = it.getString(nameIndex) ?: "Unknown"
                var phoneNumber = it.getString(phoneNumberIndex) ?: "Unknown"
                val photoUri = it.getString(photoUriIndex)
                val isStarred = it.getInt(starIndex)

                val bitmap = photoUri?.let { uri ->
                    try {
                        val inputStream = contentResolver.openInputStream(android.net.Uri.parse(uri))
                        BitmapFactory.decodeStream(inputStream)
                    } catch (e: Exception) {
                        null
                    }
                }
                phoneNumber = phoneNumber.replace("\\s".toRegex(), "")
                contactMap[phoneNumber] = Contact(id,name, phoneNumber, bitmap, isStarred)
            }
        }

        return contactMap
    }

    fun markContactAsFavorite(context: Context, contactId: String, isFavorite: Boolean): Boolean {
        val contentResolver = context.contentResolver
        val uri = ContactsContract.Contacts.CONTENT_URI
        val values = ContentValues().apply {
            put(ContactsContract.Contacts.STARRED, if (isFavorite) 1 else 0)
        }

        val selection = "${ContactsContract.Contacts._ID} = ?"
        val selectionArgs = arrayOf(contactId)

        val rowsUpdated = contentResolver.update(uri, values, selection, selectionArgs)
        return rowsUpdated > 0
    }

    fun addContact(context: Context, name: String, phoneNumber: String): Boolean {
        return try {
            val contentResolver = context.contentResolver
            val contactValues = ContentValues().apply {
                put(ContactsContract.RawContacts.ACCOUNT_TYPE, "")
                put(ContactsContract.RawContacts.ACCOUNT_NAME, "")
            }

            val rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, contactValues)
            val rawContactId = rawContactUri?.lastPathSegment?.toLong() ?: return false

            // Insert Name
            val nameValues = ContentValues().apply {
                put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
            }
            val nameResult = contentResolver.insert(ContactsContract.Data.CONTENT_URI, nameValues) != null

            // Insert Phone Number
            val phoneValues = ContentValues().apply {
                put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
            }
            val phoneResult = contentResolver.insert(ContactsContract.Data.CONTENT_URI, phoneValues) != null

            return nameResult && phoneResult
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}