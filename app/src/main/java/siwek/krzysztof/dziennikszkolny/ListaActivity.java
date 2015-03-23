package siwek.krzysztof.dziennikszkolny;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListaActivity extends ActionBarActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView=(ListView)findViewById(R.id.listView);
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        Cursor c= dbHelper.WyswietlUczniow(dbHelper);
        String kolumny[] = {DbHelper.COLUMN_NAME_NAZWA};
        int widoki[] = {R.id.textView};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, c, kolumny, widoki, 0);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor)parent.getItemAtPosition(position);
                Bufor.nazwa=c.getString(1);
                Bufor.data=c.getString(2);
                Bufor.pesel=c.getString(3);
                Bufor.miejsce=c.getString(4);
                Intent intent = new Intent(ListaActivity.this, DetaleActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
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
