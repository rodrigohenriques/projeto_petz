package br.com.projeto.pets.features.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.projeto.pets.R
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.card_informations.*
import kotlinx.android.synthetic.main.card_opitional_informations.*
import javax.inject.Inject


class CreateActivity : DaggerAppCompatActivity(), CreateContract.View {

    @Inject
    lateinit var presenter: CreateContract.Presenter

    private val LIMIT_PHOTO: Int = 6
    private var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
    private var breedId = -1
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_create)

        add_photo.setOnClickListener {
            Album.image(this) // Camera function.
                    .multipleChoice()
                    .camera(false)
                    .checkedList(mAlbumFiles)
                    .selectCount(LIMIT_PHOTO)
                    .onResult { this.updateRecycleView(it) }
                    .start()
        }

        setRecycleview()
        radioAdjust()

        bt_publish.setOnClickListener {
            checkFields()
        }

        breed.addItems(presenter.breedList)
        breed.setOnItemSelectedListener { item, _ ->
            breedId = item.id
        }
    }

    private fun updateRecycleView(albumFiles: ArrayList<AlbumFile>) {
        mAlbumFiles = albumFiles
        (recyclerView.adapter as MyRecyclerViewAdapter).updateData(albumFiles)
    }


    private fun setRecycleview() {
        recyclerView = findViewById<View>(R.id.rvNumbers) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val adapter = MyRecyclerViewAdapter(this, mAlbumFiles)
        recyclerView.adapter = adapter
    }

    private fun radioAdjust() {
        radio_sell.setOnClickListener {
            deselectRadio(it.id)
        }
        radio_adoption.setOnClickListener {
            deselectRadio(it.id)
        }

    }

    private fun deselectRadio(id: Int) {
        when (id) {
            R.id.radio_sell -> {
                radio_sell.isChecked = true
                radio_adoption.isChecked = false
                price.visibility = View.VISIBLE
            }
            R.id.radio_adoption -> {
                radio_sell.isChecked = false
                radio_adoption.isChecked = true
                price.visibility = View.GONE
            }
        }
    }

    private fun checkFields() {
        if (breedId == -1) {
            showSnackBarError("Raça")
        } else if (getLocale().isEmpty()) {
            showSnackBarError("Localização")
        } else if (getPrice().isEmpty() && radio_sell.isChecked) {
            showSnackBarError("Preço")
        } else if (getPhone().isEmpty()) {
            showSnackBarError("Telefone")
        } else {
            presenter.sendRequest()
        }
    }

    override fun success() {
        finish()
    }

    override fun error() {
        Snackbar.make(radio_sell, "Ocorreu um erro, tente novamente", Snackbar.LENGTH_LONG).show()
    }

    private fun showSnackBarError(field: String) {
        Snackbar.make(radio_sell, "Campo $field não preenchido", Snackbar.LENGTH_LONG).show()
    }


    //Photo

    override fun getAlbumImages(): List<AlbumFile> = mAlbumFiles

    //Information

    override fun getBreedId(): Int = breedId
    override fun getTypeAd(): Int = if (radio_sell.isChecked) 0 else 1
    override fun getAge(): Int = indicatorSeekBar.progress
    override fun getLocale(): String = locale.text.toString()
    override fun getPrice(): String = price.text.toString()
    override fun getPhone(): String = phone.text.toString()

    //Optional Information

    override fun isBrood(): Boolean = brood.isSelected
    override fun isPedigree(): Boolean = pedigree.isSelected
    override fun isCastration(): Boolean = castration.isSelected
    override fun isMicroChip(): Boolean = micro_chip.isSelected


    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }
}
