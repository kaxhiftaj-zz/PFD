package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.techease.pfd.Adapters.GraphAdapter;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.Controller.GraphModel;
import com.techease.pfd.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FbGraphFrag extends Fragment {

    RecyclerView recyclerView;
    List<GraphModel> models;
    GraphAdapter graphAdapter;
    int a = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String FbToken;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fb_graph, container, false);
        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        FbToken=sharedPreferences.getString("api_tokenFB","");
        if (FbToken.equals(""))
        {
            Toast.makeText(getActivity(), "You have to login through facebook to see facebook post", Toast.LENGTH_SHORT).show();
        }
        else
        {
            recyclerView = (RecyclerView) view.findViewById(R.id.tvGraph);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            models = new ArrayList<>();
            GraphApicall();
            graphAdapter = new GraphAdapter(getActivity(), models);
            recyclerView.setAdapter(graphAdapter);
        }

        return view;
    }

    private void GraphApicall() {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/477220989332453/feed",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        // Insert your code here
                        Log.d("feed", String.valueOf(response));
                        try {
                            JSONArray data = response.getJSONObject().getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject post = response.getJSONObject().getJSONArray("data").getJSONObject(i);
                                GraphModel graphModel = new GraphModel();
                                String type = post.getString("type");
                                if (!type.equals("video")) {
                                    if (post.has("full_picture") && post.has("message")) {
                                        a++;
                                        graphModel.setImageUrl(post.getString("full_picture"));
                                        graphModel.setPostMessage(post.getString("message"));
                                        graphModel.setType(post.getString("type"));
                                        models.add(graphModel);

                                    } else if (post.has("message")) {
                                        graphModel.setPostMessage(post.getString("message"));
                                        models.add(graphModel);
                                    } else if (post.has("full_picture")) {
                                        graphModel.setImageUrl(post.getString("full_picture"));
                                        models.add(graphModel);
                                    }
                                }


                            }
                            graphAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "full_picture,message,type");
        parameters.putString("limit", "100");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
