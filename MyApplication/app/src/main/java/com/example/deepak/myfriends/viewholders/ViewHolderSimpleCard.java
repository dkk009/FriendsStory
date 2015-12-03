package com.example.deepak.myfriends.viewholders;

import android.view.View;
import android.widget.TextView;

import com.example.deepak.myfriends.R;
import com.example.deepak.myfriends.models.OurStory;

/**
 *
 */
public class ViewHolderSimpleCard extends ViewHolderBaseCard {
    private TextView mTxtTitle;
    private TextView mTxtContent;
    private View mView;
    public ViewHolderSimpleCard(View itemView) {
        super(itemView);
        mTxtContent = (TextView) itemView.findViewById(R.id.txt_content);
        mTxtTitle = (TextView) itemView.findViewById(R.id.txt_title);
    }

    @Override
    public void binData(Object object) {
        OurStory storyCard = (OurStory) object;
        mTxtTitle.setText(storyCard.getTitle());
        mTxtContent.setText(storyCard.getContent());
    }
}
