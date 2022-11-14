package com.example.hallym_smartapp.Reservation.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hallym_smartapp.R
import com.murgupluoglu.seatview.Seat
import com.murgupluoglu.seatview.SeatViewListener
import kotlinx.android.synthetic.main.three_floor.*
import org.json.JSONArray
import org.json.JSONObject


class ThirdFloorActivity : AppCompatActivity() {
    var seatNum:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.three_floor)


        seatView.seatViewListener = object : SeatViewListener {
            override fun seatSelected(selectedSeat: Seat, selectedSeats: HashMap<String, Seat>) {
                val intent = Intent(context, ConfirmSeatActivity::class.java)
                startActivity(intent)
            } //좌석 선택시

            override fun seatReleased(releasedSeat: Seat, selectedSeats: HashMap<String, Seat>) {

            } //선택 해제 사용 안함

            override fun canSelectSeat(
                clickedSeat: Seat,
                selectedSeats: HashMap<String, Seat>
            ): Boolean {
                return clickedSeat.type != Seat.TYPE.UNSELECTABLE
            }//좌석 선택 가능한지 판단
        }

        defaultSeat()
    }

    private fun defaultSeat() {
        val rowNames : HashMap<String, String> = HashMap()
        val fiveSeat = JSONObject(loadJSONFromAsset()) //메소드 호출
        val rowCount = fiveSeat.getJSONObject("Third Floor").getInt("totalRow")
        val columnCount = fiveSeat.getJSONObject("Third Floor").getInt("totalColumn")
        val seatArray = Array(rowCount) { Array(columnCount) { Seat() } }
        val rowArray = fiveSeat.getJSONObject("Third Floor").getJSONArray("rows")

        seatView.initSeatView(
            loadSeat(seatArray, rowNames, rowArray, rowCount, columnCount),
            rowCount,
            columnCount
        )
    }

    private fun loadSeat(
        seatArray: Array<Array<Seat>>,
        rowNames: HashMap<String, String>,
        rowArray: JSONArray,
        rowCount: Int,
        columnCount: Int
    ): Array<Array<Seat>> {

        val reverseSeats = true

        for (index in 0 until rowArray.length()) {

            val oneRow = rowArray.getJSONObject(index)

            val rowName = oneRow.getString("rowName")
            var rowIndex = oneRow.getInt("rowIndex")
            val seats = oneRow.getJSONArray("seats")

            if (reverseSeats) {
                rowIndex = (rowCount - 1) - rowIndex
            }
            rowNames[rowIndex.toString()] = rowName

            for (columnIndex in 0 until seats.length()) {
                val seatObject = seats.getJSONObject(columnIndex)

                var rowIndexObject = seatObject.getInt("rowIndex")
                val columnIndexObject = seatObject.getInt("columnIndex")
                val seatNameObject = seatObject.getString("name")
                val seatType = seatObject.getString("type")
                val seatIsSelected = seatObject.getBoolean("isSelected")


                if (reverseSeats) {
                    rowIndexObject = (rowCount - 1) - rowIndexObject
                }

                val seat = Seat() // 생성자 호출
                seat.id = seatNameObject
                seat.seatName = seatNameObject
                seat.rowIndex = rowIndexObject
                seat.columnIndex = columnIndexObject

                seat.rowName = rowName
                seat.isSelected = seatIsSelected



                seat.selectedDrawableResourceName = "seat_selected" //선택시 좌석 이미지 변경
                when (seatType) {
                    "available" -> {
                        seat.drawableResourceName = "seat_available" //이용가능 좌석
                        seat.type = Seat.TYPE.SELECTABLE
                    }
                    "notavailable" -> {
                        seat.drawableResourceName = "seat_notavailable" //이용 불가 좌석
                        seat.type = Seat.TYPE.UNSELECTABLE
                        seat.selectedDrawableResourceName = "ic_android_24dp"
                        seatNum++
                    }
                }


                seatArray[rowIndexObject][columnIndexObject] = seat
            }

        }

        return seatArray
    }

    private fun loadJSONFromAsset(): String {
        val fileName = "thirdFloor.json"
        val jsonString = assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return jsonString
    }
}