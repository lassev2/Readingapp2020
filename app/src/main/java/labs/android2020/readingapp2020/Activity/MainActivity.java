package labs.android2020.readingapp2020.Activity;
//https://firebase.google.com/docs/database/android/start
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import labs.android2020.readingapp2020.Fragments.DiscoverFragment;
import labs.android2020.readingapp2020.Fragments.ListsFragment;
import labs.android2020.readingapp2020.R;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 1995;



    List<AuthUI.IdpConfig> providers;

    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DiscoverFragment()).commit();
        //Init provider
        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
        showSignInOptions();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.discover_nav_item:
                            selectedFragment = new DiscoverFragment();
                            break;
                        case R.id.lists_nav_item:
                            selectedFragment = new ListsFragment();
                            break;
                        case R.id.movie_nav_item:
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            startActivity(intent);
                            break;

                            case R.id.logout_nav_item:
                            Intent intent1 = new Intent(MainActivity.this, LogoutActivity.class);
                            startActivity(intent1);
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    private void showSignInOptions(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).setTheme(R.style.MyTheme)
                        .build(),MY_REQUEST_CODE
        );
    }

}