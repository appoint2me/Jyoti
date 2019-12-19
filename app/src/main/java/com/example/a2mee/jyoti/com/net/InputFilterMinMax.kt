package com.example.a2mee.jyoti.com.net

import android.text.Spanned
import android.text.InputFilter


class InputFilterMinMax : InputFilter {

    private var min: Double = 0.0
    private var max: Double = 0.0

    constructor(min: String, max: String) {
        this.min = min.toDouble()
        this.max = max.toDouble()
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = Integer.parseInt(dest.toString() + source.toString())
            if (isInRange(min, max, input))
                return null
        } catch (nfe: NumberFormatException) {
        }

        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Int): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}