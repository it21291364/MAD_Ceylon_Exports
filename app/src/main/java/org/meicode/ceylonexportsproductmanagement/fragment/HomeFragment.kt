package org.meicode.ceylonexportsproductmanagement.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.getCategory
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.meicode.ceylonexportsproductmanagement.R
import org.meicode.ceylonexportsproductmanagement.activity.ProductDetailsActivity
import org.meicode.ceylonexportsproductmanagement.adapter.CategoryAdapter
import org.meicode.ceylonexportsproductmanagement.adapter.ProductAdapter
import org.meicode.ceylonexportsproductmanagement.databinding.FragmentHomeBinding
import org.meicode.ceylonexportsproductmanagement.model.AddProductModel
import org.meicode.ceylonexportsproductmanagement.model.CategoryModel

private val ViewBinding.button2: Any
    get() {
        TODO("Not yet implemented")
    }

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)



        binding.button2.setOnClickListener{

            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            startActivity(intent)
        }


        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)

        if (preference.getBoolean("isCart", false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        getCategory()

        getSliderImage()
        getProducts()
        return binding.root
    }




    private fun getSliderImage() {
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImage)
            }
    }

    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
              binding.productRecycler.adapter = ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategory() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter = CategoryAdapter(requireContext(),list)
            }
    }

}

private fun Any.setOnClickListener(function: () -> Unit) {

}
