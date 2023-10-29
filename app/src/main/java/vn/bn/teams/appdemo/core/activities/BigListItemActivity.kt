package vn.bn.teams.appdemo.core.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import vn.bn.teams.appdemo.core.adapter.BigListFollowAdapter


import android.widget.Toast
import vn.bn.teams.appdemo.core.utils.DialogUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.bn.teams.appdemo.databinding.ActivityBigListFollowBinding
import vn.bn.teams.appdemo.api.ApiInterface
import vn.bn.teams.appdemo.api.RetrofitInstance
import vn.bn.teams.appdemo.api.SessionManager
import vn.bn.teams.appdemo.data.Constants
import vn.bn.teams.appdemo.data.database.MyDatabase
import vn.bn.teams.appdemo.data.models.BigListResponse
import vn.bn.teams.appdemo.data.models.DataFollow


class BigListItemActivity : AppCompatActivity() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var bigListAdapter: BigListFollowAdapter? = null
    var key: String? = null
    private lateinit var sessionManager: SessionManager
     lateinit var binding: ActivityBigListFollowBinding
    private var db: MyDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBigListFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        //getData()
        db = MyDatabase(this)
        val list = db?.getAllItemsBigList()
        initRecyclerview(list)
        initView()
        onClick()
    }

    private fun getData() {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.getListTopPic("${sessionManager.fetchAuthToken()}").enqueue(object :
            Callback<BigListResponse> {
            override fun onFailure(call: Call<BigListResponse>, t: Throwable) {
                DialogUtil.progressDlgHide()
                Toast.makeText(
                    this@BigListItemActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<BigListResponse>,
                response: Response<BigListResponse>
            ) {
                if (response.body()?.code == 200) {
                   // initRecyclerview(response.body()?.result!!.data)
                } else {
                    Toast.makeText(
                        this@BigListItemActivity,
                        "Kết Nối Gặp Sự Cố! Vui Lòng Kiểm Tra Lại",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        })
    }

    private fun onClick() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this@BigListItemActivity, HomeScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        val title = intent.getStringExtra(Constants.KEY_HOME)
        key = intent.getStringExtra(Constants.KEY_HOME_FOLLOW)
        binding.txtTitle.text = title
    }

    private fun initRecyclerview(arrayList: ArrayList<DataFollow>?) {
        gridLayoutManager = GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        binding.listFollow.layoutManager = gridLayoutManager
        binding.listFollow.setHasFixedSize(true)
        bigListAdapter = arrayList?.let { BigListFollowAdapter(applicationContext, it, this) }
        binding.listFollow.adapter = bigListAdapter
        DialogUtil.progressDlgHide()
    }

}