package labs.android2020.readingapp2020.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.List;

import labs.android2020.readingapp2020.R;

public class LogoutActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 1995;


    Button btn_sign_out;

    List<AuthUI.IdpConfig> providers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_logout);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
        showSignInOptions();

        showSignInOptions();
        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(LogoutActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btn_sign_out.setEnabled(false);
                        showSignInOptions();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogoutActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).setTheme(R.style.MyTheme)
                        .build(), MY_REQUEST_CODE
        );
    }

}