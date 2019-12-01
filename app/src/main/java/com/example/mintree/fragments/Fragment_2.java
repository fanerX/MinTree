package com.example.mintree.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.mintree.R;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import myView.DrawTree;

public class Fragment_2 extends Fragment {
    private Button back,histroy,save,prim,kruskal;
    private DrawTree drawTree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);
        drawTree=getActivity().findViewById(R.id.drawTree);
        drawTree.setCan_touch(false);

        back=view.findViewById(R.id.back);
        histroy=view.findViewById(R.id.history);
        save=view.findViewById(R.id.save);
        prim=view.findViewById(R.id.prim);
        kruskal=view.findViewById(R.id.kruskal);

        init();

        return view;
    }

    private void init(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_1()).commit();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream out =null;
                BufferedWriter writer = null;
                int i;
                try{
                    out = getActivity().openFileOutput("data.txt",Context.MODE_PRIVATE);
                    //Log.d("Fragment_2", ");
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    //writer
                    writer.write(drawTree.points.size()+"\n");
                    writer.write(drawTree.sides.size()+"\n");
                    for(i=0;i<drawTree.points.size();i++){
                        writer.write(drawTree.points.get(i).toString());
                    }
                    for(i=0;i<drawTree.sides.size();i++){
                        writer.write(drawTree.sides.get(i).toString());
                    }
                    Log.d("Fragment_2", "文件存储成功");
                    Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                }catch (IOException e) {
                    e.printStackTrace();
                    Log.d("Fragment_2", "文件存储失败！");
                } finally {
                    if(writer != null){
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        histroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream in = null;
                BufferedReader reader=null;
                try {
                    in = getActivity().openFileInput("data.txt");
                    reader = new BufferedReader(new InputStreamReader(in));
                    //reader
                    int points,sides,i;
                    drawTree.points.clear();
                    drawTree.sides.clear();
                    points=Integer.parseInt(reader.readLine());
                    sides=Integer.parseInt(reader.readLine());
                    Log.d("Fragment_2", "points："+points);
                    Log.d("Fragment_2", "sides："+sides);
                    for(i=0;i<points;i++){
                        drawTree.points.add(new DrawTree.Point(reader.readLine()));
                    }
                    for (i=0;i<sides;i++){
                        drawTree.sides.add(new DrawTree.Side(reader.readLine()));
                    }

                    Toast.makeText(getContext(),"读取成功",Toast.LENGTH_SHORT).show();
                    drawTree.invalidate();
                }catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"无历史记录！",Toast.LENGTH_SHORT).show();
                } finally {
                    if (reader !=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        prim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_select()).commit();
            }
        });

        kruskal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new Fragment_Kruskal()).commit();
            }
        });

    }


}
