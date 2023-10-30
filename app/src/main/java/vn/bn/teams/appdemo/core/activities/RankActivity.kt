package vn.bn.teams.appdemo.core.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import vn.bn.teams.appdemo.BaseActivity
import vn.bn.teams.appdemo.core.adapter.RankAdapter
import vn.bn.teams.appdemo.data.models.Rank
import vn.bn.teams.appdemo.databinding.ActivityRankBinding

class RankActivity : BaseActivity() {
    private lateinit var binding: ActivityRankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
        loadNotes(false)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private lateinit var adapter: RankAdapter
    var isLoading = false
    var isLastItemReached = false
    private var layoutManager: LinearLayoutManager? = null
    private fun initList() {
        layoutManager = LinearLayoutManager(this@RankActivity)
        binding.listRank.layoutManager = layoutManager
        binding.listRank.hasFixedSize()
        binding.listRank.itemAnimator = DefaultItemAnimator()
        adapter = RankAdapter()
        binding.listRank.adapter = adapter
        binding.listRank.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    if (!isLoading && !isLastItemReached && (adapter.itemCount - 1) == layoutManager!!.findLastVisibleItemPosition()) {
                        loadNotes(true)
                    }

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    private var lastVisible: DocumentSnapshot? = null
    private fun loadNotes(isLoadMore: Boolean) {
        val collectionReference = FirebaseFirestore.getInstance().collection("users")
        var q = collectionReference
            .orderBy("rank", Query.Direction.DESCENDING)
        if (lastVisible != null) {
            q = q.startAfter(lastVisible)
        }

        q.limit(20)
            .get()
            .addOnCompleteListener { task: Task<QuerySnapshot> ->
                if (isUnavailable) {
                    return@addOnCompleteListener
                }
                isLoading = false
                if (task.isSuccessful) {
                    isLastItemReached = task.result.size() < 20
                    if (!task.result.isEmpty) {
                        val rank: MutableList<Rank> = mutableListOf()
                        for (document in task.result) {
                            val m: Rank = document.toObject(Rank::class.java)
                            rank.add(m)
                            if (!document.metadata.hasPendingWrites()) {
                                lastVisible = document
                            }
                        }
                        if (!isLoadMore) {
                            adapter.setListRank(rank)
                        } else {
                            adapter.addRank(rank)
                        }
                        binding.progressbar.visibility = View.GONE
                        binding.listRank.visibility = View.VISIBLE


                    }

                } else {
                    Log.d("__message", "Error getting documents: ", task.exception)
                }
            }

    }
}