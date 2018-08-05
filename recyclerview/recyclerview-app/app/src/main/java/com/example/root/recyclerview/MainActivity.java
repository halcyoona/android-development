package com.example.root.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements greenAdapter.ListItemClickListener{

    private static final int NUM_LIST_ITEM = 50;
    private greenAdapter mAdapter;
    private RecyclerView mNumberList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberList = (RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mNumberList.setLayoutManager(layoutManager);

        mNumberList.setHasFixedSize(true);

        mAdapter = new greenAdapter(NUM_LIST_ITEM, this);

        mNumberList.setAdapter(mAdapter);
    }

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
                mAdapter = new greenAdapter(NUM_LIST_ITEM,this);
                mNumberList.setAdapter(mAdapter);
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
}
