package com.abdallahapps.g2mdx_task.ui.searchCountry.view;

import com.abdallahapps.g2mdx_task.model.data.dto.Country;
import com.abdallahapps.g2mdx_task.ui.base.view.BaseView;

import java.util.List;

public interface SearchCountryView extends BaseView {

    void onSueccesSearchCoutries(List<Country> countries);
}
