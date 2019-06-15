package com.abdallahapps.g2mdx_task.ui.searchCountry.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.model.data.dto.Country;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseActivity;
import com.abdallahapps.g2mdx_task.ui.searchCountry.persenter.SearchCountryPersenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchCountryActivity extends BaseActivity implements SearchCountryView {

    SearchCountryPersenter searchCountryPersenter;
    CountriesRVAdapter countriesRVAdapter;
    SearchView searchView;
    List<Country> countries;
    RecyclerView mCoutriesRV;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_country);
        countries = new ArrayList<>();
        initViews();

        searchCountryPersenter = new SearchCountryPersenter(this);
        searchCountryPersenter.fillCountriesTable();
        searchCountryPersenter.searchAboutCoutry("");    // to get all Coutries


    }

    void initViews(){
        mCoutriesRV = (RecyclerView)findViewById(R.id.mCoutriesRV);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back_white_24);
        setSupportActionBar(toolbar);

        countriesRVAdapter = new CountriesRVAdapter(countries);
        mCoutriesRV.setLayoutManager(new LinearLayoutManager(this));
        mCoutriesRV.setAdapter(countriesRVAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int type) {

    }

    @Override
    public void onSueccesSearchCoutries(List<Country> countries) {

        this.countries.clear();
        this.countries.addAll(countries);
        countriesRVAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        EditText searchEditText = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(android.R.color.white));


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchCountryPersenter.searchAboutCoutry(""+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return true;
    }

}
