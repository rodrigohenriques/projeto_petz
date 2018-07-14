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
import br.com.projeto.pets.util.Mask
import com.jakewharton.rxbinding2.view.ViewGroupHierarchyChangeEvent
import com.jakewharton.rxbinding2.view.ViewGroupHierarchyChildViewAddEvent
import com.jakewharton.rxbinding2.view.changeEvents
import com.jakewharton.rxbinding2.widget.checkedChanges
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.card_description.*
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

        setRecyclerView()
        phone.addTextChangedListener(Mask.insert("(##)#####-####", phone))

        radio_group.setOnCheckedChangeListener { _, id ->
            when(id) {
                R.id.radio_sell -> {
                    priceTextInputLayout.visibility = View.VISIBLE
                }
                R.id.radio_adoption -> {
                    price.setText("")
                    priceTextInputLayout.visibility = View.GONE
                }
            }
        }

        breed.addItems(presenter.breedList)
        breed.setOnItemSelectedListener { item, _ ->
            breedId = item.id
        }

        add_photo.setOnClickListener {
            Album.image(this) // Camera function.
                    .multipleChoice()
                    .camera(true)
                    .checkedList(mAlbumFiles)
                    .selectCount(LIMIT_PHOTO)
                    .onResult { this.updateRecycleView(it) }
                    .start()
        }

        bt_publish.setOnClickListener {
            it.isEnabled = false
            checkFields()
        }
    }

    override fun success() {
        finish()
    }

    override fun error() {
        loadingBackground.visibility = View.INVISIBLE
        Snackbar.make(radio_sell, "Ocorreu um erro, tente novamente",
                Snackbar.LENGTH_LONG).show()
        bt_publish.isEnabled = true
    }

    override fun showLoading() {
        loadingBackground.visibility = View.VISIBLE
    }

    override fun getAlbumImages(): List<AlbumFile> = mAlbumFiles

    override fun getDescription(): String = description.text.toString()
    override fun getBreedId(): Int = breedId
    override fun getAge(): Int = indicatorSeekBar.progress
    override fun getCity(): String = city.text.toString()
    override fun getState(): String = state.text.toString()
    override fun getPrice(): Float {
        var priceNumeric = 0F
        if (price.text.isNotEmpty()) {
            priceNumeric = price.text.toString().toFloat()
        }

        return priceNumeric
    }
    override fun getPhone(): String = Mask.unmask(phone.text.toString())
    override fun getCategoryId(): Int = if (radio_sell.isChecked) 2 else 1
    override fun isHatch(): Boolean = hatch.isChecked
    override fun isVaccinated(): Boolean = vacinnated.isChecked

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }

    private fun updateRecycleView(albumFiles: ArrayList<AlbumFile>) {
        mAlbumFiles = albumFiles
        (recyclerView.adapter as AlbumRecyclerViewAdapter).updateData(albumFiles)
    }

    private fun setRecyclerView() {
        recyclerView = findViewById<View>(R.id.rvNumbers) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val adapter = AlbumRecyclerViewAdapter(this, mAlbumFiles)
        recyclerView.adapter = adapter
    }

    private fun checkFields() {
        if (getPrice() == 0F && radio_sell.isChecked) {
            showSnackBarError("Preço")
        } else if (breedId == -1) {
            showSnackBarError("Raça")
        } else if (getCity().isEmpty() || getState().isEmpty()) {
            showSnackBarError("Localização")
        } else if (getPhone().length < 11) {
            showSnackBarError("Telefone")
        } else {
            presenter.sendRequest()
        }
    }

    private fun showSnackBarError(field: String) {
        Snackbar.make(radio_sell, "Campo $field não preenchido",
                Snackbar.LENGTH_LONG).show()
        bt_publish.isEnabled = true
    }
}
