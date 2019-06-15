package com.abdallahapps.g2mdx_task.ui.searchCountry.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.model.data.dto.Country;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountriesRVAdapter extends RecyclerView.Adapter<CountriesRVAdapter.ViewHolder> {

    List<Country> countries ;
    public CountriesRVAdapter (List<Country> countries) {
        this.countries=countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coutry_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCountryTV.setText(countries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mCountryTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountryTV = (TextView) itemView.findViewById(R.id.mCountryTV);
        }

    }

}
