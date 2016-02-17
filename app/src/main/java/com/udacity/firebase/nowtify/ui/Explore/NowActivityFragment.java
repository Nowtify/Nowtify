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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.EntityChild;
import com.udacity.firebase.nowtify.model.UserFollows;
import com.udacity.firebase.nowtify.ui.MainActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.FirebaseUtils;
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
    private FirebaseUtils fireBaseUtils = new FirebaseUtils(getActivity());
    private ProgressDialog mRefreshProgressDialog;
    ExploreListAdapter mEntityChildAdapter;
    UserFollows userFollows;
    private ArrayList<String> userFollowList = new ArrayList<String>();

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
        mRefreshProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mRefreshProgressDialog.setCancelable(false);
        //geofireUtils.createLocations();
        fireBaseUtils.getImageFromEntityItemId("adidasGroupOffer");
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
                //refreshList();
                //((MainActivity)getActivity()).refreshEntityChildList();
            }
        });

        /**
         * Query for EntityChildren nearby
         */
        refreshList();
        ((MainActivity)getActivity()).refreshEntityChildList();

        Log.v("Refreshed", "Refreshed");

        return rootView;
    }

    public void refreshList(){
        mRefreshProgressDialog.show();
        resultList.clear();
        mRefreshProgressDialog.dismiss();
    }

    public void refreshAdapter(){
        Log.v("GeoFire", "Adapter Set");
        mRefreshProgressDialog.show();

        /**
         * Set the adapter to the mListView
         */
        mListView.setAdapter(mEntityChildAdapter);
        mEntityChildAdapter.clear();

        resultList = ((MainActivity)getActivity()).getResultList();
        //resultList = fireBaseUtils.convertEntityChildsToFollowedEntityChild(resultList, userFollowList);

        for(EntityChild entityChild : resultList) {
            mEntityChildAdapter.add(entityChild);
        }

        mRefreshProgressDialog.dismiss();
    }

    public interface Refresh{
        public void refreshList();
    }

    /**
     * Link listview from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_entity_child);
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
                    //showErrorToast(getString(R.string.log_error_cannot_find_user));
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //showErrorToast(firebaseError.toString());
                Log.e("NowActivity", getString(R.string.log_error_the_read_failed) + firebaseError.getMessage());
            }
        });
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
}
