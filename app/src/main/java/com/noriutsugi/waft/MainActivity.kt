package com.noriutsugi.waft

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val WAFT_CORE = "http://192.168.96.61/"
const val WAFT_PRO = "http://192.168.96.72/"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWaftCore = findViewById<TextView>(R.id.tv_T_waft2)
        val tvWaftPro = findViewById<TextView>(R.id.tv_T_waft)
        val sbWaftCore = findViewById<SeekBar>(R.id.sb_waft2)
        val sbWaftPro = findViewById<SeekBar>(R.id.sb_waft)
        val btninccore = findViewById<Button>(R.id.btn_inc2)
        val btndeccore = findViewById<Button>(R.id.btn_dec2)
        val btnincpro = findViewById<Button>(R.id.btn_inc)
        val btndecpro = findViewById<Button>(R.id.btn_dec)

        btninccore?.setOnClickListener() {
            waftcoreinc(WAFT_CORE)
        }
        btndeccore?.setOnClickListener() {
            waftcoredec(WAFT_CORE)
        }
        btnincpro?.setOnClickListener() {
            waftcoreinc(WAFT_PRO)
        }
        btndecpro?.setOnClickListener() {
            waftcoredec(WAFT_PRO)
        }

        sbWaftCore.setMax( (30 - 18) / 1 );
        sbWaftPro.setMax( (30 - 18) / 1 );

        sbWaftCore.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
                val value: Int = 18 + sbWaftCore.progress * 1
                tvWaftCore.text = value.toString()
                setTemp(WAFT_CORE,value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.

            }
        })

        sbWaftPro.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
                val value: Int = 18 + sbWaftPro.progress * 1
                tvWaftPro.text = value.toString()
                setTemp(WAFT_PRO,value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.

            }
        })

        Thread {
            while (true) {
                getClimateWaft(WAFT_CORE)
                getClimate(WAFT_PRO)
                Thread.sleep(1000)
            }
        }.start()

        //getTemperature(WAFT_CORE)

        //button2?.setOnClickListener() {
        //    playBuzzer(WAFT_PRO)
        //}
        //buttonI?.setOnClickListener() {
        //    setTemp(WAFT_PRO,Integer.parseInt(textView2.text.toString()) + 1)
        //}
        //buttonD?.setOnClickListener() {
        //    setTemp(WAFT_PRO,Integer.parseInt(textView2.text.toString()) - 1)
        //}
    }

    private fun getClimateWaft(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(GetClimate::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Climate?> {
            override fun onResponse(call: Call<Climate?>, response: Response<Climate?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.tv_T_ac2)
                val textView2 = findViewById<TextView>(R.id.tv_T_waft2)
                textView.text = responseBody.current_temperature
                textView2.text = responseBody.target_temperature
            }

            override fun onFailure(call: Call<Climate?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun getClimate(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(GetClimate::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Climate?> {
            override fun onResponse(call: Call<Climate?>, response: Response<Climate?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.tv_T_ac)
                val textView2 = findViewById<TextView>(R.id.tv_T_waft)
                textView.text = responseBody.current_temperature
                textView2.text = responseBody.target_temperature
            }

            override fun onFailure(call: Call<Climate?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun waftcoreinc(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(Post_Waft_inc::class.java)
        val retrofitData = retrofitBuilder.setData()

        retrofitData.enqueue(object : Callback<EmptyPost?> {
            override fun onResponse(call: Call<EmptyPost?>, response: Response<EmptyPost?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.toString()
            }

            override fun onFailure(call: Call<EmptyPost?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun waftcoredec(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(Post_Waft_dec::class.java)
        val retrofitData = retrofitBuilder.setData()

        retrofitData.enqueue(object : Callback<EmptyPost?> {
            override fun onResponse(call: Call<EmptyPost?>, response: Response<EmptyPost?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.toString()
            }

            override fun onFailure(call: Call<EmptyPost?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun setTemp(device: String, target_temp: Int) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(SetTargetTemp::class.java)
        val retrofitData = retrofitBuilder.setData(target_temp)

        retrofitData.enqueue(object : Callback<EmptyPost?> {
            override fun onResponse(call: Call<EmptyPost?>, response: Response<EmptyPost?>) {
                val responseBody = response.body()!!
                //val textView = findViewById<TextView>(R.id.textView)
                //textView.text = responseBody.toString()
            }

            override fun onFailure(call: Call<EmptyPost?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun setTempWaft(device: String, target_temp: Int) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(SetTargetTemp::class.java)
        val retrofitData = retrofitBuilder.setData(target_temp)

        retrofitData.enqueue(object : Callback<EmptyPost?> {
            override fun onResponse(call: Call<EmptyPost?>, response: Response<EmptyPost?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.toString()
            }

            override fun onFailure(call: Call<EmptyPost?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }


    private fun getHumidity(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(Get_Humidity::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Humidity?> {
            override fun onResponse(call: Call<Humidity?>, response: Response<Humidity?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.value.toString()
            }

            override fun onFailure(call: Call<Humidity?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

    private fun getBrightness(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(Get_Brightness::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Brightness?> {
            override fun onResponse(call: Call<Brightness?>, response: Response<Brightness?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.value.toString()
            }

            override fun onFailure(call: Call<Brightness?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }
    private fun getMotion(device: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(device)
            .build()
            .create(Get_Motion::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Motion?> {
            override fun onResponse(call: Call<Motion?>, response: Response<Motion?>) {
                val responseBody = response.body()!!
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = responseBody.value.toString()
            }

            override fun onFailure(call: Call<Motion?>, t: Throwable) {
                Log.d("temp", t.message.toString())
            }
        })
    }

}