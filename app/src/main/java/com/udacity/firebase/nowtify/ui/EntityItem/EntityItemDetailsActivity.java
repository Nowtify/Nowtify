package com.udacity.firebase.nowtify.ui.EntityItem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.koushikdutta.ion.Ion;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityItemDetails;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.FirebaseUtils;

/**
 * Created by smu on 17/2/16.
 */
public class EntityItemDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = EntityItemDetailsActivity.class.getSimpleName();
    private FirebaseUtils fireBaseUtils = new FirebaseUtils(getParent());
    private Firebase firebaseRef;
    private EntityItemDetails entityItemDetails;
    private String entityParentName, entityItemDetailsTitle, entityItemDetailsId, latitudeString, longitudeString;
    private Double latitude, longitude;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_item_details);

        context = EntityItemDetailsActivity.this;

        //retrieve intent
        entityParentName = getIntent().getStringExtra("entityParentName");
        entityItemDetailsTitle = getIntent().getStringExtra("title");
        entityItemDetailsId = getIntent().getStringExtra("entityItemDetailsId");
        latitudeString = getIntent().getStringExtra("latitude");
        longitudeString = getIntent().getStringExtra("longitude");
        latitude = Double.parseDouble(latitudeString);
        longitude = Double.parseDouble(longitudeString);
        getEntityItemDetailsFromId("adidasGroupOffer");

        /**
         * Link layout elements from XML and setup the progress dialog
         */

        //test

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude));
        startActivity(intent);

    }

    public void getEntityItemDetailsFromId (String entityItemDetailsId){
        firebaseRef = new Firebase(Constants.FIREBASE_URL_ENTITY_ITEM_DETAILS).child(entityItemDetailsId);

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                entityItemDetails = dataSnapshot.getValue(EntityItemDetails.class);
                setDetails();
                Log.v(LOG_TAG, entityItemDetails.getParentPushId());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void setDetails(){
        //edit here to set views
        //use entityItemDetails.[get]
        TextView mEntityParentNameView = (TextView) findViewById(R.id.entity_parent_name);
        mEntityParentNameView.setText(entityParentName);

        ImageView mEntityItemImageView = (ImageView) findViewById(R.id.entity_item_image);
        Ion.with(context)
                .load("http://dope.sg/wp-content/uploads/2014/03/111.jpg")
                .withBitmap()
                .placeholder(R.drawable.icon_add)
                .error(R.drawable.icon_profile_add)
                .intoImageView(mEntityItemImageView);


        TextView mEntityItemAddressView = (TextView) findViewById(R.id.entity_item_address);
        mEntityItemAddressView.setText(entityItemDetails.getTitle());

        TextView mEntityItemDetailTitle = (TextView) findViewById(R.id.entity_item_address);
        mEntityItemAddressView.setText(entityItemDetails.getTitle());

        //Details
        //Hi Momo, you can insert here:

        TextView mOpeningHours = (TextView) findViewById(R.id.entity_child_opening_hours);
        mOpeningHours.setText("11am - 11pm");

        TextView mStartDate = (TextView) findViewById(R.id.entity_item_start_date);
        mStartDate.setText("999");

        TextView mEndDate = (TextView) findViewById(R.id.entity_item_end_date);
        mEndDate.setText("000");

        //Description
        TextView mDescription = (TextView) findViewById(R.id.entity_item_description);
        mDescription.setText("tage 1: Confirm ideal date/time, change the colour status of the timetable doc when they confirm (link below)\n" +
                "\n" +
                "Stage 2: If unable to make it, put on hold and let us know, we will wait till all companies in the same day to reply. If happened that same day got company cant make it, we will propose a switch between companies within the same day only.\n" +
                "\n" +
                "Stage 3a: If really cannot, we will slot in extra/new companies within the location that recently updated their status (to fill the empty slot).\n" +
                "            \"");

        //Terms and Condition
        TextView mTermsAndCondition = (TextView) findViewById(R.id.entity_item_tnc);
        mTermsAndCondition.setText("have a scenario where, after logging in through a login page");



    }

    public void onDirectionPressed(){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude));
        startActivity(intent);
    }
}
