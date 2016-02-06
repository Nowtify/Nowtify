package com.udacity.firebase.nowtify.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MohamedAfiq on 21/1/16.
 */
public class AddDetailsActivity extends BaseActivity implements
    GoogleApiClient.OnConnectionFailedListener{
    private static final String LOG_TAG = AddDetailsActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private String mEditGender, oldPassword, mEditDateOfBirth, mEditOccupation;
    private EditText mCreateNewPassword1, mCreateNewPassword2;
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
        mEditOccupation = "";
        mEditGender = "";

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

        /**
         * If email and password are not empty show progress dialog and try to authenticate
         */
        String password1 = mCreateNewPassword1.getText().toString();
        String password2 = mCreateNewPassword2.getText().toString();
        if (password1.equals("")) {
            mCreateNewPassword1.setError(getString(R.string.error_cannot_be_empty));
            return;
        }
        if (password2.equals("")) {
            mCreateNewPassword1.setError(getString(R.string.error_cannot_be_empty));
            return;
        }
        if (!password2.equals(password1)) {
            mCreateNewPassword1.setError(getString(R.string.error_password_do_not_match));
            mCreateNewPassword2.setError(getString(R.string.error_password_do_not_match));
            return;
        }
        if(mEditGender.equals("ENTER YOUR GENDER") || mEditGender==null || mEditGender.equals("") ){
            showErrorToast(getString(R.string.error_please_input_gender));
            return;
        }
        if(mEditDateOfBirth==null){
            showErrorToast(getString(R.string.error_please_input_birthdate));
            return;
        }
        if(mEditOccupation.equals("ENTER YOUR OCCUPATION") || mEditOccupation==null || mEditOccupation.equals("") ){
            showErrorToast(getString(R.string.error_please_input_occupation));
            return;
        }

        final AuthData authData = mFirebaseRef.getAuth();

        // Change password of the user
        final String unprocessedEmail = authData.getProviderData().get(Constants.FIREBASE_PROPERTY_EMAIL).toString().toLowerCase();
        /**
         * Encode user email replacing "." with ","
         * to be able to use it as a Firebase db key
         */
        mEncodedEmail = Utils.encodeEmail(unprocessedEmail);


        mAuthProgressDialog.show();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                mAuthProgressDialog.dismiss();
            }
        };

        timer.schedule(task, 4000);

        mFirebaseRef.changePassword(unprocessedEmail, oldPassword, mCreateNewPassword1.getText().toString(), new Firebase.ResultHandler() {

            final Firebase userRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mEncodedEmail);


            @Override
            public void onSuccess() {
                /* The password was changed */
                Log.d(LOG_TAG, getString(R.string.log_message_password_changed_successfully) + mCreateNewPassword1.getText().toString());
                addUserDetails(unprocessedEmail, authData);
            }

            @Override
            public void onError(FirebaseError firebaseError) {

                /**
                 * Use utility method to check the network connection state
                 * Show "No network connection" if there is no connection
                 * Show Firebase specific error message otherwise
                 */
                switch (firebaseError.getCode()) {
                    case FirebaseError.INVALID_EMAIL:
                        showErrorToast(getString(R.string.error_message_email_issue));
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        showErrorToast(getString(R.string.error_message_email_issue));
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        showErrorToast(firebaseError.getMessage());
                        break;
                    case FirebaseError.NETWORK_ERROR:
                        showErrorToast(getString(R.string.error_message_failed_sign_in_no_network));
                        break;
                    default:
                        showErrorToast(firebaseError.toString());
                }
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

                if (firebaseError == null) {
                    Intent intent = new Intent(AddDetailsActivity.this, ExploreActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    mAuthProgressDialog.dismiss();
                    showErrorToast(firebaseError.getMessage());
                    return;
                }

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
        mEditDateOfBirth = selection;
        Log.d(LOG_TAG, selection);
    }

    public void selectOccupation(View view){
        SetOccupationFragment occupation_dialog = new SetOccupationFragment();
        occupation_dialog.show(getSupportFragmentManager(), "dialog_occupation_fragment");
    }


    public void setOccupation(String selection) {
        mEditOccupation = (String) selection;
        Log.d(LOG_TAG, selection);
    }

    /**
     * Show error toast to users
     */
    private void showErrorToast(String message) {
        Toast.makeText(AddDetailsActivity.this, message, Toast.LENGTH_LONG).show();
    }


}