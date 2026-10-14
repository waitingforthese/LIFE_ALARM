package rahul.jagtap.life_alarm.widget.snaptimepicker

import androidx.recyclerview.widget.RecyclerView
import rahul.jagtap.life_alarm.databinding.LayoutSnapTimePickerNumberItemBinding

class TimeNumberViewHolder(
    private val binding: LayoutSnapTimePickerNumberItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun setNumber(number: String?) {
        binding.textViewNumber.text = number ?: "-"
    }
}
