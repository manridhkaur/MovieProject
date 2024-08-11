package com.mandrid.omdb

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()


        retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(MediaType.get("application/json")))
            .build()

         omdbApi = retrofit!!.create(OmdbApi::class.java)



        loadData("thee")


        findViewById<MaterialButton>(R.id.btn_search).setOnClickListener {

            val search = findViewById<TextInputEditText>(R.id.tiet_search).text.toString()

            loadData(search)
        }



    }


    fun initUI() {

        recyclerView = findViewById(R.id.rvRecycler)
        fab = findViewById(R.id.fab)


         adapter = MovieAdapter(listOf(), object: OnMovieInteraction {
            override fun onClick(movie: Movie) {
                MovieBottomSheetFragment.getInstance(movie).show(supportFragmentManager,"tagId")
            }

            override fun onFavorite(movie: Movie) {


            }

            override fun onAdd(movie: Movie) {

                Toast.makeText(this@MainActivity,"Add/Updated the movie.",Toast.LENGTH_SHORT).show()
            }

        })

        fab.setOnClickListener {
           // MovieBottomSheetFragment.getInstance(Movie()).show(supportFragmentManager,"tagId")
        loadData("hero")
        }


        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)





    }

    private  var retrofit: Retrofit?=null
    var omdbApi: OmdbApi?=null

    fun loadData(key:String) {


        val progreessDilaog = ProgressDialog(this)
        progreessDilaog.show()
        omdbApi!!.searchMovies(key,"5b27e0a4").enqueue(object: Callback<SearchMovie> {
            override fun onResponse(call: Call<SearchMovie>, response: Response<SearchMovie>) {
                Log.d("TAG", "onResponse: $response")
                progreessDilaog.dismiss()
                adapter.updateMovieList(response.body()!!.search)
            }

            override fun onFailure(call: Call<SearchMovie>, t: Throwable) {
                progreessDilaog.dismiss()
                t.printStackTrace()
            }

        })



    }

}