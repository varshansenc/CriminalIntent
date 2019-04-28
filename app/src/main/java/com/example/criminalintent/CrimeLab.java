package com.example.criminalintent;

import android.content.Context;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimeList;


    public static CrimeLab getInstance(Context context){
        if(sCrimeLab == null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimeList=new ArrayList<>();
        for(int i=0;i<100;i++){
            Crime crime=new Crime();
            crime.setmTitle("Crime #"+i);
            crime.setmSolved(i%2==0);
            mCrimeList.add(crime);
        }
    }

    //获得crime列表
    public List<Crime> getmCrimeList(){
        return mCrimeList;
    }

    public Crime getCrime(UUID uuid){
        for(Crime crime:mCrimeList){
            if(crime.getmId().equals(uuid)){
                return crime;
            }
        }
        return null;
    }
}
