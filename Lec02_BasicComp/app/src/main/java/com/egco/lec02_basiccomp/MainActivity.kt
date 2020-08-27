package com.egco.lec02_basiccomp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addBtn.setOnClickListener{
            val intent = Intent(this,add::class.java)
            val result:Int
            intent.putExtra("input1",inputText1.text.toString())
            intent.putExtra("input2",inputText2.text.toString())
            startActivity(intent)
        }

        minusBtn.setOnClickListener{
            val intent = Intent(this,minus::class.java)
            val result:Int
            intent.putExtra("input1",inputText1.text.toString())
            intent.putExtra("input2",inputText2.text.toString())
            startActivity(intent)
        }


        /*submitBtn.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("username",inputText1.text.toString())
            intent.putExtra("password",inputText2.text.toString())
            startActivity(intent)
        }*/
 /*       submitBtn.setOnClickListener{
            var ans:Int
            var ansString:String
            var a = 30
            var b = 20

            val max:Int
            max = if(a>b) a else b

            ans = plusFunction(2,9)
            ansString = ans.toString()
            Log.d("TEST","check button event "+ansString)
            Log.d("MAX","MAX: $max")
//            editText.text.toString()
//            Toast.makeText(this,"it's toast btn event!",Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"msg: "+editText.text.toString(),Toast.LENGTH_SHORT).show()
            Toast.makeText(this,"msg: "+ansString,Toast.LENGTH_SHORT).show()
            editText.setText(ansString)



            var x = 20
            when (x) {
                10-> Log.d("result","x==$x")
                else->{
                    Log.d("result","x!=$x")
                }
            }
//            val items = listOf<Int>(1,2,3,4,5,6,7,8,9)
            val items = listOf<Int>(9,8,7,6,5,4,3,2,1)
            for(index in items.indices){
                Log.d("result ","value=${items[index]}")
            }

            for((index,value) in items.withIndex())
                Log.d("result","index: $index = $value")

            for(i in 1 .. 10){
                Log.d("te","$i")
            }

            val items = mutableListOf<Int>(1,2,3,4,5,6,7,8,9)
            items.add(10)
            for(i in items)
                Log.d("result","value: $i")


            val items = hashSetOf<String>("d","g","c","c","a")
            for(item in items.toSortedSet())
                Log.d("result","item=$item")

//            val items = hashMapOf<String,Int>("First" to 1,"Second" to 2)
//            Log.d("result",items["First"].toString())
        }


*/    }
}