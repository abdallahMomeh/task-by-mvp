package com.abdallahapps.g2mdx_task.ui.contacts.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdallahapps.g2mdx_task.R;
import com.abdallahapps.g2mdx_task.model.data.dto.Contact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsRVAdapter extends RecyclerView.Adapter<ContactsRVAdapter.ViewHolder> {

    List<Contact> contacts;
    public ContactsRVAdapter(List contacts){
        this.contacts=contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_itrm,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNameTV.setText(contacts.get(position).getName());
        holder.mPhoneTV.setText(contacts.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mNameTV,mPhoneTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTV = itemView.findViewById(R.id.mNameTV);
            mPhoneTV = itemView.findViewById(R.id.mPhoneTV);
        }
    }
}
