package android.example.tic_tac_toe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {

    String data1[];
    int data2[];
    Context context;
    public MyAdapter(Context ct,String Names[],int score[] ){
        context = ct;
        data1 = Names;
        data2 = score;
    }
    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.playerName);
            myText2 = itemView.findViewById(R.id.score);
        }
    }
}
