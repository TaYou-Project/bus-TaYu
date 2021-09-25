package org.devTayu.busTayu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends Service implements LocationListener {
    private final Context mContext;
    private final Activity activity;

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,  Manifest.permission.INTERNET};

    boolean isGPSEnable = false;

    boolean isNetWorkEnable = false;

    boolean isGetLocation = false;

    Location location;
    double lat;
    double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    private static final long MIN_DISTANCE_UPDATE = 10;
    private static final long MIN_TIME_UPDATE = 1000 * 10 * 1;

    protected LocationManager locationManager;

    public LocationActivity(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            isNetWorkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            int hasFineLocationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);

            if (!isGPSEnable && !isNetWorkEnable) {
            } else {
                if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
                    this.isGetLocation = true;
                    if (isNetWorkEnable) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, MIN_DISTANCE_UPDATE, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                    if (isGPSEnable) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, MIN_DISTANCE_UPDATE, this);
                        if (location == null) {

                            if (locationManager != null) {
                                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    lat = location.getLatitude();
                                    lon = location.getLongitude();
                                }
                            }
                        }
                    }
                }
                else {
                    ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public double getLatitude() {
        if (location != null)
            lat = location.getLatitude();
        return lat;
    }

    public double getLongitude() {
        if (location != null)
            lon = location.getLongitude();
        return lon;
    }

    public boolean isGetLocation() {
        return this.isGetLocation;
    }

    public void stopUsingGPS() {
        if (locationManager != null)
            locationManager.removeUpdates(org.devTayu.busTayu.activity.LocationActivity.this);
    }

    public String getTextView() {
        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        String getAddress = "위치 조회 불가";
        //사용자의 현재 위치
        if( location != null ) {
            System.out.println("★★★ 현재 내 위치값 : "+lat+","+lon+" ★★★");
            String address = getAddress(mContext, lat, lon);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                if(address.contains(","))
                    address = address.replace(",", "");

                String strAddress[] = address.split(" ");

                if(strAddress.length > 4)
                    if(address.contains("대한민국"))
                        getAddress = strAddress[2] + " " + strAddress[3];
                    else
                        getAddress = strAddress[3] + " " + strAddress[2];
                else
                    getAddress = address;
            }
            else {
                getAddress = "위치 조회 불가";
            }
        }

        return getAddress; // __구 __동
    }

    public String getTextView2() {
        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        String getAddress = "위치 조회 불가";
        //사용자의 현재 위치
        if( location != null ) {
            System.out.println("★★★ 현재 내 위치값 : "+lat+","+lon+" ★★★");
            String address = getAddress(mContext, lat, lon);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                if(address.contains(","))
                    address = address.replace(",", "");

                String strAddress[] = address.split(" ");

                if(strAddress.length > 4)
                    if(address.contains("대한민국"))
                        getAddress = strAddress[1];
                    else
                        getAddress = strAddress[4];
                else
                    getAddress = address;
            }
            else {
                getAddress = "위치 조회 불가";
            }
        }

        return getAddress; // __시
    }

    public String getTextView3() {
        String address = getTextView2();
        String getAddress = "";

        switch (address) {
            case "서울특별시" : getAddress = "서울"; break;
            case "부산광역시" : getAddress = "부산"; break;
            case "대구광역시" : getAddress = "대구"; break;
            case "인천광역시" : getAddress = "인천"; break;
            case "광주광역시" : getAddress = "광주"; break;
            case "대전광역시" : getAddress = "대전"; break;
            case "울산광역시" : getAddress = "울산"; break;
            case "세종특별자치시" : getAddress = "세종"; break;
            case "경기도" : getAddress = "경기"; break;
            case "강원도" : getAddress = "강원"; break;
            case "충청북도" : getAddress = "충북"; break;
            case "충청남도" : getAddress = "충남"; break;
            case "전라북도" : getAddress = "전북"; break;
            case "전라남도" : getAddress = "전남"; break;
            case "경상북도" : getAddress = "경북"; break;
            case "경상남도" : getAddress = "경남"; break;
            case "제주특별자치도" : getAddress = "제주"; break;
        }

        return getAddress; // __ (시 단위)
    }

    public String getTextView4() {
        String address = getTextView2();
        String getAddress = "";

        switch (address) {
            case "서울특별시" : getAddress = "seoul"; break;
            case "부산광역시" : getAddress = "busan"; break;
            case "대구광역시" : getAddress = "daegu"; break;
            case "인천광역시" : getAddress = "incheon"; break;
            case "광주광역시" : getAddress = "gwangju"; break;
            case "대전광역시" : getAddress = "daejeon"; break;
            case "울산광역시" : getAddress = "ulsan"; break;
            case "세종특별자치시" : getAddress = "sejong"; break;
            case "경기도" : getAddress = "gyeonggi"; break;
            case "강원도" : getAddress = "gangwon"; break;
            case "충청북도" : getAddress = "chungbuk"; break;
            case "충청남도" : getAddress = "chungnam"; break;
            case "전라북도" : getAddress = "jeonbuk"; break;
            case "전라남도" : getAddress = "jeonnam"; break;
            case "경상북도" : getAddress = "gyeongbuk"; break;
            case "경상남도" : getAddress = "gyeongnam"; break;
            case "제주특별자치도" : getAddress = "jeju"; break;
        }

        return getAddress; // 영문 (시 단위)
    }

    public String getTextView5() {
        locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        String getAddress = "위치 조회 불가";
        //사용자의 현재 위치
        if( location != null ) {
            System.out.println("★★★ 현재 내 위치값 : "+lat+","+lon+" ★★★");
            String address = getAddress(mContext, lat, lon);

            System.out.println("★★★ 현재 내 위치값 : "+address+" ★★★");
            if(!address.equals("주소를 가져올 수 없습니다.") && !address.equals("현재 위치를 확인 할 수 없습니다.")) {
                if(address.contains(","))
                    address = address.replace(",", "");

                String strAddress[] = address.split(" ");

                if(strAddress.length > 4)
                    if(address.contains("대한민국"))
                        getAddress = getTextView3() + " " + strAddress[2];
                    else
                        getAddress = strAddress[4] + " " + strAddress[3];
                else
                    getAddress = address;
            }
            else {
                getAddress = "위치 조회 불가";
            }
        }

        return getAddress; // __(시 단위) __구
    }

    // LocationDustActivity totalCount 에 사용
    public String getTextView6() {
        String address = getTextView();
        address = address.substring(0, address.length()-1); // 읍 면 동
        address = address.replaceAll("[0-9]", "");
        String getAddress = address;

        return getAddress; // __시군구 __읍면동 -> '숫자'와 '읍면동' 뺀 것 ex) 목1동 => 목 / 괴안동 => 괴안
    }

    public String getTextView7() {
        String address = getTextView();
        address = address.replaceAll("[0-9]", "");
        String getAddress = address;

        return getAddress; // __시군구 __읍면동 -> 숫자 뺀 것 ex) 목1동 => 목동
    }

    public static String getAddress(Context mContext, double lat, double lon) {
        String nowAddress ="현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lon, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress  = currentLocationAddress;
                }
            }
        } catch (IOException e) {
            nowAddress = "주소를 가져올 수 없습니다.";
            System.out.println("주소를 가져올 수 없습니다.");
            e.printStackTrace();
        }
        return nowAddress;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
