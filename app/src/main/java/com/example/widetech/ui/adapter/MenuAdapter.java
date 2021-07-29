package com.example.widetech.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.widetech.R;
import com.example.widetech.data.models.MenuItem;
import com.example.widetech.utilities.FontHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private Context context;
    private List<MenuItem> menuList;
    private GeneralAdapterView<MenuItem> adapterView;

    public MenuAdapter(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        MenuViewHolder viewHolder = new MenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, final int position) {
        holder.imgIcon.setImageResource(menuList.get(position).getImgIcon());
        holder.txtName.setText(menuList.get(position).getTxtName());
        holder.imgNext.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_next));
    }

    @Override
    public int getItemCount() {
        return menuList != null ? menuList.size() : 0;
    }

    public void setAdapterView(GeneralAdapterView<MenuItem> adapterView) {
        this.adapterView = adapterView;
    }

    private MenuItem getItem(int position) {
        return menuList.get(position);
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgIcon)
        ImageView imgIcon;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.imgNext)
        ImageView imgNext;

        MenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            new FontHelper(context).applyFont(itemView.findViewById(R.id.globalConstraint));
        }

        @Override
        public void onClick(View view) {
            if (adapterView != null) {
                adapterView.onItemSelected(getItem(getAdapterPosition()));
            }
        }
    }
}
