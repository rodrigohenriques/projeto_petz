package br.com.projeto.pets.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.projeto.pets.R;

public class Util {

    public static final String xxx = "";


    private Util() {
        super();
    }


    /**
     * Esconde o Teclado
     *
     * @param getActivity
     * @param getView
     */
    public static void hideKeyboard(Activity getActivity, View getView) {
        final InputMethodManager imm = (InputMethodManager) getActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView.getWindowToken(), 0);
    }


    public static void hideStatusBar(Activity activity) {
        Window window = activity.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * Modifica a Barra de Status da Aplicação
     * Lolipop >=
     *
     * @param activity
     * @param color
     */
    public static void colorStatusBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }


    /**
     * Modifica a Barra de Navegação da Aplicação
     * Lolipop >=
     *
     * @param activity
     * @param color
     */
    public static void colorNavigationBar(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(color);
        }
    }


    public static String getDeviceName() {
        return Build.BRAND + " | " + Build.MODEL;
    }


    public static String getVersionName() throws PackageManager.NameNotFoundException {
        return CustomContext.getContext().getPackageManager().getPackageInfo(CustomContext.getContext().getPackageName(), 0).versionName;
    }


    public static long getVersionCode() throws PackageManager.NameNotFoundException {
        return CustomContext.getContext().getPackageManager().getPackageInfo(CustomContext.getContext().getPackageName(), 0).versionCode;
    }


    public static String getPackageName() {
        return CustomContext.getContext().getPackageName();
    }


    public static String getUID() {
        return Settings.Secure.getString(CustomContext.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static String getURL(String ip, String porta) {
        String prefixoURL = "http://";
        String prefixoPorta = ":";
        String url = "";
        url = prefixoURL + ip;
        if (!porta.trim().isEmpty()) {
            url = url + prefixoPorta + porta;
        }
        return url;
    }


    public static void showErrorAlert(Activity activity, String titulo, String mensagem) {
        new AlertDialog.Builder(activity, R.style.mAlert)
                .setCancelable(true)
                .setMessage(mensagem)
                .setTitle(titulo)
                .setPositiveButton(CustomContext.getContext().getResources().getString(R.string.lbl_fechar), null)
                .show();
    }


    public static Snackbar showErrorSnack(Activity activity) {
        String texto = "";
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), texto, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(CustomContext.getContext().getResources().getColor(R.color.accent));
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        return snackbar;
    }


    public static Snackbar showErrorSnack(View mView) {
        String texto = "";
        Snackbar snackbar = Snackbar.make(mView.findViewById(android.R.id.content), texto, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(CustomContext.getContext().getResources().getColor(R.color.accent));
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        return snackbar;
    }


    public static void showToast(Activity activity, String texto) {
        Toast.makeText(activity, texto, Toast.LENGTH_LONG).show();
    }


    /**
     * Recebe uma string no padrão XSD Data Type e retorna um Objeto java.util.Date convertido </br>
     * Formato: "yyyy-MM-DD'T'hh:mm:ss"
     *
     * @param stringDate
     * @return java.util.Date
     * @throws ParseException
     */
    public static Date getStringDateXSD(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomContext.getContext().getString(R.string.xsd_datetime_format));
        return simpleDateFormat.parse(stringDate);
    }


    /**
     * Recebe uma java.util.Date e retorna uma String no padrão XSD Data Type </br>
     * Formato: "yyyy-MM-DD'T'hh:mm:ss"
     *
     * @param date
     * @return String no formato XSD
     * @throws ParseException
     */
    public static String getDateStringXSD(Date date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CustomContext.getContext().getString(R.string.xsd_datetime_format));
        return simpleDateFormat.format(date);
    }


    public static CharSequence date_time_24_mask(Date date) {
        CharSequence data = date_mask(date);
        CharSequence time = time_24_mask(date);
        String conv = time.toString() + " " + data.toString();
        return conv;
    }


    public static CharSequence time_24_date_mask(Date date) {
        CharSequence data = date_mask(date);
        CharSequence time = time_24_mask(date);
        String conv = data.toString() + " " + time.toString();
        return conv;
    }


    public static CharSequence time_24_mask(Date date) {
        java.text.DateFormat d = android.text.format.DateFormat.getTimeFormat(CustomContext.getContext());
        return d.format(date);
    }


    public static CharSequence date_mask(Date date) {
        java.text.DateFormat d = android.text.format.DateFormat.getDateFormat(CustomContext.getContext());
        return d.format(date);
    }


    /**
     * bloco de envio de mensagem por broadcast receiver
     */
    public static void enviaMensagemLocalBroadcast(Intent mensagem, Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(mensagem);
    }


    /**
     * Pinta uma imagem em PNG presente no sistema
     *
     * @param resources define a imagem a ser redefinida
     * @param layView   recebe o conteiner onde fica a imagem
     * @param imgView   recebe a imagem modificada
     */
    public static void tintView(Drawable resources, LinearLayout layView, AppCompatImageView imgView, Context context, int colorSelected, int colorDeselected) {
        //processo do AppCompat 23.+ para forçar o comportamento de Tint e TintBackground em uma view programaticamente
        Drawable wrap = DrawableCompat.wrap(resources).mutate();//indica a view a ser pintada e torna ela mutavel
        if (layView.isSelected()) {//depois do processo acima, força a view a pintar a cor da imagem de acordo com o estado atual
            DrawableCompat.setTint(wrap, context.getResources().getColor(colorSelected));
        } else {
            DrawableCompat.setTint(wrap, context.getResources().getColor(colorDeselected));
        }
        imgView.setBackgroundDrawable(wrap);//troca o background da imagem... não funciona com a imagem do SRCCompat nem com SRC normal, nem SVG ( em dispositivos pré 21)
    }

    public static void setTitleActionBar(ActionBar actionBar, String title) {
        actionBar.setTitle(title);
    }

}
