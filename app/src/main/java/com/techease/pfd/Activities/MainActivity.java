package com.techease.pfd.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.techease.pfd.Utils.DialogUtils;
import com.techease.pfd.Configuration.Links;
import com.techease.pfd.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnEmail;
    private LoginButton FBloginButton;
    CallbackManager callbackManager;
    String provider_id,email,name,provider,device_type,device_token;
    String id,story;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //for getting device token
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        sharedPreferences = this.getSharedPreferences(Links.MyPrefs, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        device_type="Android";
        device_token=android_id;
        callbackManager = CallbackManager.Factory.create();
        FBloginButton=(LoginButton) findViewById(R.id.btnFb);
        FBloginButton.setHeight(100);
        FBloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FBloginButton.setReadPermissions("email");
                FBloginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        String accessToken = loginResult.getAccessToken().getToken();
                        provider_id = accessToken;
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString());
                                // Get facebook data from login
                                editor.putString("token",provider_id);
                                editor.commit();
                                try {
                                    id=object.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(new Intent(MainActivity.this,Dashboard.class));
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {

                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(FacebookException error) {

                        Toast.makeText(MainActivity.this, String.valueOf(error.getCause()), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });



        btnEmail=(Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,FullscreenActivity.class));

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
    private void apiCall() {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.User_Url + "social-login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zma  reg response", response);
//                DialogUtils.sweetAlertDialog.dismiss();
                if (response.contains("true")) {
                    try {
                        JSONObject jsonObject = new JSONObject(response).getJSONObject("message");
                            editor.putString("api_token", device_token);
                            editor.putString("name",name);
                            editor.commit();
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    startActivity(new Intent(MainActivity.this, Dashboard.class));

                } else   {
                    DialogUtils.showWarningAlertDialog(MainActivity.this, "Something went wrong");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("zma error", String.valueOf(error));
                DialogUtils.showWarningAlertDialog(MainActivity.this, String.valueOf(error.getCause()));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("name", name);
                params.put("provider_id", provider_id);
                params.put("provider", provider);
                params.put("device_type",device_type);
                params.put("device_token",device_token);
                return params;
            }

        };
        RequestQueue mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(stringRequest);

    }


}
