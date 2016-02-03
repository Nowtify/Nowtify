package com.udacity.firebase.nowtify.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.google.android.gms.common.api.GoogleApiClient;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.model.User;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.ui.Explore.ExploreActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.Utils;

import java.util.HashMap;

/**
 * Created by MohamedAfiq on 21/1/16.
 */
public class AddDetailsActivity extends BaseActivity implements
    GoogleApiClient.OnConnectionFailedListener{
    private static final String LOG_TAG = AddDetailsActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private String mEditGender, oldPassword;
    private Long mEditDateOfBirth;
    private EditText mCreateNewPassword1, mCreateNewPassword2, mEditOccupation;
    private String mUserEmail, mPassword;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_details);

            /**
             * Create Firebase references
             */
            mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

            oldPassword = getIntent().getStringExtra("oldPassword");

            /**
             * Link layout elements from XML and setup the progress dialog
             */
            initializeScreen();
        }

        /**
         * Link layout elements from XML and setup the progress dialog
         */
    public void initializeScreen() {
        mCreateNewPassword1 = (EditText) findViewById(R.id.create_new_password);
        mCreateNewPassword2 = (EditText) findViewById(R.id.create_retype_password);
        mEditOccupation = (EditText) findViewById(R.id.edit_text_password);

        LinearLayout linearLayoutAddDetailsActivity = (LinearLayout) findViewById(R.id.linear_layout_add_details_activity);
        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mAuthProgressDialog.setCancelable(false);
    }


    public void onAddDetailsPressed(View view) {changeUserPassword();}

    /**
     * Change password details to Firebase when user clicks Enter Nowtify
     */
    public void changeUserPassword() {
        final AuthData authData = mFirebaseRef.getAuth();

        // Change password of the user
        final String unprocessedEmail = authData.getProviderData().get(Constants.FIREBASE_PROPERTY_EMAIL).toString().toLowerCase();
        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        mEncodedEmail = Utils.encodeEmail(unprocessedEmail);
        mAuthProgressDialog.show();
        mFirebaseRef.changePassword(unprocessedEmail, oldPassword, mCreateNewPassword1.getText().toString() , new Firebase.ResultHandler() {

            final Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);


            @Override
            public void onSuccess() {
                /* The password was changed */
                Log.d(LOG_TAG, getString(R.string.log_message_password_changed_successfully) + mCreateNewPassword1.getText().toString());
                addUserDetails(unprocessedEmail, authData);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d(LOG_TAG, getString(R.string.log_error_failed_to_change_password) + firebaseError);
            }
        });
    }

    /**
     * Change password details to Firebase when user clicks Enter Nowtify
     */
    public void addUserDetails(String unprocessedEmail, AuthData authData) {
        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        mEncodedEmail = Utils.encodeEmail(unprocessedEmail);

        final Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);

        /**
         * Set raw version of date to the ServerValue.TIMESTAMP value and save into
         * timestampCreatedMap
         */
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        /* build the User object */
        User user = new User(unprocessedEmail, timestampCreated, mEditGender, mEditDateOfBirth, "Student", true);
        mAuthProgressDialog.show();
        /* add user details */
        //userRef.child(Constants.FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD).setValue(true);
        userRef.setValue(user, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                //changeUtilsMessage(firebaseError);
                Intent intent = new Intent(AddDetailsActivity.this, ExploreActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Override onCreateOptionsMenu to inflate nothing
     *
     * @param menu The menu with which nothing will happen
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void takeUserToLoginScreenOnUnAuth() {
        /* Move user to LoginActivity, and remove the backstack */
        Intent intent = new Intent(AddDetailsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /* Cleanup the AuthStateListener */
        if (!((this instanceof AddDetailsActivity))) {
            mFirebaseRef.removeAuthStateListener(mAuthListener);
        }

    }

    public void selectGender(View view){
        SetGenderFragment gender_dialog = new SetGenderFragment();
        gender_dialog.show(getSupportFragmentManager(), "dialog_gender_fragment");

    }


    public void setGender(String selection){
        mEditGender = (String) selection;
    }

    public void selectDateOfBirth(View view){
        SetDateOfBirthFragment dob = new SetDateOfBirthFragment();
        dob.show(getFragmentManager(), "dob");
    }

    public void setDateOfBirth(String selection){
        mEditDateOfBirth = Long.parseLong(selection);
        Log.d(LOG_TAG, selection);
    }


}