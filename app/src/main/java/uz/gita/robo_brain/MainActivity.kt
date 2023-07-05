package uz.gita.robo_brain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import uz.gita.robo_brain.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this){
            val request = AdRequest.Builder().build()
            binding.adsBrain.loadAd(request)
        }
    }
}