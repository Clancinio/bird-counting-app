package com.example.birdspotting;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.BirdView> {

    private Context mContext;
    private List<Bird> birdList;

    public BirdAdapter(Context mContext, List<Bird> birdList) {
        this.mContext = mContext;
        this.birdList = birdList;
    }

    @NonNull
    @Override
    public BirdView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_birds,  parent, false);
        return new BirdView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BirdView holder, final int position) {

        // Set the
        holder.birdImage.setImageResource(getImageId(mContext, birdList.get(position).getNumberImage()));
        holder.mainName.setText(birdList.get(position).getCommonName());
        //     holder.birdActivityImage.setImageResource(getImageId(mContext, birdList.get(position).getNumberImage()));
        //holder.birdImage.setImageResource(birdList.get(position).getNumberImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BookActivity.class);
                // Post data to the book activity
                intent.putExtra("CommonName", birdList.get(position).getCommonName());
                intent.putExtra("SciName", birdList.get(position).getScientificName());
                intent.putExtra("Description", birdList.get(position).getDescription());
                intent.putExtra("Image", birdList.get(position).getNumberImage());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return birdList.size();
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public class BirdView extends RecyclerView.ViewHolder{

        ImageView birdImage;
        TextView mainName;
        CardView cardView;

        public BirdView(@NonNull View itemView) {
            super(itemView);
            birdImage = itemView.findViewById(R.id.bird_image_id);
            mainName = itemView.findViewById(R.id.main_name);
            cardView = itemView.findViewById(R.id.card_view_id);
        }
    }
}

