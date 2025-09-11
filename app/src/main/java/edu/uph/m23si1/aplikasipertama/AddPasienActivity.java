package edu.uph.m23si1.aplikasipertama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPasienActivity extends AppCompatActivity {
    EditText edtNama,edtNik,edtAlamat,edtNoHp;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pasien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtNama = findViewById(R.id.edtNama);
        edtNik = findViewById(R.id.edtNik);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtNoHp = findViewById(R.id.edtNoHp);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ambil data dari edit text
                String nama = edtNama.getText().toString();
                String nik = edtNik.getText().toString();
                String alamat = edtAlamat.getText().toString();
                String noHp = edtNoHp.getText().toString();
                //kirim ke endpoint
            }
        });
    }
}