package com.valter.motivation.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.valter.motivation.R
import com.valter.motivation.infra.MotivationConstants
import com.valter.motivation.infra.SecurityPreferences
import com.valter.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var securityPreferences: SecurityPreferences

    private var mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        securityPreferences = SecurityPreferences(this)

        val name = securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = name

        buttonNewPhrase.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageSun.setOnClickListener(this)

        //Init the phrase
        imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()
    }

    override fun onClick(v: View) {
        val id = v.id
        val listImages = arrayListOf(R.id.imageAll, R.id.imageHappy, R.id.imageSun)

        if(id == R.id.buttonNewPhrase) {
            handleNewPhrase()
        } else if (id in listImages){
            handleFilter(id)
        }

    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(this.mPhraseFilter)
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageSun.setColorFilter(resources.getColor(R.color.white))

        when(id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent))
                this.mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent))
                this.mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageSun -> {
                imageSun.setColorFilter(resources.getColor(R.color.colorAccent))
                this.mPhraseFilter = MotivationConstants.PHRASEFILTER.SUN
            }
        }

        handleNewPhrase()
    }
}