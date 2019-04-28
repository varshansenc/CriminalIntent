package com.example.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import java.util.UUID;

public class MainActivity extends SingleFragmentActivity{

    //public static final String EXTRA_CRIME_ID="EXTRA_CRIME_ID";
    private static final String EXTRA_CRIME_ID="EXTRA_CRIME_ID";

    protected Fragment createFragment(){
        //return new CrimeFragment();
        UUID crimeId=(UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext,UUID crimeId){
        Intent intent=new Intent(packageContext,MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return intent;
    }

}
