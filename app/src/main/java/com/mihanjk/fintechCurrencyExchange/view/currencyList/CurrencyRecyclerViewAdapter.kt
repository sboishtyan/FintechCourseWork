package com.mihanjk.fintechCurrencyExchange.view.currencyList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.mihanjk.fintechCurrencyExchange.R
import com.mihanjk.fintechCurrencyExchange.model.data.ForeignItem
import com.mihanjk.fintechCurrencyExchange.view.currencyList.CurrencyListFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_currency_item.view.*

class CurrencyRecyclerViewAdapter(private val mValues: List<ForeignItem>,
                                  private val mListener: OnListFragmentInteractionListener?) :
        RecyclerView.Adapter<CurrencyRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_currency_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mCurrencyName.text = holder.mItem.base.toString()
        holder.mStarImage.setImageResource(if (holder.mItem.isFavorite)
            R.drawable.ic_star_black_24dp else R.drawable.ic_star_border_black_24dp)

        RxView.clicks(holder.mStarImage).subscribe { mListener?.onStarClicked(holder.mItem) }
//        holder.mStarImage.setOnClickListener { mListener?.onStarClicked(holder.mItem)}
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        lateinit var mItem: ForeignItem
        val mCurrencyName: TextView = mView.currencyName
        val mStarImage: ImageView = mView.starImage

        init {
            mStarImage.setOnClickListener { mStarImage.isEnabled = !mStarImage.isEnabled}
        }

    }
}
