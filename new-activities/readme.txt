New Activities


How to create new activities and transfer data from one activity to another activity..

So far we are working on a single screen on a single activity.
How we start one activity from another activity, instead of having activities directly call each other, android facilitates communication using message object called intent.Intents let an app request that an action take place, that will be any thing i.e start a new activity ,open a gallery or open a camera etc.Intents is like envalop, each one include who, component you want to deliver to.component recieve this and read it. And open the intent.

Working.

First open the activity_main.xml and make linear layout and inside linear layout make EditText and button and give them proper ids.And also set dimens.

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/et_text_entry"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <Button
        android:id="@+id/b_do_something_cool"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"/>

</LinearLayout>   


Open MainActivity.java and create a fields to store button and EditText inside the MainActivity class.

private EditText mNameEntry;
private Button mDoSomethingCoolButton;


Using findViewById, we get a reference to our Button from xml. This allows us to do things like set the onClickListener which determines what happens when the button is clicked.

mDoSomethingCoolButton = (Button) findViewById(R.id.b_do_something_cool);
mNameEntry = (EditText) findViewById(R.id.et_text_entry);

Setting an OnClickListener allows us to do something when this button is clicked.
Storing the Context in a variable in this case is redundant since we could have just used "this" or "MainActivity.this" in the method call below. However, we wanted to demonstrate what parameter we were using "MainActivity.this" for as clear as possible.
The onClick method is triggered when this button (mDoSomethingCoolButton) is clicked.

mDoSomethingCoolButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Context context = MainActivity.this;
        String message = "Button clicked!\nTODO: Start a new Activity and pass some data.";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
});



We are now creating new activity with the help of android studio's activity wizard. Create New Activity with name ChildActivity.
open child_activity.xml and change constraint layout to linear layout and make appropriate setting and create a textView and give id.

<FrameLayout xmlns:tools="http://schemas.android.com/tools"
    android:layout_height="match_parent"
    android:layout_width="match_parent"
    android:id="@+id/activity_child"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    xmlns:android="http://schemas.android.com/apk/res/android"
    tools:ignore="InvalidId">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/tv_display"
        android:text="Default Text"
        android:textSize="30sp"/>
</FrameLayout>

Open ChildActivity.java and make field for TextView inside the childActivity class.

TextView mDisplayText;

Get a reference by findViewById.
mDisplayText = (TextView) findViewById(R.id.tv_display);


open AndroidManifest.xml and make some cahnges inside the application tags.

<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
<activity
    android:name=".ChildActivity">
</activity>


Open MainActivity.java and make some changes in the mDoSomethingCoolButton.Link the mainActivity to childActivity.

mDoSomethingCoolButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Context context = MainActivity.this;

        Class destinationActivity = ChildActivity.class;
        Intent intent = new Intent(context,destinationActivity);
        startActivity(intent);
    }
});

last thing we have to do is passing data between activities.First you have to open MainActivity.java and store the content of edit text into a string variable(And this should be done inside the onClick() Function).

String textEntered = mNameEntry.getText().toString();

To pass this data to child activity you can use the method putextra(). putextra() function need two parameter i.e key and value.  value  is the data that you want to transfer and key is the name of the data that you are transferring.

Intent startChildActivityIntent = new Intent(context,destinationActivity);
startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
startActivity(startChildActivityIntent);

Now move to ChildActivity.java and get the intent from MainActivity.So we can retrieve the data that passed in it.And then if intent has some extra text save it in a string and display the text.

Intent intentThatStartThisActivity = getIntent();
if(intentThatStartThisActivity.hasExtra(Intent.EXTRA_TEXT)){
    String textEntered = intentThatStartThisActivity.getStringExtra(Intent.EXTRA_TEXT);
    mDisplayText.setText(textEntered);
} 

