package com.example.udemytest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    //We declare adapter as FoodAdapter which is the instance equalling to null
    var adapter:FoodAdapter?=null

    //We are declaring an ArrayList of object food
    var listOfFoods = ArrayList<food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load foods


        listOfFoods.add(food("Coffee", "Espresso is boiled using the hot coffee pot it comes when its hot.And the price ranges from 5000ugsh to 10000ugsh", R.drawable.espresso))
        listOfFoods.add(food("French Fries", "Is a type of fries that made using french receipe ,not only the chips but chicknen also and the quantity is enough to satisify someone.A plate of chicken plain is at 10000ugsh and with chicken it is 15000ugsh", R.drawable.french_fries))
        listOfFoods.add(food("Sugar Cubes", "Sugar cubes especially for the ones that have dabetes and it is recommended by the doctors.A bowel goes for 10000ugsh", R.drawable.sugar_cubes))
        listOfFoods.add(food("StrawBerry", "Ice cream fresh from the fridge and the strawberries are fresh too,will get to you very fast and will be as cold as u expected it from the fridge.Price for the icecream is fixed and it costs 10000ugsh", R.drawable.strawberry_ice_cream))
        //We now set our adapter here
        //In the adapter we pass two parameters that is this and listOfFoods which will then be passed to the constructor
        adapter = FoodAdapter(this, listOfFoods)

        gvListFood.adapter = adapter

    }

    class FoodAdapter:BaseAdapter{
        var listOfFood = ArrayList<food>()
        var context:Context? = null

        //we now declare a constructor listOfFood with instance ArrayList<food>
        //declaring this helps us to get rid of the error in BaseAdapter
        //We want the constructor to give us the context and the list of food
        constructor(context: Context,listOfFood:ArrayList<food>):super(){
            this.context=context
            this.listOfFood=listOfFood
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val Food = this.listOfFood[p0]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            //so here we inflate the layout called food_ticket and the second problem we don't care about it so we pass it to null
            //The inflater changes the layout food_ticket to an object, so meaning that u can access each object on that layout
            //Now in this case, food is the object of the layout
            var foodView = inflator.inflate(R.layout.food_ticket, null)
            foodView.tvFoodImage.setImageResource(Food.image!!)

            //when someone clicks on one activity, it should take him to another activity
            foodView.setOnClickListener {

                //This intents from context layout to the FoodDetails layout
                val intent = Intent(context, FoodDetails::class.java)

                //In that layout we have intented to, we need the following details
                //!! These mean that there multiples of names, descriptions and images to be worked on
                intent.putExtra("name",Food.name!!)
                intent.putExtra("des",Food.des!!)
                intent.putExtra("image", Food.image!!)
                context!!.startActivity(intent)
            }

            foodView.tvName.text = Food.name!!

            return foodView
        }

        override fun getItem(p0: Int): Any {
            //we return p0 coz the argument in getItemId is p0 and if its another, we also change to the same
            return listOfFood[p0]

        }

        override fun getItemId(p0: Int): Long {
            //we return p0 coz the argument in getItemId is p0 and if its another, we also change to the same
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfFood.size
        }

    }
}
