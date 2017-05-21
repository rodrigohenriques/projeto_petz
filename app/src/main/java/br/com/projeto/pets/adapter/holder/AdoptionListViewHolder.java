package br.com.projeto.pets.adapter.holder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.projeto.pets.R;

public class AdoptionListViewHolder extends RecyclerView.ViewHolder{

    private CardView cardView;
    private AppCompatImageView imgItemSale;
    private AppCompatTextView txtTxt1, txtTxt2, txtTxt3;

    public AdoptionListViewHolder(View v) {
        super(v);
        cardView = (CardView) v.findViewById(R.id.cardView);
        imgItemSale = (AppCompatImageView) v.findViewById(R.id.imgItemSale);
        txtTxt1 = (AppCompatTextView) v.findViewById(R.id.txtTxt1);
        txtTxt2 = (AppCompatTextView) v.findViewById(R.id.txtTxt2);
        txtTxt3 = (AppCompatTextView) v.findViewById(R.id.txtTxt3);
    }

    public CardView getCardView() {
        return cardView;
    }

    public AppCompatImageView getImgItemSale() {
        return imgItemSale;
    }

    public AppCompatTextView getTxtTxt1() {
        return txtTxt1;
    }

    public AppCompatTextView getTxtTxt2() {
        return txtTxt2;
    }

    public AppCompatTextView getTxtTxt3() {
        return txtTxt3;
    }
}