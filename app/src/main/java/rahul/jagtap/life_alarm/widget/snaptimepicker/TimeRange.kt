package rahul.jagtap.life_alarm.widget.snaptimepicker

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import rahul.jagtap.life_alarm.widget.snaptimepicker.TimeValue

@Parcelize
data class TimeRange(
    var start: TimeValue?,
    var end: TimeValue?
) : Parcelable
