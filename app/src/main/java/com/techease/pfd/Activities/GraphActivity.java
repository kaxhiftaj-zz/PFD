package com.techease.pfd.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.techease.pfd.R;

public class GraphActivity extends AppCompatActivity {

    AccessToken accessToken;
  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        textView = (TextView)findViewById(R.id.tvid);

        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/477220989332453/feed",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        // Insert your code here
                        Log.d("feed",String.valueOf(response));

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("limit", "20");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
