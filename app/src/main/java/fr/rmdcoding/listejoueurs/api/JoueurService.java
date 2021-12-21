package fr.rmdcoding.listejoueurs.api;

import java.util.List;

import fr.rmdcoding.listejoueurs.models.Joueur;
import fr.rmdcoding.listejoueurs.models.Page;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JoueurService {

    String BASE_URL = "https://okochatest.footbar.com/profile/list/";
    @GET("results")
    Call<Page> getJoueurs(@Query("page") int pageIndex);
}