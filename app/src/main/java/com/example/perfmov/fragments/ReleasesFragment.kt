package com.example.perfmov.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perfmov.*

import kotlinx.android.synthetic.main.films_list.*
import kotlinx.android.synthetic.main.fragment_releases2.*
import kotlinx.android.synthetic.main.fragment_top.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class ReleasesFragment : Fragment() {

    val URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=${BuildConfig.API_KEY1}&language=ru&page=1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_releases2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        get()
    }

    fun get(){

        val request: Request = Request.Builder().url(URL).build()
        OkHttpClient().newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("Error", e.toString())
            }

            var moviesList: ArrayList<Movies> = arrayListOf()
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val json = (JSONObject(response.body()!!.string()))
                activity?.runOnUiThread {
                    val moviesArray = json.getJSONArray("results")
                    for (i in 0..moviesArray.length() - 1) {
                        var movie = moviesArray.optJSONObject(i)
                        var title = movie.getString("title")
                        var poster_path = movie.getString("poster_path")
                        var release_date = movie.getString("release_date")
                        var vote_average = movie.getDouble("vote_average")
                        var original_title = movie.getString("original_title")
                        var vote_count = movie.getString("vote_count")
                        var overview = movie.getString("overview")
                        var backdrop_path = movie.getString("backdrop_path")
                        moviesList.add(Movies(title, poster_path,release_date, vote_average,original_title,vote_count,overview,backdrop_path))



                    }
                    releasesReView.layoutManager = LinearLayoutManager(activity)
                    releasesReView.setHasFixedSize(true)
                    releasesReView.adapter = MovieAdapter(context,moviesList ){
                        val intent = Intent(context, DetailActivity:: class.java)
                        intent.putExtra("OBJECT_INTENT",it)
                        startActivity(intent)

                    }
                }
            }

        })
    }

}
