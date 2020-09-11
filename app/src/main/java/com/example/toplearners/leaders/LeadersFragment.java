package com.example.toplearners.leaders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toplearners.R;
import com.example.toplearners.model.LeaderListItem;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class LeadersFragment extends Fragment {

    private RecyclerView mLeadersRecyclerView;
    private List<LeaderListItem> mItems = new ArrayList<>();
    private static final String TAG = "LearnersFragment";

    public LeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new LeadersTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learners, container, false);
        mLeadersRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_learners);
        mLeadersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        if (isAdded()) {
            mLeadersRecyclerView.setAdapter(new LeadersAdapter(mItems));
        }
    }


    private class LeadersTask extends AsyncTask<Void,Void,List<LeaderListItem>> {
        @Override
        protected List<LeaderListItem> doInBackground(Void... params) {
            return new Leaders().fetchLearners();
        }

        protected void onPostExecute(List<LeaderListItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    private class LeadersAdapter extends RecyclerView.Adapter<LeaderHolder> {

        private List<LeaderListItem> mLeaderItems;
        public LeadersAdapter(List<LeaderListItem> listItems) {
            mLeaderItems = listItems;
        }
        @NonNull
        @Override
        public LeaderHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.learners_list_item, viewGroup, false);
            return new LeaderHolder(view);
        }
        @Override
        public void onBindViewHolder(LeaderHolder holder, int position) {
            LeaderListItem mListItem = mLeaderItems.get(position);
            holder.bindGalleryItem(mListItem);
        }
        @Override
        public int getItemCount() {
            return mLeaderItems.size();
        }
    }

    private class LeaderHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mHoursTextView;
        private TextView mCountryTextView;
        private ImageView mImageView;
        public LeaderHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.learnerNameTxt);
            mHoursTextView = (TextView) itemView.findViewById(R.id.learnerHoursSkillsTxt);
            mCountryTextView = (TextView) itemView.findViewById(R.id.learnerCountryTxt);
            mImageView = itemView.findViewById(R.id.image_learner);
        }
        public void bindGalleryItem(LeaderListItem item) {
            mNameTextView.setText(item.toString());
            mHoursTextView.setText(item.getHours() + " hours,");
            mCountryTextView.setText(item.getCountry());
            Picasso.with(getContext()).load(item.getBadgeUrl()).into(mImageView);

        }
    }
}