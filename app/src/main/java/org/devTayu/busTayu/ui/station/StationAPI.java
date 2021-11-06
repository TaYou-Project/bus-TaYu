package org.devTayu.busTayu.ui.station;

import android.util.Log;

import org.devTayu.busTayu.model.Station;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class StationAPI {

    // 인증키
    String ServiceKey = "kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";
    String ServiceKey2 = "kSIkCxXaf%2BeZwuYHgNCzyQiGH4a8vzSsNw76tf%2FH6jv9l7Z5DHcWH6pZez2eGvCeLuHQ6I7QByouB%2BcdJd2vQQ%3D%3D";
    String ServiceKey3 = "RQoyY8HDXDZrc%2BOQWCatBS%2BHtG5njexyplmK%2BYRbVhYb7Rw4QCvCi2NwJLNWuEYgn581I1N6TUoBavt7%2BOWEHQ%3D%3D";

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
    String rtNm = null, adirection = null, arrmsgSec1 = null, arrmsgSec2 = null, stNm = null, busType1 = null, busType2 = null;
    String stationNum = null; // asrID 와 같은 내용 : 정류소 고유번호

    // Check
    boolean rtNmCheck = false, adirectionCheck = false, arrmsgSec1Check = false, arrmsgSec2Check = false, stationNumCheck = false, stNmCheck = false, busType1Check = false, busType2Check = false;

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

    public String getBusType1() {
        return busType1;
    }

    public void setBusType1(String busType1) {
        this.busType1 = busType1;
    }

    public String getBusType2() {
        return busType2;
    }

    public void setBusType2(String busType2) {
        this.busType2 = busType2;
    }

    public ArrayList<Station> station_arsId(String asrId) {
        Log.d("유소정 : StationAPI", "station_arsId() 호출");

        // 서울특별시_정류소정보조회 서비스 : getStationByUidItem
        String queryUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?"// 요청 URL
                + "ServiceKey=" + ServiceKey2 // 인증키
                + "&arsId=" + asrId; // 정류소고유번호
        StringBuffer buffer = new StringBuffer();

        try {
            URL url = new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(url.openStream(), "UTF-8");

            System.out.println(queryUrl);
            System.out.println("====== station_arsId() 파싱 시작 ======");

            ArrayList<Station> station = new ArrayList<Station>();

            int parserEvent = parser.getEventType();
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("rtNm")) {
                            rtNmCheck = true;
                        } else if (parser.getName().equals("adirection")) {
                            adirectionCheck = true;
                        } else if (parser.getName().equals("arrmsgSec1")) {
                            arrmsgSec1Check = true;
                        } else if (parser.getName().equals("arrmsgSec2")) {
                            arrmsgSec2Check = true;
                        } else if (parser.getName().equals("arsId")) {
                            stationNumCheck = true;
                        } else if (parser.getName().equals("stNm")) {
                            stNmCheck = true;
                        } else if (parser.getName().equals("busType1")) {
                            busType1Check = true;
                        } else if (parser.getName().equals("busType2")) {
                            busType2Check = true;
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
                        } else if (stationNumCheck) {
                            setStationNum(parser.getText());
                            stationNumCheck = false;
                        } else if (stNmCheck) {
                            setStNm(parser.getText());
                            stNmCheck = false;
                        } else if (busType1Check) {
                            setBusType1(parser.getText());
                            busType1Check = false;
                        } else if (busType2Check) {
                            setBusType2(parser.getText());
                            busType2Check = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("itemList")) {
                            Station entity = new Station();
                            entity.setRtNm(getRtNm());
                            entity.setAdirection(getAdirection());
                            entity.setArrmsgSec1(getArrmsgSec1());
                            entity.setArrmsgSec2(getArrmsgSec2());
                            entity.setStationNum(getStationNum());
                            entity.setStNm(getStNm());
                            entity.setBusType1(getBusType1());
                            entity.setBusType2(getBusType2());
                            System.out.println("사이즈 " + station.size());
                            station.add(entity);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
            System.out.println("====== station_arsId() 파싱 끝 ======");
            return station;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== station_arsId() 파싱 에러 ======");
        }
        return null;
    }
}

