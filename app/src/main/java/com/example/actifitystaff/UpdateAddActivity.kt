package com.example.actifitystaff

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update_add.*

@Suppress ("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity() {
    private  lateinit var presenter: Presenter2
    @SuppressLint("SetTextI118n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        presenter = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if (itemDataItem == null) {
            btnAction.text = "Tambah"
            btnAction.setOnClickListener() {
                presenter.aadData(
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAddress.text.toString())
            }
        } else if (itemDataItem != null) {
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAddress.setText(item?.staffAlamat.toString())
            btnAction.setOnClickListener() {
                presenter.onUpdateData(
                    item?.staffId ?: "",
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etPhone.text.toString())
                finish()
            }
        }
    }
    fun onSuccessAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorAdd(msg: String) {

    override fun  onSuccessUpdate(msg: String) {
        startActivity((Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorupdate(msg: String) {}

    override fun onSuccessGet(data: List<DataItem>?) {}

    override fun onFailedGet(msg: String) {}

    override fun onSuccessDelete(msg: String) {}

    override fun onErrorDelete(msg: String) {}
    }
}