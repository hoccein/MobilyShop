package com.ynwa.kdl.hosein.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ynwa.kdl.hosein.shopping.realm_db.Detail;
import com.ynwa.kdl.hosein.shopping.realm_db.DetailesDAO;
import com.ynwa.kdl.hosein.shopping.realm_db.Phone;
import com.ynwa.kdl.hosein.shopping.realm_db.PhoneDAO;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.MyApiService;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.PhoneApi;
import com.ynwa.kdl.hosein.shopping.retrofit.my_api.RetrofitClient;

import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDataActivity extends AppCompatActivity {

    private final String TAG="REALM_TAG";
    private PhoneDAO phoneDAO = new PhoneDAO();
    private DetailesDAO detailesDAO = new DetailesDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        /*String desc = "";

        RealmList<String> colors = new RealmList<>();
        colors.add("مشكی");
        colors.add("طلایی");
        colors.add("آبی");
        colors.add("بنفش");

        RealmList<String> images = new RealmList<>();
        images.add("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_02_0_f.jpg");
        images.add("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_07_0_f.jpg");
        images.add("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_09_0_f.jpg");
        images.add("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_18_0_f.jpg");
        images.add("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_16_0_f.jpg");


        RealmList<String> features = new RealmList<>();
        features.add("");
        features.add("");
        features.add("");
        features.add("");

        RealmList<String> sensors = new RealmList<>();
        sensors.add("اثر انگشت");
        sensors.add("شتاب سنج");
        sensors.add("ژیروسکوپ");
        sensors.add("حسگر مجاورتی");
        sensors.add("قطب نما");

        Detail detailes = new Detail();
        detailes.setPhoneSize("7.7 × 70.8 × 149.9 میلی متر");
        detailes.setSimSize(" نانو (8.8 × 12.3 میلی متر)");
        detailes.setSimNumber("دو سیم کارت");
        detailes.setWeight("");
        detailes.setRamSize("4 گیگابایت");
        detailes.setStorageSize("64 گیگابایت");
        detailes.setCpuChip("Exynos 7870 Octa");
        detailes.setCpuName("Octa-core 1.6 GHz Cortex-A53");
        detailes.setCpuType("");
        detailes.setCpuFrequency("۱.۶ گیگاهرتزی");
        detailes.setGpu("Mali-T830 MP1");
        detailes.setScreenType("Super AMOLED");
        detailes.setScreenSize("۵.۶ اینچ");
        detailes.setScreenResolution("720 × 1480 پیکسل");
        detailes.setScreenPixel("294ppi");
        detailes.setCameraFront("16 مگاپیکسل");
        detailes.setCameraMain("16 مگاپیکسل");
        detailes.setCameraFilmed(" 1080p با سرعت 30 فریم در ثانیه");
        detailes.setSensors(sensors);
        detailes.setOs("Android Oreo 8.0");
        detailes.setBattery("لیتیوم یونی ۳،۰۰۰ میلی آمپر");
        detailes.setFeatures(features);
        detailesDAO.save(detailes);

        Phone phone = new Phone();
        phone.setName("Galaxy A6");
        phone.setBrand("Samsung");
        phone.setDesc(desc);
        phone.setImage("https://s.mobile.ir/Static/cache/phonephotos/phone_38331_Samsung_Galaxy_A6_2018_08_0_f.jpg");

        phone.setPrice("3250000");
        phone.setColor(colors);
        phone.setDetail(detailes);
        phone.setOtherImg(images);
        phoneDAO.save(phone);*/



        //detailesDAO.deleteAllDetails();
        //phoneDAO.deleteAllPhones();
        //phoneDAO.deletePhoneById();

        //printData(phoneDAO.findAllPhones());
        //printData(phoneDAO.findAllPhones().get(1));
        //printDetail(detailesDAO.findAllDetailes());



        /*int size = phoneDAO.findAllPhones().size();

        for (int i = 1; i <= size ; i++) {

            Phone phone = new Phone();
            Phone p = phoneDAO.findPhoneById(i);
            phone.setId(i);
            phone.setName(p.getName());
            phone.setBrand(p.getBrand());
            phone.setDesc(p.getDesc());
            phone.setImage(p.getImage());
            phone.setPrice(p.getPrice());
            phone.setPrice(Integer.parseInt(p.getPrice()));
            phone.setColor(p.getColor());
            phone.setDetail(p.getDetail());
            phone.setOtherImg(p.getOtherImg());
            phoneDAO.update(phone);
        }*/




        /*int id = 9;

        int year = 2018;

        RealmList<String> colors = new RealmList<>();
        colors.add("مشكی");
        colors.add("آبی");
        colors.add("طلایی");


        RealmList<String> images = new RealmList<>();
        images.add("https://cdn.gsm.ir/static/files/image/2018/5/5/r7571z_1.jpg");
        images.add("https://cdn.gsm.ir:7443/static/files/image/2018/5/5/eumso0_3.jpg");
        images.add("https://cdn.gsm.ir:7443/static/files/image/2018/5/5/r3zi5y_4.jpg");
        images.add("https://cdn.gsm.ir:7443/static/files/image/2018/5/5/4rcki5_2.jpg");

        Phone phone = new Phone();
        Phone p = phoneDAO.findPhoneById(id);
        phone.setId(id);
        phone.setName(p.getName());
        phone.setBrand(p.getBrand());
        phone.setDesc(p.getDesc());
        phone.setImage(p.getImage());
        phone.setPrice(p.getPrice());
        phone.setYear( year );
        phone.setColor(colors);
        phone.setDetail(p.getDetail());
        phone.setOtherImg(images);
        phoneDAO.update(phone);*/


        //printData( phoneDAO.findAllPhones() );

        // phoneDAO.deletePhoneById( );


        MyApiService service = RetrofitClient.getClient().create(MyApiService.class);
        Call<PhoneApi> call = service.getPhones();
        call.enqueue(new Callback<PhoneApi>() {
            @Override
            public void onResponse(Call<PhoneApi> call, Response<PhoneApi> response) {
                Log.i("myapi", "Response message: "+ response.message());
            }

            @Override
            public void onFailure(Call<PhoneApi> call, Throwable t) {
                Log.i("myapi", "onFailure message: "+ t.getMessage());
            }
        });
    }

    public void update(){

        int size = phoneDAO.findAllPhones().size();

        for (int i = 1; i <= size ; i++) {

            Phone phone = new Phone();
            Phone p = phoneDAO.findPhoneById(i);
            phone.setId(i);
            phone.setName(p.getName());
            phone.setBrand(p.getBrand());
            phone.setDesc(p.getDesc());
            phone.setImage(p.getImage());
            phone.setPrice(p.getPrice());
            phone.setPrice(p.getPrice());
            phone.setColor(p.getColor());
            phone.setDetail(p.getDetail());
            phone.setOtherImg(p.getOtherImg());
            phoneDAO.update(phone);
        }
    }

    public void printData(RealmResults<Phone> list){
        if (list.size() > 0) {
            for (Phone p : list) {
                Log.i(TAG, p.getId()+ " "+ p.getName());
                Log.i(TAG, "year: "+ p.getYear());
                Log.i(TAG, "imgs size: "+ p.getOtherImg().size());
                Log.i(TAG, "color size: "+ p.getColor().size());
                Log.i(TAG, "detail: "+ p.getDetail());
                Log.i(TAG, "sensor size: "+ p.getDetail().getSensors().size());
                Log.i(TAG, "--------------------------------------------");
               // Log.i(TAG, p.getDetail().toString());
            }
            Log.i(TAG, "size: "+ list.size());
        }
        else
            Log.i(TAG, "list is empty");
    }

    public void printData(Phone phone){
        if (phone != null){
            Log.i(TAG, "name: "+phone.getName());
            Log.i(TAG, "color: "+phone.getColor());
            Log.i(TAG, "price: "+phone.getPrice());
            Log.i(TAG, "color: "+phone.getDetail().toString());
        }
        else
            Log.i(TAG, "phone is null");

    }

    public void printDetail(RealmResults<Detail> list){
        if (list.size() > 0) {
            for (Detail d : list) {
                Log.i(TAG, d.toString()+ " ");
            }
        }
        else
            Log.i(TAG, "list is empty");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        phoneDAO.close();
        detailesDAO.close();
    }
}
