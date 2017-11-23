package com.techease.pfd.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.techease.pfd.Adapters.GraphAdapter;
import com.techease.pfd.Controller.GraphModel;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    TextView textView;
    RecyclerView recyclerView;
    List<GraphModel> models;
    GraphAdapter graphAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        recyclerView=(RecyclerView)findViewById(R.id.tvGraph);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        models=new ArrayList<>();
        graphAdapter=new GraphAdapter(this, models);
        recyclerView.setAdapter(graphAdapter);

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/477220989332453/feed",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        // Insert your code here
                        Log.d("feed",String.valueOf(response));
                        try {
                            for(int i=0;i<response.getJSONObject().getJSONArray("data").length();i++){
                                JSONObject post = response.getJSONObject().getJSONArray("data").getJSONObject(i);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("limit", "20");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
