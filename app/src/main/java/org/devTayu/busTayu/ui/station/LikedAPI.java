package org.devTayu.busTayu.ui.station;

import android.util.Log;

import org.devTayu.busTayu.model.Liked;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
    String rtNm = null, adirection = null, arrmsgSec1 = null, arrmsgSec2 = null, stNm = null;
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

    int index;

    // 정류소 번호 : asrId , 버스 번호 : busNumber 받아와서 사용
    // asrId로 API 돌리고 돌린 결과에서 해당 버스 번호로 걸러내기
    public ArrayList<Liked> liked_arsId(String asrId, String busNumber) {
        Log.d("유소정 : LikedAPI", "liked_arsatId() 호출");

        // 서울특별시_정류소정보조회 서비스 : getStationByUidItem
        String queryUrl = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid?"// 요청 URL
                + "ServiceKey=" + ServiceKey // 인증키
                + "&arsId=" + asrId; // 정류소고유번호
        StringBuffer buffer = new StringBuffer();

        BufferedReader bufferedReader = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder;
        Document document = null;

        try {
            ArrayList<Liked> liked = new ArrayList<Liked>();

            URL url = new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
            System.out.println(queryUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("====== liked_arsId() 파싱 시작 ======");

            Document doc = parseXML(urlConnection.getInputStream());
            NodeList descNodes = doc.getElementsByTagName("rtNm");

            for (int i = 0; i < descNodes.getLength(); i++) {
                //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
                Node node1 = descNodes.item(i);
                if (node1.getTextContent().equals(busNumber)) {
                    //System.out.println("부모" + node1.getParentNode().getNodeName());
                    //System.out.println("본인" + node1.getTextContent());
                    //System.out.println("i번호" + i);
                    index = i; // rtNm 과 busNumber가 일치하는 게 없는 경우가 발생하더라도 index=1로 들어가서 실행에 문제는 X
                    break;
                }
            }

            NodeList descNodes2 = doc.getElementsByTagName("itemList");
            for (Node node = descNodes2.item(index).getFirstChild();
                 node != null; node = node.getNextSibling()) {
                // System.out.println(node.getNodeName() + " : " + node.getTextContent());
                Liked entity = new Liked();
                if (node.getNodeName().equals("adirection")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getAdirection());
                } else if (node.getNodeName().equals("arrmsgSec1")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getArrmsgSec1());
                } else if (node.getNodeName().equals("arrmsgSec2")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getArrmsgSec2());
                } else if (node.getNodeName().equals("arsId")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getAsrId());
                } else if (node.getNodeName().equals("rtNm")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getRtNm());
                } else if (node.getNodeName().equals("stNm")) {
                    System.out.println(node.getTextContent());
                    entity.setAdirection(getStNm());
                }
                liked.add(entity);
            }

            System.out.println("====== liked_arsId() 파싱 끝 ======");
            return liked;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== liked_arsId() 파싱 에러 ======");
        }
        return null;
    }

    private Document parseXML(InputStream stream) throws Exception {

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }
        return doc;
    }
}