package team2.com.easyaspi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import team2.com.easyaspi.LessonsFragment.OnListFragmentInteractionListener;
import team2.com.easyaspi.databasePackage.ChapterBean;

import java.util.List;

public class LessonsViewAdapter extends RecyclerView.Adapter<LessonsViewAdapter.ViewHolder> {
    private final List<ChapterBean> chapters;
    private final OnListFragmentInteractionListener mListener;

    // Setes items (passed from LessonsFragment) into chapters
    public LessonsViewAdapter(List<ChapterBean> items, OnListFragmentInteractionListener listener) {
        chapters = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lessons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = chapters.get(position);
        holder.mIdView.setText("" + chapters.get(position).getChapter());
        holder.mContentView.setText(chapters.get(position).getChaptername());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ChapterBean mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
