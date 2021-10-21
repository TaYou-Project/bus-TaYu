package org.devTayu.busTayu.ui.station;

import android.util.Log;

import org.devTayu.busTayu.model.Liked;
import org.devTayu.busTayu.model.Station;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class LikedAPI {

    // 인증키
    String ServiceKey = "kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    // 정류소고유번호
    String asrId;

    // 정류소 번호
    public String getAsrId() {
        return asrId;
    }

    public void setAsrId(String asrId) {
        this.asrId = asrId;
    }

    // String
    String rtNm = null, adirection = null, arrmsgSec1 = null, arrmsgSec2 = null , stNm=null;
    String stationNum = null; // asrID 와 같은 내용 : 정류소 고유번호

    // Check
    boolean rtNmCheck = false, adirectionCheck = false, arrmsgSec1Check = false, arrmsgSec2Check = false, stNmCheck = false, stationNumCheck = false;

    public String getRtNm() {
        return rtNm;
    }

    public void setRtNm(String rtNm) {
        this.rtNm = rtNm;
    }

    public String getAdirection() {
        return adirection;
    }

    public void setAdirection(String adirection) {
        this.adirection = adirection;
    }

    public void setArrmsgSec1(String arrmsgSec1) {
        this.arrmsgSec1 = arrmsgSec1;
    }

    public String getArrmsgSec1() {
        return arrmsgSec1;
    }

    public String getArrmsgSec2() {
        return arrmsgSec2;
    }

    public void setArrmsgSec2(String arrmsgSec2) {
        this.arrmsgSec2 = arrmsgSec2;
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public String getStNm() {
        return stNm;
    }

    public void setStNm(String stNm) {
        this.stNm = stNm;
    }

    // 정류소 번호 : asrId , 버스 번호 : busNumber 받아와서 사용
    // asrId로 API 돌리고 돌린 결과에서 해당 버스 번호로 걸러내기
    public ArrayList<Liked> liked_arsId(String asrId, String busNumber) {
        Log.d("유소정 : LikedAPI", "liked_arsatId() 호출");

        // 서울특별시_정류소정보조회 서비스 : getStationByUidItem
        String queryUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?"// 요청 URL
                + "ServiceKey=" + ServiceKey // 인증키
                + "&arsId=" + asrId; // 정류소고유번호
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(url.openStream(), "UTF-8");

            System.out.println(queryUrl);
            System.out.println("====== liked_arsId() 파싱 시작 ======");

            ArrayList<Liked> liked = new ArrayList<Liked>();

            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("rtNm")) {
                            adirectionCheck = true;
                        } else if (parser.getName().equals("adirection")) {
                            adirectionCheck = true;
                        } else if (parser.getName().equals("arrmsgSec1")) {
                            arrmsgSec1Check = true;
                        } else if (parser.getName().equals("arrmsgSec2")) {
                            arrmsgSec2Check = true;
                        } else if (parser.getName().equals("stNm")) {
                            stNmCheck = true;
                        }else if (parser.getName().equals("arsId")) {
                            stationNumCheck = true;
                        }
                        break;
                    case XmlPullParser.TEXT: // parser가 내용에 접근했을때
                        if (rtNmCheck) {
                            setRtNm(parser.getText());
                            rtNmCheck = false;
                        } else if (adirectionCheck) {
                            setAdirection(parser.getText());
                            adirectionCheck = false;
                        } else if (arrmsgSec1Check) {
                            setArrmsgSec1(parser.getText());
                            arrmsgSec1Check = false;
                        } else if (arrmsgSec2Check) {
                            setArrmsgSec2(parser.getText());
                            arrmsgSec2Check = false;
                        } else if (stNmCheck) {
                            setStNm(parser.getText());
                            stNmCheck = false;
                        } else if (stationNumCheck) {
                            setStationNum(parser.getText());
                            stationNumCheck = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("itemList")) {
                            Liked entityLiked = new Liked();
                            entityLiked.setRtNm(getRtNm());
                            entityLiked.setAdirection(getAdirection());
                            entityLiked.setArrmsgSec1(getArrmsgSec1());
                            entityLiked.setArrmsgSec2(getArrmsgSec2());
                            entityLiked.setStNm(getStNm());
                            entityLiked.setStationNum(getStationNum());
                            liked.add(entityLiked);

                            System.out.println("여기에 하나만 들어가야 함"+entityLiked.getAdirection());
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("====== liked_arsId() 파싱 끝 ======");
            return liked;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== liked_arsId() 파싱 에러 ======");
        }
        return null;
    }
}

