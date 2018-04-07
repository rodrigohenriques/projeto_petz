package br.com.projeto.pets.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import br.com.projeto.pets.R;

public class Spinner extends android.support.v7.widget.AppCompatEditText implements View.OnClickListener {

    CharSequence[] items;
    private Context context;
    private OnItemSelectedListener onItemSelectedListener;

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
        setFocusable(false);
        setClickable(true);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        requestFocus();
        buildDialogWithItems();
    }

    public void addItems(List<String> items) {
        this.items = items.toArray(new CharSequence[items.size()]);
    }

    public void addItems(CharSequence[] items) {
        this.items = items;
    }

    private void buildDialogWithItems() {
        @SuppressLint("RestrictedApi") ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.CustomDialogTheme);
        AlertDialog.Builder builder = new AlertDialog.Builder(contextThemeWrapper);
        // builder.setTitle(mHint);

        builder.setItems(items, new OnClickListenerToDialog());
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
            setText(items[selectedIndex]);

            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelectedListener(items[selectedIndex].toString(), selectedIndex);
            }
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener){
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(String item, int selectedIndex);
    }

}