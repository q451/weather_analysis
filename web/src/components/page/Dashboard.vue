<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;"
                        v-loading="loading2"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{name}}</div>
                            <div>{{role}}</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        当前登录时间：
                        <span>{{nowTime}}</span>
                    </div>
                    <div class="user-info-list">
                        登录地点：
                        <span>{{city}}</span>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:252px;"
                        v-loading="loading2"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                    <div slot="header" class="clearfix">
                        <span>历史天气统计</span>
                    </div>
                    晴天
                    <el-progress :percentage="49.7" color="#42b983"></el-progress>
                    雾霾</el-progress>
                    <el-progress :percentage="24.1" color="#f1e05a"></el-progress>
                    多云
                    <el-progress :percentage="20.3"></el-progress>
                    有雨
                    <el-progress :percentage="5.9" color="#f56c6c"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20"
                        v-loading="loading2"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                    <el-col :span="6">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-sunrise-1 grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{weather}}</div>
                                    <div>Weather</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-stopwatch grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{tempture}} ℃</div>
                                    <div>Temperature</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="6">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-s-flag grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{shidu}} %rh</div>
                                    <div>Humidity</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>

                    <el-col :span="6">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-4">
                                <i class="el-icon-wind-power grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{windPower}} 级</div>
                                    <div>Windpower</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>

                <el-card shadow="hover" style="height:403px;">
                    <div slot="header" class="clearfix">
                        <span>当前历史数据</span>
                    </div>
                    <el-table 
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        height="300"
                        border
                        :data="todoList" style="width:100%;">

                        <el-table-column prop="date" label="日期"></el-table-column>

                        <el-table-column prop="week" label="星期"></el-table-column>

                        <el-table-column prop="max" label="最高气温"></el-table-column>

                        <el-table-column prop="min" label="最低气温"></el-table-column>

                        <el-table-column prop="weather" label="天气"></el-table-column>

                        <el-table-column prop="wind" label="风向强度"></el-table-column>

                    </el-table>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">

            <el-col :span="24">
                <el-card shadow="hover"
                        v-loading="loading1"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                    <schart ref="bar" class="schart" canvasId="bar" :options="options"></schart>
                </el-card>
            </el-col>

            <el-col :span="24">
                <el-card shadow="hover"
                        v-loading="loading1"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading">
                    <schart ref="line" class="schart" canvasId="line" :options="options2"></schart>
                </el-card>
            </el-col>
            
        </el-row>
    </div>
</template>

<script>
import Schart from 'vue-schart';
import { getThisDayList, getThisDayLatest, getMonthAgrTemperature, getStatistics} from '@/api/index'
export default {
    name: 'dashboard',
    data() {
        return {
            name: localStorage.getItem('ms_username'),
            todoList: [],
            loading: true,
            loading1: true,
            loading2: true,

            city:'',
            nowTime:'',
            tempture:'',
            weather:'',
            shidu:'',
            windPower:'',

            rain:'',
            xue:'',
            yin:'',
            qin:'',
            clould:'',
            mai:'',

            value: 2022,

            options: {
                type: 'bar',
                title: {
                    text: '2019到2023年平均气温柱状图'
                },
                tooltip:{
                    trigger: 'axis'
                },
                xRorate: 1,
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        label: '2019年平均气温值',
                        data: []
                    },
                    {
                        label: '2020年平均气温值',
                        data: []
                    },
                    {
                        label: '2021年平均气温值',
                        data: []
                    },
                    {
                        label: '2022年平均气温值',
                        data: []
                    },
                    {
                        label: '2023年平均气温值',
                        data: []
                    }
                ]
            },
            options2: {
                type: 'line',
                title: {
                    text: '2019到2023年平均气温趋势图'
                },
                labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一', '十二'],
                datasets: [
                    {
                        label: '2019年平均气温值',
                        data: []
                    },
                    {
                        label: '2020年平均气温值',
                        data: []
                    },
                    {
                        label: '2021年平均气温值',
                        data: []
                    },
                    {
                        label: '2022年平均气温值',
                        data: []
                    },
                    {
                        label: '2023年平均气温值',
                        data: []
                    }
                ]
            }
        };
    },
    components: {
        Schart
    },
    computed: {
        role() {
            return this.name === 'admin' ? '超级管理员' : '普通用户';
        }
    },
    mounted() {
        this.ThisDayList(),
        this.getThisDayLatest(),
        this.getMonthAgrTemperature(),
        this.getStatistics()
    },
    methods: {
        getThisDayLatest() {
            getThisDayLatest().then(response => {
                var infoLives = response.data.data.lives;

                this.nowTime = infoLives[0].reporttime;
                this.city = infoLives[0].city;
                this.tempture = infoLives[0].temperature;
                this.weather = infoLives[0].weather;
                this.shidu = infoLives[0].humidity;
                this.windPower = infoLives[0].windpower;
                if(response.data.code == 0){
                    this.loading2 = false
                }else{
                    this.loading2 = false
                    this.$message.error(response.data.msg);
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        // 获取 easy-mock 的模拟数据
        ThisDayList() {
            getThisDayList().then(response => {
                console.log(response);
                this.todoList = response.data.data;
                if(response.status == 200){
                    this.loading = false
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        getMonthAgrTemperature() {
            getMonthAgrTemperature().then(response => {
                var infoLives = response.data.data;

                this.options.datasets[0].data = infoLives["2019"];
                this.options.datasets[1].data = infoLives["2020"];
                this.options.datasets[2].data = infoLives["2021"];
                this.options.datasets[3].data = infoLives["2022"];
                this.options.datasets[4].data = infoLives["2023"];

                this.options2.datasets[0].data = infoLives["2019"];
                this.options2.datasets[1].data = infoLives["2020"];
                this.options2.datasets[2].data = infoLives["2021"];
                this.options2.datasets[3].data = infoLives["2022"];
                this.options2.datasets[4].data = infoLives["2023"];
                if(response.status == 200){
                    this.loading1 = false
                }
            }).catch(error=>{
                console.log(error)
            });
        },
        getStatistics() {
            getStatistics().then(response => {
                console.log(response);
                var statistics = response.data.data;
                var count = statistics.count;
                this.mai = (statistics.num1/count).toFixed(1);
                this.xue = statistics.num2/count;
                this.yin = statistics.num3/count;
                this.qin = (statistics.num4/count).toFixed(1);
                this.rain = statistics.num5/count;
                this.clould = statistics.num6/count;
            }).catch(error=>{
                console.log(error)
            });
        },
    }
};
</script>


<style scoped>
.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.grid-con-4 .grid-con-icon {
    background: rgb(99, 241, 4);
}

.grid-con-4 .grid-num {
    color: rgb(99, 241, 4);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 70px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}

.content-title {
    clear: both;
    font-weight: 400;
    line-height: 50px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
}
</style>
