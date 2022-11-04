package com.azimut4946777.shoptask

import com.azimut4946777.shoptask.*



import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.graphics.alpha
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.azimut4946777.shoptask.data.Task
import com.azimut4946777.shoptask.data.TaskRoomDatabase
import com.azimut4946777.shoptask.databinding.FragmentMainTaskBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder



class MainTaskFragment : Fragment() {


    private val viewModel: ShopTaskViewModel by activityViewModels {
        ShopTaskViewModelFactory(
            (activity?.application as ShopTaskApplication).database.taskDao()
        )
    }


    // For Layout Manager
    private var isLinearLayoutManager = true
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskListAdapter {
            val action = MainTaskFragmentDirections.actionMainTaskFragmentToEditTaskFragment(it.id)
            this.findNavController().navigate(action)
        }

        val recyclerView = binding.taskRecyclerview

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allTasks.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }
            //For CheckBox


        //FAB bottom action
        binding.fab.setOnClickListener {
            val action = MainTaskFragmentDirections.actionMainTaskFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }
        //Hide FAB
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    }

    //For AppBar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu, menu)

    }

    //For AppBar. Button Action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                if (binding.taskRecyclerview.isEmpty()) {
                }else {
                    showDeleteAllDialog()
                }

                true
            }
            R.id.grid -> {
                // For Layout Manager
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // For Layout Manager. Change RecyclerView LayoutManager
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            binding.taskRecyclerview.layoutManager = LinearLayoutManager(context)
        } else {
            //binding.taskRecyclerview.layoutManager = GridLayoutManager(context, 2)
            binding.taskRecyclerview.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        }
    }

    // For Layout Manager. Change Icon
    private fun setIcon(menuItem: MenuItem) {
        if (isLinearLayoutManager) {
            menuItem.setIcon(R.drawable.ic_linear_layout)
        } else {
            menuItem.setIcon(R.drawable.ic_grid_layout)
        }
    }


    private fun showDeleteAllDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)){_,_->}
            .setPositiveButton(getString(R.string.yes)){_,_->
                viewModel.deleteAllTask()
            }
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
