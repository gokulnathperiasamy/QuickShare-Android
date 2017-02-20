package com.quickshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quickshare.R;
import com.quickshare.entity.ProfileData;
import com.quickshare.fragment.CardFragment;
import com.quickshare.utility.ProfileDataHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardListItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.company_name)
    TextView companyName;

    @Bind(R.id.company_address)
    TextView companyAddress;

    @Bind(R.id.email_id)
    TextView emailID;

    @Bind(R.id.mobile_number)
    TextView mobileNumber;

    @Bind(R.id.office_number)
    TextView officeNumber;

    @Bind(R.id.fax)
    TextView fax;

    Context context;

    public CardListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(final ProfileData profileData, final Context context, final CardFragment cardFragment) {
        this.context = context;

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing...
            }
        });

        setData(profileData);
    }

    private void setData(ProfileData profileData) {
        name.setText(ProfileDataHelper.getFullName(profileData.firstName, profileData.lastName));

        if (profileData.companyName.length() > 0) {
            companyName.setText(profileData.companyName);
        } else {
            companyName.setVisibility(View.GONE);
        }

        if (profileData.companyAddress.length() > 0) {
            companyAddress.setText(profileData.companyAddress);
        } else {
            companyAddress.setVisibility(View.GONE);
        }

        if (profileData.email.length() > 0) {
            emailID.setText(ProfileDataHelper.getFormattedEmail(profileData.email));
        } else {
            emailID.setVisibility(View.GONE);
        }

        if (profileData.mobileNumber.length() > 0) {
            mobileNumber.setText(ProfileDataHelper.getFormattedMobileNumber(profileData.mobileNumber));
        } else {
            mobileNumber.setVisibility(View.GONE);
        }

        if (profileData.officeNumber.length() > 0) {
            officeNumber.setText(ProfileDataHelper.getFormattedOfficeNumber(profileData.officeNumber));
        } else {
            officeNumber.setVisibility(View.GONE);
        }

        if (profileData.fax.length() > 0) {
            fax.setText(ProfileDataHelper.getFormattedFax(profileData.fax));
        } else {
            fax.setVisibility(View.GONE);
        }
    }

}