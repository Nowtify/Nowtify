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

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityChild;
import com.udacity.firebase.nowtify.ui.MainActivity;
import com.udacity.firebase.nowtify.utils.GeofireUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ExploreActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreActivityFragment extends Fragment {
    private ExploreListAdapter mActiveListAdapter;
    private ListView mListView;
    private ArrayList<EntityChild> resultList = new ArrayList<EntityChild>();
    private ArrayList<String> rawQueryList = new ArrayList<String>();
    Firebase firebase = new Firebase("https://nowtify.firebaseio.com/geofire");
    GeoFire geoFire = new GeoFire(firebase);
    private GeofireUtils geofireUtils = new GeofireUtils(getActivity());
    private ProgressDialog mRefreshProgressDialog;
    ExploreListAdapter mEntityChildAdapter;

    public ExploreActivityFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ExploreActivityFragment newInstance() {
        ExploreActivityFragment fragment = new ExploreActivityFragment();
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
        mRefreshProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
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


        resultList.add(new EntityChild("test1","test","test","test"));
        resultList.add(new EntityChild("test2","test","test","test"));
        resultList.add(new EntityChild("test3","test","test","test"));


                // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
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
                refreshList();
            }
        });

        /**
         * Query for EntityChildren nearby
         */
        refreshList();

        return rootView;
    }

    public void refreshList(){
        mRefreshProgressDialog.show();
        GeoQuery query = geoFire.queryAtLocation(new GeoLocation(getLatitude(), getLongitude()), 10);
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
                resultList = geofireUtils.convertRawQueryToEntityChild(rawQueryList);
                refreshAdapter();
                Log.v("GeoFire", "Done");
                mRefreshProgressDialog.dismiss();
            }

            @Override
            public void onGeoQueryError(FirebaseError error) {

            }
        });
    }

    public void refreshAdapter(){
        Log.v("GeoFire", "Adapter Set");
        mRefreshProgressDialog.show();

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mEntityChildAdapter);
        mEntityChildAdapter.clear();

        for(EntityChild entityChild : resultList) {
            mEntityChildAdapter.add(entityChild);
        }

        mRefreshProgressDialog.dismiss();
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
}
