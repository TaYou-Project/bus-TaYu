package org.devTayu.busTayu.model;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class StationAPI{

    // 인증키
    String ServiceKey = "kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    // 정류소고유번호
    // 신도림역2번출구
    String asrId="17909";

    // Check
    boolean rtNmCheck=false;
    // String
    String rtNm;

    public String getRtNm() {
        return rtNm;
    }
    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public void station_arsId(){
        Log.d("유소정 : StationAPI", "station_arsId() 호출");

        // 서울특별시_정류소정보조회 서비스 : getStationByUidItem
        String queryUrl="http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?"// 요청 URL
                + "ServiceKey=" + ServiceKey // 인증키
                + "&arsId=" + asrId; // 정류소고유번호
        StringBuffer buffer=new StringBuffer();

        try {
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser= factory.newPullParser();
            parser.setInput(url.openStream(), "UTF-8");

            System.out.println(queryUrl);
            System.out.println("====== station_arsId() 파싱 시작 ======");

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("rtNm")){
                            rtNmCheck = true;
                        }
                        break;
                    case XmlPullParser.TEXT: // parser가 내용에 접근했을때
                        if(rtNmCheck) {
                            setRtNm(parser.getText());
                            rtNmCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("====== station_arsId() 파싱 끝 ======");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== station_arsId() 파싱 에러 ======");
        }
    }
}

