package com.example.mintree.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mintree.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import myClass.Prime;
import myView.DrawTree;

public class Fragment_prim extends Fragment {
    private Button automatic,next,back;
    private TextView point;
    private DrawTree drawTree;
    private static boolean is_run;
    private Prime prime;
    private int min_size;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_prim,container,false);
        automatic=view.findViewById(R.id.automatic);
        next=view.findViewById(R.id.next);
        back=view.findViewById(R.id.back);
        point=view.findViewById(R.id.point);
        drawTree=getActivity().findViewById(R.id.drawTree);

        prime=new Prime(drawTree);
        is_run=false;
        min_size=0;
        int min=prime.MiniSpanTree_Prim(drawTree.getSelect());
        point.setText((char)(drawTree.getSelect()+'A')+":"+min);
        init();
        return view;
    }
    private void init(){

        automatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_run){
                    return;
                }
                min_size=0;
                //
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        is_run=true;
                        int i;
                        for(i=1;i<=drawTree.min_sides.size()&&is_run;i++){
                            drawTree.setMin_size(i);
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        is_run=false;
                    }
                }).start();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_run){
                    return;
                }
                if(min_size==0){
                    min_size++;
                    drawTree.setMin_size(min_size);
                }else {
                    min_size++;
                    drawTree.setMin_size(min_size);
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_run=false;
                min_size=0;
                drawTree.min_sides.clear();
                drawTree.setMin_size(0);
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_select()).commit();
            }
        });

    }


}
