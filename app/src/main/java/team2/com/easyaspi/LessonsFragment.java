package team2.com.easyaspi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import team2.com.easyaspi.databasePackage.ChapterBean;
import team2.com.easyaspi.databasePackage.TopicBean;
import team2.com.easyaspi.databasePackage.XmlParser;

/**
*   Name: LessonsFragment.java
*   Last Modified: 2017, March 23
*   Last Modified By: Taera Kwon
*   Description: Lesson Fragment Class that represents list of Lessons
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 */

public class LessonsFragment extends Fragment {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    // Instatiate ArrayList for chapters
    List<ChapterBean> chapters = new ArrayList<ChapterBean>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     */

    public LessonsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LessonsFragment newInstance(int columnCount) {
        LessonsFragment fragment = new LessonsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    // On creating the Lessons Fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Returns the arguments supplied to setArguments(Bundle) if there are any
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        // Stores chapters and topics object names (sets chapters and topics)
        bringLessonItems();
    }

    // Instatiate the user interface view for the Fragment
    // LayoutInflator = layout XML to view object

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // fragment_lessons_list lists layout from layout/fragment_lessons
        View view = inflater.inflate(R.layout.fragment_lessons_list, container, false);
        TextView header = (TextView) view.findViewById(R.id.header_lessons);
        header.setText("List of available chapters");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_lessons);
        recyclerView.setAdapter(new LessonsViewAdapter(chapters, mListener));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // If container activity did not implement callback interface, if not, it throws an exception
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ChapterBean chapter);
    }

    // Uses XmlPaser class to parse xml file for lessons
    private void bringLessonItems() {
        try {
            InputStream iStream = getResources().getAssets().open("grade1_lessons.xml");
            XmlParser xmlParser = new XmlParser();
            HashMap<String, List> parsed = xmlParser.GradeParser(iStream);
            Set<String> keys = parsed.keySet();
            chapters = parsed.get("chapters"); // Stores arrays of Chapter Objects
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}