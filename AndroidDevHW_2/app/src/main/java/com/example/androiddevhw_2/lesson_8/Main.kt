package com.example.androiddevhw_2.lesson_8

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val listOfProducts: List<Item> = listOf(
                Item("Яблоко", 3.4f, 4, Date()),
                Item("Груша", 13.4f, 2, Date()),
                Item("Арбуз", 4f, 3, Date()),
                Item("Ананас", 122.4f, 1, Date()),
            )
            val cart = Cart(listOfProducts)
            cart.showProducts(SortingByPrice())
            println("===================================================================")

            Cart(listOfProducts).showProducts(SortingByProductName())
        }

        class Cart(private val initialList: List<Item>) {
            fun showProducts(sortingType: Sorting) {
                println("Сортируем с помощью ${sortingType.sortName(Localization.RU)}")
                val newList: List<Item> = sortingType.sort(initialList)
                newList.printItems()
            }

            fun List<Item>.printItems() {
                this.forEach {
                    println(it)
                }
            }
        }

        class Item(
            val productName: String,
            val price: Float,
            val popularity: Int,
            val date: Date
        ) {
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:SSSSSS")
            private fun getDate(): String {
                return dateFormat.format(date)

                /*val newDate = SimpleDateFormat("dd-MM-yyyy").parse("10-03-2020")
                return newDate.time.toString()*/ 
            }

            override fun toString(): String {
                return "Товар: ${productName}, за цену: ${price}, " +
                        "находящийся на $popularity месте популярности выпущенный в ${getDate()}"
            }
        }

        interface Sorting {
            //val sortName: String
            fun sortName(locale: Localization): String
            fun sort(list: List<Item>): List<Item>
        }

        enum class Localization {
            RU,
            En
        }

        class SortingByPrice : Sorting {

            override fun sortName(locale: Localization): String {
                return when (locale) {
                    Localization.RU -> "Отсортировать по цене"
                    Localization.En -> "Sort by Price"
                }
            }

            override fun sort(list: List<Item>): List<Item> {
                return list.sortedByDescending { it.price }
            }
        }

        class SortingByProductName : Sorting {
            //override val sortName: String = "Отсортировать по названию"

            override fun sortName(locale: Localization): String {
                return when (locale) {
                    Localization.RU -> "Отсортировать по названию"
                    Localization.En -> "Sort by Name"
                }
            }

            override fun sort(list: List<Item>): List<Item> {
                return list.sortedBy { it.productName }
            }
        }
    }
}