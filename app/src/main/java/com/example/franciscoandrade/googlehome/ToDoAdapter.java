package com.example.franciscoandrade.googlehome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chelsi on 12/17/2017.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    ArrayList<String> myList;

    public ToDoAdapter(ArrayList<String> myList) {
        this.myList = myList;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtodo, parent,false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        holder.bind(myList.get(position), position);
//        holder.myTask.setText();
                // delete the task by referring to the position in the array list and removing it

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder{
        public TextView myTask;
        CheckBox myCheckBox;
        public ToDoViewHolder(View itemView) {
            super(itemView);
            myTask = (TextView) itemView.findViewById(R.id.taskTextView);
            myCheckBox = (CheckBox) itemView.findViewById(R.id.todoCheckBox);


        }

        public  void bind(final String myTaskString, final int position){
            myTask.setText(myTaskString);
            myCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < myList.size(); i++){
                        if (myList.get(i).equals(myTaskString)){
                            myList.remove(i);
                        }
                    }
                }
            });
        }
    }

}
