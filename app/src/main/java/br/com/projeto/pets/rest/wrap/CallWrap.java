package br.com.projeto.pets.rest.wrap;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.projeto.pets.rest.RespostaErro;
import br.com.projeto.pets.utils.CustomContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallWrap<T> {
    private static final String TAG = "CallWrap";


    private Delegate<T> delegate;
    private Call<T> call;

    private CallWrap() {
    }

    public CallWrap(Call<T> call, Delegate<T> delegate) {
        this.delegate = delegate;
        this.call = call;
    }

    public void execute() {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Log.w(TAG, "onResponse");
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: isSuccessful");
                    Log.d(TAG, "onResponse: " + response.body());
                    delegate.onSuccess(response.body());
                } else {
                    Log.i(TAG, "onResponse: not isSuccessful");
                    RespostaErro resposta = null;
                    String onError = "onResponse: not isSuccessful";
                    StringBuilder builder = new StringBuilder();
                    try {
                        resposta = new Gson().fromJson(response.errorBody().string(), RespostaErro.class);
                        if (resposta != null) {
                            if (resposta.getMessageInfo() != null) {
                                builder.append(resposta.getMessageInfo());
                                builder.append("\n");
                            }
                            if (resposta.getError() != null) {
                                builder.append(resposta.getError());
                                builder.append("\n");
                            }
                            onError = builder.toString();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        onError = e.getMessage();
                    }
                    delegate.onError(onError);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);

                t.printStackTrace();
                if (!call.isCanceled()) {
                    //String errorMsg = CustomContext.getContext().getString(ConnectionErrorHelper.getError(t).value());
                    Log.v(TAG, "onFailure: " + "errorMsg", t);
                    delegate.onError("errorMsg");
                }//não dispara o alert
                if (!call.isCanceled() && !(t instanceof IOException)) {
                    //testar parse de json errado
                    //Crashlytics.setString("onFailure: BaseOperation > call", "URL: " + call.request().url().toString());
                    //Util.sendReport("onFailure", "BaseOperation", "call", t);
                    delegate.onError(null);
                }//não é necessário o log
            }
        });
    }
}