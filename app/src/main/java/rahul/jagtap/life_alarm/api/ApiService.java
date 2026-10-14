package rahul.jagtap.life_alarm.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {
    @GET("bills.json")
    Call<ResponseBody> getBills();

    @GET("esuvidha.json")
    Call<ResponseBody> getEsuvidha();

    @GET("users.json")
    Call<ResponseBody> getUsers();

    @GET("text_messages.json")
    Call<ResponseBody> getTextMessages();

    @GET("govt_schemes.json")
    Call<ResponseBody> getGovtSchemes();

    @GET("esuvidha_suchna.json")
    Call<ResponseBody> getSuchna();

    @GET("dynamic_types.json")
    Call<ResponseBody> getDynamicTypes();

    @GET("esuvidha_dynamic_types.json")
    Call<ResponseBody> getEsuvidhaDynamicTypes();
}
