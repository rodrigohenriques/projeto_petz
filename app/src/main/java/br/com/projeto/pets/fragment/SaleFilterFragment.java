package br.com.projeto.pets.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.ActivityImpl;

public class SaleFilterFragment extends Fragment implements ActivityImpl {

    private static final String ARG_TAG = "ARG_TAG";
    private String mFragmentTag;

    private AppCompatSpinner spnBreed;

    private OnFragmentInteractionListener mListener;

    public SaleFilterFragment() {
        // Required empty public constructor
    }

    public static SaleFilterFragment newInstance(String tag) {
        SaleFilterFragment fragment = new SaleFilterFragment();
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
        return inflater.inflate(R.layout.fgm_sale_filter, container, false);
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
        spnBreed = (AppCompatSpinner) getActivity().findViewById(R.id.spnBreedSale);
    }

    @Override
    public void listners() {

    }

    @Override
    public void init() {
        initSpn();
    }

    public interface OnFragmentInteractionListener {
    }

    private void initSpn() {
        List<String> labels = new ArrayList<>();
        labels.add("Raça");

        String[] arrayMock = getResources().getStringArray(R.array.mock_array);


        for (int i = 0; i < arrayMock.length; i++) {
            labels.add(arrayMock[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, labels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBreed.setAdapter(arrayAdapter);
    }
}
