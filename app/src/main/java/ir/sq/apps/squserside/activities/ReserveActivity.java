package ir.sq.apps.squserside.activities;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.ClubHandler;
import ir.sq.apps.squserside.controllers.PlanHandler;
import ir.sq.apps.squserside.controllers.RequestHandler;
import ir.sq.apps.squserside.models.Club;
import ir.sq.apps.squserside.models.Plan;
import ir.sq.apps.squserside.uiControllers.TypeFaceHandler;
import ir.sq.apps.squserside.utils.Constants;
import ir.sq.apps.squserside.views.MonthLoader;
import ir.sq.apps.squserside.views.WeekView;
import ir.sq.apps.squserside.views.WeekViewEvent;

public class ReserveActivity extends AppCompatActivity implements MonthLoader.MonthChangeListener, WeekView.EventClickListener {

    @BindView(R.id.weekView)
    WeekView weekView;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Club club;
    private Scene startScene, endScene;
    private Transition transition;
    private boolean flag = true;
    private Plan plan;

    TextView clubNameTextView;
    TextView clubNameInputTextView;
    TextView planPriceNameTextView;
    TextView planPriceTextView;
    TextView planDateNameTextView;
    TextView planDateTextView;
    TextView planTimeNameTextView;
    TextView planTimeTextView;
    Button btnReserve;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        ButterKnife.bind(this);
        setToolbar();
        weekView.setMonthChangeListener(this);
        weekView.setOnEventClickListener(this);
        mHandler = new Handler();
        int pos = getIntent().getIntExtra(Constants.CLUB_INTENT_NAME, -1);
        if (pos != -1) {
            club = ClubHandler.getInstance().getClubs().get(pos);
        } else {
            Log.e("Intent Error", "Position Is -1");
            finish();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            startScene = Scene.getSceneForLayout(container, R.layout.layout_scene_weeklyplan, this);
            endScene = Scene.getSceneForLayout(container, R.layout.layout_scene_reserve_plan, this);
            transition = TransitionInflater.from(this).inflateTransition(R.transition.test);
            startScene.setEnterAction(new Runnable() {
                @Override
                public void run() {
                    ButterKnife.bind(ReserveActivity.this);
                    weekView.setMonthChangeListener(ReserveActivity.this);
                    weekView.setOnEventClickListener(ReserveActivity.this);
                }
            });
            endScene.setEnterAction(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        View view = endScene.getSceneRoot();
                        findViews(view);
                        setFonts();
                        setFields(plan);
                    }
                }
            });
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("");
        toolbarTitle.setText("برنامه زمان بندی سانس ها");
        toolbarTitle.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
    }

    private void findViews(View root) {
        clubNameTextView = root.findViewById(R.id.club_name_text_view);
        clubNameInputTextView = root.findViewById(R.id.club_name_input_text_view);
        planPriceNameTextView = root.findViewById(R.id.plan_price_name_text_view);
        planPriceTextView = root.findViewById(R.id.plan_price_text_view);
        planDateNameTextView = root.findViewById(R.id.plan_date_name_text_view);
        planDateTextView = root.findViewById(R.id.plan_date_text_view);
        planTimeNameTextView = root.findViewById(R.id.plan_time_name_text_view);
        planTimeTextView = root.findViewById(R.id.plan_time_text_view);
        btnReserve = root.findViewById(R.id.btnShowPlan);
    }

    private void setFields(final Plan plan) {
        clubNameInputTextView.setText(club.getName());
        planPriceTextView.setText(club.getPrice());
        planDateTextView.setText(plan.getDate());
        String time[] = plan.getTime().split("-");
        planTimeTextView.setText("شروع سانس: " + time[0] + " پایان سانس: " + time[1]);
        finish();
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestHandler.reserve(club, plan, ReserveActivity.this, new RequestHandler.OnResponseTransfer() {
                    @Override
                    public Intent go() {
                        return new Intent(ReserveActivity.this, NavHolderActivity.class);
                    }
                });
            }
        });
    }

    private void setFonts() {
        clubNameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        clubNameInputTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planDateNameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planDateTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planPriceNameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planPriceTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planTimeNameTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        planTimeTextView.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
        btnReserve.setTypeface(TypeFaceHandler.getInstance(this).getFa_light());
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        long id = event.getId();
        plan = PlanHandler.getPlanById(club, id);
        Log.i("ID", id + "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (plan.getStatus() == Constants.PLAN_AVAILABLE) {
                TransitionManager.go(endScene, transition);
                flag = !flag;
            }
        }

    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return PlanHandler.getEventsFromClub(this, club, newYear, newMonth);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!flag) {
                TransitionManager.go(startScene, transition);
                flag = !flag;
            } else {
                super.onBackPressed();
            }
        }
    }
}
