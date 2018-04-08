package br.com.projeto.pets.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import br.com.projeto.pets.R;
import br.com.projeto.pets.features.ad.Breed;

public class Spinner extends android.support.v7.widget.AppCompatEditText implements View.OnClickListener {

    private List<Breed> items;
    private Context context;
    private OnItemSelectedListener onItemSelectedListener;
    private CharSequence[] breedConverted;

    public Spinner(Context context) {
        super(context);
        this.context = context;
    }

    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Spinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int x = getWidth();
        int y = getHeight() - (int)(displayMetrics.density * 8);

        @SuppressLint("DrawAllocation")
        Path path = new Path();
        path.moveTo(x, displayMetrics.density*15);
        path.lineTo(x, 0);
        path.lineTo(x, y);
        path.lineTo(x - (displayMetrics.density*22), y);

        path.close();


        path.close();

        @SuppressLint("DrawAllocation")
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.blue_light_1));

        canvas.drawPath(path, p);

        setFocusable(false);

        setClickable(true);

        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        requestFocus();
        buildDialogWithItems();
    }

    public void addItems(List<Breed> breeds) {
        if (breeds == null) return;
        this.items = breeds;

        ArrayList<String> a = new ArrayList<String>();
        for (Breed breed : breeds) {
            a.add(breed.getName());
        }
        this.breedConverted = a.toArray(new CharSequence[a.size()]);
    }


    private void buildDialogWithItems() {
        @SuppressLint("RestrictedApi") ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.CustomDialogTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextThemeWrapper);
        // builder.setTitle(mHint);

        builder.setItems(breedConverted, new OnClickListenerToDialog());
        //builder.setPositiveButton(R.string.dialog_close_button, null);
        Dialog dialog = builder.create();
        configureMode(dialog);
    }

    private void configureMode(Dialog dialog) {
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.TOP | Gravity.CENTER);
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_FRAME);
        p.x = (int) this.getX();
        p.y = (int) this.getY() + this.getHeight();
        dialog.getWindow().setAttributes(p);

        dialog.show();

    }


    private class OnClickListenerToDialog implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int selectedIndex) {
            setText(items.get(selectedIndex).getName());
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelectedListener(items.get(selectedIndex), selectedIndex);
            }
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(Breed item, int selectedIndex);
    }

}