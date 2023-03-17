package com.example.demo.service.weather.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.weather.WeatherMapper;
import com.example.demo.model.weather.Weather;
import com.example.demo.service.weather.PageDto;
import com.example.demo.service.weather.WeatherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yxp
 */
@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService {

    @Resource
    private WeatherMapper weatherMapper;

    @Override
    public List<Weather> lists() {
        LambdaQueryWrapper<Weather> lists = new LambdaQueryWrapper<>();
        lists.orderByDesc(Weather::getId);
        return weatherMapper.selectList(lists);
    }

    @Override
    public IPage<Weather> page(PageDto dto) {
        dto.verify();

        IPage<Weather> getPage = new Page<>(dto.getPageNo(), dto.getPageSize());

        LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(dto.getDate() != null, Weather::getDate, dto.getDate())
                .likeRight(dto.getWeather() != null, Weather::getWeather, dto.getWeather())
                .orderByDesc(Weather::getId);

        return weatherMapper.selectPage(getPage,queryWrapper);
    }

    @Override
    public Map<String, List<Float>> everyYearAnalysis() {
        Map<String, List<Float>> maps = new HashMap<>(10);

        List<String> yearList = Arrays.asList("2019","2020","2021","2022","2023");
        for(String item : yearList){
            LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.likeRight(Weather::getDate, item)
                    .orderByAsc(Weather::getDate);
            List<Weather> weatherList = weatherMapper.selectList(queryWrapper);

            List<Float> floatMaxList = new ArrayList<>();
            List<Float> floatMinList = new ArrayList<>();
            List<Float> floatAgrList = new ArrayList<>();

            for(Weather weather : weatherList){

                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(weather.getMax());
                String result = m.replaceAll("").trim();

                Matcher m1 = p.matcher(weather.getMin());
                String result1 = m1.replaceAll("").trim();

                BigDecimal bigDecimalMax = new BigDecimal(result);
                float maxNum = bigDecimalMax.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                BigDecimal bigDecimalMin = new BigDecimal(result1);
                float minNum = bigDecimalMin.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                BigDecimal bigDecimalAgr = new BigDecimal((maxNum+minNum)/2);
                float agrNum = bigDecimalAgr.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                floatMaxList.add(maxNum);
                floatMinList.add(minNum);
                floatAgrList.add(agrNum);
            }

            maps.put("agr"+item, floatAgrList);
            maps.put("max"+item, floatMaxList);
            maps.put("min"+item, floatMinList);
        }
        return maps;
    }

    @Override
    public List<Weather> getThisDayList() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        String thisTime = simpleDateFormat.format(date);

        LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Weather::getDate, thisTime)
                .orderByAsc(Weather::getDate);

        return weatherMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String,List<Float>> getMonthAgrTemperature() {
        Map<String,List<Float>> maps = new HashMap<>(20);

        List<String> yearList = Arrays.asList("2019","2020","2021","2022","2023");
        List<String> monthList = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");

        for(String year : yearList) {
            List<Float> list = new ArrayList<>();
            for (String item : monthList) {
                LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.likeRight(Weather::getDate, year+ "-" + item);
                List<Weather> weatherList = weatherMapper.selectList(queryWrapper);
                Float sum = 0.0f;
                int i = 1;
                for (Weather weather : weatherList) {

                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(weather.getMax());
                    String result = m.replaceAll("").trim();

                    Matcher m1 = p.matcher(weather.getMin());
                    String result1 = m1.replaceAll("").trim();
                    sum = sum + (Float.parseFloat(result) + Float.parseFloat(result1)) / 2;
                    i++;
                }
                BigDecimal bigDecimal = new BigDecimal(sum / i);
                float num = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
                if(num != 0.0){
                    list.add(num);
                }
            }
            maps.put(year,list);
        }

        return maps;
    }

    @Override
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> maps = new HashMap<>(20);
        List<String> classList = Arrays.asList("霾","雪","阴","晴","雨", "多云");
        Integer sum = 0;
        int i = 1;
        String str = "num";
        for(String item : classList){
            LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Weather::getWeather, item);
            sum = sum + weatherMapper.selectCount(queryWrapper);
            maps.put(str+i ,weatherMapper.selectCount(queryWrapper));
            i++;
        }
        maps.put("count",sum);

        return maps;
    }

    @Override
    public List<String> getHengData() {
        List<String> dataList = new ArrayList<>();
        LambdaQueryWrapper<Weather> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(Weather::getDate, "2019");
        List<Weather> weatherList = weatherMapper.selectList(queryWrapper);
        for (Weather weather : weatherList) {
            String dateInfo = weather.getDate();
            String info = dateInfo.replace("2019-","");
            dataList.add(info);
        }
        return dataList;
    }

    @Override
    public boolean updateData() {
        //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
        String[] arguments = new String[] {"D:\\IDE\\getWeather\\Scripts\\python.exe", "D:\\ProgrammeFile\\getWeather\\getWeather.py"};
        Process process;
        try {
            process = Runtime.getRuntime().exec(arguments);
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //waitFor是用来显示脚本是否运行成功，1表示失败，0表示成功，还有其他的表示其他错误
            int re = process.waitFor();
            if(re != 0){
                return false;
            }
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
