package siwek.krzysztof.dziennikszkolny;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DodajActivity extends ActionBarActivity {
    EditText editNazwa, editData, editPesel, editMiejsce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        editNazwa=(EditText)findViewById(R.id.editNazwa);
        editData=(EditText)findViewById(R.id.editData);
        editPesel=(EditText)findViewById(R.id.editPesel);
        editMiejsce=(EditText)findViewById(R.id.editMiejsce);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dodaj, menu);
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

    public void ClickDodajDodaj(View view) {
        String nazwa = editNazwa.getText().toString();
        String data = editData.getText().toString();
        long pesel = Long.parseLong(editPesel.getText().toString());
        String miejsce = editMiejsce.getText().toString();

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        dbHelper.DodajUcznia(dbHelper ,nazwa, data, pesel, miejsce);
        Toast.makeText(getApplicationContext(), "Stworzono ucznia: "+nazwa, Toast.LENGTH_SHORT).show();
        finish();
    }
}
