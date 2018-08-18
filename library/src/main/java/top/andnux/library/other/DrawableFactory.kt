package top.andnux.library.other

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable

class DrawableFactory {

    companion object {
        fun makeColorStateList(normal: Int, pressed: Int, focused: Int, unable: Int): ColorStateList {
            val colors = intArrayOf(pressed, focused, normal, focused, unable, normal)
            val states = arrayOfNulls<IntArray>(6)
            states[0] = intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled)
            states[1] = intArrayOf(android.R.attr.state_enabled, android.R.attr.state_focused)
            states[2] = intArrayOf(android.R.attr.state_enabled)
            states[3] = intArrayOf(android.R.attr.state_focused)
            states[4] = intArrayOf(android.R.attr.state_window_focused)
            states[5] = intArrayOf()
            return ColorStateList(states, colors)
        }

        fun makeStateListDrawable(context: Context, idNormal: Int,
                                  idPressed: Int, idFocused: Int, idUnable: Int): StateListDrawable {
            val bg = StateListDrawable()
            val normal = if (idNormal == -1) null else context.resources.getDrawable(idNormal)
            val pressed = if (idPressed == -1) null else context.resources.getDrawable(idPressed)
            val focused = if (idFocused == -1) null else context.resources.getDrawable(idFocused)
            val unable = if (idUnable == -1) null else context.resources.getDrawable(idUnable)
            bg.addState(intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled), pressed)
            bg.addState(intArrayOf(android.R.attr.state_enabled, android.R.attr.state_focused), focused)
            bg.addState(intArrayOf(android.R.attr.state_enabled), normal)
            bg.addState(intArrayOf(android.R.attr.state_focused), focused)
            bg.addState(intArrayOf(android.R.attr.state_window_focused), unable)
            bg.addState(intArrayOf(), normal)
            return bg
        }

        fun makeGradientDrawable(radius: Int, fillColor: Int,
                                 strokeWidth: Int, strokeColor: Int): GradientDrawable {
            val gradientDrawable = GradientDrawable()
            gradientDrawable.cornerRadius = radius.toFloat()
            gradientDrawable.setColor(fillColor)
            gradientDrawable.setStroke(strokeWidth, strokeColor)
            return gradientDrawable
        }
    }
}
