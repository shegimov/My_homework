package com.example.homework_1_activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {

    private lateinit var mMessageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        mMessageEditText = findViewById(R.id.review)
        mMessageEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val intent = Intent()
                intent.putExtra("like", if (like.isChecked){"нравится"} else {"не нравится"})
                intent.putExtra("review", mMessageEditText.text.toString())
                setResult(Activity.RESULT_OK, intent)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        val value = getIntent().getParcelableExtra<Film>("key")
        imageFilm.setImageResource(value.pictureName)
        description_text.text = value.description
    }
}
