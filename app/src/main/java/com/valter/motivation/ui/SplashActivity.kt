package com.valter.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.valter.motivation.R
import com.valter.motivation.infra.MotivationConstants
import com.valter.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        securityPreferences = SecurityPreferences(this)

        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        verifyName()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.buttonSave) {
            handleSave()
        }
    }

    private fun verifyName() {
        val name = securityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        if(name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {

        val name = editName.text.toString()

        if(name != "") {
            securityPreferences.storeString("name", name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Type your name", Toast.LENGTH_SHORT).show()
        }
    }
}