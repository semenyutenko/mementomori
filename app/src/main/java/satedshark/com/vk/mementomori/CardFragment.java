package satedshark.com.vk.mementomori;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class CardFragment extends Fragment {

    private static final String PAST_TIME = "pastTime";
    private static final String TIME_LEFT = "timeLeft";

    private TextView mTextPastTime;
    private TextView mTextTimeLeft;
    private Button mButtonSettings;
    private int pastTime;
    private int timeLeft;

    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance(int pastTime, int timeLeft) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(PAST_TIME, pastTime);
        args.putInt(TIME_LEFT, timeLeft);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pastTime = getArguments().getInt(PAST_TIME);
            timeLeft = getArguments().getInt(TIME_LEFT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card, container, false);
        mTextPastTime = v.findViewById(R.id.textPastTime);
        mTextTimeLeft = v.findViewById(R.id.textTimeLeft);
        mButtonSettings = v.findViewById(R.id.buttonSettings);
        mTextPastTime.setText("" + pastTime);
        mTextTimeLeft.setText("" + timeLeft);
        mButtonSettings.setOnClickListener((View.OnClickListener) getActivity());
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
