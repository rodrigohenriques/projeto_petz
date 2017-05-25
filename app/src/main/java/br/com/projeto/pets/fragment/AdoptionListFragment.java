package br.com.projeto.pets.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.projeto.pets.R;
import br.com.projeto.pets.activity.AdActivity;
import br.com.projeto.pets.adapter.list.AdoptionListAdapter;
import br.com.projeto.pets.adapter.list.SalesListAdapter;
import br.com.projeto.pets.model.Mock;
import br.com.projeto.pets.utils.ActivityImpl;
import br.com.projeto.pets.utils.CustomContext;
import br.com.projeto.pets.utils.Util;

public class AdoptionListFragment extends Fragment implements ActivityImpl {

    private static final String ARG_TAG = "ARG_TAG";
    private String mFragmentTag;

    private RecyclerView listAdoption;
    private FloatingActionButton fab;

    private OnFragmentInteractionListener mListener;

    public AdoptionListFragment() {
        // Required empty public constructor
    }

    public static AdoptionListFragment newInstance(String tag) {
        AdoptionListFragment fragment = new AdoptionListFragment();
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
        return inflater.inflate(R.layout.fgm_adoption_list, container, false);
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
        listAdoption = (RecyclerView) getActivity().findViewById(R.id.listAdoption);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fabAdoptionList);
    }

    @Override
    public void listners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), AdActivity.class));
            }
        });
    }

    @Override
    public void init() {
        GridLayoutManager layoutManagerQuartos = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        layoutManagerQuartos.setSmoothScrollbarEnabled(true);

        List<Mock> itens = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String url = "";
            if (i % 5 == 0) {
                url =CustomContext.getContext().getResources().getString(R.string.url5);
            } else if (i % 4 == 0) {
                url =CustomContext.getContext().getResources().getString(R.string.url4);
            } else if (i % 3 == 0) {
                url =CustomContext.getContext().getResources().getString(R.string.url3);
            } else if (i % 2 == 0) {
                url =CustomContext.getContext().getResources().getString(R.string.url2);
            } else {
                url =CustomContext.getContext().getResources().getString(R.string.url1);
            }
            itens.add(new Mock(i, url));
        }

        AdoptionListAdapter mockAdapter = new AdoptionListAdapter(itens, getActivity());

        listAdoption.setLayoutManager(layoutManagerQuartos);
        listAdoption.setAdapter(mockAdapter);
        listAdoption.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Util.hideKeyboard(getActivity(), getView());
            }
        });

    }

    public interface OnFragmentInteractionListener {
    }
}
