package br.com.projeto.pets.adapter.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.projeto.pets.R;
import br.com.projeto.pets.adapter.holder.SalesListViewHolder;
import br.com.projeto.pets.model.Mock;

/**
 * Created by samir on 15/04/2015.
 */
public class SalesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Mock> itens;
    private Activity activity;

    public SalesListAdapter(List<Mock> itens, Activity activity) {
        super();
        this.itens = itens;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    //    @Override
//    public int getItemViewType(int position) {
//        if (position==0) {
//            return 0;
//        }else{
//            return 1;
//        }
//    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder paramVH, final int position) {
        //if (getItemViewType(position)!=0) {}else{}
        final SalesListViewHolder holder = (SalesListViewHolder) paramVH;

        Glide.with(activity)
                .load(itens.get(position).getText())
                .centerCrop()
                .into(holder.getImgItemSale());

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
//        if (viewType==0) {
//            itemView = LayoutInflater.
//                    from(parent.getContext()).
//                    inflate(R.layout_toolbar_calibragem.menu_conteudo, parent, false);
//            return new HolderMenuConteudo(itemView);
//        }
        itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adp_sales_list, parent, false);
        return new SalesListViewHolder(itemView);
    }

}