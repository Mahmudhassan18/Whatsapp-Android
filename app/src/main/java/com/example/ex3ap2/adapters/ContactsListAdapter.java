package com.example.ex3ap2.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3ap2.R;
import com.example.ex3ap2.entities.Contact;
import com.example.ex3ap2.viewmodels.ContactsViewModel;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemNickname;
        private final TextView contactItemLast;
        private final TextView contactItemLastdate;
        private final ImageView contactItemPic;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactItemNickname = itemView.findViewById(R.id.contact_user_name);
            contactItemLast = itemView.findViewById(R.id.latest_message);
            contactItemLastdate = itemView.findViewById(R.id.latest_message_date);
            contactItemPic = itemView.findViewById(R.id.contact_profile_image);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    private ContactsViewModel viewModel;

    public ContactsListAdapter(Context context, ContactsViewModel viewModel) {
        mInflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_contacts_list, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.contactItemNickname.setText(current.getNickname());
            holder.contactItemLast.setText(current.getLast());
            holder.contactItemLastdate.setText(current.getLastdate());
            holder.contactItemPic.setImageResource(current.getPic());

            holder.itemView.setOnLongClickListener(l -> {
                viewModel.delete(current);
                return true;
            });

            holder.contactItemNickname.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public int getItemCount() {
        if (contacts != null)
            return contacts.size();
        else
            return 0;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> newContacts) {
        contacts = newContacts;
        notifyDataSetChanged();
    }
}