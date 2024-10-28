package com.roxx.grigarage.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.roxx.grigarage.data.local.model.BeerEntity

@Dao
interface BeerDao {

    // Вставка пива
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeer(beer: BeerEntity)

    // обновление
    @Update
    suspend fun updateBeer(beer: BeerEntity)

    // Удаление пива
    @Delete
    suspend fun deleteBeer(beer: BeerEntity)

    // Получение пива по ID
    @Query("SELECT * FROM beers WHERE id = :beerId")
    suspend fun getBeerById(beerId: Int): BeerEntity?

    // Увеличение счетчика выпитого пива на 1
    @Query("UPDATE beers SET drinkCount = drinkCount + 1 WHERE id = :beerId")
    suspend fun incrementDrinkCount(beerId: Int)

    // Уменьшение счетчика выпитого пива на 1
    @Query("UPDATE beers SET drinkCount = CASE WHEN drinkCount > 1 THEN drinkCount - 1 ELSE drinkCount END WHERE id = :beerId")
    suspend fun decrementDrinkCount(beerId: Int)

    // Получение кол-ва выпитых бутылок
    @Query("SELECT SUM(drinkCount) FROM beers")
    suspend fun getTotalBottleCount(): Int?

    // Получение общего количество пива
    @Query("SELECT COUNT(id) FROM beers")
    suspend fun getTotalBeerCount(): Int?

    // Получение самого популярного пива по количеству выпитого
    @Query("SELECT * FROM beers ORDER BY drinkCount DESC LIMIT 1")
    suspend fun getMostPopularBeer(): BeerEntity?

    // Поиск пива по названию или бренду
    @Query("SELECT * FROM beers WHERE LOWER(name) LIKE :query OR LOWER(brand) LIKE :query")
    fun searchPagedBeers(query: String): PagingSource<Int, BeerEntity>

    // Получение постраничного списка всех пив
    @Query("SELECT * FROM beers")
    fun getPagedBeers(): PagingSource<Int, BeerEntity>

    // Получение постраничного списка любимых пив
    @Query("SELECT * FROM beers WHERE isFavorite = 1 ORDER BY dateAdded DESC")
    fun getPagedFavoriteBeers(): PagingSource<Int, BeerEntity>
}
