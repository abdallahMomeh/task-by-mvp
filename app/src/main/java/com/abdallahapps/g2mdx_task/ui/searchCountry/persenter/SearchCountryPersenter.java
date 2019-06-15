package com.abdallahapps.g2mdx_task.ui.searchCountry.persenter;

import com.abdallahapps.g2mdx_task.generalUtils.Constants;
import com.abdallahapps.g2mdx_task.model.DataManager;
import com.abdallahapps.g2mdx_task.model.data.dto.Country;
import com.abdallahapps.g2mdx_task.model.data.source.preferences.SharedManager;
import com.abdallahapps.g2mdx_task.ui.searchCountry.view.SearchCountryView;

public class SearchCountryPersenter {

    SearchCountryView searchCountryView;

    public SearchCountryPersenter(SearchCountryView searchCountryView){
        this.searchCountryView=searchCountryView;
    }

    public void fillCountriesTable(){
        String c[]={"egypt","sudia","الامارات","ليبيا","تونس","المغرب","قطر"};

        if (!SharedManager.newInstance().getBoolean(Constants.CountryTableFilled,false)){

            for (int i = 0; i<c.length ; i++){
                Country country = new Country();
                country.setName(c[i]);
                DataManager.getInstance().insertCountry(country);
            }

            SharedManager.newInstance().setBoolean(Constants.CountryTableFilled,true);
        }

    }

    public void searchAboutCoutry(String coutryName){
        searchCountryView.onSueccesSearchCoutries( DataManager.getInstance().searchAboutCoutry(coutryName) );
    }

}
