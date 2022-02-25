package com.example.olx_bare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link secondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class secondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public secondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment secondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static secondFragment newInstance(String param1, String param2) {
        secondFragment fragment = new secondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    RecyclerView reses;
    RecyclerView.Adapter reseradapter;
    RecyclerView.LayoutManager reslay;
    List<Listing2> Liste;
    RequestQueue queue;
    //String URL = "http://192.168.0.104/andro/getdata.php";
    da n = new da();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("sen");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        // Inflate the layout for this fragment
        reses = view.findViewById(R.id.recyclerView);
       // reslay = new LinearLayoutManager(this.getActivity())  ;

       LinearLayoutManager reslay = new LinearLayoutManager(this.getContext());
        //  llm.setOrientation(LinearLayoutManager.VERTICAL);
        //list.setLayoutManager(llm);
        // list.setAdapter( adapter );
        reses.setLayoutManager(reslay);

        Liste = new ArrayList<>();

    JsonArrayRequest jar = new JsonArrayRequest(n.URL + "getser.php",
            responce -> {
                try {
                    System.out.println(responce);
                    parse_data(responce);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            error -> Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show());
          /*  StringRequest jar= new StringRequest(n.URL + "getdata.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG", "onResponse: "+response);
                            System.out.println(response);
                            GsonBuilder builder=new GsonBuilder();
                            Gson gson=builder.create();
                            Listing[] data =gson.fromJson(response, Listing[].class);
                            //Log.d("TAG of ", "onResponse: "+data[]);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    Log.d("TAG", "onErrorResponse: "+error.toString());
                }
            });*/

    queue = Volley.newRequestQueue(getContext());
        queue.add(jar);
        return view;
    }
    public void parse_data (JSONArray jarray) throws JSONException {
        int i=0;
        for (i = 0; i < jarray.length(); i++) {
            JSONObject jos = jarray.getJSONObject(i);
            Listing2 l = new Listing2();
            l.setLink(jos.getString("imagelink"));
            l.setDetail(jos.getString("details"));
            l.setHead(jos.getString("heading"));
            l.setLat((float) jos.getDouble("latitude"));
            l.setLongi((float) jos.getDouble("longi"));
            l.setSellerid(jos.getInt("sellerid"));
            l.setLid(jos.getInt("servis"));
            l.setContact(jos.getString("contact"));
            l.setprice(jos.getInt("price"));
            Liste.add(l);
            System.out.println(l.getHead());
        }
        System.out.println("runs in second"+i);
        //adapter
        resinf2 rar = new resinf2(Liste, getContext());
        reses.setAdapter(rar);
    }
}