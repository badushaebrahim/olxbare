package com.example.olx_bare;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.webkit.PermissionRequest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class addprod extends AppCompatActivity
{

    EditText t1,t2,pri;
    CircleImageView img;
    ImageView btncamera;
    Button btnupload;
    Bitmap bitmap;
    String encodedimage;
    String price,result;
    //private static final String apiurl="http://10.0.2.2/android_db_pool/fileupload.php";
    da k = new da();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t1=(EditText)findViewById(R.id.nameu);
        t2=(EditText)findViewById(R.id.desigu);
        img=(CircleImageView)findViewById(R.id.profile_image);
        pri=(EditText)findViewById(R.id.priceu);
        btncamera=(ImageView)findViewById(R.id.sbmit_camera);
        btnupload=(Button)findViewById(R.id.sbmit_upload);
        Spinner dropdown=findViewById(R.id.spinner);
        String[] items = new String[]{"Product", "Service"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                items);
////set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);


        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView)dropdown.getSelectedView();
               result = textView.getText().toString();
                System.out.print(result);
                Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult( intent,111);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                            }

                           // @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtoserver();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==111 && resultCode==RESULT_OK)
        {
            bitmap=(Bitmap)data.getExtras().get("data");
            img.setImageBitmap(bitmap);
            encodebitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodebitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        encodedimage=android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }

    private void uploadtoserver()
    {
        final String name=t1.getText().toString().trim();
        final String desig=t2.getText().toString().trim();
            price= pri.getText().toString().trim();
        StringRequest request=new StringRequest(Request.Method.POST, k.URL+"fileupload.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                t1.setText("");
                t2.setText("");
                pri.setText("");
                img.setImageResource(R.drawable.ic_launcher_background);
                Toast.makeText(getApplicationContext(),"FileUploaded Successfully",Toast.LENGTH_SHORT).show();Intent intent = new Intent(addprod.this, MainActivity.class);
                //intent.putExtra("lname",header);
                //intent.putExtra("rid",);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
                int me = sh.getInt("uid", 0);
                Map<String,String> map=new HashMap<String, String>();
                map.put("title",name);
                map.put("details",desig);
                map.put("price",price);
               final String mes= String.valueOf(me);
                map.put("meid",mes);
                map.put("type",result);
                map.put("upload",encodedimage);
                return map;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }  // end of function uploadto DB

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(addprod.this, MainActivity.class);
        //intent.putExtra("lname",header);
        //intent.putExtra("rid",);
        startActivity(intent);
        finish();
    }
}