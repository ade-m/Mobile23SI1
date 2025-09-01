package edu.uph.m23si1.aplikasipertama.api;

import edu.uph.m23si1.aplikasipertama.api.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/provinces.json")
    Call<ApiResponse> getProvinces();
}
