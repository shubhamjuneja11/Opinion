package com.supergeek.opinion;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.WorkingAdapter;
import model.DataModel;

public class WorkingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<DataModel>> {
WorkingAdapter adapter;
    ArrayList<DataModel> list;
    RecyclerView recyclerView;
    String url="http://localhost/opinionserver/readdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        list=new ArrayList<>();
        adapter=new WorkingAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        preparedata();
    }

    public void preparedata(){
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();

        if(networkInfo!=null&&networkInfo.isConnected()){
            LoaderManager loaderManager=getSupportLoaderManager();
            list.clear();
            loaderManager.restartLoader(0,null,this).forceLoad();
        }
        else{
            Toast.makeText(this, "Internet is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<ArrayList<DataModel>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<DataModel>> loader, ArrayList<DataModel> data) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<DataModel>> loader) {

    }
}
