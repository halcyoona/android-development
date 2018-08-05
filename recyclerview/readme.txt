Recyclerview

Intro 
RecyclerView is used in most of the application like mail app, weather app and animated app etc. RecyclerView have to deal with fast flings, animation and lots of others condition. If we putt all the items of list in memory that may cause memory problem and performance  problem.  We keep some in recycling bin or queue for reuse.when you are about to scroll, RecyclerView quickly  returns the previously created list items from queue.Your code then  binds the list item to the new content and then it should be scrolled and show it to you.View that are scrolled out are then place back into a queue for reuse.


working:

RecyclerView  have adapter which is used to provied the recyclerView with new views when needed and this adapter is connected with some data source and collect data from data source and add them to views.Adapter send the view to the recyclerView in a object called a viewHolder.The View Holder contains a reference to the root view object for the items.And you are expected to use it to cache the view objects represented in layout, to make it less costly to update the view with new data.This way find a view by Id gets called only for each data view wheneer new item is created.Then layout Manager then tells the recycler View how to lay out all those view.It may be vertical view aur grid view.
It is important that you use the view holder to cache the views other find a view by Id would make the app slow if you have populated with large data, Always kept the views in  view holder that can easily be access.


Getting Started:
First thing you have to do is to open build.grade file to add the dependency of the recyclerView.Add this line in dependencies..
compile 'com.android.support:recyclerview-v7:25.4.0'

We are going to create a colorUtils class with one method use to color the view holder in the recycler View.colorUtils.java is created in the same directory where MainActivrty.java is placed.This method is used to show how ViewHolders are recycled in a RecyclerView.At first, the colors will form a nice, consistent gradient. As the RecyclerView is scrolled.
@param context     Context for getting colors
@param instanceNum Order in which the calling ViewHolder was created.
@return A shade of green based off of when the calling ViewHolder was created.

Note: also add these colors in color.xml

public class colorUtils {

    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        switch (instanceNum) {
            case 0:
                return ContextCompat.getColor(context, R.color.material50Green);
            case 1:
                return ContextCompat.getColor(context, R.color.material100Green);
            case 2:
                return ContextCompat.getColor(context, R.color.material150Green);
            case 3:
                return ContextCompat.getColor(context, R.color.material200Green);
            case 4:
                return ContextCompat.getColor(context, R.color.material250Green);
            case 5:
                return ContextCompat.getColor(context, R.color.material300Green);
            case 6:
                return ContextCompat.getColor(context, R.color.material350Green);
            case 7:
                return ContextCompat.getColor(context, R.color.material400Green);
            case 8:
                return ContextCompat.getColor(context, R.color.material450Green);
            case 9:
                return ContextCompat.getColor(context, R.color.material500Green);
            case 10:
                return ContextCompat.getColor(context, R.color.material550Green);
            case 11:
                return ContextCompat.getColor(context, R.color.material600Green);
            case 12:
                return ContextCompat.getColor(context, R.color.material650Green);
            case 13:
                return ContextCompat.getColor(context, R.color.material700Green);
            case 14:
                return ContextCompat.getColor(context, R.color.material750Green);
            case 15:
                return ContextCompat.getColor(context, R.color.material800Green);
            case 16:
                return ContextCompat.getColor(context, R.color.material850Green);
            case 17:
                return ContextCompat.getColor(context, R.color.material900Green);

            default:
                return Color.WHITE;
        }
    }
}



Move to activity_main.xml and replace the textView with the recyclerView and constraint layout to Framelayout.

<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/rv_numbers"></android.support.v7.widget.RecyclerView>
</FrameLayout>


Next you have to do is create a number_list_item.xml in the layout directory.And add a Framelayout and textView.

Note: sp is used for Font sizes, widget and hard coded sizes that contains fonts but dp is used for padding other purposes.
1 pixel 

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/tv_item_number"
        style="@style/TextAppearance.AppCompat.Title"
        android:textSize="42sp"
        android:fontFamily="monospace"/>

</FrameLayout>


Now we havet to create greeenAdapter class in the same place where MainActivity.java is placed.Make a constructor and that have one parameter.@param numberOfItems Number of items to display in list.

public class greenAdapter extends RecyclerView.Adapter<greenAdapter.numberViewHolder> {
    private static final String TAG = greenAdapter.class.getSimpleName();
    private int mNumberItems;
    public greenAdapter(int numberOfItems) {
        mNumberItems = numberOfItems;
    }
}


