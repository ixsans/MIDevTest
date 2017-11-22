package mi.mobile.midevtest.feature.deliveryDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_bar.*
import mi.mobile.midevtest.R
import mi.mobile.midevtest.util.Util


class DeliveryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Util.changeToolbarFont(toolbar, this)

        if (savedInstanceState == null) {
            val arguments = Bundle()
            arguments.putParcelable(DeliveryDetailFragment.ARG_ITEM,
                    intent.getParcelableExtra(DeliveryDetailFragment.ARG_ITEM))
            val fragment = DeliveryDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .replace(R.id.delivery_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home ->
                {
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
