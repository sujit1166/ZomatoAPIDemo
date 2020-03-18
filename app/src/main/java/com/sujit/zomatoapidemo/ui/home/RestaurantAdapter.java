package com.sujit.zomatoapidemo.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sujit.zomatoapidemo.R;
import com.sujit.zomatoapidemo.data.models.Restaurant;
import com.sujit.zomatoapidemo.databinding.RestaurantItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = getClass().getSimpleName();
    private Context context;
    private List<Restaurant> restaurantList;
    private final int ITEM_LAYOUT = 200;
    private final int FOOTER_LAYOUT = 100;
    private boolean isAllItemLoaded;

    public interface OnItemClickListener {
        void onItemClickListener(Restaurant restaurant);
    }

    private OnItemClickListener onItemClickListener;

    public RestaurantAdapter(Context context) {
        this.context = context;
        this.restaurantList = new ArrayList<>();
        isAllItemLoaded = false;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_LAYOUT:
                RestaurantItemBinding itemBinding = RestaurantItemBinding.inflate(layoutInflater, parent, false);
                return new RestaurantViewHolder(itemBinding);

            case FOOTER_LAYOUT:
                return new FooterViewHolder(layoutInflater.inflate(R.layout.item_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM_LAYOUT:
                ((RestaurantViewHolder) holder).bind(getItem(position));
                break;
            case FOOTER_LAYOUT:
                ((FooterViewHolder) holder).configureViews();
                break;
        }
    }

    public void setItems(List<Restaurant> entities) {
        this.restaurantList.addAll(entities);
    }

    public void refreshItems() {
        isAllItemLoaded = false;
        this.restaurantList.clear();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return restaurantList == null ? 0 : restaurantList.size() + 1; // extra item for footer
    }

    @Override
    public int getItemViewType(int position) {
        return position == restaurantList.size() ? FOOTER_LAYOUT : ITEM_LAYOUT;
    }

    public Restaurant getItem(int position) {
        return restaurantList.get(position);
    }

    public void hideFooter() {
        Log.e(TAG, "hideFooter: ");
        isAllItemLoaded = true;
        notifyDataSetChanged();
    }

    protected class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private RestaurantItemBinding binding;

        public RestaurantViewHolder(RestaurantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Restaurant restaurant) {

            try {
                Glide.with(context)
                        .load(restaurant.getRestaurant().getFeaturedImage())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder_food)
                                .error(R.drawable.placeholder_food)
                                .centerCrop())
                        .into(binding.ivRestaurantMenu);
            } catch (Exception e) {
                e.printStackTrace();
            }

            binding.tvRestaurantName.setText(restaurant.getRestaurant().getName());
            binding.tvRestaurantRating.setText(restaurant.getRestaurant().getUserRating().getAggregateRating());
            binding.tvRestaurantMenu.setText(restaurant.getRestaurant().getCuisines());
            binding.tvRestaurantPrice.setText(String.format(context.getString(R.string.cost_for_two), restaurant.getRestaurant().getCurrency().concat(String.valueOf(restaurant.getRestaurant().getAverageCostForTwo()))));
            binding.cvRestaurant.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(restaurant);
                }
            });
        }
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder {

        View rowView;

        FooterViewHolder(View rowView) {
            super(rowView);
            this.rowView = rowView;
        }

        void configureViews() {
            if (isAllItemLoaded) {
                itemView.setVisibility(View.VISIBLE);
            }
        }
    }
}
