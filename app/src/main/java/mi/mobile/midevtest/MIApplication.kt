package mi.mobile.midevtest

import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ikhsan on 21/11/17.
 */
class MIApplication : MultiDexApplication()
{

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration
                .Builder()
                .name("mi-db.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(config)
    }
}