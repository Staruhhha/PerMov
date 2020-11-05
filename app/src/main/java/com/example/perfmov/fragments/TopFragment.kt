package com.example.perfmov.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perfmov.MovieAdapter
import com.example.perfmov.Movies

import com.example.perfmov.R
import kotlinx.android.synthetic.main.fragment_top.*

/**
 * A simple [Fragment] subclass.
 */
class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_top, container, false)

        val movies = listOf(
            Movies (1, "Demon Slayer","https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.geekphilia.com%2F2020%2F07%2F03%2Fdemon-slayer-kimetsu-no-yaiba-the-movie-mugen-train-is-coming-theaters%2F&psig=AOvVaw23qqf4M-Vm-Y3FnjEKazri&ust=1604670463431000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCIiusrbF6-wCFQAAAAAdAAAAABAD","7.8", "25-11-2020")
        )

        var tpRView = rootView.findViewById(R.id.topReView) as RecyclerView

        tpRView.layoutManager = LinearLayoutManager(activity)
        tpRView.adapter = MovieAdapter(movies)
        return rootView
    }

}
