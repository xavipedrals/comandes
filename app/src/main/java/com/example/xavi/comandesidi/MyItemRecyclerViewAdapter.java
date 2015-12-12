package com.example.xavi.comandesidi;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xavi.comandesidi.ItemFragment.OnListFragmentInteractionListener;
import com.example.xavi.comandesidi.domini.ProductsContainer;
import com.example.xavi.comandesidi.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<ProductsContainer.Product> productList;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public MyItemRecyclerViewAdapter(ProductsContainer productsContainer, OnListFragmentInteractionListener listener, Context context) {
        productList = productsContainer.getProductList();
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.product = productList.get(position);
        holder.nameTv.setText(productList.get(position).getName());
        String priceStr = String.valueOf(productList.get(position).getPrice()) + " €";
        holder.priceTv.setText(priceStr);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), productList.get(position).getMipmapId());
        Bitmap round = ImageHelper.getRoundedShape(bitmap, 128);
        holder.imageView.setImageBitmap(round);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.product);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final LinearLayout itemContainer;
        public final TextView nameTv;
        public final TextView priceTv;
        public TextView quantitatTv;
        public ImageView imageView;
        public ProductsContainer.Product product;

        public ViewHolder(View view) {
            super(view);
            itemContainer = (LinearLayout) view.findViewById(R.id.item_container);
            mView = view;
            nameTv = (TextView) view.findViewById(R.id.product_name);
            priceTv = (TextView) view.findViewById(R.id.product_price);
            quantitatTv = (TextView) view.findViewById(R.id.quantitat);
            imageView = (ImageView) view.findViewById(R.id.listImageView);
            int alpha = 54 * 255 / 100; //54% de opacitat, secondary text
            priceTv.setTextColor(Color.argb(alpha, 0, 0, 0));
        }

    }
}
