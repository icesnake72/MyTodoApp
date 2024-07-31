package com.example.mytodoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class TodoAdaptor extends RecyclerView.Adapter<TodoAdaptor.ViewHolder> {
    private final MyTodos myTodos;

    public TodoAdaptor(MyTodos myTodos) {
        super();
        this.myTodos = myTodos;
    }

    @NonNull
    @Override
    public TodoAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdaptor.ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.getTextTodo().setText(myTodos.getTodos()[position].getTodo());
        holder.getCbDone().setChecked(Boolean.parseBoolean(myTodos.getTodos()[position].getDone().toLowerCase(Locale.ROOT)));
        holder.getTextCreatedTime().setText(myTodos.getTodos()[position].getCreated_time());
    }

    @Override
    public int getItemCount() {
        return myTodos.getTodos().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewDelete;
        private CheckBox cbDone;
        private TextView textTodo;
        private TextView textCreatedTime;

        public ViewHolder(@NonNull View view) {
            super(view);

            imageViewDelete = view.findViewById(R.id.imageViewDelete);
            cbDone = view.findViewById(R.id.cbDone);
            textTodo = view.findViewById(R.id.textViewTodo);
            textCreatedTime = view.findViewById(R.id.textViewCreatedTime);

            cbDone.setOnCheckedChangeListener(((compoundButton, b) -> {

            }));
        }

        private void updateDone(int position, boolean b)
        {

        }

        public ImageView getImageViewDelete() {
            return imageViewDelete;
        }

        public CheckBox getCbDone() {
            return cbDone;
        }

        public TextView getTextTodo() {
            return textTodo;
        }

        public TextView getTextCreatedTime() {
            return textCreatedTime;
        }
    }
}
