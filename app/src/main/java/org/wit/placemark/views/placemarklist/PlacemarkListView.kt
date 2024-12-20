package org.wit.placemark.views.placemarklist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.placemark.R
import org.wit.placemark.adapters.PlacemarkAdapter
import org.wit.placemark.adapters.PlacemarkListener
import org.wit.placemark.databinding.ActivityPlacemarkListBinding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkListView : AppCompatActivity(), PlacemarkListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlacemarkListBinding
    lateinit var presenter: PlacemarkListPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = PlacemarkListPresenter(this)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadPlacemarks()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        updateMenu()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddPlacemark() }
            R.id.item_map -> { presenter.doShowPlacemarksMap() }
            R.id.item_login -> { presenter.doShowPlacemarkLogin() }
            R.id.item_search -> { presenter.doSearchPlacemark() }
            R.id.item_clear_search -> { presenter.clearSearch() }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateMenu() {
        if (presenter.isSearching) {
            binding.toolbar.menu.findItem(R.id.item_clear_search).isVisible = true
            binding.toolbar.menu.findItem(R.id.item_add).isVisible = false
            binding.toolbar.menu.findItem(R.id.item_map).isVisible = false
            binding.toolbar.menu.findItem(R.id.item_login).isVisible = false
            binding.toolbar.menu.findItem(R.id.item_search).isVisible = false
        } else {
            binding.toolbar.menu.findItem(R.id.item_clear_search).isVisible = false
            binding.toolbar.menu.findItem(R.id.item_add).isVisible = true
            binding.toolbar.menu.findItem(R.id.item_map).isVisible = true
            binding.toolbar.menu.findItem(R.id.item_login).isVisible = true
            binding.toolbar.menu.findItem(R.id.item_search).isVisible = true
        }
    }

    override fun onPlacemarkClick(placemark: PlacemarkModel, position: Int) {
        this.position = position
        presenter.doEditPlacemark(placemark, this.position)
    }

    private fun loadPlacemarks() {
        binding.recyclerView.adapter = PlacemarkAdapter(presenter.getPlacemarks(), this)
        onRefresh()
    }

    fun onRefresh() {
        binding.recyclerView.adapter?.
        notifyItemRangeChanged(0,presenter.getPlacemarks().size)
    }

    fun onDelete(position : Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }

    fun updatePlacemarks(placemarks: List<PlacemarkModel>) {
        binding.recyclerView.adapter = PlacemarkAdapter(placemarks, this)
        onRefresh()
    }
}