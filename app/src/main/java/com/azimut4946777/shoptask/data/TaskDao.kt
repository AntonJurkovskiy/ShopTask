package com.azimut4946777.shoptask.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)
    @Update
    suspend fun update(task: Task)
    @Delete
    suspend fun delete(task: Task)
    @Query("DELETE  from task")
   suspend fun deleteAllTask()
    @Query("SELECT * from task WHERE id = :id")
    fun getTask(id: Int): Flow<Task>
    @Query("SELECT * from task ORDER BY name ASC")
    fun getTasks():Flow<List<Task>>


}