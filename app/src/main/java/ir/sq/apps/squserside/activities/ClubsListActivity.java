package ir.sq.apps.squserside.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.controllers.ClubHandler;
import ir.sq.apps.squserside.models.Club;
import ir.sq.apps.squserside.uiControllers.ClubsListAdapter;
import ir.sq.apps.squserside.utils.Constants;

public class ClubsListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ClubsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_list);
        ButterKnife.bind(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adapter = new ClubsListAdapter(this, ClubHandler.getInstance().getClubs(), new ClubsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ClubsListActivity.this, ClubProfileActivity.class);
                intent.putExtra(Constants.CLUB_INTENT_NAME, position);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
