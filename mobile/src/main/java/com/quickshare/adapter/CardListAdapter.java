package com.quickshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.quickshare.R;
import com.quickshare.application.QuickShareApplication;
import com.quickshare.entity.ProfileData;
import com.quickshare.fragment.CardFragment;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ProfileData> list;
    CardFragment cardFragment;

    Context context;

    public CardListAdapter(List<ProfileData> listProfileData, CardFragment cardFragment) {
        this.list = listProfileData;
        this.cardFragment = cardFragment;
        QuickShareApplication.inject(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new CardListItemViewHolder(View.inflate(context, R.layout.view_card_list_item, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProfileData profileData = list.get(position);
        CardListItemViewHolder cardListItemViewHolder = (CardListItemViewHolder) holder;
        cardListItemViewHolder.bindView(profileData, context, cardFragment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
