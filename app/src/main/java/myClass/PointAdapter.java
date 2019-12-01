package myClass;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mintree.R;
import com.example.mintree.myInterface.OnRecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> implements OnRecyclerViewClickListener {
    private List<String> stringList;
    private OnRecyclerViewClickListener listener;

    public PointAdapter(int n){
        stringList=new ArrayList<String>();
        for(int i=0;i<n;i++){
            stringList.add((char)(i+'A')+"");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.point_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);

        //接口回调
        if(listener != null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(v);
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public void onItemClickListener(View view) {

    }

    public void setItemClickListener(OnRecyclerViewClickListener itemClickListener) {
        listener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.point_text);
        }
    }
}
