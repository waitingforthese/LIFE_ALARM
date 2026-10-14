package rahul.jagtap.life_alarm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rahul.jagtap.life_alarm.*
import rahul.jagtap.life_alarm.admin.*
import rahul.jagtap.life_alarm.databinding.ItemHomeBannerBinding
import rahul.jagtap.life_alarm.databinding.ItemHomeMenuBinding
import rahul.jagtap.life_alarm.model.User
import rahul.jagtap.life_alarm.user.*

class MenuListAdapter(var context: Context?, var itemList: List<String?>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var loggedInUser: User? = null
    var isAdmin = false
    var isEmployee = false

    init {
        val app = context?.applicationContext as App
        loggedInUser = app.preferences?.loggedInUser
        isAdmin = app.preferences?.loggedInUser?.isAdmin == "1"
        isEmployee = app.preferences?.loggedInUser?.userType == "2"
    }

    companion object {
        private const val TYPE_BANNER = 0
        private const val TYPE_TILE = 1
        private val BANNER_TITLES = setOf("LifeAlarm")
    }

    /** Items that should span the full grid width (rendered as banners). */
    fun isFullWidth(title: String?): Boolean = title in BANNER_TITLES

    override fun getItemViewType(position: Int): Int =
        if (isFullWidth(itemList?.get(position))) TYPE_BANNER else TYPE_TILE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_BANNER) {
            BannerViewHolder(ItemHomeBannerBinding.inflate(inflater, parent, false))
        } else {
            TileViewHolder(ItemHomeMenuBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val title = itemList?.get(position)
        when (holder) {
            is BannerViewHolder -> bindBanner(holder, title)
            is TileViewHolder -> bindTile(holder, title)
        }
        holder.itemView.setOnClickListener { onItemClick(title) }
    }

    private fun bindBanner(holder: BannerViewHolder, title: String?) {
        when (title) {
            "LifeAlarm" -> {
                holder.binding.bannerIconBg.setBackgroundResource(R.drawable.bg_banner_logo_blue)
                holder.binding.ivBanner.setImageResource(R.drawable.ic_lifealarm)
                holder.binding.tvBannerTitle.text = "लाईफ अलार्म"
                holder.binding.tvBannerSubtitle.text = "पंचांग, चंद्रकुंडली आणि नक्षत्र मार्गदर्शन — दैनिक अलार्मसह"
            }
        }
    }

    private fun bindTile(holder: TileViewHolder, title: String?) {
        holder.binding.tvMenuTitle.text = title
        when (title) {
            "Notifications" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_notifications)
            "येथून फी भरावी" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_make_payment)
            "हे अँप कसे वापरावे" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_tutorial)
            "Text Msg" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_view_reports)
            "नवनवीन माहिती" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_govt_scheme_white)
            "नियम व अटी" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_terms_conditions)
            "contact us" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_headset)
            "ट्रेनिंग व्हिडिओ" -> holder.binding.ivMenu.setImageResource(R.drawable.ic_training_video)
        }
    }

    private fun onItemClick(title: String?) {
        when (title) {
            "Notifications" -> {
                context?.startActivity(Intent(context, NotificationsActivity::class.java))
            }
            "येथून फी भरावी" -> {
                context?.startActivity(Intent(context, PaymentDetailsActivity::class.java))
            }
            "हे अँप कसे वापरावे" -> {
                context?.startActivity(Intent(context, TutorialActivity::class.java))
            }
            "ट्रेनिंग व्हिडिओ" -> {
                context?.startActivity(Intent(context, TrainingVideoActivity::class.java))
            }
            "Text Msg" -> {
                context?.startActivity(Intent(context, TextMsgActivity::class.java))
            }
            "नवनवीन माहिती" -> {
                context?.startActivity(Intent(context, GovtSchemesInfoActivity::class.java))
            }
            "All Users" -> {
                context?.startActivity(Intent(context, UsersActivity::class.java))
            }
            "नियम व अटी" -> {
                context?.startActivity(Intent(context, TermsConditionsActivity::class.java))
            }
            "contact us" -> {
                context?.startActivity(Intent(context, ContactActivity::class.java))
            }
        }
    }

    inner class TileViewHolder @SuppressLint("RestrictedApi") constructor(val binding: ItemHomeMenuBinding) : RecyclerView.ViewHolder(binding.root)

    inner class BannerViewHolder @SuppressLint("RestrictedApi") constructor(val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root)
}
