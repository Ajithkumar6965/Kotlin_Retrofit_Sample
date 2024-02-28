package me.ajith.learning.retrofitsample.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import me.ajith.learning.retrofitsample.viewmodel.MainViewModel
import me.ajith.learning.retrofitsample.R
import me.ajith.learning.retrofitsample.common.RetrofitBuilder
import me.ajith.learning.retrofitsample.common.ViewModelFactory
import me.ajith.learning.retrofitsample.data.remote.ApiHelperImpl

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var fetchApiBtn:Button
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setMainFragment()
        setupViewModel()
    }

    private fun setupView(){
        fetchApiBtn = findViewById(R.id.fetch_api_btn)
        fetchApiBtn.setOnClickListener(this)
    }

    private fun setMainFragment(){
        if(supportFragmentManager.findFragmentByTag(MainFragment.TAG)==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, MainFragment.newInstance(), MainFragment.TAG)
                .commit()
        }
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.userApiService))
        )[MainViewModel::class.java]
    }

    override fun onClick(v: View?) {
        if (v?.id==fetchApiBtn.id){
            mainViewModel.fetchUserApi()
        }
    }
}