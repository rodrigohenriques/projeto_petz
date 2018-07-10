package br.com.projeto.pets.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object Mask {

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                .replace("[)]".toRegex(), "")
    }

    fun insert(mask: String, ediTxt: EditText): TextWatcher {

        return object : TextWatcher {

            internal var isUpdating: Boolean = false
            internal var old = ""

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = Mask.unmask(s.toString())
                var maskedStr = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        maskedStr += m
                        continue
                    }
                    try {
                        maskedStr += str[i]
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }
                isUpdating = true
                ediTxt.setText(maskedStr)
                ediTxt.setSelection(maskedStr.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        }
    }
}