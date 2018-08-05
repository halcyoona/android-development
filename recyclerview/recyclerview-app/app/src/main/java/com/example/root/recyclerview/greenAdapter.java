package com.example.root.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class greenAdapter extends RecyclerView.Adapter<greenAdapter.numberViewHolder> {

    private static final String TAG = greenAdapter.class.getSimpleName();
    private int mNumberItems;
    private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;

    public greenAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public numberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        numberViewHolder viewHolder = new numberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        int backgroundColorForViewHolder = colorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(numberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }



    class numberViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView listItemNumberView;
        TextView viewHolderIndex;

        public numberViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            itemView.setOnClickListener(this);
        }

            void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

    }

}
