package com.mandrid.omdb

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import com.mandrid.omdb.databinding.FragmentAddEditBinding

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieBottomSheetFragment: BottomSheetDialogFragment() {

    var _binding: FragmentAddEditBinding?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddEditBinding.inflate(inflater,container,false)

        return binding.root

    }

    var onModelItem: OnMovieInteraction?=null

    fun onInteraction(onMovieInteraction: OnMovieInteraction) {
        this.onModelItem = onMovieInteraction
    }


    fun loadData() {
        val data = requireArguments().getParcelable<Movie>("data")!!

        (requireActivity() as MainActivity).omdbApi!!.getMovieDetails(data.imdbID,"5b27e0a4").enqueue(object: Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {

                initData(response.body()!!)
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
               t.printStackTrace()
            }

        })

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }

    }

    fun initData(data: MovieDetail) {

        binding.tietCritics.setText(data!!.response.toString())
        binding.tietDescription.setText(data.plot)
        binding.tietDirector.setText(data.director)
        binding.tietGenres.setText(data.genre)
        binding.tietMovieActors.setText(data.actors)
        binding.tietLength.setText(data.runtime.toString())
        binding.tietMovieTitle.setText(data.title)
        binding.tietStudio.setText(data.production)
        binding.tietWriters.setText(data.writer)
        binding.tietYear.setText(data.year.toString())
        binding.tietMpaRating.setText(data.imdbRating)

    }

    companion object {
        fun getInstance(movie: Movie?): MovieBottomSheetFragment {
            val frag = MovieBottomSheetFragment()
            frag.arguments = bundleOf("data" to movie)
            return frag
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            )
            parentLayout?.let { bottomSheet ->
                val behaviour = BottomSheetBehavior.from(bottomSheet)
                val layoutParams = bottomSheet.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                bottomSheet.layoutParams = layoutParams
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }


}