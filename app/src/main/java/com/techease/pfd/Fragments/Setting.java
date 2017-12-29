package com.techease.pfd.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.ProfilePictureView;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Setting extends Fragment {

    ProfilePictureView profilePictureView;
    TextView UserName,UserBirthday,UserGender,UserLocation,tvPersonalInfo,tvUserGender,tvUserLoc,tvUserBirthday,tvProfile;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String strUserId,strUserFname,strUserLname,strGender,strUserLoc,strUserBirthday;
    Typeface typeface,typeface2;
    ProgressBar progressBar;
    int progressbarstatus = 0;
    CallbackManager callbackManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);

        progressBar=(ProgressBar)view.findViewById(R.id.progress_bar_Setting);
        progressBar.setVisibility(View.VISIBLE);
        setProgressValue(progressbarstatus);

        sharedPreferences = getActivity().getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_blk.otf");
        typeface2=Typeface.createFromAsset(getActivity().getAssets(),"font/brandon_reg.otf");

        UserName=(TextView)view.findViewById(R.id.tvFbUserName);
        UserBirthday=(TextView)view.findViewById(R.id.tvUserBirthday);
        UserGender=(TextView)view.findViewById(R.id.tvUserGender);
        UserLocation=(TextView)view.findViewById(R.id.tvUserLoc);
        tvPersonalInfo=(TextView)view.findViewById(R.id.tvPersonalInfo);
        tvUserBirthday=(TextView)view.findViewById(R.id.tvBirthdayTitle);
        tvUserGender=(TextView)view.findViewById(R.id.tvUserGenderTitle);
        tvUserLoc=(TextView)view.findViewById(R.id.tvUserLocTitle);
        tvProfile=(TextView)view.findViewById(R.id.tvSetting);

        UserName.setTypeface(typeface);
        UserLocation.setTypeface(typeface2);
        UserGender.setTypeface(typeface2);
        UserBirthday.setTypeface(typeface2);
        tvUserLoc.setTypeface(typeface);
        tvUserGender.setTypeface(typeface);
        tvUserBirthday.setTypeface(typeface);
        tvPersonalInfo.setTypeface(typeface);
        tvProfile.setTypeface(typeface);

        strUserFname=sharedPreferences.getString("fname","");
        strUserLname=sharedPreferences.getString("lname","");
        strUserId=sharedPreferences.getString("userId","");

        profilePictureView = (ProfilePictureView)view.findViewById(R.id.friendProfilePicture);
        profilePictureView.setProfileId(strUserId);
        UserName.setText(strUserFname+" "+strUserLname);

        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken=AccessToken.getCurrentAccessToken();
        //accessToken=sharedPreferences.getString("accesstoken","");
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                      //  Log.i("settignAct", object.toString());

                        try {

                            strGender=object.getString("gender");
                            strUserBirthday=object.getString("email");
                            JSONObject jsonObject=new JSONObject();
                            jsonObject=object.getJSONObject("location");
                            strUserLoc=jsonObject.getString("name");

                            UserBirthday.setText(strUserBirthday);
                            UserGender.setText(strGender);
                            UserLocation.setText(strUserLoc);

                            Log.d("zmaGender",strGender);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Application code
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "gender,email,location");
        request.setParameters(parameters);
        request.executeAsync();




        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    private void setProgressValue(final int progressbarstatus) {
        // set the progress
        progressBar.setProgress(progressbarstatus);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progressbarstatus + 10);
            }
        });
        thread.start();
    }
}
