package com.tops.showdatainrecyclerview_withapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tops.showdatainrecyclerview_withapi.databinding.ActivityMain2Binding
import com.tops.showdatainrecyclerview_withapi.model.Comment
import com.tops.showdatainrecyclerview_withapi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import org.json.JSONArray
//import org.w3c.dom.Comment

private const val TAG = "MainActivity2"
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            val commentList = getdata()
            //Log.e(TAG, "${commentList}")

            binding.progressBar.visibility = View.GONE
//            binding.apiRecyclerView.layoutManager = LinearLayoutManager(application)
//            binding.apiRecyclerView.adapter = MyAdapter2(commentList!!)

            if (commentList != null) {
                binding.apiRecyclerView.layoutManager = LinearLayoutManager(application)
                binding.apiRecyclerView.adapter = MyAdapter2(commentList)
            } else {
                Toast.makeText(this@MainActivity2, "Failed to fetch data from API", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

private suspend fun getdata():ArrayList<com.tops.showdatainrecyclerview_withapi.model.Comment>? = withContext(Dispatchers.IO){

    // Created Client for API OkHttp network operation
    // Perform network operation here
    val client = okhttp3.OkHttpClient()

    // A reguest Builder created to requst data(To Get Data From API) from the url OR API
    val request: Request = Request.Builder().url("https://jsonplaceholder.typicode.com/comments").build()

    var response = client.newCall(request).execute()

    if (response.isSuccessful){
        // we use !! to tell compiler that the varaiable is
        // non-nullable type, even if it's been declared as nullable
        val jsonResponse = response.body!!.string()

        // Json Array Parsing using gson
        val commentList = Gson().fromJson<ArrayList<Comment>>(jsonResponse,
            object : TypeToken<ArrayList<Comment>>(){}.type)


//        jsonResponse.let {
//        val jsonArray = JSONArray(it)
//            var userlist = arrayListOf<User>()
//
//            for (i in 0 until jsonArray.length()){
//                val jsonObject= jsonArray.getJSONObject(i)
//                val objectID = jsonObject.getInt("id")
//                val objectName = jsonObject.getString("name")
//                val objectEmail = jsonObject.getString("email")
//                val objectBody = jsonObject.getString("body")
//
//                val user = User( objectID ,objectName,objectEmail,objectBody)
//                userlist.add(user)
//            }
//            return@withContext userlist
//        }
        return@withContext commentList
    }else{
        null
    }
    return@withContext null  // Must in every project or files where this api is done

}