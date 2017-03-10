package br.com.projeto.pets.helper;

import android.content.pm.PackageManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import br.com.projeto.pets.BuildConfig;
import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.CustomContext;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

    public static int TIME_RELEASE = 90;
    public static int TIME_DEBUG = 30;


    private RetrofitHelper() {
    }


    /**
     * URL de Conexão
     * Intercepor para definir os Headers
     *
     * @param url
     * @return
     */
    public static Retrofit getInstance(String url) {
        return getInstance(url, null);
    }


    /**
     * URL de Conexão
     * Intercepor para definir os Headers
     *
     * @param url
     * @param interceptor
     * @return
     */
    public static Retrofit getInstance(String url, Interceptor interceptor) {
        final int time = BuildConfig.DEBUG ? TIME_DEBUG : TIME_RELEASE;
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(time, TimeUnit.SECONDS);
        builder.connectTimeout(time, TimeUnit.SECONDS);
        builder.addInterceptor(getDefaultHeader());
        if (interceptor != null) {//header adicional externo
            builder.addInterceptor(interceptor);
        }
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        final OkHttpClient okHttpClient = builder.build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url + CustomContext.getContext().getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    private static Interceptor getDefaultHeader() {
        return new Interceptor() {

            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder requestBuilder = null;
                Request request = null;

                requestBuilder = chain.request().newBuilder();
                requestBuilder.addHeader("Accept", "application/json");
                requestBuilder.addHeader("Content-Type", "application/json");
                request = requestBuilder.build();

                return chain.proceed(request);
            }
        };
    }

}
