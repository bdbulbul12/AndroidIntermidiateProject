package com.example.bulbul.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView= (ExpandableListView) findViewById(R.id.expandableListViewId);
        prepareListData();
    }
    public void prepareListData(){
        String headerString[] = getResources().getStringArray(R.array.abbrabation_list_header);
        String childString[] = getResources().getStringArray(R.array.abbrabation_list_child);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for (int i = 0;i<headerString.length;i++){

            //Adding header Data
            listDataHeader.add(headerString[i]);
            List<String> child = new ArrayList<>();
            child.add(childString[i]);

            listDataChild.put(listDataHeader.get(i),child);
        }
    }
}
