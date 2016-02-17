package com.udacity.firebase.nowtify.ui.Explore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityChild;
import com.udacity.firebase.nowtify.ui.EntityItem.EntityItemDetailsActivity;

import java.util.List;

/**
 * Created by MohamedAfiq on 6/2/16.
 */
public class ExploreListAdapter extends ArrayAdapter<EntityChild> {

    private boolean isFollowing = false;
    private Context context;

    public ExploreListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public ExploreListAdapter(Context context, int resource, List<EntityChild> items) {
        super(context, resource, items);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button mFollowButton;
        View v = convertView;
        ImageView mImageView;
        TextView mEntityItemTitle;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.single_active_list_item, null);

            //Creates ViewHolder object that takes in a view and returns the button ID
            ViewHolder h = new ViewHolder(v);
            mFollowButton = (Button) h.getButton();
            v.setTag(h);
        }

        EntityChild p = getItem(position);
        ViewHolder h = (ViewHolder) v.getTag();
        mFollowButton = h.getButton();
        mImageView = (ImageView)h.getImageView();
        mEntityItemTitle = (TextView) h.getTextView();

        final boolean isFollowingFinal = isFollowing;

        //Following button listener
        mFollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button mFollowButton= (Button) v.findViewById(R.id.entity_item_button);

                if (isFollowingFinal == false) {
                    mFollowButton.setText("Following");
                    mFollowButton.setBackgroundColor(Color.parseColor("#e7eecc"));
                }
                if (isFollowingFinal == true) {
                    mFollowButton.setText("Follow");
                    mFollowButton.setBackgroundColor(666666);

                }

            }
        });

        //Image Listener
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EntityItemDetailsActivity.class);
                context.startActivity(intent);

            }
        });
        //Title Listener
        mEntityItemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EntityItemDetailsActivity.class);
                context.startActivity(intent);

            }
        });

/*        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            TextView tt2 = (TextView) v.findViewById(R.id.categoryId);
            TextView tt3 = (TextView) v.findViewById(R.id.description);

            if (tt1 != null) {
                tt1.setText(p.getId());
            }

            if (tt2 != null) {
                tt2.setText(p.getCategory().getId());
            }

            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }
        }*/

        /**
         * Grab the needed Textivews and strings
         */
        TextView textViewListName = (TextView) v.findViewById(R.id.entity_parent_name);
        TextView entityItemTitle = (TextView) v.findViewById(R.id.entity_item_title);
        //TextView textViewCreatedByUser = (TextView) v.findViewById(R.id.text_view_created_by_user);


        /* Set the list name and owner */
        textViewListName.setText(p.getEntityParentName());
        entityItemTitle.setText(p.getTitle());
        //textViewCreatedByUser.setText(p.getEntityParentName());




        return v;
    }

    public class ViewHolder{
        View view;
        Button button;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view)
        {
            this.view = view;
        }

        public Button getButton(){
            if (button == null)
            {
                button = (Button) view.findViewById(R.id.entity_item_button);
            }
            return button;
        }

        public ImageView getImageView(){
            if ( imageView == null)
            {
                imageView = (ImageView) view.findViewById(R.id.entity_item_image);
            }
            return imageView;
        }

        public TextView getTextView(){
            if ( textView == null)
            {
                textView = (TextView) view.findViewById(R.id.entity_item_title);
            }
            return textView;
        }

    }


}