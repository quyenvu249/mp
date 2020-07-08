package com.example.musicplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongsFragment extends Fragment {
    List<Songs> songsList;
    SongsAdapter adapter;
    RecyclerView rcSongs;
    String url = "https://api-v2.soundcloud.com/charts?kind=top&genre=soundcloud%3Agenres%3Ahiphoprap&client_id=a3e059563d7fd3372b49b37f00a00bcf&limit=10&linked_partitioning=1";

    public SongsFragment() {
        // Required empty public constructor
    }

    public static SongsFragment newInstance() {
        return new SongsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_songs, container, false);
        rcSongs=root.findViewById(R.id.rcSongs);
        songsList = new ArrayList<>();
        adapter=new SongsAdapter(getActivity().getApplicationContext(),songsList);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("collection");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONObject track = object.getJSONObject("track");
                        String id = track.getString("id");
                        String name = track.getString("title");
                        String media = track.getString("permalink_url");
                        String dur=track.getString("full_duration");
                        JSONObject user = track.getJSONObject("user");
                        String singer = user.getString("full_name");
                        String linkAnh = track.getString("artwork_url");
                        Songs song = new Songs(id, name, singer, linkAnh, media, Double.parseDouble(dur));
                        songsList.add(song);
                        rcSongs.setAdapter(adapter);
                        rcSongs.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
        requestQueue.add(jsonObjectRequest);
        return root;
    }
}
