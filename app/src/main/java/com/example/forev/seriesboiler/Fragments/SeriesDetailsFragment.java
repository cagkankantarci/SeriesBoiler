package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.seriesboiler.Adapters.CheckModelTestAdapter;
import com.example.forev.seriesboiler.Models.AddMyListModel;
import com.example.forev.seriesboiler.Models.CheckModel;
import com.example.forev.seriesboiler.Models.DeleteMyListModel;
import com.example.forev.seriesboiler.Models.ListMyListModel;
import com.example.forev.seriesboiler.Models.TotalSeasonModel;
import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.RestApi.ManagerAll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesDetailsFragment extends Fragment{

    View view;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    private TextView detailsTitle,detailsDescription;
    private ImageView SeriesThumbnailImg;
    Spinner spinner;
    int totalSeason;
    RecyclerView detailsRecyclerView;
    String id,seriesTitle,imageResourceId,seriesDescription;
    Button saveButton,clearButton;
    private ArrayList<CheckModel> list2;
    StringBuilder sb=null;
    CheckModelTestAdapter checkModelTestAdapter;
    private List<ListMyListModel> myListModels;

    //SONRADAN EKLENEN GEREKSİZLER
    public ArrayList<Integer>deneme;
    String seasonGet;
    DatabaseReference ref;
    //SONRADAN EKLENEN GEREKSİZLER




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_series_details, container, false);
        define();
        listMyList();
        seasonNumber(id);
        return view;
    }

    public void check(final ArrayList<CheckModel> listx,final int number) {
        deneme = new ArrayList<>();
        final ArrayList<CheckModel>listT = new ArrayList<>();

            ref.child("Users").child(user.getUid()).child("Watched").child(seriesTitle).child("Season").child(seasonGet)
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            String st = dataSnapshot.getKey();
                            int no = Integer.parseInt(st);

                            if(!deneme.contains(no))
                            {
                                deneme.add(no);
                            }

                            if(listT.size()== 0)
                            {
                                for(int j=0; j < number;j++)
                                {
                                    if(deneme.contains(j+1))
                                    {
                                        listT.add(new CheckModel("Episode "+(j+1),true));
                                    }
                                    else
                                    {
                                        listT.add(new CheckModel("Episode "+(j+1),false));
                                    }
                                }
                            }
                            else
                            {
                                listT.get(no-1).setSelected(true);
                            }

                            sendToAdapter(listT);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

    }

    private void sendToAdapter(final ArrayList<CheckModel> listx) {

        checkModelTestAdapter = new CheckModelTestAdapter(getContext(),getCheckModel(listx));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb = new StringBuilder();
                Map map = new HashMap();
                int i =0;
                int outCount=0;
                int innerCount=0;
                do{
                    if(checkModelTestAdapter.checkedEpisode.size()>0)
                    {
                        int count=0;
                        if(myListModels.size() != 0)
                        {
                            for(int k=0; k<myListModels.size(); k++)
                            {
                                if(myListModels.get(k).getUserid().equals(user.getUid()) && myListModels.get(k).getSeriesid().equals(id)
                                        && myListModels.get(k).getSeason().equals(seasonGet))
                                {
                                    count++;
                                }
                            }
                            if(count == 0 && innerCount==0)
                            {
                                addMyList(user.getUid(),id,seasonGet);
                                listMyList();
                                Log.i("tag","save1");
                            }
                            innerCount++;
                        }
                        else {
                            if(outCount == 0)
                            {
                                addMyList(user.getUid(),id,seasonGet);
                                Log.i("tag","save2");
                                listMyList();
                            }
                            outCount++;
                        }

                        CheckModel checkModel = checkModelTestAdapter.checkedEpisode.get(i);
                        sb.append(checkModel.getName());

                        if(checkModel.isSelected() == true)
                        {
                            map.put(checkModel.getName().substring(8),1);
                            reference.setValue(map);
                        }
                        else
                        {
                            map.put(checkModel.getName().substring(8),0);
                            reference.setValue(map);
                        }

                        if(i != checkModelTestAdapter.checkedEpisode.size()-1)
                        {
                            sb.append("\n");
                        }
                        i++;
                    }
                    else
                    {
                        break;
                    }

                }while(i < checkModelTestAdapter.checkedEpisode.size());


                if(checkModelTestAdapter.checkedEpisode.size()>0)
                {
                    Toast.makeText(getContext(),sb.toString(),Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.i("tagDetail",user.getUid()+"+"+id+"+"+seasonGet);
                    deleteMyList(user.getUid(),id,seasonGet);
                    listMyList();
                    Toast.makeText(getContext(),"Please Check an item first.",Toast.LENGTH_LONG).show();
                }
            }
        });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map map = new HashMap();
                    int i =0;
                    do{
                        if(checkModelTestAdapter.checkedEpisode.size()>0) {
                            if (checkModelTestAdapter.checkedEpisode.get(i).isSelected()) {
                                for (int k = 0; k < checkModelTestAdapter.checkedEpisode.size(); k++) {
                                    checkModelTestAdapter.checkedEpisode.get(i).setSelected(false);
                                    reference.child(checkModelTestAdapter.checkedEpisode.get(i).getName().substring(8)).removeValue();
                                }
                                checkModelTestAdapter.notifyDataSetChanged();
                                i++;
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(),"You must mark to delete.",Toast.LENGTH_LONG).show();
                        }
                    }while(i < checkModelTestAdapter.checkedEpisode.size());
                }
            });

        detailsRecyclerView.setAdapter(checkModelTestAdapter);
        checkModelTestAdapter.notifyDataSetChanged();
    }


    public void define()
    {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        //////////////////////////////////
        ref = database.getReference();
        ////////////////////////////////


        id = getArguments().getString("id");
        seriesTitle = getArguments().getString("title");
        imageResourceId = getArguments().getString("imgurl");
        seriesDescription = getArguments().getString("desc");

        reference = database.getReference("Users"+user.getUid()+"/Watched/"+seriesTitle+"/Season");

        SeriesThumbnailImg = (ImageView)view.findViewById(R.id.detailImageView);
        detailsTitle = (TextView)view.findViewById(R.id.detailsTitle);
        detailsDescription = (TextView)view.findViewById(R.id.detailsDescription);
        saveButton = (Button)view.findViewById(R.id.saveButton);
        clearButton = (Button)view.findViewById(R.id.clearButton);

        detailsTitle.setText(seriesTitle);
        detailsDescription.setText(seriesDescription);
        Picasso.get().load(imageResourceId).resize(0,175).into(SeriesThumbnailImg);

        spinner = (Spinner)view.findViewById(R.id.spinner1);

        detailsRecyclerView = (RecyclerView)view.findViewById(R.id.detailsRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        detailsRecyclerView.setLayoutManager(layoutManager);

        myListModels = new ArrayList<>();

    }

    public void seasonNumber(String seriesid)
    {
        Call<List<TotalSeasonModel>> req = ManagerAll.getInstance().getTotalSeasonNumber(seriesid);
        req.enqueue(new Callback<List<TotalSeasonModel>>() {
            @Override
            public void onResponse(Call<List<TotalSeasonModel>> call, final Response<List<TotalSeasonModel>> response) {

                totalSeason = response.body().size();

                List<String> list = new ArrayList<String>();

                for(int i=0; i < totalSeason;i++)
                {
                    list.add("Season "+(i+1));
                }

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                        R.layout.support_simple_spinner_dropdown_item,list);

                myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(myAdapter);



                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        reference = database.getReference("Users/"+user.getUid()+"/Watched/"+seriesTitle+"/Season/"
                                +response.body().get(i).getSeason().toString());

                        ////////////////CHECKBOX AREA////////////////////////
                        list2 = new ArrayList<>();

                        String totalEpisode = response.body().get(i).getEpisode();

                        int number = Integer.parseInt(totalEpisode);

                        seasonGet = response.body().get(i).getSeason().toString();

                        for(int j=0; j < number;j++)
                        {
                            list2.add(new CheckModel("Episode "+(j+1),false));
                        }
                        sendToAdapter(list2);
                        check(list2,number);

                        ////////////////CHECKBOX AREA////////////////////////
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

            }

            @Override
            public void onFailure(Call<List<TotalSeasonModel>> call, Throwable t) {
            }
        });
    }

    private CheckModel[] getCheckModel(ArrayList<CheckModel> list)
    {
        return list.toArray(new CheckModel[list.size()]);
    }

    private void addMyList(String userid, String seriesid,String season)
    {
        Call<AddMyListModel> req = ManagerAll.getInstance().addMyList(userid,seriesid,season);
        req.enqueue(new Callback<AddMyListModel>() {
            @Override
            public void onResponse(Call<AddMyListModel> call, Response<AddMyListModel> response) {
             Log.i("response","calisti burasi");
            }

            @Override
            public void onFailure(Call<AddMyListModel> call, Throwable t) {

            }
        });
    }

    private void listMyList()
    {
        Call<List<ListMyListModel>> req = ManagerAll.getInstance().getMyList(user.getUid());
        req.enqueue(new Callback<List<ListMyListModel>>() {
            @Override
            public void onResponse(Call<List<ListMyListModel>> call, Response<List<ListMyListModel>> response) {
                myListModels = response.body();
            }

            @Override
            public void onFailure(Call<List<ListMyListModel>> call, Throwable t) {

            }
        });
    }

    private void deleteMyList(String userid, String seriesid,String season)
    {
        Call<DeleteMyListModel> req = ManagerAll.getInstance().deleteMyList(userid,seriesid,season);
        req.enqueue(new Callback<DeleteMyListModel>() {
            @Override
            public void onResponse(Call<DeleteMyListModel> call, Response<DeleteMyListModel> response) {

            }

            @Override
            public void onFailure(Call<DeleteMyListModel> call, Throwable t) {

            }
        });
    }


}

