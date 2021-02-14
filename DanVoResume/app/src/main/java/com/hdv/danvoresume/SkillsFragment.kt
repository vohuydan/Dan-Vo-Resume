package com.hdv.danvoresume

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SkillsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =  inflater.inflate(R.layout.fragment_skills, container, false)
        val img:ImageView = view.findViewById(R.id.googleplaylogo)
        img.setOnClickListener{
            gotoURL("https://play.google.com/store/apps/developer?id=HDV+Apps")
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun gotoURL(string: String){
        val uri: Uri = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}