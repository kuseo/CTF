// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package suninatas.project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;

public class Suninatas extends Activity
{

    public Suninatas()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        ((Button)findViewById(0x7f070003)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Object obj = (EditText)findViewById(0x7f070000);
                Object obj1 = (EditText)findViewById(0x7f070001);
                view = (EditText)findViewById(0x7f070002);
                obj = ((EditText) (obj)).getText();
                obj1 = ((EditText) (obj1)).getText();
                view = view.getText();
                if(view.toString().equals("WE1C0mEToandr01d".toString()))
                {
                    view = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://www.suninatas.com/Part_one/web24/chk_key.asp?id=")).append(((Editable) (obj)).toString()).append("&pw=").append(((Editable) (obj1)).toString()).append("&key=").append(view.toString()).toString()));
                    startActivity(view);
                    return;
                } else
                {
                    (new android.app.AlertDialog.Builder(Suninatas.this)).setMessage("Wrong!").setNeutralButton("Close", new android.content.DialogInterface.OnClickListener() {

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
            }

            final Suninatas this$0;

            
            {
                this$0 = Suninatas.this;
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
