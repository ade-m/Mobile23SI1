package edu.uph.m23si1.aplikasipertama.api;

import edu.uph.m23si1.aplikasipertama.api.ApiResponse;
import edu.uph.m23si1.aplikasipertama.model.Pasien;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/provinces.json")
    Call<ApiResponse> getProvinces();

    @POST("pasien")
    Call<PasienResponse> createPasien(@Body Pasien pasien);
}
