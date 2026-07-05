package com.tastedaily.app.ui.components

import com.tastedaily.core.model.Difficulty

object Format {
    fun difficultyLabel(d: Difficulty): String = d.label

    fun durationLabel(minutes: Int): String {
        return if (minutes < 60) "${minutes}分钟"
        else "${minutes / 60}小时${if (minutes % 60 == 0) "" else "${minutes % 60}分"}"
    }

    fun videoDurationLabel(seconds: Int): String {
        val m = seconds / 60
        val s = seconds % 60
        return "%d:%02d".format(m, s)
    }

    fun caloriesLabel(cal: Int): String = "${cal} 千卡"
}
