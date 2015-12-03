package com.example.deepak.myfriends.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 */
public abstract class ViewHolderBaseCard extends RecyclerView.ViewHolder {
    public ViewHolderBaseCard(View itemView) {
        super(itemView);
    }

    public abstract void binData(Object object);
}
