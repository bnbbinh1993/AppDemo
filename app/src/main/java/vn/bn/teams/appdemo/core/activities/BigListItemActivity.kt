package vn.bn.teams.appdemo.core.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
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
import java.util.Locale


class BigListItemActivity : AppCompatActivity() {

    private var gridLayoutManager: GridLayoutManager? = null
    private var bigListAdapter: BigListFollowAdapter? = null
    var key: String? = null
    private lateinit var sessionManager: SessionManager
    lateinit var binding: ActivityBigListFollowBinding
    private var db: MyDatabase? = null
    private var list: ArrayList<DataFollow>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBigListFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        //getData()
        db = MyDatabase(this)
        list = db?.getAllItemsBigList()
        initRecyclerview()
        initView()
        onClick()
    }


    private fun onClick() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this@BigListItemActivity, HomeScreenActivity::class.java)
            startActivity(intent)
        }

        binding.edtText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterData(s.toString())
            }
        })

    }

    private fun filterData(query: String?) {
        list!!.clear()
        if (query.isNullOrBlank()) {
            list = db?.getAllItemsBigList()
        } else {
            for (item in db?.getAllItemsBigList()!!) {
                if (item.title.lowercase().contains(query.lowercase())) {
                    list!!.add(item)
                }
            }
        }
        list?.let { bigListAdapter!!.setData(it) }
    }


    private fun initView() {
        val title = intent.getStringExtra(Constants.KEY_HOME)
        key = intent.getStringExtra(Constants.KEY_HOME_FOLLOW)
        binding.txtTitle.text = title

    }

    private fun initRecyclerview() {
        gridLayoutManager =
            GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        binding.listFollow.layoutManager = gridLayoutManager
        binding.listFollow.setHasFixedSize(true)
        bigListAdapter =BigListFollowAdapter(applicationContext, this)
        binding.listFollow.adapter = bigListAdapter
        bigListAdapter!!.setData(list!!)
        DialogUtil.progressDlgHide()
    }

}