package com.azimut4946777.shoptask

import androidx.lifecycle.*
import com.azimut4946777.shoptask.data.Task
import com.azimut4946777.shoptask.data.TaskDao
import kotlinx.coroutines.launch

class ShopTaskViewModel(private val taskDao: TaskDao) : ViewModel() {

val allTasks: LiveData<List<Task>> = taskDao.getTasks().asLiveData()

    // For Update Task
    fun updateTask(taskId: Int, taskName: String, taskQty: String){
        val updatedTask = getUpdatedTaskEntry(taskId,taskName,taskQty)
        updateTask(updatedTask)
    }
    // For Update Task
    private fun getUpdatedTaskEntry(taskId: Int, taskName: String, taskQty: String): Task {
        return Task(id = taskId,taskName = taskName,taskQuantity = taskQty.toInt())
    }
// For Edit Task
fun retrieveTask(id: Int): LiveData<Task> {
    return taskDao.getTask(id).asLiveData()
}
// Chek input text
fun isEntryValid(taskName: String, taskQty: String): Boolean {
    if (taskName.isBlank() || taskQty.isBlank()) {
        return false
    }
    return true
}
    //Insert new Task in BD
    fun addNewTask(taskName: String, taskQty: String) {
        val newTask = getNewTaskEntry(taskName, taskQty)
        insertTask(newTask)
    }
    //Insert new Task in BD
    private fun getNewTaskEntry(taskName: String, taskQty: String): Task {
        return Task(
            taskName = taskName,
            taskQuantity = taskQty.toInt()
        )
    }
    //Insert new Task in BD
    private fun insertTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
    // For Update Task
    private  fun updateTask(task: Task){
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
    // For Delete Task
    fun deleteTask(task: Task){
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }
    fun deleteAllTask() {
        viewModelScope.launch {
            taskDao.deleteAllTask()
        }
    }
}




class ShopTaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShopTaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShopTaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
