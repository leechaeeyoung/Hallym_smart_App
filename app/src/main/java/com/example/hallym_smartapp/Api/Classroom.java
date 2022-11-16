package com.example.hallym_smartapp.Api;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class Classroom { // 강의실 변수 클래스
    private String roomName; // 강의실 이름 변수 ex)1212, 10312
    private double lat, lon; // lat: 위도 lon: 경도
    private String buildingName; // 강의실 건물 이름
    public Classroom() {}
    public Classroom(String n) { // 강의실 변수 클래스 생성자
        this.roomName=n;
    }

    public void setRoomName(String n) {
        this.roomName=n;
    }
    public String getRoomName() {
        return roomName;
    }
    public double getLat() { return lat; }
    public double getLon() { return lon; }

    public String building() { // 건물명, 위도, 경도 찾기
        if (roomName.charAt(0)=='A') {
            lat=37.8863206;
            lon=127.7358122;
            return "공학관";
        }

        for(int i=0;i<roomName.length();i++) { // 건물에 숫자 안 들어가는 경우 예외처리
            boolean error=true;
            for(int j=48;j<=57;j++) {
                if(roomName.charAt(i)==(char)j) error=false;
            }
            if(error==true) return "존재하지 않습니다.";
        }

        switch (Integer.parseInt(roomName) / 1000) {
            case 1:
                lat=37.8863206;
                lon=127.7358122;
                return "공학관";
            case 2:
                lat=37.8865053;
                lon=127.7379939;
                return "대학본부-인문1관";
            case 3:
                lat=37.8859867;
                lon=127.7373176;
                return "의학관";
            case 4:
                lat=37.8863701;
                lon=127.7374383;
                return "인문2관";
            case 5:
                lat=37.8866762;
                lon=127.7387471;
                return "대학본부별관";
            case 6:
                lat=37.8862905;
                lon=127.7369709;
                return "실험동물센터";
            case 7:
                lat=37.8856713;
                lon=127.7369505;
                return "자연과학관";
            case 8:
                lat=37.8851548;
                lon=127.7358716;
                return "생명과학관";
            case 9:
                lat=37.8865826;
                lon=127.7402208;
                return "CLC";
            case 10:
                lat=37.8883414;
                lon=127.7384250;
                return "사회경영1관";
            case 11:
                lat=37.8868706;
                lon=127.7369695;
                return "일송아트홀";
            case 12:
                lat=37.8849015;
                lon=127.7357247;
                return "창업보육센터";
            case 13:
                lat=37.8877733;
                lon=127.7383724;
                return "사회경영2관";
            case 14:
                lat=37.8866342;
                lon=127.7409302;
                return "국제관";
            case 15:
                lat=37.8839915;
                lon=127.7382718;
                return "국제회의관";
            case 16:
                lat=37.8884338;
                lon=127.7378913;
                return "기초교육관";
            case 17:
                lat=37.8848917;
                lon=127.7373521;
                return "일송기념도서관";
            case 18:
                lat=37.8843628;
                lon=127.7387445;
                return "한림레크레이션센터";
            case 19:
                lat=37.8883182;
                lon=127.7390621;
                return "학군단";
            case 20:
                lat=37.8874614;
                lon=127.7411314;
                return "실내테니스장";
            case 21:
                lat=37.8860018;
                lon=127.7377276;
                return "의료바이오융합연구원";
            case 22:
                lat=37.8872454;
                lon=127.7379566;
                return "산학협력관";
            case 23:
                lat=37.8846025;
                lon=127.7364228;
                return "도현글로벌스쿨";
            case 24:
                lat=37.8855625;
                lon=127.7407428;
                return "학생생활관 1관";
            case 25:
                lat=37.8858970;
                lon=127.7406918;
                return "학생생활관 2관";
            case 26:
                lat=37.8859068;
                lon=127.7411666;
                return "학생생활관 3관";
            case 27:
                lat=37.8862447;
                lon=127.7408410;
                return "학생생활관 4관";
            case 28:
                lat=37.8863204;
                lon=127.7413419;
                return "학생생활관 5관";
            case 29:
                lat=37.8861486;
                lon=127.7417746;
                return "학생생활관 6관";
            case 30:
                lat=37.8866448;
                lon=127.7415585;
                return "학생생활관 7관";
            case 31:
                lat=37.8871960;
                lon=127.7413262;
                return "학생생활관 8관";
            case 32:
                lat=37.8873187;
                lon=127.7388637;
                return "체육 기자재실";
            case 33:
                lat=37.8881245;
                lon=127.7372717;
                return "H Stadium";
            case 34:
                lat=37.8877680;
                lon=127.7398295;
                return "ILSONG Studium";
            case 35:
                lat=37.8876065;
                lon=127.7408427;
                return "씨름장";
            case 36:
                lat=37.8866458;
                lon=127.7356359;
                return "온실";
            default:
                return "존재하지 않습니다.";
        }
    }

    public String building2() {
        switch (roomName) {
            case "공학관":
                lat=37.8863206;
                lon=127.7358122;
                return "공학관";
            case "대학본부-인문1관":
                lat=37.8865053;
                lon=127.7379939;
                return "대학본부-인문1관";
            case "의학관":
                lat=37.8859867;
                lon=127.7373176;
                return "의학관";
            case "인문2관":
                lat=37.8863701;
                lon=127.7374383;
                return "인문2관";
            case "대학본부별관":
                lat=37.8866762;
                lon=127.7387471;
                return "대학본부별관";
            case "실험동물센터":
                lat=37.8862905;
                lon=127.7369709;
                return "실험동물센터";
            case "자연과학관":
                lat=37.8856713;
                lon=127.7369505;
                return "자연과학관";
            case "생명과학관":
                lat=37.8851548;
                lon=127.7358716;
                return "생명과학관";
            case "CLC":
                lat=37.8865826;
                lon=127.7402208;
                return "CLC";
            case "사회경영1관":
                lat=37.8883414;
                lon=127.7384250;
                return "사회경영1관";
            case "일송아트홀":
                lat=37.8868706;
                lon=127.7369695;
                return "일송아트홀";
            case "창업보육센터":
                lat=37.8849015;
                lon=127.7357247;
                return "창업보육센터";
            case "사회경영2관":
                lat=37.8877733;
                lon=127.7383724;
                return "사회경영2관";
            case "국제관":
                lat=37.8866342;
                lon=127.7409302;
                return "국제관";
            case "국제회의관":
                lat=37.8839915;
                lon=127.7382718;
                return "국제회의관";
            case "기초교육관":
                lat=37.8884338;
                lon=127.7378913;
                return "기초교육관";
            case "일송기념도서관":
                lat=37.8848917;
                lon=127.7373521;
                return "일송기념도서관";
            case "한림레크레이션센터":
                lat=37.8843628;
                lon=127.7387445;
                return "한림레크레이션센터";
            case "학군단":
                lat=37.8883182;
                lon=127.7390621;
                return "학군단";
            case "실내테니스장":
                lat=37.8874614;
                lon=127.7411314;
                return "실내테니스장";
            case "의료바이오융합연구원":
                lat=37.8860018;
                lon=127.7377276;
                return "의료바이오융합연구원";
            case "산학협력관":
                lat=37.8872454;
                lon=127.7379566;
                return "산학협력관";
            case "도현글로벌스쿨":
                lat=37.8846025;
                lon=127.7364228;
                return "도현글로벌스쿨";
            case "학생생활관 1관":
                lat=37.8855625;
                lon=127.7407428;
                return "학생생활관 1관";
            case "학생생활관 2관":
                lat=37.8858970;
                lon=127.7406918;
                return "학생생활관 2관";
            case "학생생활관 3관":
                lat=37.8859068;
                lon=127.7411666;
                return "학생생활관 3관";
            case "학생생활관 4관":
                lat=37.8862447;
                lon=127.7408410;
                return "학생생활관 4관";
            case "학생생활관 5관":
                lat=37.8863204;
                lon=127.7413419;
                return "학생생활관 5관";
            case "학생생활관 6관":
                lat=37.8861486;
                lon=127.7417746;
                return "학생생활관 6관";
            case "학생생활관 7관":
                lat=37.8866448;
                lon=127.7415585;
                return "학생생활관 7관";
            case "학생생활관 8관":
                lat=37.8871960;
                lon=127.7413262;
                return "학생생활관 8관";
            case "체육 기자재실":
                lat=37.8873187;
                lon=127.7388637;
                return "체육 기자재실";
            case "H Stadium":
                lat=37.8881245;
                lon=127.7372717;
                return "H Stadium";
            case "ILSONG Studium":
                lat=37.8877680;
                lon=127.7398295;
                return "ILSONG Studium";
            case "씨름장":
                lat=37.8876065;
                lon=127.7408427;
                return "씨름장";
            case "온실":
                lat=37.8866458;
                lon=127.7356359;
                return "온실";
            default:
                return "존재하지 않습니다.";
        }
    }


    public int thisFloor() { // 층 수 구하기
        if(roomName.length()==4) return (int)roomName.charAt(1)-48;
        else return (int)roomName.charAt(2)-48;
    }

}