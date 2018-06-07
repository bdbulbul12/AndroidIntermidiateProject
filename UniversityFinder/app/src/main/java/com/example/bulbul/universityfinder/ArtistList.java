package com.example.bulbul.universityfinder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bulbul on 2/1/2018.
 */

public class ArtistList extends ArrayAdapter<Artist> implements Filterable {
    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context,List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView session = (TextView) listViewItem.findViewById(R.id.addsession);






        Artist artist = artistList.get(position);

        textViewName.setText(artist.getArtistName());
        textViewGenre.setText(artist.getArtistGenre());
        session.setText(artist.getAddSession());


        return listViewItem;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
