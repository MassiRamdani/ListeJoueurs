package fr.rmdcoding.listejoueurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.util.List;

import fr.rmdcoding.listejoueurs.adapter.JoueurAdapter;
import fr.rmdcoding.listejoueurs.api.JoueurApi;
import fr.rmdcoding.listejoueurs.api.JoueurService;
import fr.rmdcoding.listejoueurs.models.Joueur;
import fr.rmdcoding.listejoueurs.models.Page;
import fr.rmdcoding.listejoueurs.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity   {

    private final String TAG = "Informations";
    private RecyclerView rvJoueurs;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 7;
    private int currentPage = PAGE_START;
    private JoueurService joueurService;
    private JoueurAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvJoueurs = (RecyclerView) findViewById(R.id.rv_Joueurs);
        adapter = new JoueurAdapter(this);


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvJoueurs.setLayoutManager(linearLayoutManager);

        rvJoueurs.setItemAnimator(new DefaultItemAnimator());

        rvJoueurs.setAdapter(adapter);

        rvJoueurs.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        //init service and load data
        joueurService = JoueurApi.getClient().create(JoueurService.class);

        loadFirstPage();
    }


    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        callPageApi().enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {

                List<Joueur> results = fetchResults(response);
                adapter.addAll(results);
                Log.d(TAG, "loadFirstPage: "+results);
                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    private List<Joueur> fetchResults(Response<Page> response) {
        Page page = response.body();
        return page.getResults();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callPageApi().enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<Joueur> results = fetchResults(response);
                adapter.addAll(results);

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
    private Call<Page> callPageApi() {
        return joueurService.getJoueurs(

                currentPage
        );
    }
}