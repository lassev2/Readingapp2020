package labs.android2020.readingapp2020.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import labs.android2020.readingapp2020.R;


public class AddMovieActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "labs.android2020.Readingapp2020.EXTRA_ID";
    public static final String EXTRA_NAME =
            "labs.android2020.Readingapp2020.EXTRA_NAME";
    public static final String EXTRA_PRODUCER =
            "labs.android2020.Readingapp2020.EXTRA_PRODUCER";
    public static final String EXTRA_YEAR =
            "labs.android2020.Readingapp2020.EXTRA_YEAR";
    public static final String EXTRA_COUNTRY =
            "labs.android2020.Readingapp2020.EXTRA_COUNTRY";

    private EditText editTextName;
    private EditText editTextProducer;
    private EditText editTextYear;
    private EditText editTextCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        editTextName = findViewById(R.id.edit_text_name);
        editTextProducer = findViewById(R.id.edit_text_producer);
        editTextYear = findViewById(R.id.edit_text_year);
        editTextCountry = findViewById(R.id.edit_text_country);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Movie");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextProducer.setText(intent.getStringExtra(EXTRA_PRODUCER));
            editTextYear.setText(intent.getStringExtra(EXTRA_YEAR));
            editTextCountry.setText(intent.getStringExtra(EXTRA_COUNTRY));
        } else {
            setTitle("Add Movie");
        }
    }

    private void saveMovie() {
        String name = editTextName.getText().toString();
        String producer = editTextProducer.getText().toString();
        String year = editTextYear.getText().toString();
        String country = editTextCountry.getText().toString();

        if (name.trim().isEmpty() || producer.trim().isEmpty() || year.trim().isEmpty() || country.trim().isEmpty()) {
            Toast.makeText(this, "All fields are not fulfilled. Please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_PRODUCER, producer);
        data.putExtra(EXTRA_YEAR, year);
        data.putExtra(EXTRA_COUNTRY, country);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_movie:
                saveMovie();
                return true;
            case R.id.close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
