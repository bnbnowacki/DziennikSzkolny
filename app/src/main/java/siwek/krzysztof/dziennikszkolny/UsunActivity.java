package siwek.krzysztof.dziennikszkolny;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class UsunActivity extends ActionBarActivity {
    EditText editNazwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usun);
        editNazwa =(EditText)findViewById(R.id.editNazwa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usun, menu);
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

    public void ClickUsunUsun(View view) {
        DbHelper dbHelper=new DbHelper(getApplicationContext());
        String nazwa = editNazwa.getText().toString();
            dbHelper.UsunUcznia(dbHelper, nazwa);
            Toast.makeText(getApplicationContext(), "Usunięto ucznia: " + nazwa, Toast.LENGTH_SHORT).show();
        finish();
    }
}
