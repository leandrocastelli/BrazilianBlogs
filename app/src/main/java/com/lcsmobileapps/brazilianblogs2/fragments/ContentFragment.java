package com.lcsmobileapps.brazilianblogs2.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcsmobileapps.brazilianblogs2.R;
import com.lcsmobileapps.brazilianblogs2.adapter.PostAdapter;


public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "index";



    private int index;
    private int mainImage;
    private FragmentCallback mCallback;


    public static ContentFragment newInstance(int index) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_blogs,container);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PostAdapter());
        return view;
    }

    public interface FragmentCallback {
        public void onFragmentAttached(int imageId);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback =(FragmentCallback)context;
        }catch (ClassCastException ex) {
            throw new ClassCastException("This Activity must Implement FragmentCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (mCallback != null) {
            mCallback = null;
        }
    }
}
