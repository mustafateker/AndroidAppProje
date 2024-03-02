package com.crossborders.studyhard



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DenemeAdapter(private val denemeList: List<Deneme>) :
    RecyclerView.Adapter<DenemeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siraIndeksiTextView: TextView = itemView.findViewById(R.id.siraIndeksiTextView)
        val adiTextView: TextView = itemView.findViewById(R.id.adiTextView)
        val tarihTextView: TextView = itemView.findViewById(R.id.tarihTextView)
        val genelNetBilgisiTextView: TextView = itemView.findViewById(R.id.genelNetBilgisiTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gecmis_deneme_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deneme = denemeList[position]
        holder.siraIndeksiTextView.text = "Deneme sıra indeksi: ${deneme.siraIndex}"
        holder.adiTextView.text = "Deneme adı: ${deneme.name}"
        holder.tarihTextView.text = "Deneme Tarihi: ${deneme.date}"
        holder.genelNetBilgisiTextView.text = "Deneme genel net bilgisi: ${deneme.GenelNetBilgisi}"
    }

    override fun getItemCount(): Int {
        return denemeList.size
    }
}
