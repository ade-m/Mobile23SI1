package edu.uph.m23si1.aplikasipertama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.uph.m23si1.aplikasipertama.api.ApiClient;
import edu.uph.m23si1.aplikasipertama.api.ApiService;
import edu.uph.m23si1.aplikasipertama.api.PasienResponse;
import edu.uph.m23si1.aplikasipertama.model.Pasien;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPasienActivity extends AppCompatActivity {
    EditText edtNama,edtNik,edtAlamat,edtNoHp;
    Button btnSimpan;
    ApiService apiService;
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
        apiService = ApiClient.getClient().create(ApiService.class);
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
                Pasien pasien = new Pasien(alamat,nama,nik,noHp);
                apiService.createPasien(pasien).enqueue(new Callback<PasienResponse>() {
                    @Override
                    public void onResponse(Call<PasienResponse> call, Response<PasienResponse> response) {
                        if(response.isSuccessful() && response.body()!=null){
                            Toast.makeText(AddPasienActivity.this,
                                    response.message().toString(),Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(AddPasienActivity.this,
                                    "Gagal Menyimpan Data",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PasienResponse> call, Throwable t) {
                        Toast.makeText(AddPasienActivity.this,
                                "Gagal : " + t.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });


                //apiService.createPasien(pasien).enqueue(new Callback<PasienResponse>() {
            }
        });
    }
}