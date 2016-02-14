package es.agustruiz.pollenalert.ui.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.agustruiz.pollenalert.R;

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.tvDeveloperEmail)
    TextView developerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);/**/
        developerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // send email as plain text
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.about_developer_email)});
                intent.putExtra(Intent.EXTRA_SUBJECT, "[PollenApp] ");
                startActivity(Intent.createChooser(intent, ""));
            }
        });/**/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
