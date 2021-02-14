package com.hdv.danvoresume

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hdv.danvoresume.AcademicsFragment
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private lateinit var viewPage :ViewPager2
    private lateinit var tablayout:TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        val profilepic: CircleImageView = findViewById(R.id.profile_image)
        val expimg : CircleImageView = findViewById(R.id.expanded_image)
        val expandedpic: ConstraintLayout = findViewById(R.id.piczoom)
        val info:TextView = findViewById(R.id.info)

        val playimg:ImageView = findViewById(R.id.playimg)
        val githubimg :ImageView = findViewById(R.id.githubimg)
        val linkedinimg:ImageView = findViewById(R.id.linkedinimg)

        info.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("vohuydan@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Resume App")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(intent, ""))
        }

        linkedinimg.setOnClickListener{
            gotoURL("https://www.linkedin.com/in/huydanvo/")
        }
        playimg.setOnClickListener{
            gotoURL("https://play.google.com/store/apps/developer?id=HDV+Apps")
        }
        githubimg.setOnClickListener{
            gotoURL("https://github.com/vohuydan")
        }
        profilepic.setOnClickListener{
            expandedpic.visibility = View.VISIBLE
            expimg.alpha =1.0f
        }
        expandedpic.setOnClickListener{
            expandedpic.visibility= View.INVISIBLE
        }



        viewPage = findViewById(R.id.viewPage)
        //viewPage.setPageTransformer(ZoomOutPageTransformer())
        tablayout = findViewById(R.id.tablayout)

        val mFragmentList = ArrayList<Fragment>()
        mFragmentList.add(WorkFragment())
        mFragmentList.add(SkillsFragment())
        mFragmentList.add(EducationFragment())
        mFragmentList.add(AcademicsFragment())

        val mFragmentListTitle = ArrayList<String>()
        mFragmentListTitle.add("Work")
        mFragmentListTitle.add("Skills")
        mFragmentListTitle.add("Education")
        mFragmentListTitle.add("Academics")

        val mFragmentIcon = ArrayList<Int>()
        mFragmentIcon.add(R.drawable.ic_work)
        mFragmentIcon.add(R.drawable.ic_skill)
        mFragmentIcon.add(R.drawable.ic_education)
        mFragmentIcon.add(R.drawable.ic_academic)

        val adapter = ViewPagerAdapter(this, mFragmentList)
        viewPage.adapter = adapter

        val tabconfigstrat : TabLayoutMediator.TabConfigurationStrategy= TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, i: Int ->
            for (x in (0..i)) {
                tab.text = mFragmentListTitle[i]

                tab.setIcon(mFragmentIcon[i])
            }
        }

        TabLayoutMediator(tablayout,viewPage,tabconfigstrat).attach()

    }

    private fun gotoURL(string: String){
        val uri: Uri = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }


    override fun onBackPressed() {
        if(viewPage.currentItem == 0){
            super.onBackPressed()
        }else {
            viewPage.currentItem = viewPage.currentItem-1
        }

    }

}