package com.example.coronatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Constraints;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {
private  Context context;
private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelfilter;

    public MyCustomAdapter(Context context, List<CountryModel>countryModelList) {
        super(context, R.layout.list_custom,countryModelList);
         this.context=context;
         this.countryModelList=countryModelList;
         this.countryModelfilter=countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom,null,true);
        TextView Countryname=view.findViewById(R.id.countryname);
        ImageView flagImg=view.findViewById(R.id.flagimg);
        Countryname.setText(countryModelfilter.get(position).getCountry());
        Glide.with(context).load(countryModelfilter.get(position).getFloag()).into(flagImg);



        return view;
    }

    @Override
    public int getCount() {
        return countryModelfilter.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelfilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if (constraint==null || constraint.length()==0){
                    filterResults.count=countryModelList.size();
                    filterResults.values=countryModelList;
                }else{
                    List<CountryModel>resultsModels=new ArrayList<>();
                    String searchStr=constraint.toString().toLowerCase();
                    for (CountryModel itemsModel:countryModelList){
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModels.add(itemsModel);
                        }
                        filterResults.count=resultsModels.size();
                        filterResults.values=resultsModels;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                  countryModelfilter=(List<CountryModel>)results.values;
                  TrackCountries.countryModelList=(List<CountryModel>)results.values;
                  notifyDataSetChanged();
            }
        };

        return filter;
    }
}
