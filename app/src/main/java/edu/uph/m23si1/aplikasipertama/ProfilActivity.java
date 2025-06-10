package edu.uph.m23si1.aplikasipertama;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {
    Button btnSimpan,btnBersihkan;
    EditText edtNama,edtProdi,edtMobile,edtBisnis;
    TextView txvHasil;
    RadioButton rdbLaki, rdbPerempuan;
    RadioGroup rdgJenisKelamin;
    CheckBox chbMancing, chbMakan;
    Spinner sprProdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initVariable();

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.prodi_array,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        sprProdi.setAdapter(adapter);


        btnBersihkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNama.setText("");
                edtProdi.setText("");
                edtBisnis.setText("");
                edtMobile.setText("");
                txvHasil.setText("");
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String prodi = sprProdi.getSelectedItem().toString();
                String jenisKelamin="";
                String hobi="";
                int nilaiBisnis = Integer.parseInt(edtBisnis.getText().toString());
                int nilaiMobile = Integer.parseInt(edtMobile.getText().toString());

                if(rdbLaki.isChecked()) jenisKelamin=rdbLaki.getText().toString();
                else if(rdbPerempuan.isChecked()) jenisKelamin=rdbPerempuan.getText().toString();

                if(chbMancing.isChecked()) hobi+=chbMancing.getText().toString()+";";
                if(chbMakan.isChecked()) hobi+=chbMakan.getText().toString()+";";

                txvHasil.setText(nama
                        +"\nJenis Kelamin "+ jenisKelamin
                        +"\nIPK "+getIPK(nilaiBisnis,nilaiMobile).toString()
                        +"\n"+prodi
                        +"\n"+getNamaFakultas(prodi)+
                        "\n"+"Universitas Pelita Harapan"
                        +"\nHobi "+hobi);
            }
        });
    }

    public void initVariable(){
        //inisialisasi variabel
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBersihkan = findViewById(R.id.btnBersihkan);
        edtNama = findViewById(R.id.edtNama);
        edtProdi = findViewById(R.id.edtProdi);
        txvHasil = findViewById(R.id.txvHasil);
        edtMobile = findViewById(R.id.edtMobile);
        edtBisnis = findViewById(R.id.edtBisnis);
        rdgJenisKelamin = findViewById(R.id.rdgJenisKelamin);
        rdbLaki = findViewById(R.id.rdbLaki);
        rdbPerempuan = findViewById(R.id.rdbPerempuan);
        chbMakan = findViewById(R.id.chbMakan);
        chbMancing = findViewById(R.id.chbMancing);
        sprProdi = (Spinner) findViewById(R.id.sprProdi);

        edtNama.setText(getIntent().getStringExtra("nama").toString());
    }
    String getNamaFakultas(String prodi){
        String namaProdi = prodi.toLowerCase();
        if(namaProdi.equals("sistem informasi") || namaProdi.equals("informatika")){
            return "Fakultas Teknologi Informasi";
        }
        else if(namaProdi.equals("hukum") || namaProdi.equals("law")){
            return "Fakultas Hukum";
        }
        else if(namaProdi.equals("akuntansi") || namaProdi.equals("manajemen")||
                namaProdi.equals("perhotelan")){
            return "Fakultas Ekonomi dan Bisnis";
        }
        else
            return "Fakultas Tidak di Temukan";
    }
    Double getIPK(int nilaiBisnis, int NilaiMobile){
        return ((getBobotNilai(nilaiBisnis)*3)+
                (getBobotNilai(NilaiMobile)*3))/6;
    }
    Double getBobotNilai(int nilai){
       if(nilai>=90) return 4.0;
       else if(nilai>=85) return 3.75;
       else if(nilai>=80) return 3.5;
       else if(nilai>=75) return 3.25;
       else if(nilai>=70) return 3.0;
       else if(nilai>60)  return 2.75;
       else if(nilai>50)  return 2.5;
       else return 0.0;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}