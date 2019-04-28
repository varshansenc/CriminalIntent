package com.example.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import java.util.List;

public class CrimeListFragment extends Fragment {


    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mCrimeAdapter;
    private ImageView mSolvedImageView;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecycleView=view.findViewById(R.id.crime_recycler_view);
        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.getInstance(getActivity());
        List<Crime> crimes=crimeLab.getmCrimeList();

        if(mCrimeAdapter==null) {
            mCrimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecycleView.setAdapter(mCrimeAdapter);
        }else{
            //mCrimeAdapter.notifyItemChanged(int crimeId);
            mCrimeAdapter.notifyDataSetChanged();
        }
    }

    //定义内部类ViewHolder
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        public void bind(Crime mCrime){
            this.mCrime=mCrime;
            mTitleTextView.setText(mCrime.getmTitle());
            mDateTextView.setText(mCrime.getmDate().toString());
            mSolvedImageView.setVisibility(View.GONE);
            //mSolvedImageView.setVisibility(mCrime.ismSolved() ? View.VISIBLE : View.GONE);
        }

        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
            itemView.setOnClickListener(this);

            mTitleTextView=itemView.findViewById(R.id.crime_title);
            mDateTextView=itemView.findViewById(R.id.crime_date);
            mSolvedImageView=itemView.findViewById(R.id.crime_solved);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),mCrime.getmTitle()+" clicked!",Toast.LENGTH_SHORT).show();

            Intent intent=CrimePageActivity.newIntent(getActivity(),mCrime.getmId());
            startActivity(intent);
        }
    }

    //定义内部类Adapter
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> crimeList;

        public CrimeAdapter(List<Crime> crimes){
            this.crimeList=crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,viewGroup);
        }

        //  @Override
        //public CrimeHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
          //  LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            //return new CrimeHolder(layoutInflater,viewGroup);
      //  }

        @Override
        public void onBindViewHolder(CrimeHolder crimeHolder, int i) {
            Crime crime=crimeList.get(i);
            crimeHolder.bind(crime);
        }


        @Override
        public int getItemCount() {
            return this.crimeList.size();
        }
    }


}
