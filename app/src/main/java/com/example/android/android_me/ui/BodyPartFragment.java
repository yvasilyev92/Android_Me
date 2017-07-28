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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    //TAG for logging
    private static final String TAG = "BodyPartFragment";
    //Variables to store a list of images resources and the index of the image that this fragment displays.
    private List<Integer> mImageIds;
    private int mListIndex;
    //Final strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment
     */
    public BodyPartFragment() {
    }






    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //If I detect a saved state I load the values of the 2 variables.
        //I load the saved state (the list of images and list index) if there is one.
        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }


        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);




        // Get a reference to the ImageView in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);



        //If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found
        if (mImageIds != null){
            //set the image resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));



            //set a clicklistener on the imageview and on a click increment the list index and
            //update the image resource to the next image in our list of image id's
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //increment position as long as the index remains <= the size of the image id's list
                    if (mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    } else {
                        //If you reach end of list of images, reset index back to zero(first image)
                        mListIndex = 0;
                    }
                    //set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }
        else {
            //Log a message saying the image id list is null
            Log.v(TAG, "This fragment has a null list of image id's");
        }





        // Return the rootView
        return rootView;
    }


    //Setter methods for keeping track of the list images this fragment can display and which image
    //in the list is currently being display
    public void setImageIds(List<Integer> imageIds){
        mImageIds = imageIds;
    }
    public void setListIndex(int index){
        mListIndex = index;
    }


    //We override onSaveInstanceState so when we the orientation of the phone is changed,
    //we dont lose our current images and have them reset to index 0.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //We store the current Image ID list and current List index
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX, mListIndex);
    }
}
