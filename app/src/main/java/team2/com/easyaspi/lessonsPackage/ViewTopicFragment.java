package team2.com.easyaspi.lessonsPackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import bsh.Interpreter;
import team2.com.easyaspi.R;
import team2.com.easyaspi.databasePackage.TopicBean;
/*
 *   Name: ViewTopicFragment.java
 *   Last Modified: 2017, April 4th
 *   Last Modified By: Taera Kwon
 *   Description: Fragment that uses xml data to display content to users
 */

public class ViewTopicFragment extends Fragment{
    // Private Variables
    private OnViewTopicFragmentListener mListener;

    public ViewTopicFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ViewTopicFragment newInstance(TopicBean topic) {
        // Local variables
        String sChapterName, sTopicName, sImage, sInstruction;
        int iChapterNumber;
        Double dTopicId;
        // Assign values
        sChapterName = topic.getChaptername();
        sTopicName = topic.getTopicname();
        sImage = topic.getImageaddress();
        sInstruction = topic.getInstruction();
        iChapterNumber = topic.getChapter();
        dTopicId = topic.getId();

        ViewTopicFragment fragment = new ViewTopicFragment();
        Bundle args = new Bundle();
        args.putString("chapterName", sChapterName);
        args.putString("topicName", sTopicName);
        args.putString("image", sImage);
        args.putString("instruction", sInstruction);
        args.putInt("chapterNumber", iChapterNumber);
        args.putDouble("topidId", dTopicId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If argument is not null
        if (getArguments() != null) {
            Log.d("Successful: ", getArguments().getString("topicName"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // BEAN INTERPRETER
        Interpreter interpreter = new Interpreter();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_topic, container, false);
        ImageView topic_image = (ImageView) view.findViewById(R.id.topic_image);
        Resources res = view.getResources();
        int resId = res.getIdentifier(getArguments().getString("image"), "drawable", getContext().getPackageName());
        Log.d("RES ID: ", "" + resId);
        Drawable drawable = res.getDrawable(resId);
        topic_image.setImageDrawable(drawable);
        //interpreter.set("image", "getArguments().getString(\"image\")");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewTopicFragmentListener) {
            mListener = (OnViewTopicFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnViewTopicFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
