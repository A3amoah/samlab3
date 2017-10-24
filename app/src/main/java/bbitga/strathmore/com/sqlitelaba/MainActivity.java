package bbitga.strathmore.com.sqlitelaba;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHandler db = new DatabaseHandler(this);
        // inserting contacts
        Log.d("Insert:","Inserting..");
        db.addContact(new Contact(" kevin"," 54646464646464"));
        db.addContact(new Contact(" collins"," 6557567777777"));
        db.addContact(new Contact(" winny"," 3442424242424242"));
        db.addContact(new Contact(" hillary"," 46758388383838"));
        //reading all contacts
        Log.d("Reading:","Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id:" + cn.get_id() + ",Name:" + cn.get_name() + ",Phone:" + cn.get_phone_number();
            // writing contacts to log
            Log.i("Name:", log);
        }



        Log.d("Insert:","Inserting..");
        db.addUnit(new Unit(" api"," 678383873789"));
        db.addUnit(new Unit(" mad"," 3443494948494"));
        db.addUnit(new Unit(" ethics"," 2883938383983"));
        db.addUnit(new Unit(" research"," 723939273932938392"));
        //reading all contacts
        Log.d("Reading:","Reading all contacts..");
        List<Unit> unit = db.getAllUnit();

        for (Unit un : unit) {
            String log = "Uid:" + un.get_uid() + ",Name:" + un.get_uname() + ",Phone:" + un.get_ucode();
            // writing contacts to log
            Log.i("Name:", log);
        }


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
           // }
        //});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
