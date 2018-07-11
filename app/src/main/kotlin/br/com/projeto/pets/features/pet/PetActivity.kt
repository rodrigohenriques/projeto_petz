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
import br.com.projeto.pets.data.entity.Ad
import br.com.projeto.pets.extension.setImageBase64
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_pet.*
import javax.inject.Inject
import br.com.projeto.pets.data.entity.Breed


class PetActivity : DaggerAppCompatActivity(), PetContract.View {

    @Inject
    lateinit var presenter: PetContract.Presenter
    private var phoneNumber: String? = null

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, PetActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        presenter.onStart(intent.extras.getInt("PET_ID"))
        super.onStart()
    }

    override fun success(ad: Ad) {

        if (ad.photos.isNotEmpty()) {
            pet_image.setImageBase64(ad.photos[0].photo)
        }

        breed.text = ad.breed.name
        collapsing.title = ad.breed.name
        age.text = ad.age.toString()
        vacinnated.text = if (ad.isVaccinated) "Sim" else "Não"

        ad.user?.let {
            ad.user.phone.let { phone ->
                call_button.visibility = View.VISIBLE
                phoneNumber = phone
                call_button.setOnClickListener {
                    onCallBtnClick()
                }
            }

            ad.user.email.let { email ->
                email_button.visibility = View.VISIBLE
                email_button.setOnClickListener {
                    sendEmail(email, ad.breed)
                }
            }

            advertiser.text = ad.user.name
            city.text = ad.user.city
            state.text = ad.user.state
        }
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

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        var permissionGranted = false
        when (requestCode) {
            9 -> permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
        if (permissionGranted) {
            phoneCall()
        } else {
            Toast.makeText(this, "You don't assign permission.",
                    Toast.LENGTH_SHORT).show()
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

    private fun onCallBtnClick() {
        if (Build.VERSION.SDK_INT < 23) {
            phoneCall()
        } else {
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {

                phoneCall()
            } else {
                val PERMISSIONS_STORAGE = arrayOf(android.Manifest.permission.CALL_PHONE)
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 9)
            }
        }
    }

    private fun sendEmail(email: String, breed: Breed) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:$email")
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Interesse no anúncio de $breed")

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email com..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@PetActivity, "Não há um aplicativo de " +
                    "envio de emails instalado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun phoneCall() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + phoneNumber)
            startActivity(callIntent)
        } else {
            Toast.makeText(this, "You don't assign permission.",
                    Toast.LENGTH_SHORT).show()
        }
    }
}