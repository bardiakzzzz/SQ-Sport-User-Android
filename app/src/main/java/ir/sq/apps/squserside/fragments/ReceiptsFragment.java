package ir.sq.apps.squserside.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.sq.apps.squserside.R;
import ir.sq.apps.squserside.adapters.ReceiptAdapter;
import ir.sq.apps.squserside.models.Receipt;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReceiptsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceiptsFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.receipts_recycler_view)
    RecyclerView receiptsRecyclerView;
    @BindView(R.id.root_scene)
    FrameLayout rootScene;
    private ReceiptAdapter adapter;
    private OnFragmentInteractionListener mListener;
    private List<Receipt> items;
    private ReceiptAdapter.OnItemClickListener onClick;
    private Scene startScene, endScene;
    private Transition transition;
    private View receiptView;

    public ReceiptsFragment() {
    }

    public static ReceiptsFragment newInstance() {
        return new ReceiptsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reciepts, container, false);
        receiptView = getLayoutInflater().inflate(R.layout.layout_receipt, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            startScene = Scene.getSceneForLayout(rootScene, R.layout.fragment_reciepts, getContext());
            endScene = Scene.getSceneForLayout(rootScene, R.layout.layout_receipt, getContext());
            transition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.test);
        }
        setItems();
        setOnClick();
        adapter = new ReceiptAdapter(getActivity(), items, onClick);
        receiptsRecyclerView.setAdapter(adapter);
        receiptsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    private void setOnClick() {
        onClick = new ReceiptAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.circle_animation);
                final View v = receiptView.findViewById(R.id.place_animator_view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.go(endScene, transition);
                    transition.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionStart(Transition transition) {

                        }

                        @Override
                        public void onTransitionEnd(Transition transition) {
                            v.startAnimation(animation);

                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {

                        }

                        @Override
                        public void onTransitionPause(Transition transition) {

                        }

                        @Override
                        public void onTransitionResume(Transition transition) {

                        }
                    });
                }
            }
        };
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void setItems() {
        items = new ArrayList<>();
        items.add(new Receipt(50000, "1397/4/2", "باشگاه نمونه 1"));
        items.add(new Receipt(20000, "1397/7/2", "باشگاه نمونه 2"));
        items.add(new Receipt(8000, "1396/5/18", "باشگاه نمونه 3"));
        items.add(new Receipt(100000, "1394/2/2", "باشگاه نمونه 4"));
    }
}
