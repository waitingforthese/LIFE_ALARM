package rahul.jagtap.life_alarm.widget.snaptimepicker.extension

import androidx.lifecycle.ViewModel
import rahul.jagtap.life_alarm.widget.snaptimepicker.extension.TimePickedEvent
import rahul.jagtap.life_alarm.widget.snaptimepicker.extension.TimePickedLiveData

class SnapTimePickerViewModel : ViewModel() {
    val timePickedEvent =
        TimePickedLiveData<TimePickedEvent>()

    fun onTimePicked(hour: Int, minute: Int) {
        timePickedEvent.value = TimePickedEvent(hour, minute)
    }
}
