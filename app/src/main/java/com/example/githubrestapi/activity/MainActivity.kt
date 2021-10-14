package com.example.githubrestapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.githubrestapi.*
import com.example.githubrestapi.adapter.GetAdapter
import com.example.githubrestapi.enum.Order
import com.example.githubrestapi.enum.Sort


class MainActivity : AppCompatActivity() {

    private val list = ArrayList<RepositoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchRepos()
    }

    fun searchRepos(
        q: String = DEFAULT_Q_PARAM,
        page: Int = DEFAULT_PAGE_PARAM,
        perPage: Int = DEFAULT_PER_PAGE_PARAM,
        sort: String = Sort.UPDATED.toString().lowercase(),
        order: String = Order.DESC.toString().lowercase()
    ) {
        RetrofitClient.instance.searchRepositories(
            q, page, perPage, sort, order
        ).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(
                call: Call<RepositoryResponse>,
                response: Response<RepositoryResponse>
            ) {
                val responseCode = response.code().toString()

                list.clear()
                response.body()?.items?.let { list.addAll(it) }
                val adapter = GetAdapter(list)
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                print(t.stackTrace)
            }
        })
    }

    fun searchByOwner(view: View){
        val searchField = findViewById<EditText>(R.id.searchField)
        val sortRg = findViewById<RadioGroup>(R.id.rgSort)
        val orderRg = findViewById<RadioGroup>(R.id.rgOrder)
        val selectedSort = findViewById<RadioButton>(sortRg.checkedRadioButtonId)
        val selectedOrder = findViewById<RadioButton>(orderRg.checkedRadioButtonId)

        searchRepos(
            searchField.text.toString(),
            DEFAULT_PAGE_PARAM,
            DEFAULT_PER_PAGE_PARAM,
            selectedSort.text.toString().lowercase(),
            selectedOrder.text.toString().lowercase()
        )
    }

    companion object {
        private const val DEFAULT_Q_PARAM = "a"
        private const val DEFAULT_PAGE_PARAM = 1
        private const val DEFAULT_PER_PAGE_PARAM = 30

        object Extras{
            const val THUMBNAIL_URL = "thumbnail_url"
            const val AUTHOR_NAME = "author_name"
            const val LANGUAGE = "language"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val REPO_URL = "repo_url"
            const val USER_URL = "user_url"
        }

    }
}