package rahul.jagtap.life_alarm

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.afollestad.materialdialogs.MaterialDialog
import rahul.jagtap.life_alarm.databinding.ActivityEditUserBinding
import rahul.jagtap.life_alarm.extensions.gone
import rahul.jagtap.life_alarm.extensions.longToast
import rahul.jagtap.life_alarm.extensions.visible
import rahul.jagtap.life_alarm.model.User
import rahul.jagtap.life_alarm.utils.Utils
import androidx.core.widget.doAfterTextChanged

class EditUserActivity : BaseActivity() {
    private val TAG = EditUserActivity::class.java.simpleName
    var user: User? = null
    var isBlock = false
    lateinit var binding: ActivityEditUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        if (Utils.disableScreenshot) this.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(binding.root)

        // input-field migration: clear errors on edit
        listOf(binding.tilName, binding.tilShopName, binding.tilEmail, binding.tilContactNo, binding.tilAddress, binding.tilReferrer, binding.tilAadharNo, binding.tilUserType, binding.tilType)
            .forEach { til -> til.editText?.doAfterTextChanged { til.error = null } }
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        user = intent?.getSerializableExtra("user") as User?
        binding.toolbarLayout.toolbarTitle?.text = "Edit User"
        binding.etName.setText(user?.name)
        binding.etShopName.setText(user?.shopName)
        binding.etEmail.setText(user?.email)
        binding.etContactNo.setText(user?.contactNo)
        binding.etAddress.setText(user?.address)
        binding.etReferrer.setText(user?.referrer)
        binding.etType.setText(if (user?.isBlocked == "1") "Block" else "Unblock")
        binding.etUserType.setText(if (user?.userType == "0") "User" else "Employee")
        isBlock = user?.isBlocked == "1"
        binding.btnSubmit?.setOnClickListener {
            createAccount()
        }
        binding.etType?.setOnClickListener {
            val list = java.util.ArrayList<String>()
            list.add("Block")
            list.add("Unblock")
            MaterialDialog.Builder(mContext!!).items(list).itemsCallback { dialog: MaterialDialog?, itemView: View?, position: Int, text: CharSequence ->
                run {
                    dialog?.dismiss()
                    binding.etType.setText(list[position])
                    isBlock = position == 0
                }
            }.show()
        }
        binding.etUserType?.setOnClickListener {
            val list = java.util.ArrayList<String>()
            list.add("User")
            list.add("Employee")
            MaterialDialog.Builder(mContext!!).items(list).itemsCallback { dialog: MaterialDialog?, itemView: View?, position: Int, text: CharSequence ->
                run {
                    dialog?.dismiss()
                    binding.etUserType.setText(list[position])
                }
            }.show()
        }

    }

    private fun createAccount() {
        val strName = binding.etName.text.toString().trim()
        val strShopName = binding.etShopName.text.toString().trim()
        val strEmail = binding.etEmail.text.toString().trim()
        val strContactNo = binding.etContactNo.text.toString().trim()
        val strAddress = binding.etAddress.text.toString().trim()
        val strReferrer = binding.etReferrer.text.toString().trim()
        val strUserType = binding.etUserType.text.toString()
        val strType = binding.etType.text.toString()
        if (TextUtils.isEmpty(strName)) {
            binding.tilName.error = "Enter Name"
            binding.etName.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strShopName)) {
            binding.tilShopName.error = "Enter Shop Name"
            binding.etShopName.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strContactNo)) {
            binding.tilContactNo.error = "Enter Contact Number"
            binding.etContactNo.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strAddress)) {
            binding.tilAddress.error = "Enter Address"
            binding.etAddress.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strReferrer)) {
            binding.tilReferrer.error = "Enter Referrer"
            binding.etReferrer.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strUserType)) {
            binding.tilUserType?.error = "Select User Type"
            binding.etUserType?.requestFocus()
            return
        }
        if (TextUtils.isEmpty(strType)) {
            binding.tilType?.error = "Select Block/Unblock"
            binding.etType?.requestFocus()
            return
        }

        binding.progressBar?.visible()

        val localUser =
            User(uid = user?.uid, name = strName, username = user?.username, shopName = strShopName,
                isAdmin = user?.isAdmin, email = user?.email!!, contactNo = strContactNo, address = strAddress,
                referrer = strReferrer, createdAt = user?.createdAt, aadharNo = "", userType = if (strUserType == "User") "0" else "2", isBlocked = if (isBlock) "1" else "0")
        user?.uid?.let { database.child(Utils.USERS_TABLE).child(it).setValue(localUser) }
        binding.progressBar?.gone()
        longToast("User info updated successfully")
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Utils.hideSoftKeyboard(this)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    public override fun onStop() {
        super.onStop()
        binding.progressBar?.gone()
    }
}
