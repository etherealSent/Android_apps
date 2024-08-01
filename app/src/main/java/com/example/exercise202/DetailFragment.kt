package com.example.exercise202

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val starSign: TextView?
        get() = view?.findViewById(R.id.star_sign)
    private val symbol: TextView?
        get() = view?.findViewById(R.id.symbol)
    private val dateRange: TextView?
        get() = view?.findViewById(R.id.date_range)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    fun setStarSignData(starSignId: Int) {
        when (starSignId) {
            R.id.aquarius -> {
                starSign?.text = getString(R.string.aquarius)
                symbol?.text = getString(R.string.symbol, "Water Carrier")
                dateRange?.text = getString(R.string.date_range,"January 20 - February 18")
            }
            R.id.aries -> {
                starSign?.text = getString(R.string.aries)
                symbol?.text = getString(R.string.symbol, "Water Carrier")
                dateRange?.text = getString(R.string.date_range,"January 20 - Mart 10")
            }
            R.id.pisces -> {
                starSign?.text = getString(R.string.pisces)
                symbol?.text = getString(R.string.symbol, "Water Carrier")
                dateRange?.text = getString(R.string.date_range,"January 5 - May 18")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val starSignId = arguments?.getInt(STAR_SIGN_ID, 0) ?: 0
        setStarSignData(starSignId)
    }

    companion object {
        private const val STAR_SIGN_ID = "STAR_SIGN_ID"
        fun newInstance(starSignId: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(STAR_SIGN_ID, starSignId)
                }
            }
    }
}