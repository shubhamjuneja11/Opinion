package loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import adapter.WorkingAdapter;
import model.DataModel;

/**
 * Created by Junejas on 4/15/2017.
 */

public class WorkingLoader extends AsyncTaskLoader<ArrayList<DataModel>>{
    Context context;
    String url;
    ArrayList<DataModel> data;
    WorkingAdapter adapter;
    String response;
    public WorkingLoader(Context context, String url, ArrayList<DataModel> data, WorkingAdapter adapter){
        super(context);
        this.context=context;
        this.url=url;
        this.data=data;
        this.adapter=adapter;
    }
    private String makeRequest(URL url){
        String response="";
        InputStream inputStream;
        if(url==null)
            return response;

        HttpURLConnection connection;
        try {
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setDoOutput(true);

            inputStream=connection.getInputStream();
            response=readfromStream(inputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    @Override
    public ArrayList<DataModel> loadInBackground() {
        try {
            response=makeRequest(new URL(url));
            getData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return data;
    }
    private String readfromStream(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        if(inputStream!=null){
            try {
                InputStreamReader reader=new InputStreamReader(inputStream,"UTF-8");
                BufferedReader reader1=new BufferedReader(reader);
                String r=reader1.readLine();
                while(r!=null){
                    stringBuilder.append(r);
                    r=reader1.readLine();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    private void getData(){
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray array=jsonObject.getJSONArray("data");
            for(int i=0;i<array.length();i++){
                JSONObject jsonObject1=array.getJSONObject(i);
                String a=jsonObject1.getString("data");
                DataModel model=new DataModel(a);
                data.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
