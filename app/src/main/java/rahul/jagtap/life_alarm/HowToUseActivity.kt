package rahul.jagtap.life_alarm

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import rahul.jagtap.life_alarm.databinding.ActivityHowToUseBinding
import rahul.jagtap.life_alarm.utils.Utils


class HowToUseActivity : BaseActivity() {
    lateinit var binding: ActivityHowToUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHowToUseBinding.inflate(layoutInflater)
        if (Utils.disableScreenshot) this.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(binding.root)
        binding.btnSkip?.setOnClickListener {
            startActivity(Intent(mContext, MainActivity::class.java).putExtra("fromLogin", true))
            finish()
        }
    }

    companion object {}
}
