package org.devTayu.busTayu.ui.station;

import android.util.Log;

import org.devTayu.busTayu.model.Reserved;
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

public class ReservedAPI {

    // 인증키
    String ServiceKey = "kd3zWLkxFKVIuT0XejOXR1qWycWNx03d21q75t5AHS2gIRKGQXQhqtwrvDWy3Huf04BaJZQL2vQHDvEkT8coDw%3D%3D";

    // rtNm 과 busNumber 가 같을 때의 index 저장 --> node = descNodes2.item(index).getFirstChild() 이걸로 특정 rtNm 태그 밑의 child node 접근
    int index;

    // 정류소 번호 : asrId , 버스 번호 : busNumber 받아와서 사용
    // asrId로 API 돌리고 돌린 결과에서 해당 버스 번호로 걸러내기
    public ArrayList<Reserved> reserve_arsId(String asrId, String busNumber) {
        Log.d("유소정 : ReservedAPI", "reserve_arsId() 호출");

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
            URL url = new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
            System.out.println(queryUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            System.out.println("====== reserve_arsId() 파싱 시작 ======");

            Document doc = parseXML(urlConnection.getInputStream());
            NodeList descNodes = doc.getElementsByTagName("rtNm");

            for (int i = 0; i < descNodes.getLength(); i++) {
                //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
                Node node1 = descNodes.item(i);
                if (node1.getTextContent().equals(busNumber)) {
                    //System.out.println("부모" + node1.getParentNode().getNodeName());
                    //System.out.println("본인" + node1.getTextContent());
                    //System.out.println("i번호" + i);
                    index = i; // rtNm 과 busNumber 가 일치하는 게 없는 경우가 발생하더라도 index=1로 들어가서 실행에 문제는 X
                    break;
                }
            }

            ArrayList<Reserved> reserved = new ArrayList<>();
            Reserved entity = new Reserved();

            NodeList descNodes2 = doc.getElementsByTagName("itemList");
            for (Node node = descNodes2.item(index).getFirstChild();
                 node != null; node = node.getNextSibling()) {
                // System.out.println(node.getNodeName() + " : " + node.getTextContent());

                /*공통 정보*/
                if (node.getNodeName().equals("adirection")) {
                    // System.out.println(node.getTextContent());
                    entity.setAdirection(node.getTextContent());
                    reserved.add(entity);
                } else if (node.getNodeName().equals("arsId")) {
                    entity.setArsId(node.getTextContent());
                    reserved.add(entity);
                } else if (node.getNodeName().equals("rtNm")) {
                    entity.setRtNm(node.getTextContent());
                    reserved.add(entity);
                } else if (node.getNodeName().equals("stNm")) {
                    entity.setStNm(node.getTextContent());
                    reserved.add(entity);
                }

                /*첫 번째 버스 정보*/
                else if (node.getNodeName().equals("arrmsgSec1")) {
                    entity.setArrmsgSec1(node.getTextContent());
                    reserved.add(entity);
                }
                /*두 번째 버스 정보*/
                else if (node.getNodeName().equals("arrmsgSec2")) {
                    entity.setArrmsgSec2(node.getTextContent());
                    reserved.add(entity);
                }
            }
            System.out.println("====== reserve_arsId() 파싱 끝 ======");
            return reserved;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====== reserve_arsId() 파싱 에러 ======");
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
