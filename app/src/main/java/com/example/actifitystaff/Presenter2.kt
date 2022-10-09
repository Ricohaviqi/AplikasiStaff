package com.example.actifitystaff

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter2(val crudView: UpdateAddActivity) {
    //Add data
    fun aadData(name: String, hp: String, alamat: String) {
        NetworkConfig.getService()
            .addStaff(name, hp, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus> {
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorAdd(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResultStatus>, response: Response<ResultStatus>
                ) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.onSuccessAdd(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorAdd(response.body()?.pesan ?: "")
                    }
                }
            })

        //update data
        fun updateData(id: String, name: String, hp: String, alamat: String) {
            NetworkConfig.getService()
                .updateStaff(id, name, hp, alamat)
                .enqueue(object : retrofit2.Callback<ResultStatus> {
                    override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                        crudView.errorUpdate(t.localizedMessage)
                    }

                    override fun onResponse(
                        call: Call<ResultStatus>, response: Response<ResultStatus>
                    ) {
                        if (response.isSuccessful && response.body()?.status == 200) {
                            crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                        } else {
                            crudView.onErrorupdate(response.body()?.pesan ?: "")
                        }
                    }
                })
        }
    }

private fun Any.enqueue(callback: Callback<ResultStatus>) {

}
}

