package com.udacity.firebase.nowtify.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.utils.Constants;
import com.udacity.firebase.nowtify.utils.FirebaseUtils;
import com.udacity.firebase.nowtify.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MohamedAfiq on 21/1/16.
 */
public class AddDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = AddDetailsActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private EditText mCreateNewPassword1, mCreateNewPassword2, mEditGender, mEditOccupation, mEditDateOfBirth;
    private String mUserEmail, mPassword;

        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
        private GoogleApiClient client;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_details);

            /**
             * Create Firebase references
             */
            mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

            /**
             * Link layout elements from XML and setup the progress dialog
             */
            initializeScreen();
            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        }

        /**
         * Link layout elements from XML and setup the progress dialog
         */
    public void initializeScreen() {
        mCreateNewPassword1 = (EditText) findViewById(R.id.hint_enter_new_password_1);
        mCreateNewPassword2 = (EditText) findViewById(R.id.hint_enter_new_password_2);
        mEditGender = (EditText) findViewById(R.id.edit_text_password);
        mEditOccupation = (EditText) findViewById(R.id.edit_text_password);
        mEditDateOfBirth = (EditText) findViewById(R.id.edit_text_password);

        LinearLayout linearLayoutAddDetailsActivity = (LinearLayout) findViewById(R.id.linear_layout_add_details_activity);
        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mAuthProgressDialog.setCancelable(false);
        /* Comment this out for now. Setup Google Sign In */
        //setupGoogleSignIn();
    }

    public long formatDateInput(int day, int month, int year){
        long toReturn;
        Date date = new Date();

        String inString = Integer.toString(year) + Integer.toString(month) + Integer.toString(year);
        Integer value = Integer.parseInt(inString);
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");

        try{
            date = originalFormat.parse(value.toString());
        } catch (Exception exception){

        }

        toReturn = date.getTime();

        return toReturn;
    }

    public void onAddDetailsPressed(View view) {addUserDetails();}

    /**
     * Add user details to Firebase when user clicks Enter Nowtify
     */
    public void addUserDetails() {
        AuthData authData = mFirebaseRef.getAuth();

        // Change password of the user
        final String unprocessedEmail = authData.getProviderData().get(Constants.FIREBASE_PROPERTY_EMAIL).toString().toLowerCase();
        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        mEncodedEmail = Utils.encodeEmail(unprocessedEmail);
        mFirebaseRef.changePassword(unprocessedEmail, mCreateNewPassword1.getText().toString(), mCreateNewPassword2.getText().toString() , new Firebase.ResultHandler() {
            final Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);


            @Override
            public void onSuccess() {
                userRef.child(Constants.FIREBASE_PROPERTY_USER_HAS_LOGGED_IN_WITH_PASSWORD).setValue(true);

                /* The password was changed */
                Log.d(LOG_TAG, getString(R.string.log_message_password_changed_successfully) + mCreateNewPassword1.getText().toString());
                Log.v(LOG_TAG, "CP3");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d(LOG_TAG, getString(R.string.log_error_failed_to_change_password) + firebaseError);
            }
        });

        // Add details other than password
        FirebaseError checkIfUserIsUpdated;
        FirebaseUtils firebaseUtils = new FirebaseUtils();
        checkIfUserIsUpdated = firebaseUtils.updateUserDetails("afiq980@gmail.com", authData, "M", 1000L, "FUCKEDUP");
        switch (checkIfUserIsUpdated.getCode()) {
            case FirebaseError.INVALID_EMAIL:
            case FirebaseError.USER_DOES_NOT_EXIST:
                //mEditTextEmailInput.setError(getString(R.string.error_message_email_issue));
                break;
            case FirebaseError.INVALID_PASSWORD:
                //mEditTextPasswordInput.setError(checkIfUserIsUpdated.getMessage());
                break;
            case FirebaseError.NETWORK_ERROR:
                //showErrorToast(getString(R.string.error_message_failed_sign_in_no_network));
                break;
            default:
                //showErrorToast(checkIfUserIsUpdated.toString());
        }
        Log.v(LOG_TAG, "User details added");

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


}