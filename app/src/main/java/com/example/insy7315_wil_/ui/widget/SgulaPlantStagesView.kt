package com.example.insy7315_wil_.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.use
import com.example.insy7315_wil_.R

class SgulaPlantStagesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val itemViews = mutableListOf<View>()
    private val ringSmall = resources.getDimensionPixelSize(R.dimen.sgula_plant_ring_small)
    private val ringLarge = resources.getDimensionPixelSize(R.dimen.sgula_plant_ring_large)

    private var wiltedIndex = -1

    var stageIndex: Int = 0
        set(value) {
            field = value
            applyStages()
        }

    var wilted: Boolean = false
        set(value) {
            field = value
            applyStages()
        }

    init {
        orientation = HORIZONTAL

        val labels = resources.getStringArray(R.array.sgula_plant_stages).toList()
        wiltedIndex = labels.lastIndex
        buildItems(labels)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SgulaPlantStagesView,
            defStyleAttr,
            0,
        ).use { a ->
            stageIndex = a.getInt(R.styleable.SgulaPlantStagesView_sgulaStageIndex, 0)
            wilted = a.getBoolean(R.styleable.SgulaPlantStagesView_sgulaWilted, false)
        }
    }

    private fun buildItems(labels: List<String>) {
        val inflater = LayoutInflater.from(context)
        val gap = resources.getDimensionPixelSize(R.dimen.sgula_space_2)

        labels.forEachIndexed { index, label ->
            val item = inflater.inflate(R.layout.view_plant_stage, this, false)
            val params = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
            if (index > 0) params.marginStart = gap
            item.layoutParams = params

            item.findViewById<TextView>(R.id.plant_label).text = label

            addView(item)
            itemViews += item
        }
    }

    private fun applyStages() {
        if (itemViews.isEmpty()) return

        val current = if (wilted) wiltedIndex else stageIndex

        itemViews.forEachIndexed { index, item ->
            val isCurrent = index == current
            val isWiltedSlot = isCurrent && wilted

            item.isSelected = isCurrent
            item.isActivated = isWiltedSlot

            val ring = item.findViewById<View>(R.id.plant_ring)
            val size = if (isCurrent && !isWiltedSlot) ringLarge else ringSmall
            if (ring.layoutParams.width != size) {
                ring.layoutParams = ring.layoutParams.apply {
                    width = size
                    height = size
                }
            }
        }
    }
}
