package es.agustruiz.pollenalert.ui.search;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class SearchActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        // TODO: call detail activity for clicked entry
        Toast.makeText(getApplicationContext(), "Pressed "+position, Toast.LENGTH_SHORT).show();
    }

    private void handleIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "handleIntent", Toast.LENGTH_SHORT).show();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query =
                    intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String queryStr) {
        // TODO Get a Cursor, prepare the ListAdapter and set it
        Toast.makeText(getApplicationContext(), "Query: "+queryStr, Toast.LENGTH_SHORT).show();
    }
}
