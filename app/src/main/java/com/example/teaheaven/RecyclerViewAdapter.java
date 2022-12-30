package com.example.teaheaven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<TeaCardModel> contactList;
    DBHelper helper;
    SharedPreferences preferences;
    String username = "";
    static int cartTotalPrice = 0;
//    screen 0 implies recycler view is of homepage
//    screen 1 implies it is of see all page
//    screen 2 implies it is of cart page
    private int screen;
    public RecyclerViewAdapter(Context context, List<TeaCardModel> contactList, int screen) {
        this.context = context;
        this.contactList = contactList;
        this.screen = screen;
        helper = new DBHelper(context);
        preferences = context.getSharedPreferences("users",Context.MODE_PRIVATE);
        username = preferences.getString("username","null");
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(screen == 0)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_card, parent, false);
        else if (screen == 1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_tea_card, parent, false);
        else if(screen == 2)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card, parent, false);
        return new ViewHolder(view);
    }
    static int color = 0;
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        TeaCardModel contact = contactList.get(position);
        int totalPrice = 0;
        holder.imPic.setImageResource(contact.getImage());
        Log.v("name", contact.getName());
        holder.tvName.setText(contact.getName());
        holder.tvPrice.setText(contact.getPrice());
        holder.tvWeight.setText(contact.getWeight());
        holder.itemNumForCart = helper.getCounts(username,contact.getName());

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(50);
        if (color == 0)
            shape.setColor(Color.rgb(185, 235, 152));
        else if (color == 1)
            shape.setColor(Color.rgb(250, 214, 146));
        else if (color == 2)
            shape.setColor(Color.rgb(216, 232, 209));
        else if (color == 3) {
            shape.setColor(Color.rgb(243, 227, 196));
            color = 0;
        }
//        else if(color == 4)
//            shape.setColor(Color.rgb(244, 90, 2));
//        else if(color == 5)
//            shape.setColor(Color.rgb(88, 39, 16));
//        else if(color == 6) {
//            shape.setColor(Color.rgb(157, 123, 97));
//        }
        color++;

        if (screen == 0)
            holder.container.setBackground(shape);
//        test else if, remove it after testing along with content inside it.
//        then uncomment the else if below
//        else if(screen == 1) {
//            holder.cardView.setBackground(shape);
//        }

        else if (screen == 1 || screen == 2)
            holder.cardView.setBackground(shape);

        if(screen == 2) {
            holder.num.setText(contact.getTotal());
            totalPrice = Integer.parseInt(contact.getPrice());

//            plus btn
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.itemNumForCart++;
                    String name = holder.tvName.getText().toString();
                    helper.updateCartData(username,name,holder.itemNumForCart);
                    int price = helper.getPrice(name);
                    int currPrice = Integer.parseInt(holder.tvPrice.getText().toString());
                    currPrice += price;
                    holder.tvPrice.setText(currPrice+"");
                    helper.updatePrice(currPrice, name);

                    holder.num.setText(holder.itemNumForCart+"");
                }
            });
//            minus btn
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.itemNumForCart > 0) {
                        holder.itemNumForCart--;
                        String name = holder.tvName.getText().toString();
                        if(holder.itemNumForCart != 0) {
                            helper.updateCartData(username, name, holder.itemNumForCart);
                            int price = helper.getPrice(name);
                            int currPrice = Integer.parseInt(holder.tvPrice.getText().toString());
                            currPrice -= price;
                            holder.tvPrice.setText(currPrice + "");
                            helper.updatePrice(currPrice, name);

                            holder.num.setText(holder.itemNumForCart + "");
                        } else {
                            holder.tvPrice.setText("0");
                            holder.num.setText("0");
                            helper.deleteItemFromCart(name);
                        }
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvWeight;
        public ImageView imPic;
        public RelativeLayout container;
        public CardView cardView;
        public Button plus;
        public Button minus;
        public TextView num;
        public int itemNumForCart = 0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            if(screen == 0) {
                tvName = itemView.findViewById(R.id.tvName);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvWeight = itemView.findViewById(R.id.tvWeight);
                imPic = itemView.findViewById(R.id.imTea);
                container = itemView.findViewById(R.id.cardLayout);
            } else if (screen == 1) {
                tvName = itemView.findViewById(R.id.tvAllTeaName);
                tvPrice = itemView.findViewById(R.id.tvAllTeaPrice);
                tvWeight = itemView.findViewById(R.id.tvAllTeaWeight);
                imPic = itemView.findViewById(R.id.imAllTeaPic);
                cardView = itemView.findViewById(R.id.allTeaCard);
            } else if(screen == 2) {
                tvName = itemView.findViewById(R.id.tvAllTeaName);
                tvPrice = itemView.findViewById(R.id.tvCartTeaPrice);
                tvWeight = itemView.findViewById(R.id.tvAllTeaWeight);
                imPic = itemView.findViewById(R.id.imAllTeaPic);
                cardView = itemView.findViewById(R.id.allTeaCard);
                plus = itemView.findViewById(R.id.cartPlusBtn);
                minus = itemView.findViewById(R.id.cartMinusBtn);
                num = itemView.findViewById(R.id.cartTotalItem);
            }
        }

        @Override
        public void onClick(View v) {
            if(screen == 0 || screen == 1) {
                Intent intent = new Intent(v.getContext(), TeaItemActivity.class);
//            Log.v("name",tvName.getText().toString());
                intent.putExtra("name", tvName.getText().toString());
                intent.putExtra("price", tvPrice.getText().toString());
                Log.v("Price",tvPrice.getText().toString());
                intent.putExtra("weight", tvWeight.getText().toString());
//            imPic.buildDrawingCache();
//            Bitmap bitmap = imPic.getDrawingCache();
//            intent.putExtra("bitmap",bitmap);
                v.getContext().startActivity(intent);
            }
        }
    }
}