Create another function onCreateViewHolder().This gets called when each new ViewHolder is created.
@param viewGroup The ViewGroup that these ViewHolders are contained within.
@param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you can use this viewType integer to provide a different layout
@return A new NumberViewHolder that holds the View for each list item.

@Override
public numberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    Context context = viewGroup.getContext();
    int layoutIdForListItem = R.layout.number_list_item;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;

    View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
    numberViewHolder viewHolder = new numberViewHolder(view);

    return viewHolder;
}



Create another Function onBindViewHolder().OnBindViewHolder is called when the RecyclerView wants to populate the view with the data from our model.So, the user can see it.In this method,  we update the contents of the ViewHolder to display the correct indices in the list for this particular position, using the "position" argument that is conveniently passed into us.
@param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
@param position The position of the item within the adapter's data set.

@Override
public void onBindViewHolder(numberViewHolder holder, int position) {
    Log.d(TAG, "#" + position);
    holder.bind(position);
}



Create another method getItemCount().This simply returns the number of item to display.

@Override
public int getItemCount() {
    return mNumberItems;
}




Create a class inside the greenAdapter class with the name numberViewHolder which extends RecyclerView.ViewHolder.
Create a constructor of numberViewHolder class.Within this constructor, we get a reference to our TextViews and set an onClickListener to listen for clicks. Those will be handled in the onClick method below.
@param itemView The View that you inflated in.


class numberViewHolder extends RecyclerView.ViewHolder{    
    TextView listItemNumberView;
    public NumberViewHolder(View itemView) {
        super(itemView);
        listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
    }

}


Create another function bind() inside the numberViewHolder class.A method we wrote for convenience. This method will take an integer as input and use that integer to display the appropriate text within a list item.
@param listIndex Position of the item in the list.

void bind(int listIndex) {
    listItemNumberView.setText(String.valueOf(listIndex));
}

Now open MainActivity.java and make some changes inside the MainActivty class.

private static final int NUM_LIST_ITEM = 50;
private greenAdapter mAdapter;
private RecyclerView mNumberList;

main some changes insdie the onCreate function inside the MainActivity class.

mNumberList = (RecyclerView) findViewById(R.id.rv_numbers);

LinearLayoutManager layoutManager = new LinearLayoutManager(this);

mNumberList.setLayoutManager(layoutManager);

mNumberList.setHasFixedSize(true);

mAdapter = new greenAdapter(NUM_LIST_ITEM);

mNumberList.setAdapter(mAdapter);


Now launch the App.


Additional.

makes some other changes inside the list_number_item.xml. add textView inside the FrameLayout.

<TextView
    android:id="@+id/tv_view_holder_instance"
    style="@style/TextAppearance.AppCompat.Title"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center_vertical|end"
    android:fontFamily="monospace"
    android:textColor="#000"
    tools:text="ViewHolder instance: 7" />


Make some changes in the greenAdapter class and numberViewHolder class.

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

        public NumberViewHolder(View itemView) {
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

Open strings.xml and make one change.
<string name="action_reset">Reset</string>

And create a menu directory in res directory and in menu directory create main.xml and make a item in the menu.

<menu 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".MainActivity">

    <item
        android:id="@+id/action_refresh"
        android:orderInCategory="1"
        android:title="@string/action_reset"
        app:showAsAction="ifRoom"/>
</menu>


Add directory dimens, to set dimensions for horizontol layout and vertical layout,inside the values directory.inside dimens directory create dimens.xml two files.One file contains this.

<resources>
    <dimen name="activity_horizontal_margin">16dp</dimen>
    <dimen name="activity_vertical_margin">16dp</dimen>
</resources>


And second file contains this.

<resources>
    <dimen name="activity_horizontal_margin">64dp</dimen>
</resources>


Now Move to MainActivity.java file.override some function.
add a variable of Toast as mToast inside MainActivity.

private Toast mToast;

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {

    int itemId = item.getItemId();

    switch (itemId) {
        case R.id.action_refresh:
            mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
            mNumbersList.setAdapter(mAdapter);
            return true;
    }
    return super.onOptionsItemSelected(item);
}


@Override
public void onListItemClick(int clickedItemIndex) {

    if (mToast != null) {
        mToast.cancel();
    }
    String toastMessage = "Item #" + clickedItemIndex + " clicked.";
    mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
    mToast.show();
}


Also change this in onCreate() method inside the MainActivity class.
mAdapter = new greenAdapter(NUM_LIST_ITEM, this);


Also change the header of the class.
public class MainActivity extends AppCompatActivity implements greenAdapter.ListItemClickListener