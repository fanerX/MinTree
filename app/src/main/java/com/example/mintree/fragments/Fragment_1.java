package com.example.mintree.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mintree.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import myView.DrawTree;

public class Fragment_1 extends Fragment {
    private int weight;
    private Button add_1,add_10,less_1,less_10,complete;
    private TextView textView;
    private DrawTree drawTree;



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_1,container,false);

        add_1=view.findViewById(R.id.add_1);
        add_10=view.findViewById(R.id.add_10);
        less_1=view.findViewById(R.id.less_1);
        less_10=view.findViewById(R.id.less_10);
        textView=view.findViewById(R.id.weight);
        drawTree=getActivity().findViewById(R.id.drawTree);
        complete=view.findViewById(R.id.complete);

        weight = 1;
        textView.setText(weight+"");
        drawTree.setWeight(weight);
        drawTree.setCan_touch(true);
        init();
        return view;
    }

    private void init(){
        add_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight>=100){
                    return;
                }

                weight+=1;
                drawTree.setWeight(weight);
                textView.setText(weight+"");
            }
        });

        add_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight>90){
                    weight=100;
                }else {
                    weight+=10;
                }
                drawTree.setWeight(weight);
                textView.setText(weight+"");
            }
        });
        less_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight<=1){
                    return;
                }
                weight-=1;
                drawTree.setWeight(weight);
                textView.setText(weight+"");
            }
        });

        less_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight<=10){
                    weight=1;
                }else {
                    weight-=10;
                }
                drawTree.setWeight(weight);
                textView.setText(weight+"");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawTree.cancel();
            }
        });


        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_2()).commit();
            }
        });
    }

}
