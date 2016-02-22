package com.delta.pragyan16;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class QRScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscreen);

        GetQR();

    }

public void GetQR() {

    final ProgressDialog pDialog = new ProgressDialog(this);
    pDialog.setMessage("Loading...");
    pDialog.setCancelable(false);
    pDialog.setCanceledOnTouchOutside(false);
    pDialog.show();

    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://www.pragyan.org/quadtest/getQR", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            byte[] imageAsBytes = Base64.decode(response.getBytes(), Base64.DEFAULT);
            ImageView image = (ImageView) QRScreen.this.findViewById(R.id.qr_code_image);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            image.setImageBitmap(bitmap);
            pDialog.dismiss();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            pDialog.dismiss();
            error.printStackTrace();
            Toast.makeText(QRScreen.this, "Please check your internet and try again", Toast.LENGTH_LONG).show();

        }
    }) {
        @Override
        protected Map<String, String> getParams()
        {
            Map<String, String> params = new HashMap<>();
            // the POST parameters:
            int pid = 2;
            params.put("id", String.valueOf(pid));
            return params;
        }

    };

    int socketTimeout = 10000;//10 seconds
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    stringRequest.setRetryPolicy(policy);
    Volley.newRequestQueue(this).add(stringRequest);

}
}