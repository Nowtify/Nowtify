package com.udacity.firebase.nowtify.ui;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.UserFollows;
import com.udacity.firebase.nowtify.ui.Explore.ExploreActivityFragment;
import com.udacity.firebase.nowtify.ui.Explore.NowActivityFragment;

/**
 * Represents the home screen of the app which
 */
public class MainActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ValueEventListener mUserRefListener;
    private double latitude;
    private double longitude;
    private Firebase firebaseUserFollowsRef;
    private UserFollows userFollows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        getCurrentLocation();
        //getUserFollows();
    }

    public void getCurrentLocation(){
        //Get current or last recorded location
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.v("Location", String.valueOf(mLastLocation.getLatitude()));
            Log.v("Location",String.valueOf(mLastLocation.getLongitude()));
            latitude = mLastLocation.getLatitude();
            Log.v("Location", Double.toString(latitude));
            longitude = mLastLocation.getLongitude();
        } else {
            Log.v("Location", "Null");
        }
    }


    /**
     * Override onOptionsItemSelected to use main_menu instead of BaseActivity menu
     *
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Override onOptionsItemSelected to add action_settings only to the MainActivity
     *
     * @param item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Link layout elements from XML and setup the toolbar
     */
    public void initializeScreen() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        /**
         * Create SectionPagerAdapter, set it as adapter to viewPager with setOffscreenPageLimit(2)
         **/
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        /**
         * Setup the mTabLayout with view pager
         */
        //tabLayout.setSelectedTabIndicatorHeight(15);
        //tabLayout.setSelectedTabIndicatorColor(00000000);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onConnected(Bundle bundle) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            Log.v("Location", String.valueOf(mLastLocation.getLatitude()));
            Log.v("Location",String.valueOf(mLastLocation.getLongitude()));
            latitude = mLastLocation.getLatitude();
            Log.v("Location", Double.toString(latitude));
            longitude = mLastLocation.getLongitude();
        } else {
            Log.v("Location", "Null");
        }
        initializeScreen();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * SectionPagerAdapter class that extends FragmentStatePagerAdapter to save fragments state
     */
    public class SectionPagerAdapter extends FragmentStatePagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Use positions (0 and 1) to find and instantiate fragments with newInstance()
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            /**
             * Set fragment to different fragments depending on position in ViewPager
             */
            switch (position) {
                case 0:
                    fragment = ExploreActivityFragment.newInstance();
                    break;
                case 1:
                    fragment = NowActivityFragment.newInstance();
                    break;
                case 2:
                    fragment = ExploreActivityFragment.newInstance();
                    break;
                default:
                    fragment = ExploreActivityFragment.newInstance();
                    break;
            }

            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        /**
         * Set string resources as titles for each fragment by its position
         *
         * @param position
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.pager_title_now);
                case 1:
                    return getString(R.string.pager_title_explore);
                case 2:
                    return getString(R.string.pager_title_followings);
                default:
                    return getString(R.string.pager_title_now);
            }
        }
    }

    //for testing
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
