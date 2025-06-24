package edu.uph.m23si1.aplikasipertama;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.m23si1.aplikasipertama.model.Mahasiswa;
import io.realm.Realm;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout llyProfil;
    TextView txvHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        llyProfil= findViewById(R.id.llyProfil);
        llyProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfil();
            }
        });

        txvHasil = findViewById(R.id.txvHasil);
        Realm realm = Realm.getDefaultInstance();
        Mahasiswa mhs = realm.where(Mahasiswa.class).findFirst();
        if (mhs != null) {
            txvHasil.setText(mhs.toString());
        }
    }
    public void toProfil(){
        String nama = getIntent().getStringExtra("nama");
        Intent intent = new Intent(this, ProfilActivity.class);
        intent.putExtra("nama",nama);
        startActivity(intent);
    }
}