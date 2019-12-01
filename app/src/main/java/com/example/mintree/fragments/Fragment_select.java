package com.example.mintree.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mintree.R;
import com.example.mintree.myInterface.OnRecyclerViewClickListener;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import myClass.PointAdapter;
import myView.DrawTree;

public class Fragment_select extends Fragment {
    private RecyclerView recyclerView;
    private DrawTree drawTree;
    private PointAdapter adapter;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_select,container,false);
        button=view.findViewById(R.id.back);
        recyclerView=view.findViewById(R.id.recycler_view);
        drawTree=getActivity().findViewById(R.id.drawTree);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_2()).commit();
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new PointAdapter(drawTree.points.size());
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClickListener(View view) {
                int position =recyclerView.getChildAdapterPosition(view);
                drawTree.setSelect(position);
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_prim()).commit();
            }
        });
        return view;
    }



}
