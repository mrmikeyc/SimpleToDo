package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

// Responsible for displaying the data from the model into a row in the Recycler View
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    // In order to be able to Long click an item to delete it, we will need an interface that the main activity implements
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener onClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener onClickListener) {
        this.longClickListener = longClickListener;
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Use layout inflator to inflate view
        View toDoView = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        // Wrap it inside view holder and return it
        return new ViewHolder(toDoView);
    }

    // Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // Grab the item at the postion
        String item = items.get(i);

        // Bind the item into the specified viewholder
        viewHolder.bind(item);
    }

    // Tells how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Define ViewHolder - Container to provide easy access to views that represent each row in the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // This is what our 'simple_list_item_1' is referenced by.
            textViewItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with the data of String item
        public void bind(String item) {
            textViewItem.setText(item);
            textViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Inform which was tapped
                    onClickListener.onItemClicked(getAdapterPosition());
                }
            });
            textViewItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // When the item is long pressed, notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
