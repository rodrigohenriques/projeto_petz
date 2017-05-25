package br.com.projeto.pets.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;

public class AdoptionFilterFragment extends Fragment implements ActivityImpl {

    private static final String ARG_TAG = "ARG_TAG";
    private String mFragmentTag;

    private OnFragmentInteractionListener mListener;

    public AdoptionFilterFragment() {
        // Required empty public constructor
    }

    public static AdoptionFilterFragment newInstance(String tag) {
        AdoptionFilterFragment fragment = new AdoptionFilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFragmentTag = getArguments().getString(ARG_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fgm_adoption_filter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind();
        listners();
        init();
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
    public void bind() {

    }

    @Override
    public void listners() {

    }

    @Override
    public void init() {

    }

    public interface OnFragmentInteractionListener {
    }
}
