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
import com.example.mynewsapp.adapter.Adapter
import com.example.mynewsapp.models.Article
import com.example.mynewsapp.models.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class home : Fragment() {

    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<Article>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context: Context = requireContext()
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        arrayList = ArrayList()
        recyclerView = view.findViewById(R.id.recyclerHome)
        adapter = Adapter(context, arrayList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        getNews()
        searchNews("technology")

        return view
    }

    private fun getNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ApiInterface = retrofit.create(ApiInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response: Response<NewsResponse> = withContext(Dispatchers.IO) {
                    api.getHeadlines("in", 1, API_KEY)
                }
                if (response.isSuccessful) {
                    val newsResponse: NewsResponse? = response.body()
                    if (newsResponse != null) {
                        arrayList.addAll(newsResponse.articles)
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    private fun searchNews(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: ApiInterface = retrofit.create(ApiInterface::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response: Response<NewsResponse> = withContext(Dispatchers.IO) {
                    api.searchNews(query, 1, API_KEY)
                }
                if (response.isSuccessful) {
                    val newsResponse: NewsResponse? = response.body()
                    if (newsResponse != null) {
                        arrayList.addAll(newsResponse.articles)
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}
