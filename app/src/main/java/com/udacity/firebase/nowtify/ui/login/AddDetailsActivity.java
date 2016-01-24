package com.udacity.firebase.nowtify.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.udacity.firebase.nowtify.R;
import com.udacity.firebase.nowtify.ui.BaseActivity;
import com.udacity.firebase.nowtify.utils.Constants;

/**
 * Created by MohamedAfiq on 21/1/16.
 */
public class AddDetailsActivity extends BaseActivity {
    private static final String LOG_TAG = AddDetailsActivity.class.getSimpleName();
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private EditText mCreateNewPassword1, mCreateNewPassword2, mEditGender;
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
        LinearLayout linearLayoutAddDetailsActivity = (LinearLayout) findViewById(R.id.linear_layout_add_details_activity);
        initializeBackground(linearLayoutAddDetailsActivity);
        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle(getString(R.string.progress_dialog_loading));
        mAuthProgressDialog.setMessage(getString(R.string.progress_dialog_authenticating_with_firebase));
        mAuthProgressDialog.setCancelable(false);
        /* Comment this out for now. Setup Google Sign In */
        //setupGoogleSignIn();
    }

    public void onAddDetailsPressed(View view) {addUserDetails();}

    /**
     * Add user details to Firebase when user clicks Enter Nowtify
     */
    public void addUserDetails() {

        /*
        mFirebaseRef.changeEmail(Constants.KEY_ENCODED_EMAIL, Constants.PASSWORD_PROVIDER, mCreateNewPassword1.getText().toString(), new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // email changed

                /* Go to main activity
                Intent intent = new Intent(AddDetailsActivity.this, NowActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        })
        */

        /*

        FirebaseError checkIfUserIsUpdated;
        FirebaseUtils firebaseUtils = new FirebaseUtils();
        checkIfUserIsUpdated = firebaseUtils.updateUserDetails("afiq980@gmail.com", authData, "M", "Student");
        switch (checkIfUserIsUpdated.getCode()) {
            case FirebaseError.INVALID_EMAIL:
            case FirebaseError.USER_DOES_NOT_EXIST:
                mEditTextEmailInput.setError(getString(R.string.error_message_email_issue));
                break;
            case FirebaseError.INVALID_PASSWORD:
                mEditTextPasswordInput.setError(checkIfUserIsUpdated.getMessage());
                break;
            case FirebaseError.NETWORK_ERROR:
                showErrorToast(getString(R.string.error_message_failed_sign_in_no_network));
                break;
            default:
                showErrorToast(checkIfUserIsUpdated.toString());
        }
        Log.v(LOG_TAG, "User details added");
        */
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