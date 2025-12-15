package com.sabrina.listilla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class RecordAdapter(
    private val records: List<MainActivity.Record>,
    private val context: Context
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    private val logoBitmap: Bitmap =
        BitmapFactory.decodeStream(context.assets.open("ieti_logo.png"))

    class RecordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nom: TextView = view.findViewById(R.id.nom)
        val intents: TextView = view.findViewById(R.id.intents)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = records[position]
        holder.nom.text = record.nom
        holder.intents.text = record.intents.toString()
        holder.image.setImageBitmap(logoBitmap)
    }

    override fun getItemCount(): Int = records.size
}