// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.example.suninatas25;

import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;

public class Suninatas25 extends Activity
{

    public Suninatas25()
    {
    }

    public String getContacts(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Cursor cursor = getContentResolver().query(android.provider.ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        do
        {
            String s1;
            String s2;
            do
            {
                if(!cursor.moveToNext())
                    return stringbuffer.toString();
                s1 = cursor.getString(cursor.getColumnIndex("display_name"));
                s2 = cursor.getString(cursor.getColumnIndex("_id"));
            } while(!s1.equals("SuNiNaTaS"));
            if(s.equals("sb"))
                stringbuffer.append(s1);
            else
            if(s.equals("id"))
                stringbuffer.append(s2);
        } while(true);
    }

    public String getTel(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        s = getContentResolver().query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, (new StringBuilder("contact_id=")).append(s).toString(), null, null);
        do
        {
            if(!s.moveToNext())
                return stringbuffer.toString();
            stringbuffer.append(s.getString(s.getColumnIndex("data1")));
        } while(true);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        ((Button)findViewById(0x7f070002)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view = (EditText)findViewById(0x7f070000);
                Object obj = (EditText)findViewById(0x7f070001);
                view = view.getText();
                obj = ((EditText) (obj)).getText();
                String s = getContacts("sb");
                String s1;
                try
                {
                    s1 = getContacts("id");
                    s1 = getTel(s1);
                }
                // Misplaced declaration of an exception variable
                catch(View view)
                {
                    (new android.app.AlertDialog.Builder(Suninatas25.this)).setMessage("Wrong!").setNeutralButton("Close", new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.dismiss();
                        }

                        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                    }
).show();
                    return;
                }
                if(s == null)
                    break MISSING_BLOCK_LABEL_153;
                view = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://www.suninatas.com/Part_one/web25/chk_key.asp?id=")).append(view.toString()).append("&pw=").append(((Editable) (obj)).toString()).append("&Name=").append(s.toString()).append("&Number=").append(s1.toString()).toString()));
                startActivity(view);
            }

            final Suninatas25 this$0;

            
            {
                this$0 = Suninatas25.this;
                super();
            }
        }
);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(0x7f060000, menu);
        return true;
    }
}
