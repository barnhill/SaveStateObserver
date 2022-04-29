package com.pnuema.android.savestateobserver.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pnuema.android.savestateobserver.app.BundleGenerator.generateOversizeBundle

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment private constructor() : Fragment(R.layout.fragment_main) {
    companion object {
        fun newInstance() = MainFragment().apply {
            arguments = Bundle().generateOversizeBundle() //TODO detect oversize arguments
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.generateOversizeBundle()
        super.onSaveInstanceState(outState)
    }
}