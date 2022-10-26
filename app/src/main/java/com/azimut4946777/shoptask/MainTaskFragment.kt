package com.azimut4946777.shoptask

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.azimut4946777.shoptask.databinding.FragmentMainTaskBinding


class MainTaskFragment : Fragment() {

    private var _binding: FragmentMainTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //For AppBar
        setHasOptionsMenu(true)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainTaskBinding.inflate(layoutInflater, container, false)
            //Hide FAB
        binding.taskRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // if the recycler view is scrolled
                // above hide the FAB
                if (dy > 10 && binding.fab.isShown) {
                    binding.fab.hide()
                }

                // if the recycler view is
                // scrolled above show the FAB
                if (dy < -10 && !binding.fab.isShown) {
                    binding.fab.show()
                }

                // of the recycler view is at the first
                // item always show the FAB
                if (!recyclerView.canScrollVertically(-1)) {
                    binding.fab.show()
                }
            }
        })
        //FAB bottom action
        binding.fab.setOnClickListener {
            val action = MainTaskFragmentDirections.actionMainTaskFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }



        return binding.root
    }
    //For AppBar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)
    }
    //For AppBar. Delete All Button Action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                TODO()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }
