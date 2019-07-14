package daniel.rivero.homematters.presentation.common.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.UserEffort


open class AccentSeekBar : FrameLayout {
    private var min: TextView? = null
    private var max: TextView? = null

    lateinit var seekBar: AppCompatSeekBar

    private var onSeekBarChangeListener: ((Int) -> Unit)? = null

    private var minValue: Int = 0
    private var maxValue: Int = 0
    private var stepSize: Int = 1
    private var comesFromRefresh: Boolean = false

    private var progress: Int = minValue

    constructor(context: Context) : super(context) {
        initializeView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeView(context)
    }

    private fun initializeView(context: Context) {
        View.inflate(context, R.layout.view_accent_seek_bar, this)
        min = findViewById(R.id.min)
        max = findViewById(R.id.max)

        seekBar = findViewById(R.id.seek_bar)
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener())

        if (isInEditMode) {
            setValuesSeekBar(0, 6, 1, 3)
            setLimitValues(UserEffort.XS.description, UserEffort.XXL.description)

            updateData(3)
        }
    }

    private fun onSeekBarChangeListener(): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateData(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }
    }

    private fun updateData(progress: Int) {
        if (comesFromRefresh) {
            this.progress = progress + minValue
            onSeekBarChangeListener?.invoke(this.progress)
        } else
            defaultUpdate(progress)
    }

    private fun defaultUpdate(progress: Int) {
        this.progress = (progress + minValue) / getStepSize() * getStepSize()
        onSeekBarChangeListener?.invoke(this.progress)
    }

    private fun getStepSize(): Int {
        return if (stepSize > 0) stepSize else 1
    }

    fun refreshProgress(newProgress: Int) {
        if (newProgress in minValue..maxValue) {
            comesFromRefresh = true
            seekBar.progress = newProgress - minValue
            comesFromRefresh = false
        }
    }

    fun setValuesSeekBar(minValue: Int, maxValue: Int, stepSize: Int, progress: Int) {
        this.stepSize = stepSize
        setValuesSeekBar(minValue, maxValue, progress)
    }

    fun setValuesSeekBar(minValue: Int, maxValue: Int, progress: Int) {
        this.progress = minValue
        this.minValue = minValue
        this.maxValue = maxValue
        this.seekBar.max = maxValue - minValue
        this.seekBar.progress = progress
    }

    fun resetProgress() {
        seekBar.progress = 0
    }

    fun setLimitValues(minValue: String, maxValue: String) {
        this.min?.text = minValue
        this.max?.text = maxValue
    }

    fun getProgress() = progress

    fun setOnSeekBarChangeListener(onSeekBarChangeListener: (Int) -> Unit) {
        this.onSeekBarChangeListener = onSeekBarChangeListener
    }
}