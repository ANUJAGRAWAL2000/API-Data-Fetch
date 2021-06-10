package com.example.asiaCountrylist;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    Context context;
    List<Countries> countries;

    public CountryAdapter(Context ctx, List<Countries> countries) {
        this.context = ctx;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Countries currentCountry=countries.get(position);
        holder.name.setText(currentCountry.getName());
        holder.capital.setText(currentCountry.getCapital());
        holder.region.setText(currentCountry.getRegion());
        holder.subRegion.setText(currentCountry.getSubRegion());
        holder.population.setText(currentCountry.getPopulation());
        holder.border.setText(currentCountry.getBorders());
        holder.language.setText(currentCountry.getLanguages());
        if(currentCountry.getFlag_URL()!=null) {
            Uri flagUri=Uri.parse(currentCountry.getFlag_URL());
            GlideToVectorYou
                    .init()
                    .with(context)
                    .withListener(new GlideToVectorYouListener() {
                        @Override
                        public void onLoadFailed() {
//                            Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResourceReady() {
//                            Toast.makeText(context, "Image ready", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPlaceHolder(R.drawable.ic_baseline_flag_24, R.drawable.ic_baseline_flag_24)
                    .load(flagUri, holder.flagImage);
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,capital,region,subRegion,population,border,language;
        ImageView flagImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagImage=itemView.findViewById(R.id.flagImage);
            name=itemView.findViewById(R.id.name);
            capital=itemView.findViewById(R.id.capital);
            region=itemView.findViewById(R.id.region);
            subRegion=itemView.findViewById(R.id.subRegion);
            language=itemView.findViewById(R.id.language);
            border=itemView.findViewById(R.id.border);
            population=itemView.findViewById(R.id.population);
        }
    }
}