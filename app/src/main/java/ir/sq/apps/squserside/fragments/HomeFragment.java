package ir.sq.apps.squserside.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.activities.ClubProfileActivity;
import ir.sq.apps.squserside.controllers.ClubHandler;
import ir.sq.apps.squserside.adapters.ClubsListAdapter;
import ir.sq.apps.squserside.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ClubsListAdapter adapter;
    private Scene startScene, endScene;
    private View receiptView;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView1;
    private HomeFragment.OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        receiptView = getLayoutInflater().inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

        // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed (Uri uri){
            if (mListener != null) {
                mListener.onFragmentInteraction(uri);
            }
        }

        @Override
        public void onAttach (Context context){
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

        @Override
        public void onDetach () {
            super.onDetach();
            mListener = null;
        }

        /**
         * This interface must be implemented by activities that contain this
         * fragment to allow an interaction in this fragment to be communicated
         * to the activity and potentially other fragments contained in that
         * activity.
         * <p>
         * See the Android Training lesson <a href=
         * "http://developer.android.com/training/basics/fragments/communicating.html"
         * >Communicating with Other Fragments</a> for more information.
         */
        public interface OnFragmentInteractionListener {
            // TODO: Update argument type and name
            void onFragmentInteraction(Uri uri);
        }
        private void setUpRecyclerView() {
            adapter = new ClubsListAdapter(getActivity(), ClubHandler.getInstance().getClubs(), new ClubsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(),ClubProfileActivity.class);
                    intent.putExtra(Constants.CLUB_INTENT_NAME, position);
                    startActivity(intent);
                }
            });
            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView1.setAdapter(adapter);
        }
    }

