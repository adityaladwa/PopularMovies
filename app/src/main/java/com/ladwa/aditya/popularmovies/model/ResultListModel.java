package com.ladwa.aditya.popularmovies.model;

import java.util.ArrayList;

/**
 * Created by Aditya on 03-Feb-16.
 */
public class ResultListModel {
    private ArrayList<ResultModel> results;
    private String pageno;

    public String getPageno() {
        return pageno;
    }

    public void setPageno(String pageno) {
        this.pageno = pageno;
    }


    public ArrayList<ResultModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultModel> results) {
        this.results = results;
    }
}
