package com.harryawoodworth.multiweather.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harryawoodworth.multiweather.R
import com.harryawoodworth.multiweather.data.model.ForecastModel

/**
 * A custom adapter for the weather recyclerview
 */
class ForecastAdapter(private val forecasts: Array<ForecastModel>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    /**
     * Custom ViewHolder class
     * Provide references to the views used in each forecast card
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    /**
     * Create a new view and inflate it with the forecast card layout, then return a ViewHolder
     * holding that view.
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view that defines the list item UI
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.forecast_card, viewGroup, false)

        return ViewHolder(view)
    }

    /**
     * Replace the contents of a view (recycling)
     * The position is the forecast that is to be shown in the view
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = forecasts[position].toString()
    }

    /**
     * Return the number of forecasts
     */
    override fun getItemCount(): Int = forecasts.size

}