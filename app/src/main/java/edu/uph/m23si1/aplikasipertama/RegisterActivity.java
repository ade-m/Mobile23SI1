package edu.uph.m23si1.aplikasipertama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edtName;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true) // sementara aktifkan untuk demo
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        edtName = findViewById(R.id.edtName);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Tombol Register diTekan", Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                //toast.show();
                toDashboard();
            }
        });

        //inisialisasi
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }
    public void toProfil(){
        String nama = edtName.getText().toString();
        Intent intent = new Intent(this, ProfilActivity.class);
        intent.putExtra("nama",nama);
        startActivity(intent);
    }

    public void toDashboard(){
        String nama = edtName.getText().toString();

        editor.putString(getString(R.string.username_key), nama);
        editor.apply();

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("nama",nama);
        startActivity(intent);
    }
}