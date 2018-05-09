package br.com.projeto.pets.features.pet

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.setImageBase64
import br.com.projeto.pets.features.ad.Ad
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_pet.*
import javax.inject.Inject


class PetActivity : DaggerAppCompatActivity(), PetContract.View {
    @Inject
    lateinit var presenter: PetContract.Presenter

    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
//        presenter.onStart(intent.extras.getInt("PET_ID"))
        super.onStart()
    }

    override fun success(ad: Ad) {
        pet_image.setImageBase64(ad.photos[0].photo)

        progressBar.visibility = View.GONE
        result.visibility = View.VISIBLE

        phoneNumber = ad.user.phone
        call_button.setOnClickListener {
            onCallBtnClick()
        }


        breed.text = ad.breed.name
        age.text = ad.age.toString()
        pedigree.text = ""

        worm.text = ""
        castrated.text = ""
        micro_chip.text = ""


        advertiser.text = ad.user.name
        city.text = ad.user.city
        phone.text = ad.user.phone
        email.text = ad.user.email

        description.text = ""

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onCallBtnClick() {
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall()
        } else {

            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                phoneCall()
            } else {
                val PERMISSIONS_STORAGE = arrayOf<String>(android.Manifest.permission.CALL_PHONE)
                //Asking request Permissions
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 9)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        var permissionGranted = false
        when (requestCode) {
            9 -> permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (permissionGranted) {
            phoneCall()
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun phoneCall() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + phoneNumber)
            startActivity(callIntent)
        } else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun error(throwable: Throwable) {
        AlertDialog.Builder(this)
                .setTitle("Occoreu um erro")
                .setMessage(throwable.message)
                .setPositiveButton("Fechar", { dialog, _ -> dialog.dismiss();finish() })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PetActivity::class.java)
    }
}
