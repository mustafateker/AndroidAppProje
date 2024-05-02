package com.crossborders.studyhard

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DenemeAdapter(private val denemeList: MutableList<Deneme>) :
    RecyclerView.Adapter<DenemeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siraIndeksiTextView: TextView = itemView.findViewById(R.id.siraIndeksiTextView)
        val adiTextView: TextView = itemView.findViewById(R.id.adiTextView)
        val tarihTextView: TextView = itemView.findViewById(R.id.tarihTextView)
        val genelNetBilgisiTextView: TextView = itemView.findViewById(R.id.genelNetBilgisiTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.gecmis_deneme_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deneme = denemeList[position]
        holder.siraIndeksiTextView.typeface = Typeface.create("libre_franklin_semibold", Typeface.BOLD)
        holder.siraIndeksiTextView.textSize = 16f
        holder.adiTextView.typeface = Typeface.create("open_sans", Typeface.NORMAL)
        holder.adiTextView.textSize = 16f
        holder.tarihTextView.typeface = Typeface.create("open_sans", Typeface.NORMAL)
        holder.tarihTextView.textSize = 16f
        holder.genelNetBilgisiTextView.typeface = Typeface.create("open_sans", Typeface.NORMAL)
        holder.genelNetBilgisiTextView.textSize = 16f

        holder.siraIndeksiTextView.text = "${deneme.siraIndex}"
        holder.adiTextView.text = deneme.name
        holder.tarihTextView.text = deneme.date
        holder.genelNetBilgisiTextView.text = "${deneme.GenelNetBilgisi}"
    }

    override fun getItemCount(): Int {
        return denemeList.size
    }
}
