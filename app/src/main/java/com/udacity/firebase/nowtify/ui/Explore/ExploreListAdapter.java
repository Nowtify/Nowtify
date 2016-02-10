package com.udacity.firebase.nowtify.ui.Explore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityChild;

import java.util.List;

/**
 * Created by MohamedAfiq on 6/2/16.
 */
public class ExploreListAdapter extends ArrayAdapter<EntityChild> {

    public ExploreListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ExploreListAdapter(Context context, int resource, List<EntityChild> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.single_active_list_item, null);
        }

        EntityChild p = getItem(position);

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

}