package com.example.todolist.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class TodoData(
    @ColumnInfo(name = "content") var Text: String?,
    @ColumnInfo(name = "done_state") var IsDone: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var ID: Int = 0
}

@Dao
interface TodoDao {
    @Query("SELECT * FROM TodoData LIMIT 1")
    fun getOne(): TodoData

    @Query("SELECT * FROM TodoData")
    fun getAll(): LiveData<MutableList<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(todo: TodoData)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateOne(todo: TodoData)

    @Delete
    suspend fun deleteOne(user: TodoData)

    @Query("SELECT count(*) FROM TodoData  WHERE Tododata.done_state == 1 ")
    fun countDone(): Int

    @Query("SELECT count(*) FROM TodoData  WHERE Tododata.done_state == 0 ")
    fun countUnDone(): Int
}