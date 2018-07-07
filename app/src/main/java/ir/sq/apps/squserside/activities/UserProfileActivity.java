package ir.sq.apps.squserside.activities;

import android.os.Bundle;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.UserHandler;
import ir.sq.apps.squserside.models.User;
import ir.sq.apps.squserside.uiControllers.TypeFaceHandler;

public class UserProfileActivity extends AppCompatActivity {

    @BindView(R.id.balance_text_view)
    TextView balanceTextView;
    @BindView(R.id.balance_title_text_view)
    TextView balanceTitleTextView;
    @BindView(R.id.name_title_text_view)
    TextView nameTitleTextView;
    @BindView(R.id.name_text_view)
    TextView nameTextView;
    @BindView(R.id.user_name_title_text_view)
    TextView userNameTitleTextView;
    @BindView(R.id.user_name_text_view)
    TextView userNameTextView;
    @BindView(R.id.email_title_text_view)
    TextView emailTitleTextView;
    @BindView(R.id.email_text_view)
    TextView emailTextView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        setFonts();
        user = UserHandler.getInstance().getUser();
        UserHandler.getInstance().setUser(user);
        setFields();
    }

    private void setFields() {
        if (user != null) {
            nameTextView.setText(user.getName());
            userNameTextView.setText(user.getUserName());
            emailTextView.setText(user.getEmail());
        } else {
            throw new NullPointerException("User Is Null");
        }
    }

    private void setFonts() {
        balanceTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        balanceTitleTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        nameTitleTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        nameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        userNameTitleTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
//        userNameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        emailTitleTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
//        emailTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
    }
}
