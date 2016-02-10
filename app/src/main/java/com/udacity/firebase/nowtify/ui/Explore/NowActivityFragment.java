package com.udacity.firebase.nowtify.ui.Explore;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityChild;
import com.udacity.firebase.nowtify.model.UserFollows;
import com.udacity.firebase.nowtify.ui.MainActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.FirebaseUtils;
import com.udacity.firebase.nowtify.utils.GeofireUtils;
import com.udacity.firebase.nowtify.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ExploreActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowActivityFragment extends Fragment {
    private ListView mListView;
    private ArrayList<EntityChild> resultList = new ArrayList<EntityChild>();
    private ArrayList<String> rawQueryList = new ArrayList<String>();
    Firebase firebase = new Firebase(Constants.FIREBASE_URL_GEOFIRE);
    GeoFire geoFire = new GeoFire(firebase);
    private GeofireUtils geoFireUtils = new GeofireUtils(getActivity());
    private FirebaseUtils fireBaseUtils = new FirebaseUtils(getActivity());
    private ProgressDialog mRefreshProgressDialog;
    UserFollows userFollows;
    private ArrayList<String> userFollowList = new ArrayList<String>();
    ExploreListAdapter mEntityChildAdapter;

    public NowActivityFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as its arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static NowActivityFragment newInstance() {
        NowActivityFragment fragment = new NowActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mRefreshProgressDialog = new ProgressDialog(getActivity());
        mRefreshProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        //mRefreshProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mRefreshProgressDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * Initialize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mEntityChildAdapter);

        mEntityChildAdapter =
                new ExploreListAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.single_active_list, // The name of the layout ID.
                        resultList
                );

        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/*                EntityChild selectedList = mActiveListAdapter.getItem(position);
                if (selectedList != null) {
                    //change MainActivity to ActiveListDetails later on
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    *//* Get the list ID using the adapter's get ref method to get the Firebase
                     * ref and then grab the key.
                     *//*
                    //String listId = mEntityChildAdapter.getRef(position).getKey();
                    String listId = "exampleTest";
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                    *//* Starts an active showing the details for the selected list *//*
                    startActivity(intent);
                }*/
                refresh();
            }
        });

        /**
         * refresh list
         */
        refresh();
        Log.v("Refreshed", "Refreshed");

        return rootView;
    }

    public void refresh(){
        mRefreshProgressDialog.show();
        refreshLocation();
        getUserFollows();
    }

    public void getUserFollows(){
        mEntityChildAdapter.clear();
        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        String mEncodedEmail = Utils.encodeEmail("afiq980@gmail,com");
        Firebase firebaseUserFollowsRef = new Firebase(Constants.FIREBASE_URL_ENTITY_USER_FOLLOWS).child(mEncodedEmail);

        /**
         * Check if current user has logged in at least once
         */
        firebaseUserFollowsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userFollows = dataSnapshot.getValue(UserFollows.class);
                if (userFollows != null) {
                    userFollowList = getFollowsInString();
                    refreshList();
                } else {
                    showErrorToast(getString(R.string.log_error_cannot_find_user));
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                showErrorToast(firebaseError.toString());
                Log.e("NowActivity", getString(R.string.log_error_the_read_failed) + firebaseError.getMessage());
            }
        });
    }

    public void refreshList(){
        resultList.clear();
        rawQueryList.clear();

        //to add example locations here
        Log.v("Location LiKaShing Lat",Double.toString(getLatitude()));
        Log.v("Location LiKaShing Long", Double.toString(getLongitude()));

        //geoFire.setLocation("LiKaShing,LiKaShing,LiKaShing,LiKaShing", new GeoLocation(getLatitude(),getLongitude()));
        //geoFire.setLocation("Starbucks,Starbucks,Starbucks,Starbucks", new GeoLocation(1.297605,103.850333));
        //geoFire.setLocation("Tea Party,Tea Party,Tea Party,Tea Party", new GeoLocation(1.297799,103.848661));
        //geoFire.setLocation("NSM,NSM,NSM,NSM", new GeoLocation(1.296759,103.848548));
        //geoFire.setLocation("7KickStart,7KickStart,7KickStart,7KickStart", new GeoLocation(1.296461,103.849812));


        GeoQuery query = geoFire.queryAtLocation(new GeoLocation(getLatitude(), getLongitude()), Double.parseDouble(Constants.WALKING_DISTANCE));
        Log.v("Location in fragment", Double.toString(getLatitude()));
        Log.v("Location in fragment", Double.toString(getLongitude()));
        query.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                //Log.v("GeoFire",key + " " + location.toString());
                //testLocs.add(key + " " + location.toString());
                rawQueryList.add(key);
                Log.v("GeoFire", "Waiting");
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                resultList = fireBaseUtils.convertRawQueryToEntityChild(rawQueryList);
                resultList = fireBaseUtils.convertEntityChildsToFollowedEntityChild(resultList, userFollowList);
                refreshAdapter();
                Log.v("GeoFire", "Done");
            }

            @Override
            public void onGeoQueryError(FirebaseError firebaseError) {
                showErrorToast(firebaseError.toString());
            }
        });
    }

    public void refreshAdapter() {
        Log.v("GeoFire", "Adapter Set");
        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mEntityChildAdapter);
        mEntityChildAdapter.clear();

        if(resultList != null){
            for(EntityChild entityChild : resultList) {
                mEntityChildAdapter.add(entityChild);
            }
        } else {
            mEntityChildAdapter.add(new EntityChild("No Data","No Data","No Data","No Data","No Data"));
        }


        test();

        mRefreshProgressDialog.dismiss();
    }

    public void refreshLocation(){
         ((MainActivity)getActivity()).getCurrentLocation();
    }

    public double getLatitude(){
        return ((MainActivity)getActivity()).getLatitude();
    }

    public double getLongitude(){
        return ((MainActivity)getActivity()).getLongitude();
    }

    /**
     * Cleanup the adapter when activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //mActiveListAdapter.cleanup();
        mEntityChildAdapter.clear();
    }

    /**
     * Link list view from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_entity_child);
    }

    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public ArrayList<String> getFollowsInString(){
        ArrayList<String> toReturn;
        HashMap<String, Object> userFollowHashMap = userFollows.getFollows();
        if(userFollowHashMap!=null){
            toReturn = new ArrayList<String>(userFollowHashMap.keySet());
        } else {
            toReturn = null;
        }
        return toReturn;
    }

    private void test(){
        fireBaseUtils.followEntityParent("afiq980@gmail.com", "Shawn Lau", userFollows, true);
        fireBaseUtils.followEntityParent("afiq980@gmail.com", "NSM", userFollows, false);
        fireBaseUtils.getEntityItemDetails("adidasGroupOffer");
    }
}
