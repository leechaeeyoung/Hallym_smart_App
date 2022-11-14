package com.example.hallym_smartapp.Reservation.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.murgupluoglu.seatview.Seat;
import com.murgupluoglu.seatview.SeatView;
import com.murgupluoglu.seatview.SeatViewListener;
import com.example.hallym_smartapp.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.*;

import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

public class ThreeFloorActivity extends AppCompatActivity implements SeatViewListener{
    private int seatNum = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_floor);

        try {
            this.defaultSeat();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override // 좌석 선택시
    public void seatSelected(@NonNull Seat selectedSeat, @NonNull HashMap<String, Seat> hashMap) {

    }

    @Override // 좌석 선택 해제 <사용 안함>
    public void seatReleased(@NonNull Seat releasedSeat, @NonNull HashMap<String, Seat> hashMap) {

    }

    @Override // 좌석 선택 가능한지 판단
    public boolean canSelectSeat(@NonNull Seat clickedSeat, @NonNull HashMap<String, Seat> hashMap) {
        return clickedSeat.getType() != 2;
    }

    private void defaultSeat() throws JSONException, IOException{
        HashMap rowNames = new HashMap();

        JSONObject thirdseat = new JSONObject(this.loadJSONFormAsset());
        int rowCount = thirdseat.getJSONObject("Third Floor").getInt("totalRow");
        int columnCount = thirdseat.getJSONObject("Third Floor").getInt("totalColumn");
        Seat[][] var6 = new Seat[rowCount][];

        for(int var7 = 0; var7 < rowCount; ++var7) {
            int var10 = columnCount;
            Seat[] var11 = new Seat[columnCount];

            for(int var12 = 0; var12 < var10; ++var12) {
                Seat var17 = new Seat();
                var11[var12] = var17;
            }

            var6[var7] = var11;
        }
        Seat[][] seatArray = (Seat[][])var6;
        //Seat[][] seatArray = new Seat[rowCount][columnCount];
        JSONArray rowArray = thirdseat.getJSONObject("Third Floor").getJSONArray("rows");

        SeatView seatview = new SeatView(this);
        seatview.initSeatView(
                loadSeat(seatArray, rowNames, rowArray, rowCount, columnCount),
                rowCount,
                columnCount
        );

    }

    private final Seat[][] loadSeat( Seat[][] seatArray, HashMap rowNames, JSONArray rowArray, int rowCount, int columnCount) throws JSONException {
        boolean reverseSeats = true;

        for(int i = 0 ; i < rowArray.length() ; i++){
            JSONObject oneRow = rowArray.getJSONObject(i);

            String rowName = oneRow.getString("rowName");
            int rowIndex = oneRow.getInt("rowIndex");
            JSONArray seats = oneRow.getJSONArray("seats");

            if(reverseSeats) {
                rowIndex = (rowCount - 1) - rowIndex;
            }

            String s = String.valueOf(rowIndex);
            rowNames.put(s, rowName);
            //rowNames[Integer.toString(rowIndex)] = rowName;

            for(int columnIndex = 0 ; columnIndex < seats.length() ; columnIndex++){
                JSONObject seatObject = seats.getJSONObject(columnIndex);

                int rowIndexObject = seatObject.getInt("rowIndex");
                int columnIndexObject = seatObject.getInt("columnIndex");
                String seatNameObject = seatObject.getString("name");
                String seatType = seatObject.getString("type");
                boolean seatIsSelected = seatObject.getBoolean("isSelected");

                if (reverseSeats){
                    rowIndexObject = (rowCount - 1) - rowIndexObject;
                }

                Seat seat = new Seat();
                seat.setId(seatNameObject);
                seat.setSeatName(seatNameObject);
                seat.setRowIndex(rowIndexObject);
                seat.setColumnIndex(columnIndexObject);

                seat.setRowName(rowName);
                seat.setSelected(seatIsSelected);

                seat.setSelectedDrawableResourceName("seat_selected");

                if (seatType != null) {
                    switch(seatType) {
                        case "available":
                            if (seatType.equals("available")) {
                                seat.setDrawableResourceName("seat_available");
                                seat.setType(1);
                            }
                            break;
                        case "notavailable":
                            if (seatType.equals("notavailable")) {
                                seat.setDrawableResourceName("seat_notavailable");
                                seat.setType(2);
                                seat.setSelectedDrawableResourceName("ic_android_24dp");
                                int var24 = this.seatNum++;
                            }
                    }
                }

                seatArray[rowIndexObject][columnIndexObject] = seat;
            }
        }
        return seatArray;
    }


    private String loadJSONFormAsset() throws IOException {
        String fileName = "thirdFloor.json";

        InputStream var10000 = this.getAssets().open(fileName);
        Intrinsics.checkNotNullExpressionValue(var10000, "assets.open(fileName)");
        InputStream var3 = var10000;
        Charset var4 = Charsets.UTF_8;
        Reader var5 = (Reader)(new InputStreamReader(var3, var4));
        short var6 = 8192;
        Closeable var11 = (Closeable)(var5 instanceof BufferedReader ? (BufferedReader)var5 : new BufferedReader(var5, var6));
        Throwable var12 = null;

        String var14;
        try {
            BufferedReader it = (BufferedReader)var11;
            var14 = TextStreamsKt.readText((Reader)it);
        } catch (Throwable var9) {
            var12 = var9;
            throw var9;
        }

        return var14;

        /*String fileName = "thirdFloor.json";
        String jsonString = "";
        InputStream is = getAssets().open(fileName);
        int fileSize = is.available();

        byte[] buffer = new byte[fileSize];
        is.read(buffer);
        is.close();

        jsonString = new String(buffer, "UTF-8");
        return jsonString;*/
    }
}
