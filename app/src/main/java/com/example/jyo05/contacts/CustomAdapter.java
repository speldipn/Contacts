package com.example.jyo05.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * @Author: Oh, Joon young (speldipn)
 * @Since: 2018-10-29
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {

    List<Contact> list;

    public CustomAdapter(List<Contact> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Contact contact = list.get(i);
        holder.text_id.setText(contact.id);
        holder.text_name.setText(contact.name);
        holder.text_phone.setText(contact.phone);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView text_id;
        TextView text_name;
        TextView text_phone;
        Button btn_call;

        public Holder(@NonNull View v) {
            super(v);
            text_id = v.findViewById(R.id.text_id);
            text_name = v.findViewById(R.id.text_name);
            text_phone = v.findViewById(R.id.text_phone);
            btn_call = v.findViewById(R.id.btn_call);

            btn_call.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri uri = Uri.parse("tel:" + text_phone.getText().toString());
                    intent.setData(uri);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
