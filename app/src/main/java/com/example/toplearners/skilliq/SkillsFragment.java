package com.example.toplearners.skilliq;

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
import com.example.toplearners.model.SkillListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SkillsFragment extends Fragment {

    private RecyclerView mLeadersRecyclerView;
    private List<SkillListItem> mItems = new ArrayList<>();
    private static final String TAG = "LearnersFragment";

    public SkillsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new SkillsFragment.SkillLearnerTask().execute();
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
            mLeadersRecyclerView.setAdapter(new SkillsFragment.SkillLearnersAdapter(mItems));
        }
    }


    private class SkillLearnerTask extends AsyncTask<Void,Void,List<SkillListItem>> {
        @Override
        protected List<SkillListItem> doInBackground(Void... params) {
            return new SkillLearners().fetchSkillLearners();
        }

        protected void onPostExecute(List<SkillListItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    private class SkillLearnersAdapter extends RecyclerView.Adapter<SkillsFragment.SkillLearnerHolder> {

        private List<SkillListItem> mLeaderItems;
        public SkillLearnersAdapter(List<SkillListItem> listItems) {
            mLeaderItems = listItems;
        }
        @NonNull
        @Override
        public SkillsFragment.SkillLearnerHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.learners_list_item, viewGroup, false);
            return new SkillsFragment.SkillLearnerHolder(view);
        }
        @Override
        public void onBindViewHolder(SkillsFragment.SkillLearnerHolder holder, int position) {
            SkillListItem mListItem = mLeaderItems.get(position);
            holder.bindGalleryItem(mListItem);
        }
        @Override
        public int getItemCount() {
            return mLeaderItems.size();
        }
    }

    private class SkillLearnerHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mSkillsTextView;
        private TextView mCountryTextView;
        private ImageView mImageView;
        public SkillLearnerHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.learnerNameTxt);
            mSkillsTextView = (TextView) itemView.findViewById(R.id.learnerHoursSkillsTxt);
            mCountryTextView = (TextView) itemView.findViewById(R.id.learnerCountryTxt);
            mImageView = itemView.findViewById(R.id.image_learner);
        }
        public void bindGalleryItem(SkillListItem item) {
            mNameTextView.setText(item.toString());
            mSkillsTextView.setText(item.getScore() + " Skill IQ Score,");
            mCountryTextView.setText(item.getCountry());
            Picasso.with(getContext()).load(item.getBadgeUrl()).into(mImageView);

        }
    }
}