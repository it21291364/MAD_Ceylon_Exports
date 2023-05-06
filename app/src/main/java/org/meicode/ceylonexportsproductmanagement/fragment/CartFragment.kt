package org.meicode.ceylonexportsproductmanagement.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import org.meicode.ceylonexportsproductmanagement.R
import org.meicode.ceylonexportsproductmanagement.adapter.CartAdaptor
import org.meicode.ceylonexportsproductmanagement.databinding.FragmentCartBinding
import org.meicode.ceylonexportsproductmanagement.roomdb.AppDatabase
import org.meicode.ceylonexportsproductmanagement.roomdb.ProductModel


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var list : ArrayList<String>




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentCartBinding.inflate(layoutInflater)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart", false)
        editor.apply()

        val dao = AppDatabase.getInstance(requireContext()).productDao()

        dao.getAllProducts().observe(requireActivity()) {
            binding.cartRecycler.adapter = CartAdaptor(requireContext() ,it)

            list.clear()
            for (data in it){
                list.add(data.productId)
            }

        }

        return binding.root
    }


}


