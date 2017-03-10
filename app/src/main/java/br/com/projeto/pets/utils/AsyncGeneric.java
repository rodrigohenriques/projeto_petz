package br.com.projeto.pets.utils;

import android.os.AsyncTask;

public class AsyncGeneric extends AsyncTask<Void, Void, Void> {
    private AsyncGenericImpl delegate;

    public AsyncGeneric(AsyncGenericImpl delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(Void... params) {
        delegate.background();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        delegate.post();
    }

    public interface AsyncGenericImpl {
        void background();

        void post();
    }
}
