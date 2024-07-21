package com.example.mynewsapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.API.ApiInterface
import com.example.mynewsapp.API.Constants.Companion.API_KEY
import com.example.mynewsapp.API.Constants.Companion.BASE_URL
import com.example.mynewsapp.adapter.Adapter
import com.example.mynewsapp.models.Article
import com.example.mynewsapp.models.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class sports : Fragment() {

    private lateinit var adapter: Adapter
    private lateinit var data: ArrayList<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_sports, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerSports)
        val context: Context = requireContext()

        data = ArrayList()
        adapter = Adapter(context, data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        getNews1()

        return view
    }

    private fun getNews1() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ApiInterface = retrofit.create(ApiInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response: Response<NewsResponse> = withContext(Dispatchers.IO) {
                    api.getCategory("in", "sports", 100, API_KEY)
                }
                if (response.isSuccessful) {
                    val res: NewsResponse? = response.body()
                    if (res != null) {
                        data.addAll(res.articles)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle unsuccessful response
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
