package rahul.jagtap.life_alarm.widget.areditor.styles;

import android.view.View;

import rahul.jagtap.life_alarm.widget.areditor.Constants;

public class ARE_Helper {

  /**
   * Updates the check status.
   * 
   * @param areStyle
   * @param checked
   */
  public static void updateCheckStatus(IARE_Style areStyle, boolean checked) {
	areStyle.setChecked(checked);
	View imageView = areStyle.getImageView();
    int color = checked ? Constants.CHECKED_COLOR : Constants.UNCHECKED_COLOR;
    imageView.setBackgroundColor(color);
  }
  
  
}
