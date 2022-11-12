package ru.test.ecommerce.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.test.ecommerce.adapter.DelegateAdapter
import ru.test.ecommerce.adapter.DelegateAdapterItem
import ru.test.ecommerce.databinding.ItemRecycleCategoryBinding

class AuthAdapter(
    private val onAuthClicked: ((String) -> Unit)
) : DelegateAdapter<AuthAdapterModel, AuthAdapter.AuthViewHolder>(AuthAdapterModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        AuthViewHolder(
            ItemRecycleCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun bindViewHolder(
        model: AuthAdapterModel,
        viewHolder: AuthViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class AuthViewHolder(
        private val binding: ItemRecycleCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AuthAdapterModel) {
        }
    }
}