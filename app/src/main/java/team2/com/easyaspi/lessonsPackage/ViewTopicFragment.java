package team2.com.easyaspi.lessonsPackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

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
    private TextToSpeech t2s; // Text to speech

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
        args.putDouble("topicId", dTopicId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t2s = new TextToSpeech(getContext().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                try{
                    // Change language to US
                    t2s.setLanguage(Locale.US);
                    // Set language speed to 80%
                    t2s.setSpeechRate(0.8f);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        // If argument is not null
        if (getArguments() != null) {
            Log.d("Successful: ", getArguments().getString("topicName"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_topic, container, false);
        ImageView topic_image = (ImageView) view.findViewById(R.id.topic_image);
        Resources res = view.getResources();
        int resId = res.getIdentifier(getArguments().getString("image"), "drawable", getContext().getPackageName());
        Drawable drawable = res.getDrawable(resId);
        topic_image.setImageDrawable(drawable);
        // Header
        TextView topic_header_text = (TextView) view.findViewById(R.id.topic_header_text);
        topic_header_text.setText(getArguments().getString("chapterName") + "\n" + getArguments().getString("topicName") + " (" +
                getArguments().getDouble("topicId") + ")");
        // Read Button
        Button read_button = (Button) view.findViewById(R.id.topic_play_button);
        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Text2Speech(getArguments().getString("instruction"));
            }
        });
        return view;
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
        t2s.stop();
    }

    public interface OnViewTopicFragmentListener {
    }

    public void Text2Speech(String text){
        t2s.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
