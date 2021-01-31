package android.com.movieapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//abstract significa que se puede reutilizar en cualquier parte del programa
abstract class BaseViewHolder<T> (itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item:T)
}