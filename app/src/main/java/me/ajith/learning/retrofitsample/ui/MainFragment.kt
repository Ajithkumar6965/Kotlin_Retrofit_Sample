package me.ajith.learning.retrofitsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ajith.learning.retrofitsample.viewmodel.MainViewModel
import me.ajith.learning.retrofitsample.R
import me.ajith.learning.retrofitsample.common.RetrofitBuilder
import me.ajith.learning.retrofitsample.data.remote.UiState
import me.ajith.learning.retrofitsample.data.remote.UserAdapter
import me.ajith.learning.retrofitsample.common.ViewModelFactory
import me.ajith.learning.retrofitsample.data.remote.ApiHelperImpl

class MainFragment : Fragment() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var progressBar:ProgressBar

    companion object{
        const val TAG = "MainFragment"
        fun newInstance(): MainFragment {
            val args = Bundle()
            val mainFragment = MainFragment()
            mainFragment.arguments = args
            return mainFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupViewModel()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(arrayListOf())
        recyclerView.adapter = userAdapter
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(requireActivity(),
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.userApiService))
        )[MainViewModel::class.java]
        mainViewModel.getUserData().observe(requireActivity()){
            when(it){
                is UiState.Loading ->{
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                is UiState.Success ->{
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    userAdapter.addUsers(it.data)
                    userAdapter.notifyDataSetChanged()
                }
                is UiState.Failure ->{
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    Toast.makeText(requireActivity(),"Error Message : ${it.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}