package fr.rmdcoding.listejoueurs.models;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private Integer page;

    private List<Joueur> results = new ArrayList<>();

    private Integer totalResults;



    public Page() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Joueur> getResults() {
        return results;
    }

    public void setResults(List<Joueur> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

}
