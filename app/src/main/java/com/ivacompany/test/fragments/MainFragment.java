package com.ivacompany.test.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivacompany.test.R;
import com.ivacompany.test.TestApp;
import com.ivacompany.test.adapter.FileRecyclerViewAdapter;
import com.ivacompany.test.interfaces.FragmentRequestListener;
import com.ivacompany.test.model.FileModel;
import com.ivacompany.test.utils.Constants;
import com.ivacompany.test.utils.RecycleViewItemDecoration;
import com.ivacompany.test.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 27.12.16.
 */

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    FragmentRequestListener fragmentRequestListener;

    private FileRecyclerViewAdapter adapter;
    private List<FileModel> fileList = new ArrayList<>();

    public static MainFragment newInstance(int id) {
        MainFragment fragment = new MainFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(Constants.ID, id);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        fragmentRequestListener = (FragmentRequestListener) getActivity();

        Utils.initDB();

        fileList = Utils.getFiles(getArguments().getInt(Constants.ID));

        initRecyclerView();

        return root;
    }



    private void initRecyclerView() {
        adapter = new FileRecyclerViewAdapter(fileList, fragmentRequestListener);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(TestApp.getAppContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(
                new RecycleViewItemDecoration(
                        TestApp.getAppContext(),
                        LinearLayoutManager.VERTICAL
                )
        );
        recyclerView.setAdapter(adapter);

    }

}
