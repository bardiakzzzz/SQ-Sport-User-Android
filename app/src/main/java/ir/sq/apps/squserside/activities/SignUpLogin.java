package ir.sq.apps.squserside.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.RequestHandler;
import ir.sq.apps.squserside.controllers.UrlHandler;
import ir.sq.apps.squserside.uiControllers.TypeFaceHandler;
import ir.sq.apps.squserside.utils.Constants;


public class SignUpLogin extends AppCompatActivity implements View.OnClickListener {
    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button signUpbutton;
    private Button signInbutton;
    private TextInputEditText[] signUPviews;
    private TextInputEditText[] signInViews;

    @BindView(R.id.userName_signup)
    TextInputEditText userName_signup;
    @BindView(R.id.passWord_signup)
    TextInputEditText passWord_signup;
    @BindView(R.id.email_signup)
    TextInputEditText email_signup;
    @BindView(R.id.userName_signin)
    TextInputEditText userName_signin;
    @BindView(R.id.passWord_signin)
    TextInputEditText passWord_signin;
    @BindView(R.id.userName_signin_layout)
    TextInputLayout userName_signin_layout;
    @BindView(R.id.passWord_signin_layout)
    TextInputLayout passWord_signin_layout;
    @BindView(R.id.userName_signup_layout)
    TextInputLayout userName_signup_layout;
    @BindView(R.id.passWord_signup_layout)
    TextInputLayout passWord_signup_layout;
    @BindView(R.id.email_signup_layout)
    TextInputLayout email_signup_layout;


    LinearLayout llsignup;


    private static final String TAG_SIGNUP = "SIGN UP";
    private static final String TAG_SIGNIN = "SIGN IN";

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        ButterKnife.bind(this);

        llSignin = findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        llsignup = findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker = findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = findViewById(R.id.tvSigninInvoker);

        signUpbutton = findViewById(R.id.signUP_button);
        signInbutton = findViewById(R.id.signIn_button);

        llSignup = findViewById(R.id.llSignup);
        llSignin = findViewById(R.id.llSignin);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
                emptyErrors();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
                emptyErrors();
            }
        });
        showSigninForm();
        setFonts();
        setviews();
        sharedPref = this.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
        if (!sharedPref.getString(Constants.ACCESS_TOKEN, "").isEmpty()) {
//            finish();
        }

    }

    private void emptyErrors() {
        userName_signup.setError(null);
        email_signup.setError(null);
        passWord_signup.setError(null);
        userName_signin.setError(null);
        passWord_signin.setError(null);
    }

    private void setviews() {
        signUPviews = new TextInputEditText[3];
        signUPviews[0] = userName_signup;
        signUPviews[1] = passWord_signup;
        signUPviews[2] = email_signup;

        signInViews = new TextInputEditText[2];
        signInViews[0] = userName_signin;
        signInViews[1] = passWord_signin;
    }

    private void setFonts() {
        tvSigninInvoker.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        tvSignupInvoker.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        signInbutton.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        signUpbutton.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        passWord_signin.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        passWord_signup.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        userName_signin.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        userName_signup.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        email_signup.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        userName_signin_layout.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        passWord_signin_layout.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        userName_signup_layout.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        passWord_signup_layout.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());
        email_signup_layout.setTypeface(TypeFaceHandler.getInstance(this).getFa_bold());

    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
        signUpbutton.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();
        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_left_to_right);
        signInbutton.startAnimation(clockwise);
    }

    private Boolean checkEmptyFieldsSignUp() {
        Boolean flag = true;
        for (EditText e : signUPviews) {
            if (e.getText().toString().length() == 0) {
                e.setError(getString(R.string.empty_field_error_meesage));
                flag = false;
            }
        }
        return flag;
    }

    private Boolean checkEmptyFieldsSignIn() {
        Boolean flag = true;
        for (EditText e : signInViews) {
            if (e.getText().toString().length() == 0) {
                e.setError(getString(R.string.empty_field_error_meesage));
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn_button:
                if (checkEmptyFieldsSignIn()) {
                    signIn();
                }
                break;
            case R.id.signUP_button:
                Animation clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_right_to_left);
                if (isSigninScreen)
                    signUpbutton.startAnimation(clockwise);
                if (checkEmptyFieldsSignUp()) {
                    signUp();
                }
                break;
        }

    }

    private void signIn() {
        hideKeyboard();
        AndroidNetworking.post(UrlHandler.signInUserURL.getUrl())
                .addHeaders("Content-Type", "application/x-www-form-urlencoded")
                .addBodyParameter("client_id", "trusted-app")
//                .addBodyParameter("client_secret", "secret")
                .addBodyParameter("grant_type", "password")
                .addBodyParameter("username", userName_signin.getText().toString())
                .addBodyParameter("password", passWord_signin.getText().toString())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG_SIGNIN, "Login response: " + response.toString());

                        String access_token = null;

                        try {
                            access_token = response.getString(Constants.ACCESS_TOKEN);
                        } catch (JSONException e) {
                            Log.e(TAG_SIGNIN, "Login response JSONException: " + e.toString());
                        }

                        if (access_token == null) {
                            Log.e(TAG_SIGNIN, "Login response have no access_token parameter.");
                        } else {
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(Constants.ACCESS_TOKEN, "Bearer " + access_token);
                            editor.putString(Constants.USERNAME, userName_signin.getText().toString());
                            editor.apply();
                            RequestHandler.getUser(SignUpLogin.this);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG_SIGNIN, "Login request error: " + anError.getErrorBody() + "\n" + anError.getErrorCode());
                        if (anError.getErrorCode() == 400) {
                            Snackbar.make(signUpbutton, getString(R.string.string_wrong_username_or_password), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void emptyFields() {
        userName_signup.setText("");
        passWord_signup.setText("");
        email_signup.setText("");
        userName_signin.setText("");
        passWord_signin.setText("");
    }

    private void signUp() {
        JSONObject body = new JSONObject();
        try {
            body.put("username", userName_signup.getText().toString());
            body.put("password", passWord_signup.getText().toString());
            body.put("email", email_signup.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(UrlHandler.signUpUserURL.getUrl())
                .addJSONObjectBody(body)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG_SIGNUP, "Signup response: " + response.toString());
                        String status = null;
                        try {
                            status = response.getString(Constants.STATUS);
                        } catch (JSONException e) {
                            Log.e(TAG_SIGNUP, "Signup response JSONException: " + e.toString());
                        }
                        if (status == null) {
                            Log.e(TAG_SIGNUP, "Signup response have no status parameter.");
                        } else if (status.equals(Constants.USERNAME_EXISTS)) {
                            Snackbar.make(signUpbutton, getString(R.string.string_username_exists), Snackbar.LENGTH_SHORT).show();
                        } else if (status.equals((Constants.EMAIL_EXISTS))) {
                            Snackbar.make(signUpbutton, getString(R.string.string_email_exists), Snackbar.LENGTH_SHORT).show();
                        } else if (status.equals(Constants.OK)) {
//                            signedUp(username, password);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG_SIGNUP, "Login request error: " + anError.toString());
                    }
                });
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

