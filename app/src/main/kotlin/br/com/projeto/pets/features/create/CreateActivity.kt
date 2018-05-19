package br.com.projeto.pets.features.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.projeto.pets.R
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumFile
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_create.*


class CreateActivity : DaggerAppCompatActivity(), CreateContract.View {

    var LIMIT_PHOTO: Int = 6
    var mAlbumFiles: ArrayList<AlbumFile> = ArrayList()
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

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }
}
