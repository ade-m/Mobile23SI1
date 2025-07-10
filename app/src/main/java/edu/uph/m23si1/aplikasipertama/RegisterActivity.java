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

import java.util.ArrayList;
import java.util.List;

import edu.uph.m23si1.aplikasipertama.model.Mahasiswa;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

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
                .name("mahasiswa2.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true) // sementara aktifkan untuk demo
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        initData();

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
    public void initData(){
//        Mahasiswa mhs = new Mahasiswa("Makan;",10,"Perempuan","Susan",
//                50,50,"Hukum");
        Realm realm = Realm.getDefaultInstance();
/// Kosongkan data dulu
        realm.executeTransaction(r -> {
            RealmResults<Mahasiswa> mhs = r.where(Mahasiswa.class)
                    .findAll();
            if (mhs != null) {
                for (Mahasiswa mahasiswa: mhs) {
                    mahasiswa.deleteFromRealm();
                }
            }
        });
/// Masukkan data
//        realm.executeTransaction(r -> {
//            Number maxId = r.where(Mahasiswa.class).max("idMahasiswa");
//            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
//            Mahasiswa mhs2 = r.createObject(Mahasiswa.class, nextId);
//            mhs2.setNama("Budi");
//            mhs2.setProdi("Hukum");
//            mhs2.setHobi("Makan;");
//            mhs2.setJenisKelamin("Perempuan");
//            mhs2.setNilaiBisnis(50);
//            mhs2.setNilaiMobile(70);
//        });
//
//        realm.executeTransaction(r -> {
//            Number maxId = r.where(Mahasiswa.class).max("idMahasiswa");
//            int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
//            Mahasiswa mhs2 = r.createObject(Mahasiswa.class, nextId);
//            mhs2.setNama("Budi Susan");
//            mhs2.setProdi("Hukum");
//            mhs2.setHobi("Makan;");
//            mhs2.setJenisKelamin("Perempuan");
//            mhs2.setNilaiBisnis(50);
//            mhs2.setNilaiMobile(70);
//        });

        List<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        daftarMahasiswa.add(new Mahasiswa("Makan", 1, "Perempuan", "Budi", 50, 70, "Hukum"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 2, "Laki-laki", "Andi", 65, 75, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 3, "Perempuan", "Siti", 80, 85, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 4, "Perempuan", "Dina", 75, 90, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 5, "Laki-laki", "Rudi", 60, 65, "Informatika"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 6, "Laki-laki", "Tono", 70, 72, "Informatika"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 7, "Perempuan", "Lisa", 88, 93, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 8, "Laki-laki", "Joko", 55, 60, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 9, "Perempuan", "Maya", 78, 82, "Sistem Informasi"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 10, "Perempuan", "Dewi", 90, 95, "Informatika"));
        daftarMahasiswa.add(new Mahasiswa("Makan", 11, "Perempuan", "Dewa", 90, 95, "Manajemen"));

        // Masukkan ke Realm
        realm.executeTransaction(r -> {
            r.insert(daftarMahasiswa);
        });
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