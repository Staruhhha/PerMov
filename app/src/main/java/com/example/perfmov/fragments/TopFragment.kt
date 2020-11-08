

package com.example.perfmov.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perfmov.*
import kotlinx.android.synthetic.main.films_list.*
import kotlinx.android.synthetic.main.fragment_top.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.coroutineContext


/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {

    val URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=${BuildConfig.API_KEY1}&language=ru&page=1"

    lateinit var progress_Bar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        progress_Bar = activity!!.findViewById<ProgressBar>(R.id.progressBar1)
        progress_Bar.visibility = View.VISIBLE
        return inflater.inflate(R.layout.fragment_top, container, false)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


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
                        topReView.layoutManager = LinearLayoutManager(activity)
                        topReView.setHasFixedSize(true)
                        topReView.adapter = MovieAdapter(context,moviesList ){
                        val intent = Intent(context, DetailActivity:: class.java)
                        intent.putExtra("OBJECT_INTENT",it)
                        startActivity(intent)

                    }
                    progress_Bar.visibility = View.INVISIBLE
                }
            }

        })
    }
}


