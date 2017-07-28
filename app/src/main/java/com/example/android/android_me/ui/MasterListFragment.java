/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Yevgeniy on 7/27/2017.
 */

// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment {

    //We define a new interface OnImageClickListener that triggers a callback in the host activity.
    //The callback is a method onImageSelected(int position) that contains information about which
    //position on the grid of images a user has clicked.
    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //We also override onAttach() to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //This makes sure the host activity has implemented the callback interface,
        //if not it throws an exception.
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }

    //mandatory empty constructor
    public MasterListFragment() {
    }


    //inflates the GridView of all AndroidMe images
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //get a reference to our layout for MasterListFragment
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        //get a reference to the GridView in fragment_master_list.xml
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        //This adapter takes in the context and an ArrayList of ALL the image resources to display.
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //set the adapter of the GridView
        gridView.setAdapter(mAdapter);


        //set a click listener on the GridView and trigger the callback onImageSelected when an item is clicked.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Trigger the callback method and pass it in the position that was clicked.
                mCallback.onImageSelected(position);
            }
        });


        //return the rootview
        return  rootView;
    }
}
