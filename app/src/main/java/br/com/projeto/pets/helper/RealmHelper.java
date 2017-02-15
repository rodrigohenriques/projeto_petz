package br.com.projeto.pets.helper;

import br.com.projeto.pets.BuildConfig;
import br.com.projeto.pets.R;
import br.com.projeto.pets.utils.CustomContext;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmHelper {

    private RealmHelper() {
    }

    public static Realm getInstance() {
        final RealmConfiguration realmConfiguration;

        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name(CustomContext.getContext().getResources().getString(R.string.nome_banco));
        if (BuildConfig.DEBUG) {
            builder.deleteRealmIfMigrationNeeded();
        } else {//sobreescrever a condição caso seja necessário o migration na versão de produção
            builder.deleteRealmIfMigrationNeeded();
        }
        realmConfiguration = builder.build();

        return Realm.getInstance(realmConfiguration);
    }

}
