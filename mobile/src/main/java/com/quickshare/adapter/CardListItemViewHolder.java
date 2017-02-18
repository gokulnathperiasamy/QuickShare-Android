package com.quickshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quickshare.entity.ProfileData;
import com.quickshare.fragment.CardFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardListItemViewHolder extends RecyclerView.ViewHolder {

    Context context;

    public CardListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(final ProfileData profileData, final Context context, final CardFragment cardFragment) {
        this.context = context;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing...
            }
        });
    }

}
