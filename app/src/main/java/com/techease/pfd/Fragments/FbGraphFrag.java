package com.techease.pfd.Fragments;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fb_graph, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.tvGraph);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        models=new ArrayList<>();
        GraphApicall();
        graphAdapter=new GraphAdapter(getActivity(), models);
        recyclerView.setAdapter(graphAdapter);
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
                        Log.d("feed",String.valueOf(response));
                        try {
                            Toast.makeText(getActivity(), AccessToken.getCurrentAccessToken().toString(), Toast.LENGTH_SHORT).show();
                            JSONArray data = response.getJSONObject().getJSONArray("data");
                            for(int i=0;i<data.length();i++){
                                JSONObject post = response.getJSONObject().getJSONArray("data").getJSONObject(i);
                                GraphModel graphModel=new GraphModel();
                                if (post.has("story") && post.has("full_picture") && post.has("message") && post.has("name")){
                                    graphModel.setStory(post.getString("story"));
                                    graphModel.setImageUrl(post.getString("full_picture"));
                                    graphModel.setPostMessage(post.getString("message"));
                                    graphModel.setPostName(post.getString("name"));
                                }

                                models.add(graphModel);
                            }
                            graphAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,full_picture,story,message");
        parameters.putString("limit", "20");
        request.setParameters(parameters);
        request.executeAsync();
    }




}
